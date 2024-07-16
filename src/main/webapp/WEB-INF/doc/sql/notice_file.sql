/**********************************/
/* Table Name: 공지 첨부파일 */
/**********************************/
CREATE TABLE NOTICE_FILE(
		NOTICE_FILE_NO                		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SIZE                          		NUMBER		 NOT NULL,
		EX                            		VARCHAR2(100)		 NOT NULL,
		SRC                           		VARCHAR2(100)		 NOT NULL,
		NOTICENO                      		NUMBER(9)		 NULL ,
  FOREIGN KEY (NOTICENO) REFERENCES NOTICE (NOTICENO)
);

COMMENT ON TABLE NOTICE_FILE is '공지 첨부파일';
COMMENT ON COLUMN NOTICE_FILE.NOTICE_FILE_NO is '파일 번호';
COMMENT ON COLUMN NOTICE_FILE.NAME is '첨부파일명';
COMMENT ON COLUMN NOTICE_FILE.SIZE is '첨부파일 크기';
COMMENT ON COLUMN NOTICE_FILE.EX is '확장자명';
COMMENT ON COLUMN NOTICE_FILE.SRC is '첨부파일 주소';
COMMENT ON COLUMN NOTICE_FILE.NOTICENO is '공지사항 번호';


SELECT notice_file_no, name, sizes, ex, src, noticeno
FORM notice_file
WHERE noticeno = 14;