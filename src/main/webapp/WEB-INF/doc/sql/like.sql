/**********************************/
/* Table Name: 좋아요 */
/**********************************/
CREATE TABLE LIKE(
		LIKENO                        		NUMBER(9)		 NULL 		 PRIMARY KEY,
		LIKE                          		VARCHAR2(2)		 NOT NULL,
		REVIEWNO                      		VARCHAR2(255)		 NULL ,
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
);

COMMENT ON TABLE LIKE is '좋아요';
COMMENT ON COLUMN LIKE.LIKENO is '좋아요 번호';
COMMENT ON COLUMN LIKE.LIKE is '좋아요';
COMMENT ON COLUMN LIKE.REVIEWNO is '후기 번호';
