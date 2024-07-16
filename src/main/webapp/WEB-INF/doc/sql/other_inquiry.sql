/**********************************/
/* Table Name: 기타 문의 */
/**********************************/
DROP TABLE OTHER_INQUIRY CASCADE CONSTRAINTS;
DROP SEQUENCE OTHER_INQUIRY_SEQ;

CREATE TABLE OTHER_INQUIRY(
    OTHER_INQUIRY_NO        NUMBER(9)        NOT NULL PRIMARY KEY,
    TITLE                   VARCHAR2(100)    NOT NULL,
    CONTENTS                VARCHAR2(1000)   NOT NULL,
    RDATE                   DATE             NOT NULL,
    ANSWER_VISIBLE          CHAR(1)          DEFAULT 'N'   NOT NULL,
    ANSWER_CONTENTS          VARCHAR2(1000)   DEFAULT '',
    MEMBERNO                NUMBER(9),   
    FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE OTHER_INQUIRY is '기타 문의';
COMMENT ON COLUMN OTHER_INQUIRY.OTHER_INQUIRY_NO is '기타 문의 번호';
COMMENT ON COLUMN OTHER_INQUIRY.TITLE is '문의 제목';
COMMENT ON COLUMN OTHER_INQUIRY.CONTENTS is '문의 내용';
COMMENT ON COLUMN OTHER_INQUIRY.RDATE is '문의 작성일';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_CONTENTS is '답변 내용';
COMMENT ON COLUMN OTHER_INQUIRY.MEMBERNO is '멤버 번호';


CREATE SEQUENCE OTHER_INQUIRY_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
 
-- 임시 데이터
INSERT INTO OTHER_INQUIRY(other_inquiry_no, title, contents, rdate, memberno)
VALUES(OTHER_INQUIRY_SEQ.nextval, '첫번째 문의입니다.', '기타 문의 사항 1', sysdate, 1);

INSERT INTO OTHER_INQUIRY(other_inquiry_no, title, contents, rdate, memberno)
VALUES(OTHER_INQUIRY_SEQ.nextval, '두번째 문의입니다.', '홈페이지 오류가 있습니다.', sysdate, 1);

INSERT INTO OTHER_INQUIRY(other_inquiry_no, title, contents, rdate, memberno)
VALUES(OTHER_INQUIRY_SEQ.nextval, '첫번째 문의입니다.', '사용법이 궁금합니다.', sysdate, 1);

INSERT INTO OTHER_INQUIRY(other_inquiry_no, title, contents, rdate, answer_visible, answer_contents, memberno)
VALUES(OTHER_INQUIRY_SEQ.nextval, '네번째 문의입니다.', '문의 서비스가 작동하지 않습니다.', sysdate, 'Y', '문의 서비스 수정 완료하였습니다. 다시 한번 확인해주세요.', 1);

SELECT other_inquiry_no, title, contents, rdate, answer_visible, answer_contents, memberno
FROM other_inquiry;
    
commit;

-- list_search_count(데이터 개수)
    SELECT COUNT(*) as cnt
    FROM other_inquiry oi
    JOIN member m ON oi.memberno = m.memberno
    WHERE
    (
        UPPER(oi.title) LIKE '%' || UPPER('a') || '%' 
        OR UPPER(oi.contents) LIKE '%' || UPPER('a') || '%'
        OR UPPER(m.nickname) LIKE '%' || UPPER('a') || '%' 
    );

-- list_search_paging(페이징, 5개까지)
SELECT other_inquiry_no, title, contents, rdate, answer_visible, nickname FROM (
    SELECT oi.other_inquiry_no as other_inquiry_no, oi.title as title, oi.contents as contents, oi.rdate as rdate, oi.answer_visible as answer_visible, m.nickname as nickname, 
    ROW_NUMBER() OVER(
        ORDER BY
            CASE
                WHEN answer_visible = 'N' THEN 0
                ELSE 1
            END,
            other_inquiry_no DESC
    ) AS r
    FROM other_inquiry oi
    JOIN member m ON oi.memberno = m.memberno
    WHERE
    (
        UPPER(oi.title) LIKE '%' || UPPER('a') || '%' 
        OR UPPER(oi.contents) LIKE '%' || UPPER('a') || '%'
        OR UPPER(m.nickname) LIKE '%' || UPPER('a') || '%' 
    )
) WHERE r >= 1 AND r <= 5;
     
-- 상세(read) -> 문의 제목, 문의 내용, 작성 날짜, 작성자, 신발이름, 사이즈&색상, 답변하기 답변 내용 
SELECT oi.other_inquiry_no as other_inquiry_no, oi.title as title, oi.contents as contents, 
oi.rdate as rdate, oi.answer_visible as answer_visible, m.nickname as nickname, oi.answer_contents as answer_contents
FROM other_inquiry oi
JOIN member m ON oi.memberno = m.memberno
WHERE other_inquiry_no = 4;

SELECT * FROM OPTIONS;
SELECT * FROM SHOES;
SELECT * FROM MEMBER;
SELECT * FROM SHOES_INQUIRY;
SELECT * FROM OTHER_INQUIRY;

SELECT oi.other_inquiry_no as other_inquiry_no, oi.title as title, oi.contents as contents, 
oi.rdate as rdate, oi.answer_visible as answer_visible, m.nickname as nickname, oi.answer_contents as answer_contents
FROM other_inquiry oi
JOIN member m ON oi.memberno = m.memberno
WHERE other_inquiry_no = 4;
    
    UPDATE other_inquiry
    SET answer_visible = 'Y', answer_contents = '바꿨습니다'
    WHERE other_inquiry_no = 1;
    
    UPDATE other_inquiry
    SET answer_visible = 'N', answer_contents = null
    WHERE other_inquiry_no = 1;
commit;