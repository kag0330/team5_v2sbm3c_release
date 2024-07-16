/**********************************/
/* Table Name: 공지사항 */
/**********************************/
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP SEQUENCE NOTICE_SEQ;
/**********************************/
/* Table Name: 공지사항 */
/**********************************/
CREATE TABLE NOTICE(
		NOTICENO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		TITLE                         		VARCHAR2(100)		 NOT NULL,
		CONTENTS                      		VARCHAR2(1000)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		VIEWS                         		NUMBER(9)		 DEFAULT 0		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
        FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);
--
COMMENT ON TABLE NOTICE is '공지사항';
COMMENT ON COLUMN NOTICE.NOTICENO is '공지사항 번호';
COMMENT ON COLUMN NOTICE.TITLE is '공지사항 제목';
COMMENT ON COLUMN NOTICE.CONTENTS is '공지사항 내용';
COMMENT ON COLUMN NOTICE.RDATE is '공지사항 작성일';
COMMENT ON COLUMN NOTICE.VIEWS is '조회수';
COMMENT ON COLUMN NOTICE.MEMBERNO is '멤버 번호';


CREATE SEQUENCE NOTICE_SEQ
    START WITH 1         -- 시작 번호
    INCREMENT BY 1       -- 증가값
    MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
    CACHE 2              -- 2번은 메모리에서만 계산
    NOCYCLE;             -- 다시 1부터 생성되는 것을 방지

/**********************************/
/* Table Name: 공지 첨부파일 */
/**********************************/
DROP TABLE NOTICE_FILE CASCADE CONSTRAINTS;
DROP SEQUENCE NOTICE_FILE_SEQ;
/**********************************/
/* Table Name: 공지 첨부파일 */
/**********************************/
CREATE TABLE NOTICE_FILE(
		NOTICE_FILE_NO                		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SIZES                          		NUMBER		 NOT NULL,
		EX                            		VARCHAR2(100)		 NOT NULL,
		SRC                           		VARCHAR2(100)		 NOT NULL,
		NOTICENO                      		NUMBER(9)		 NULL ,
  FOREIGN KEY (NOTICENO) REFERENCES NOTICE (NOTICENO)
);

COMMENT ON TABLE NOTICE_FILE is '공지 첨부파일';
COMMENT ON COLUMN NOTICE_FILE.NOTICE_FILE is '파일 번호';
COMMENT ON COLUMN NOTICE_FILE.NAME is '첨부파일명';
COMMENT ON COLUMN NOTICE_FILE.SIZES is '첨부파일 크기';
COMMENT ON COLUMN NOTICE_FILE.EX is '확장자명';
COMMENT ON COLUMN NOTICE_FILE.SRC is '첨부파일 주소';
COMMENT ON COLUMN NOTICE_FILE.NOTICENO is '공지사항 번호';

CREATE SEQUENCE NOTICE_FILE_SEQ
    START WITH 1         -- 시작 번호
    INCREMENT BY 1       -- 증가값
    MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
    CACHE 2              -- 2번은 메모리에서만 계산
    NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
    
    
    
SELECT noticeno, title, contents, rdate, views, memberno
FROM notice;

-- 공지사항 보기
SELECT noticeno, title, contents, rdate, views, memberno
FROM notice
ORDER BY rdate DESC;

SELECT n.noticeno, n.title, n.contents, n.rdate, n.views, m.nickname
FROM notice n
JOIN member m ON n.memberno = m.memberno
ORDER BY n.rdate DESC;

SELECT n.noticeno, n.title, n.contents, n.rdate, n.views, m.nickname
FROM notice n, member m
WHERE n.memberno = m.memberno
ORDER BY n.rdate DESC;

SELECT n.noticeno, n.title, n.contents, n.rdate, n.views, m.nickname, r
FROM (
    SELECT noticeno, title, contents, rdate, views, memberno, rownum as r
    FROM notice
    ORDER BY noticeno DESC
) n
JOIN member m ON n.memberno = m.memberno
AND
r <= 2;
-- 공지사항 상세
SELECT n.noticeno, n.title, n.contents, n.rdate, n.views, m.nickname
FROM notice n
JOIN member m ON n.memberno = m.memberno
WHERE noticeno = 5;

SELECT n.noticeno, nf.notice_file_no, nf.sizes, nf.ex, nf.src
FROM notice n
JOIN notice_file nf
ON n.noticeno = nf.noticeno
WHERE n.noticeno = 5;

