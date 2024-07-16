/**********************************/
/* Table Name: 신고 유형 */
/**********************************/
CREATE TABLE REPORT_TYPE(
		TYPENO                        		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		TYPE                          		VARCHAR2(100)		 NOT NULL
);

COMMENT ON TABLE REPORT_TYPE is '신고 유형';
COMMENT ON COLUMN REPORT_TYPE.TYPENO is '신고 유형번호';
COMMENT ON COLUMN REPORT_TYPE.TYPE is '신고 유형';
