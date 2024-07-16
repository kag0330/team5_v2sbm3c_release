DROP TABLE CATEGORY CASCADE CONSTRAINTS;
DROP SEQUENCE CATEGORY_SEQ;

/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE CATEGORY(
		CATEGORYNO                    		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SUBNAME                       		VARCHAR2(100)		 NOT NULL,
		SEQ                           		NUMBER(9)		 NULL ,
		CNT                           		NUMBER(9)		 NOT NULL,
		PARENTNO                      		NUMBER(9),
  FOREIGN KEY (PARENTNO) REFERENCES CATEGORY (CATEGORYNO)
);
select * from category;
COMMENT ON TABLE CATEGORY is '카테고리';
COMMENT ON COLUMN CATEGORY.CATEGORYNO is '카테고리 번호';
COMMENT ON COLUMN CATEGORY.NAME is '카테고리 이름';
COMMENT ON COLUMN CATEGORY.SUBNAME is '카테고리 서브명';
COMMENT ON COLUMN CATEGORY.SEQ is '카테고리 출력순서';
COMMENT ON COLUMN CATEGORY.CNT is '카테고리 항목수';
COMMENT ON COLUMN CATEGORY.PARENTNO is '카테고리 부모번호';

CREATE SEQUENCE CATEGORY_SEQ
    START WITH 1         -- 시작 번호
    INCREMENT BY 1       -- 증가값
    MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
    CACHE 2              -- 2번은 메모리에서만 계산
    NOCYCLE;             -- 다시 1부터 생성되는 것을 방지



INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '성별', '-', 1, 100, NULL);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '성별', '남자', 2, 100, 1);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '성별', '여자', 3, 100, 1);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '계절', '-', 11, 100, NULL);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '계절', '봄', 12, 100, 4);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '계절', '여름', 13, 100, 4);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '계절', '가을', 14, 100, 4);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '계절', '겨울', 15, 100, 4);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '연령', '-', 21, 100, NULL);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '연령', '10대 미만', 22, 100, 9);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '연령', '10대', 23, 100, 9);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '연령', '20대', 24, 100, 9);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '연령', '30대', 25, 100, 9);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '연령', '40대', 26, 100, 9);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '연령', '50대', 27, 100, 9);

INSERT INTO CATEGORY (categoryno, name, subname, seq, cnt, parentno)
VALUES (CATEGORY_SEQ.nextval, '연령', '60대 이상', 28, 100, 9);

SELECT categoryno, name, subname, seq, cnt, parentno
FROM category;

-- 출력순서(C_SEQ)를 기준으로 오름차순 정렬(list_all)
SELECT categoryno, name, subname, seq, cnt, parentno
FROM category
WHERE subname != '-'
ORDER BY seq ASC;

-- read(하나의 데이터 읽기)
SELECT categoryno, name, subname, seq, cnt, parentno
FROM category
WHERE categoryno = 1;

-- CATEGORY 데이터 추가(INSERT)
-- #{name}, #{subname}, #{seq}, #{cnt} 
INSERT INTO category (categoryno, name, subname, seq, cnt, parentno)
SELECT 
    CATEGORY_SEQ.nextval AS categoryno,
    '성별' AS name,
    CASE 
        WHEN EXISTS (SELECT 1 FROM CATEGORY WHERE name = '성별') THEN '추가할 소분류제목'
        ELSE '-'
    END AS subname,
    CASE 
        WHEN EXISTS (SELECT 1 FROM CATEGORY WHERE name = '성2별') 
        THEN (SELECT MAX(seq) + 1 FROM CATEGORY WHERE name = '성별')
        ELSE (SELECT COALESCE(CEIL(MAX(seq) / 10 + 0.5) * 10 + 1, 1) FROM CATEGORY)
    END AS seq,
    100 AS cnt,
    CASE 
        WHEN EXISTS (SELECT 1 FROM CATEGORY WHERE name = '성별') 
        THEN (SELECT categoryno FROM CATEGORY WHERE name = '성별' AND subname = '-')
        ELSE NULL
    END AS parent