SELECT n.noticeno, m.memberno, nf.notice_file_no, n.title, n.contents, n.rdate, n.views, m.nickname, nf.name, nf.sizes, nf.ex, nf.src
FROM notice n
JOIN member m 
ON n.memberno = m.memberno
JOIN notice_file nf
ON n.noticeno = nf.noticeno
WHERE n.noticeno = 5;


-- 공지사항 생성(임시 데이터)
INSERT INTO notice(noticeno, title, contents, rdate, memberno)
VALUES(NOTICE_SEQ.nextval, '시작글입니다.', '공지사항의 첫 글입니다.', sysdate, 1);

INSERT INTO notice(noticeno, title, contents, rdate, memberno)
VALUES(NOTICE_SEQ.nextval, '두 번째 글입니다.', '공지사항의 두 번째 글입니다.', sysdate, 1);

INSERT INTO notice(noticeno, title, contents, rdate, memberno)
VALUES(NOTICE_SEQ.nextval, '세 번째 글입니다.', '공지사항의 세 번째 글입니다.', sysdate, 1);

INSERT INTO NOTICE_FILE(notice_file_no, name, sizes, ex, src, noticeno)
VALUES(NOTICE_FILE_SEQ.nextval, '파일1', 100, 'jpg', 'images/admin', 1);

