/**********************************/
/* Table Name: 신발 */
/**********************************/
DROP TABLE SHOES;

CREATE TABLE SHOES(
		SHOESNO                       		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		CATEGORYNO                    		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
		TITLE                         		VARCHAR2(100)		 NOT NULL,
		BRAND                         		VARCHAR2(100)		 NOT NULL,
		RATING                        		NUMBER		 NOT NULL,
		PRICE                         		NUMBER		 NOT NULL,
		DISCOUNT                      		NUMBER		 NOT NULL,
		CONTENTS                      		VARCHAR2(1000)		 NOT NULL,
		VISIBLE                       		CHAR(1)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (CATEGORYNO) REFERENCES CATEGORY (CATEGORYNO)
);

COMMENT ON TABLE SHOES is '신발';
COMMENT ON COLUMN SHOES.SHOESNO is '신발 번호';
COMMENT ON COLUMN SHOES.CATEGORYNO is '카테고리 번호';
COMMENT ON COLUMN SHOES.MEMBERNO is '유저 번호';
COMMENT ON COLUMN SHOES.TITLE is '신발 제목';
COMMENT ON COLUMN SHOES.BRAND is '신발 브랜드명';
COMMENT ON COLUMN SHOES.RATING is '신발 평점';
COMMENT ON COLUMN SHOES.PRICE is '신발 가격';
COMMENT ON COLUMN SHOES.DISCOUNT is '신발 할인율';
COMMENT ON COLUMN SHOES.CONTENTS is '신발 설명';
COMMENT ON COLUMN SHOES.VISIBLE is '신발 판매 여부';