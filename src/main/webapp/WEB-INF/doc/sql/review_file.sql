/**********************************/
/* Table Name: 후기 첨부파일 */
/**********************************/
CREATE TABLE REVIEW_FILE(
		REVIEW_FILE_NO                		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SIZE                          		NUMBER		 NOT NULL,
		EX                            		VARCHAR2(100)		 NOT NULL,
		SRC                           		VARCHAR2(100)		 NOT NULL,
		REVIEWNO                      		VARCHAR2(255)		 NULL ,
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
);

COMMENT ON TABLE REVIEW_FILE is '후기 첨부파일';
COMMENT ON COLUMN REVIEW_FILE.REVIEW_FILE_NO is '파일 번호';
COMMENT ON COLUMN REVIEW_FILE.NAME is '첨부파일명';
COMMENT ON COLUMN REVIEW_FILE.SIZE is '첨부파일 크기';
COMMENT ON COLUMN REVIEW_FILE.EX is '확장자명';
COMMENT ON COLUMN REVIEW_FILE.SRC is '첨부파일 주소';
COMMENT ON COLUMN REVIEW_FILE.REVIEWNO is '후기 번호';