-- 공지사항 생성(mybatis)
    DECLARE
        v_noticeno NUMBER;
    BEGIN
        INSERT INTO notice (noticeno, title, contents, rdate, memberno)
        VALUES (NOTICE_SEQ.nextval, #{noticeVO.title}, #{noticeVO.contents}, SYSDATE, 1)
        RETURNING noticeno INTO v_noticeno;
        <if test="name == null and name == ''">
        INSERT INTO notice_file (notice_file_no, name, sizes, ex, src, noticeno)
        VALUES (NOTICE_FILE_SEQ.nextval, #{name}, #{sizes}, #{ex}, #{src}, v_noticeno);
        </if>
    END;
    
-- list_all(전체 목록)
    SELECT n.noticeno, n.title, n.contents, n.rdate, n.views, m.nickname
    FROM notice n, member m
    WHERE n.memberno = m.memberno
    ORDER BY n.rdate DESC
    
-- list_search_count(검색된 레코드 수)
    SELECT COUNT(*) as cnt
    FROM notice
    <if test="word != null and word != ''">
      WHERE UPPER(title) LIKE '%' || UPPER(#{word}) || '%' OR UPPER(contents) LIKE '%' || UPPER(#{word}) || '%'
    </if>
    ORDER BY rdate DESC    

-- list_search_paging(페이징)
    SELECT n.noticeno, n.title, n.contents, n.rdate, n.views, m.nickname, r
    FROM (
        SELECT noticeno, title, contents, rdate, views, memberno, rownum as r
        FROM notice
        ORDER BY noticeno DESC
    ) n
    JOIN member m ON n.memberno = m.memberno
    <if test="word != null and word != ''">
    WHERE 
      (
        UPPER(n.title) LIKE '%' || UPPER(#{word}) || '%' 
        OR UPPER(n.contents) LIKE '%' || UPPER(#{word}) || '%' 
        OR UPPER(m.nickname) LIKE '%' || UPPER(#{word}) || '%' 
      )
    </if>
    AND
        r &gt; = #{start_num} AND r &lt; = #{end_num}
        
-- file_count(공지사항의 파일 유무 확인)
     select count(*) as cnt
     FROM notice_file 
    WHERE noticeno = #{noticeno}
    
-- read (상세)
    <if test="count == 0">
        SELECT n.noticeno, m.memberno, n.title, n.contents, n.rdate, n.views, m.nickname
        FROM notice n
        JOIN member m 
        ON n.memberno = m.memberno
        WHERE n.noticeno = #{noticeno}
    </if>
    <if test="count != 0">
        SELECT n.noticeno, m.memberno, nf.notice_file_no, n.title, n.contents, n.rdate, n.views, m.nickname, nf.notice_file_no, nf.name, nf.sizes, nf.ex, nf.src
        FROM notice n
        JOIN member m 
        ON n.memberno = m.memberno
        JOIN notice_file nf
        ON n.noticeno = nf.noticeno
        WHERE n.noticeno = #{noticeno}
    </if>
    
-- increased_views(조회수 1 상승)
    UPDATE notice
    SET views = views + 1
    WHERE noticeno = #{noticeno}

-- update(공지사항 수정)
DECLARE
        v_noticeno NUMBER;
    BEGIN
        UPDATE notice 
        SET title = #{title}, 
        contents = #{contents}
        WHERE noticeno = #{noticeno};
    <if test="name != null and name != ''">
        UPDATE notice_file 
        SET name = #{name}, 
        sizes = #{sizes}, 
        ex = #{ex}, 
        src = #{src} 
        WHERE noticeno = #{noticeno};
        </if>
    END;

-- 공지사항 삭제 (member 테이블 추가 후 수정)
DELETE FROM notice WHERE noticeno = #{notice}

DELETE FROM notice_file WHERE noticeno = #{notice}
;

commit;







--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT noticeno, title, contents, rdate, views, 
       nickname, 
       notice_file_no, name, sizes, ex, src, r
FROM (
  SELECT n.noticeno, n.title, n.contents, n.rdate, n.views, n.memberno,
         m.nickname,
         nf.notice_file_no, nf.name, nf.sizes, nf.ex, nf.src,
         ROW_NUMBER() OVER (ORDER BY n.rdate DESC) as r
  FROM notice n
  INNER JOIN member m ON n.memberno = m.memberno
  LEFT JOIN notice_file nf ON n.noticeno = nf.noticeno
--   <if test="word != null and word != ''">
--   WHERE 
--     (
--       UPPER(n.title) LIKE '%' || UPPER(#{word}) || '%' 
--       OR UPPER(n.contents) LIKE '%' || UPPER(#{word}) || '%' 
--       OR UPPER(m.nickname) LIKE '%' || UPPER(#{word}) || '%' 
--     )
--   </if>
) subquery
WHERE r >= 1 AND r <= 5 ;




WITH notice_with_rownum AS (
  SELECT n.noticeno, n.title, n.contents, n.rdate, n.views, 
         m.nickname,
         ROW_NUMBER() OVER (ORDER BY n.rdate DESC) as r
  FROM notice n
  INNER JOIN member m ON n.memberno = m.memberno
--  <if test="word != null and word != ''">
--    WHERE (
--      UPPER(n.title) LIKE '%' || UPPER(#{word}) || '%' 
--      OR UPPER(n.contents) LIKE '%' || UPPER(#{word}) || '%' 
--      OR UPPER(m.nickname) LIKE '%' || UPPER(#{word}) || '%' 
--    )
--  </if>
),
distinct_notices AS (
  SELECT noticeno, title, contents, rdate, views, nickname, r
  FROM notice_with_rownum
  WHERE r >= 1 AND r <= 5
)
SELECT dn.noticeno, dn.title, dn.contents, dn.rdate, dn.views, 
       dn.nickname, 
       nf.notice_file_no, nf.name, nf.sizes, nf.ex, nf.src
FROM distinct_notices dn
LEFT JOIN notice_file nf ON dn.noticeno = nf.noticeno
ORDER BY dn.r;
--------------------------------------------------------------------------------------
SELECT n.noticeno, m.memberno, nf.notice_file_no, n.title, n.contents, n.rdate, n.views, m.nickname, nf.notice_file_no, nf.name, nf.sizes, nf.ex, nf.src
FROM notice n
JOIN member m 
ON n.memberno = m.memberno
LEFT JOIN notice_file nf
ON n.noticeno = nf.noticeno
WHERE n.noticeno = 18



-------------------------------------------------
  <insert id="create" parameterType="Map">
    DECLARE
        v_noticeno NUMBER;
    BEGIN
        INSERT INTO notice (noticeno, title, contents, rdate, memberno)
        VALUES (NOTICE_SEQ.nextval, #{title}, #{contents}, SYSDATE, 1)
        RETURNING noticeno INTO v_noticeno;
    <if test="files != null">
	    <foreach collection="files" item="file">
	      INSERT INTO notice_file (notice_file_no, name, sizes, ex, src, noticeno)
	      VALUES (NOTICE_FILE_SEQ.nextval, #{file.name}, #{file.sizes}, #{file.ex}, #{file.src}, v_noticeno);
	    </foreach>
    </if> 
    END;
  </insert>
  -----------------------------------------

  <update id="update" parameterType="Map">
    UPDATE notice 
    SET title = #{title}, 
      contents = #{contents}
    WHERE noticeno = #{noticeno};
    <if test="files != null">
      <foreach collection="files" item="file">
        INSERT INTO notice_file (notice_file_no, name, sizes, ex, src, noticeno)
        VALUES (NOTICE_FILE_SEQ.nextval, #{file.name}, #{file.sizes}, #{file.ex}, #{file.src}, #{noticeno});
      </if>
    END;
  </update>


commit;
