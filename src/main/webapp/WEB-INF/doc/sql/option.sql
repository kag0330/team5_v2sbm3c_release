/**********************************/
/* Table Name: 신발옵션 */
/**********************************/
DROP TABLE OPTIONS;

CREATE TABLE OPTION(
		OPTIONNO                      		NUMBER(9)		 NOT NULL 		 PRIMARY KEY,
		SIZE                          		NUMBER(4)		 NOT NULL,
		AMOUNT                        		NUMBER(9)		 NOT NULL,
		COLOR                         		VARCHAR2(30)		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO)
);

COMMENT ON TABLE OPTION is '신발옵션';
COMMENT ON COLUMN OPTION.OPTIONNO is '옵션 번호';
COMMENT ON COLUMN OPTION.SIZE is '신발 사이즈';
COMMENT ON COLUMN OPTION.AMOUNT is '신발 재고';
COMMENT ON COLUMN OPTION.COLOR is '신발 색상';
COMMENT ON COLUMN OPTION.SHOESNO is '신발 번호';


    SELECT DISTINCT
      o.optionno,
      o.sizes,
      o.color,
      o.amount
      
    FROM 
      shoes s
      INNER JOIN category_list cl ON s.shoesno = cl.shoesno
      INNER JOIN category c ON cl.categoryno = c.categoryno
      INNER JOIN options o ON s.shoesno = o.shoesno
    WHERE 
      s.shoesno = 1
    ORDER BY 
      o.sizes