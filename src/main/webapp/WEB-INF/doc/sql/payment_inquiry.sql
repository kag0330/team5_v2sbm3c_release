/**********************************/
/* Table Name: 신발 문의 */
/**********************************/
DROP TABLE PAYMENT_INQUIRY CASCADE CONSTRAINTS;
DROP SEQUENCE PAYMENT_INQUIRY_SEQ;

CREATE TABLE PAYMENT_INQUIRY(
    PAYMENT_INQUIRY_NO        NUMBER(9)        NOT NULL PRIMARY KEY,
    TITLE                   VARCHAR2(100)    NOT NULL,
    CONTENTS                VARCHAR2(1000)   NOT NULL,
    RDATE                   DATE             NOT NULL,
    ANSWER_VISIBLE          CHAR(1)          DEFAULT 'N'   NOT NULL,
    ANSWER_CONTENTS          VARCHAR2(1000)   DEFAULT '',
    MEMBERNO                NUMBER(9),
    PAYMENTNO               NUMBER(9),
    FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
    FOREIGN KEY (PAYMENTNO) REFERENCES PAYMENT (PAYMENTNO)
);

COMMENT ON TABLE PAYMENT_INQUIRY is '주문 문의';
COMMENT ON COLUMN PAYMENT_INQUIRY.PAYMENT_INQUIRY_NO is '주문 문의 번호';
COMMENT ON COLUMN PAYMENT_INQUIRY.TITLE is '문의 제목';
COMMENT ON COLUMN PAYMENT_INQUIRY.CONTENTS is '문의 내용';
COMMENT ON COLUMN PAYMENT_INQUIRY.RDATE is '문의 작성일';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_CONTENTS is '답변 내용';
COMMENT ON COLUMN PAYMENT_INQUIRY.MEMBERNO is '멤버 번호'; -- 작성자
COMMENT ON COLUMN PAYMENT_INQUIRY.PAYMENTNO is '주문 번호'; -- 주문

CREATE SEQUENCE PAYMENT_INQUIRY_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
 
-- 임시 데이터
INSERT INTO PAYMENT_INQUIRY(payment_inquiry_no, title, contents, rdate, memberno, paymentno)
VALUES(PAYMENT_INQUIRY_SEQ.nextval, '첫번째 문의입니다.', '문의사항 내용', sysdate, 1, 1);

INSERT INTO PAYMENT_INQUIRY(payment_inquiry_no, title, contents, rdate, memberno, paymentno)
VALUES(PAYMENT_INQUIRY_SEQ.nextval, '두번째 문의입니다.', '환불해주세요.', sysdate, 1, 2);

INSERT INTO PAYMENT_INQUIRY(payment_inquiry_no, title, contents, rdate, answer_visible, answer_contents, memberno, paymentno)
VALUES(PAYMENT_INQUIRY_SEQ.nextval, '신발 사이즈가 커서 교환', '한 사이즈 큰걸로 교환해주세요.', sysdate, 'Y', '고객님이 원하시는 사이즈로 교환해드리겠습니다.', 1, 3);

SELECT payment_inquiry_no, title, contents, rdate, answer_visible, answer_contents, memberno, paymentno
FROM payment_inquiry;
    
SELECT s.title FROM 
payment p
JOIN payment_details pd ON pd.paymentno = p.paymentno
JOIN options o ON o.optionno = pd.optionno
JOIN shoes s ON s.shoesno = o.shoesno
WHERE pd.paymentno = 1;

-- list_search_count(데이터 개수)
    SELECT COUNT(*) as cnt
    FROM payment_inquiry pi
    JOIN member m ON pi.memberno = m.memberno
    JOIN payment p ON pi.paymentno = p.paymentno
    JOIN payment_details pd ON pd.paymentno = p.paymentno
    JOIN options o ON o.optionno = pd.optionno
    JOIN shoes s ON s.shoesno = o.shoesno
    WHERE
    (
        UPPER(pi.title) LIKE '%' || UPPER('a') || '%' 
        OR UPPER(pi.contents) LIKE '%' || UPPER('a') || '%'
        OR UPPER(s.title) LIKE '%' || UPPER('a') || '%'
        OR UPPER(m.nickname) LIKE '%' || UPPER('a') || '%' 
    );
    
-- list_search_paging(페이징, 5개까지)
SELECT payment_inquiry_no, title, contents, rdate, answer_visible, shoestitle, nickname FROM (
    SELECT pi.payment_inquiry_no as payment_inquiry_no, pi.title as title, pi.contents as contents, pi.rdate as rdate, pi.answer_visible as answer_visible, s.title shoestitle, m.nickname as nickname, 
    ROW_NUMBER() OVER(
        ORDER BY
            CASE
                WHEN answer_visible = 'N' THEN 0
                ELSE 1
            END,
            payment_inquiry_no DESC
    ) AS r
    FROM payment_inquiry pi
    JOIN member m ON pi.memberno = m.memberno
    JOIN payment p ON pi.paymentno = p.paymentno
    JOIN payment_details pd ON pd.paymentno = p.paymentno
    JOIN options o ON o.optionno = pd.optionno
    JOIN shoes s ON s.shoesno = o.shoesno
    WHERE
    (
        UPPER(pi.title) LIKE '%' || UPPER('a') || '%' 
        OR UPPER(pi.contents) LIKE '%' || UPPER('a') || '%'
        OR UPPER(s.title) LIKE '%' || UPPER('a') || '%'
        OR UPPER(m.nickname) LIKE '%' || UPPER('a') || '%' 
    )
) WHERE r >= 1 AND r <= 5;
     
-- 상세(read) -> 문의 제목, 문의 내용, 작성 날짜, 작성자, 신발이름, 사이즈&색상, 답변하기 답변 내용 
SELECT pi.payment_inquiry_no as payment_inquiry_no, pi.title as title, pi.contents as contents, pi.rdate as inquiry_rdate, pi.answer_visible as answer_visible, 
p.rdate payment_rdate, p.total_payment as total_payment, s.title shoestitle, m.nickname as nickname,
o.sizes as sizes, o.color as color, pi.answer_contents as answer_contents
FROM payment_inquiry pi
JOIN member m ON pi.memberno = m.memberno
JOIN payment p ON pi.paymentno = p.paymentno
JOIN payment_details pd ON pd.paymentno = p.paymentno
JOIN options o ON o.optionno = pd.optionno
JOIN shoes s ON s.shoesno = o.shoesno
WHERE payment_inquiry_no = 3;

SELECT * FROM OPTIONS;
SELECT * FROM SHOES;
SELECT * FROM MEMBER;
SELECT * FROM SHOES_INQUIRY;


    
UPDATE payment_inquiry
SET answer_visible = 'Y', answer_contents = '바꿨습니다'
WHERE payment_inquiry_no = 1;
    
UPDATE payment_inquiry
SET answer_visible = 'N', answer_contents = 'null'
WHERE payment_inquiry_no = 1;

commit;