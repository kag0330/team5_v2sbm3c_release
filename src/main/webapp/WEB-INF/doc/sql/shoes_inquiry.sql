/**********************************/
/* Table Name: 신발 문의 */
/**********************************/
DROP TABLE SHOES_INQUIRY CASCADE CONSTRAINTS;
DROP SEQUENCE SHOES_INQUIRY_SEQ;

CREATE TABLE SHOES_INQUIRY(
    SHOES_INQUIRY_NO        NUMBER(9)        NOT NULL PRIMARY KEY,
    TITLE                   VARCHAR2(100)    NOT NULL,
    CONTENTS                VARCHAR2(1000)   NOT NULL,
    RDATE                   DATE             NOT NULL,
    ANSWER_VISIBLE          CHAR(1)          DEFAULT 'N'   NOT NULL,
    ANSWER_CONTENTS          VARCHAR2(1000)   DEFAULT '',
    OPTIONNO                NUMBER(9)        NULL,
    MEMBERNO                NUMBER(9)        NULL,
    FOREIGN KEY (OPTIONNO) REFERENCES OPTIONS (OPTIONNO),
    FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE SHOES_INQUIRY is '신발 문의';
COMMENT ON COLUMN SHOES_INQUIRY.SHOES_INQUIRY_NO is '신발 문의 번호';
COMMENT ON COLUMN SHOES_INQUIRY.TITLE is '문의 제목';
COMMENT ON COLUMN SHOES_INQUIRY.CONTENTS is '문의 내용';
COMMENT ON COLUMN SHOES_INQUIRY.RDATE is '문의 작성일';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_CONTENTS is '답변 내용';
COMMENT ON COLUMN SHOES_INQUIRY.OPTIONNO is '옵션 번호';
COMMENT ON COLUMN SHOES_INQUIRY.MEMBERNO is '멤버 번호'; -- 작성자


CREATE SEQUENCE SHOES_INQUIRY_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
 
-- 임시 데이터
INSERT INTO SHOES_INQUIRY(shoes_inquiry_no, title, contents, rdate, optionno, memberno)
VALUES(SHOES_INQUIRY_SEQ.nextval, '첫번째 문의입니다.', '문의사항 내용', sysdate, 1, 1);

INSERT INTO SHOES_INQUIRY(shoes_inquiry_no, title, contents, rdate, optionno, memberno)
VALUES(SHOES_INQUIRY_SEQ.nextval, '두번째 문의입니다.', '사이즈가 너무큽니다.', sysdate, 2, 1);

INSERT INTO SHOES_INQUIRY(shoes_inquiry_no, title, contents, rdate, optionno, memberno)
VALUES(SHOES_INQUIRY_SEQ.nextval, '세번째 문의입니다.', '색상 사진 보여주세요.', sysdate, 3, 1);

INSERT INTO SHOES_INQUIRY(shoes_inquiry_no, title, contents, rdate, answer_visible, answer_contents, optionno, memberno)
VALUES(SHOES_INQUIRY_SEQ.nextval, '네번째 문의입니다.', '완료한거에요.', sysdate, 'Y', '답변내용입니다.', 3, 1);

SELECT shoes_inquiry_no, title, contents, rdate, answer_visible, answer_contents optionno, memberno
FROM shoes_inquiry;
    
-- list_search_count(데이터 개수)
    SELECT COUNT(*) as cnt
    FROM shoes_inquiry si
    JOIN member m ON si.memberno = m.memberno
    JOIN options o ON si.optionno = o.optionno
    JOIN shoes s ON o.shoesno = s.shoesno
        WHERE
    (
        UPPER(si.title) LIKE '%' || UPPER('a') || '%' 
        OR UPPER(si.contents) LIKE '%' || UPPER('a') || '%'
        OR UPPER(s.title) LIKE '%' || UPPER('a') || '%' 
        OR UPPER(m.nickname) LIKE '%' || UPPER('a') || '%' 
    );

-- list_search_paging(페이징, 5개까지)
SELECT shoes_inquiry_no, title, contents, rdate, answer_visible, shoestitle, nickname FROM (
    SELECT si.shoes_inquiry_no as shoes_inquiry_no, si.title as title, si.contents as contents, si.rdate as rdate, si.answer_visible as answer_visible, s.title shoestitle, m.nickname as nickname, 
    ROW_NUMBER() OVER(
        ORDER BY
            CASE
                WHEN answer_visible = 'N' THEN 0
                ELSE 1
            END,
            shoes_inquiry_no DESC
    ) AS r
    FROM shoes_inquiry si
    JOIN member m ON si.memberno = m.memberno
    JOIN options o ON si.optionno = o.optionno
    JOIN shoes s ON o.shoesno = s.shoesno
    WHERE
    (
        UPPER(si.title) LIKE '%' || UPPER('a') || '%' 
        OR UPPER(si.contents) LIKE '%' || UPPER('a') || '%'
        OR UPPER(s.title) LIKE '%' || UPPER('a') || '%' 
        OR UPPER(m.nickname) LIKE '%' || UPPER('a') || '%' 
    )
) WHERE r >= 1 AND r <= 5;
     
-- 상세(read) -> 문의 제목, 문의 내용, 작성 날짜, 작성자, 신발이름, 사이즈&색상, 답변하기 답변 내용 
SELECT si.shoes_inquiry_no as shoes_inquiry_no, si.title as title, si.contents as contents, 
si.rdate as rdate, si.answer_visible as answer_visible, s.title shoestitle, m.nickname as nickname,
o.sizes as sizes, o.color as color, si.answer_contents as answer_contents
FROM shoes_inquiry si
JOIN member m ON si.memberno = m.memberno
JOIN options o ON si.optionno = o.optionno
JOIN shoes s ON o.shoesno = s.shoesno
WHERE shoes_inquiry_no = 1;

SELECT * FROM OPTIONS;
SELECT * FROM SHOES;
SELECT * FROM MEMBER;
SELECT * FROM SHOES_INQUIRY;

    SELECT si.shoes_inquiry_no as shoes_inquiry_no, si.title as title, si.contents as contents, 
    si.rdate as rdate, si.answer_visible as answer_visible, s.title shoestitle, m.nickname as nickname,
    o.sizes as sizes, o.color as color, si.answer_contents as answer_contents
    FROM shoes_inquiry si
    JOIN member m ON si.memberno = m.memberno
    JOIN options o ON si.optionno = o.optionno
    JOIN shoes s ON o.shoesno = s.shoesno
    WHERE shoes_inquiry_no = 4;
    
    UPDATE shoes_inquiry
    SET answer_visible = 'Y', answer_contents = '바꿨습니다'
    WHERE shoes_inquiry_no = 1;
    
    UPDATE shoes_inquiry
    SET answer_visible = 'N', answer_contents = null
    WHERE shoes_inquiry_no = 1;
commit;

