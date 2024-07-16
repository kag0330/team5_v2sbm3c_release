/**********************************/
/* Table Name: 장바구니 */
/**********************************/
CREATE TABLE BASKET(
		BASKETNO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		AMOUNT                        		NUMBER(9)	     DEFAULT 0    	 NOT NULL,
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




CREATE SEQUENCE BASKET_SEQ
    START WITH 1         -- 시작 번호
    INCREMENT BY 1       -- 증가값
    MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
    CACHE 2              -- 2번은 메모리에서만 계산
    NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
    
    
INSERT INTO BASKET(BASKETNO, MEMBERNO, OPTIONNO)
VALUES(OPTION_SEQ, 1, 1)