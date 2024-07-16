DROP TABLE PAYMENT_DETAILS CASCADE CONSTRAINTS;
DROP SEQUENCE PAYMENT_DETAILS_SEQ;
/**********************************/
/* Table Name: 주문상세 */
/**********************************/
CREATE TABLE PAYMENT_DETAILS(
		PAYMENT_DETAILS_NO            		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		PAYMENT_AMOUNT                      NUMBER(9)		 NOT NULL,
		OPTIONNO                      		NUMBER(9)		 NOT NULL,
		PAYMENTNO                     		NUMBER(9)		 NULL ,
  FOREIGN KEY (OPTIONNO) REFERENCES OPTIONS (OPTIONNO),
  FOREIGN KEY (PAYMENTNO) REFERENCES PAYMENT (PAYMENTNO)
);

COMMENT ON TABLE PAYMENT_DETAILS is '주문상세';
COMMENT ON COLUMN PAYMENT_DETAILS.PAYMENT_DETAILS_NO is '주문 상세번호';
COMMENT ON COLUMN PAYMENT_DETAILS.PAYMENT_AMOUNT is '주문 수량';
COMMENT ON COLUMN PAYMENT_DETAILS.OPTIONNO is '옵션 번호';
COMMENT ON COLUMN PAYMENT_DETAILS.PAYMENTNO is '주문 번호';

CREATE SEQUENCE PAYMENT_DETAILS_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
 
SELECT * FROM options;

INSERT INTO PAYMENT_DETAILS(payment_details_no, payment_amount, optionno, paymentno)
VALUES (PAYMENT_DETAILS_SEQ.nextval, 2, 1, 1);

INSERT INTO PAYMENT_DETAILS(payment_details_no, payment_amount, optionno, paymentno)
VALUES (PAYMENT_DETAILS_SEQ.nextval, 3, 1, 1);
 
INSERT INTO PAYMENT_DETAILS(payment_details_no, payment_amount, optionno, paymentno)
VALUES (PAYMENT_DETAILS_SEQ.nextval, 3, 3, 2);

INSERT INTO PAYMENT_DETAILS(payment_details_no, payment_amount, optionno, paymentno)
VALUES (PAYMENT_DETAILS_SEQ.nextval, 5, 2, 3);

INSERT INTO PAYMENT_DETAILS(payment_details_no, payment_amount, optionno, paymentno)
VALUES (PAYMENT_DETAILS_SEQ.nextval, 1, 11, 3);

INSERT INTO PAYMENT_DETAILS(payment_details_no, payment_amount, optionno, paymentno)
VALUES (PAYMENT_DETAILS_SEQ.nextval, 1, 11, 1);

SELECT payment_details_no, payment_amount, optionno, paymentno
FROM PAYMENT_DETAILS;

commit;