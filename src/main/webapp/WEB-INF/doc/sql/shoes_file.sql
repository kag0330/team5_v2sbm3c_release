/**********************************/
/* Table Name: 신발 첨부파일 */
/**********************************/
CREATE TABLE SHOES_FILE(
		SHOES_FILE_NO                 		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SIZES                          		NUMBER		 NOT NULL,
		EX                            		VARCHAR2(100)		 NOT NULL,
		SRC                           		VARCHAR2(100)		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NULL ,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO)
);

COMMENT ON TABLE SHOES_FILE is '신발 첨부파일';
COMMENT ON COLUMN SHOES_FILE.SHOES_FILE_NO is '파일 번호';
COMMENT ON COLUMN SHOES_FILE.NAME is '첨부파일명';
COMMENT ON COLUMN SHOES_FILE.SIZES is '첨부파일 크기';
COMMENT ON COLUMN SHOES_FILE.EX is '확장자명';
COMMENT ON COLUMN SHOES_FILE.SRC is '첨부파일 주소';
COMMENT ON COLUMN SHOES_FILE.SHOESNO is '신발 번호';

SELECT * from shoes_file;

commit;