FROM dual;


SELECT ROUND(MAX(seq) / 10 + 0.5, 0) * 10 + 1 FROM category;  

SELECT MAX(seq) + 1 as c_seq FROM category
WHERE name = '연령';

SELECT COALESCE(CEIL(MAX(seq) / 10 + 0.5) * 10 + 1, 1) FROM CATEGORY;


-- 중분류 삭제 가능 여부 체크
-- 해당 중분류 데이터가 2개 이상일 경우 삭제 불가능
SELECT 
    CASE 
        WHEN (
            SELECT COUNT(*) FROM CATEGORY 
         HEAD
            WHERE C_NAME = '성별'

            WHERE name = '성별'
        ) = 1 THEN 1
        ELSE 0
    END AS DeleteCheck
FROM dual;


-- list_all_name (중분류 목록)
SELECT c_no, c_name
FROM category
WHERE c_name IN (
    SELECT DISTINCT c_name
    FROM category
) AND c_subname = '-'
ORDER BY c_seq ASC;

-- list_all_subname (중분류별 소분류 목록)
SELECT c_no, c_name, c_subname
FROM category
WHERE c_name = '연령' AND c_subname != '-'
ORDER BY c_seq ASC;

-- list_search (카테고리 검색)
SELECT c_no, c_name, c_subname, c_seq, c_cnt, c_parent 
FROM category
WHERE UPPER(c_name) LIKE '%' || UPPER('성별') || '%' OR UPPER(c_subname) LIKE '%' || UPPER('성별') || '%'
ORDER BY c_seq ASC;

-- list_search_paging (페이징)
SELECT c_no, c_name, c_subname, c_seq, c_cnt, c_parent, r
FROM (
    SELECT c_no, c_name, c_subname, c_seq, c_cnt, c_parent, rownum as r
    FROM (
        SELECT c_no, c_name, c_subname, c_seq, c_cnt, c_parent
        FROM category
            
        WHERE UPPER(c_name) LIKE '%' || UPPER('성별') || '%' OR UPPER(c_subname) LIKE '%' || UPPER('성별') || '%'
         
        ORDER BY c_seq ASC

DELETE FROM category WHERE categoryno= #{categoryno};

-- list_all_name (중분류 목록)
SELECT categoryno, name
FROM category
WHERE name IN (
    SELECT DISTINCT name
    FROM category
) AND subname = '-'
ORDER BY seq ASC;

-- list_all_subname (중분류별 소분류 목록)
SELECT categoryno, name, subname
FROM category
WHERE name = '연령' AND subname != '-'
ORDER BY seq ASC;

-- list_search (카테고리 검색)
SELECT categoryno, name, subname, seq, cnt, parentno
FROM category
WHERE UPPER(name) LIKE '%' || UPPER('성별') || '%' OR UPPER(subname) LIKE '%' || UPPER('성별') || '%'
ORDER BY seq ASC;

-- list_search_paging (페이징)
SELECT categoryno, name, subname, seq, cnt, parentno, r
FROM (
    SELECT categoryno, name, subname, seq, cnt, parentno, rownum as r
    FROM (
        SELECT categoryno, name, subname, seq, cnt, parentno
        FROM category
            
        WHERE UPPER(name) LIKE '%' || UPPER('성별') || '%' OR UPPER(subname) LIKE '%' || UPPER('성별') || '%'
         
        ORDER BY seq ASC

        )
    )
    WHERE r >= 1 AND r <= 3;
    
-- list_search_count (검색된 레코드 수)
SELECT count(*) as cnt
FROM category

WHERE UPPER(c_name) LIKE '%' || UPPER('성별') || '%' OR UPPER(c_subname) LIKE '%' || UPPER('성별') || '%'
ORDER BY c_seq ASC;

WHERE UPPER(name) LIKE '%' || UPPER('성별') || '%' OR UPPER(subname) LIKE '%' || UPPER('성별') || '%'
ORDER BY seq ASC;


commit;