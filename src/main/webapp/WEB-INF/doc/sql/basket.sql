/**********************************/
/* Table Name: 장바구니 */
/**********************************/
CREATE TABLE BASKET(
		BASKETNO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		AMOUNT                        		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
		OPTIONNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (OPTIONNO) REFERENCES OPTION (OPTIONNO)
);

COMMENT ON TABLE BASKET is '장바구니';
COMMENT ON COLUMN BASKET.BASKETNO is '장바구니 번호';
COMMENT ON COLUMN BASKET.AMOUNT is '신발 수량';
COMMENT ON COLUMN BASKET.MEMBERNO is '멤버 번호';
COMMENT ON COLUMN BASKET.OPTIONNO is '옵션 번호';

select * FROM basket


SELECT b.basketno, b.amount
FROM basket b
INNER JOIN options o ON o.optionno = b.optionno
INNER JOIN shoes s ON o.shoesno = s.shoesno
INNER JOIN member m ON m.memberno = b.memberno
WHERE color ='검정색' AND sizes = '250'




SELECT b.basketno, b.amount, 
       o.optionno, o.sizes, o.color, 
       s.shoesno, s.title, s.brand, s.price, s.discount, s.contents
       
FROM basket b
INNER JOIN options o ON b.optionno = o.optionno
INNER JOIN shoes s ON o.shoesno = s.shoesno
WHERE b.memberno = ${memberno}
        
        
        
        commit;
        
        
        