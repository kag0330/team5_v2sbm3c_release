/**********************************/
/* Table Name: 주문 */
/**********************************/
DROP TABLE payment CASCADE CONSTRAINTS; 

DROP TABLE payment;

CREATE TABLE PAYMENT(
		PAYMENTNO                     		NUMBER(9)		     NOT NULL		 PRIMARY KEY,
		RDATE                          		DATE		         NOT NULL,
		STATUS                        		VARCHAR2(50)		 NOT NULL,
		PAYMENT_STATUS                		VARCHAR2(50)		 NOT NULL,
		CS_STATUS                      		VARCHAR2(50)		 NULL ,
		TOTAL_PRICE                         NUMBER		         DEFAULT 0       NULL  , 
		DELIVERY                      		NUMBER		         DEFAULT 2500    NULL,
		TOTAL_PAYMENT                 		NUMBER		         DEFAULT 0       NOT NULL,
		MEMBERNO                      		NUMBER(9)		     NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE PAYMENT is '주문';
COMMENT ON COLUMN PAYMENT.PAYMENTNO is '주문 번호';
COMMENT ON COLUMN PAYMENT.RDATE is '주문 일자';
COMMENT ON COLUMN PAYMENT.STATUS is '주문 상태';             -- 상품준비중 / 배송준비중 / 배송보류 / 배송대기 / 배송중 / 배송완료
COMMENT ON COLUMN PAYMENT.PAYMENT_STATUS is '결제 상태';     -- 입금전 / 추가입금대기 / 입금완료(수동) / 입금완료(자동) / 결제완료
COMMENT ON COLUMN PAYMENT.CS_STATUS is 'CS 상태';            -- 취소 / 교환 / 반품 / 환불
COMMENT ON COLUMN PAYMENT.TOTAL_PRICE is '총 상품금액';      -- 상품금액들의 합
COMMENT ON COLUMN PAYMENT.DELIVERY is '배송비';              -- 배송비
COMMENT ON COLUMN PAYMENT.TOTAL_PAYMENT is '총 주문금액';    -- 총상품금액 + 배송비
COMMENT ON COLUMN PAYMENT.MEMBERNO is '멤버 번호';


  
DROP SEQUENCE payment_seq;

CREATE SEQUENCE payment_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

INSERT INTO payment(paymentno, rdate, status, payment_status, cs_status, total_price, delivery, total_payment, memberno)
VALUES(payment_seq.nextval, sysdate, '상품준비중', '입금전', '', 300000, 2500, 302500, 1);

INSERT INTO payment(paymentno, rdate, status, payment_status, cs_status, total_price, delivery, total_payment, memberno)
VALUES(payment_seq.nextval, sysdate, '배송완료', '결제완료', '', 100000, 2500, 102500, 1);

SELECT paymentno, rdate, status, payment_status, cs_status, total_price, delivery, total_payment, memberno
FROM payment
WHERE memberno = 1;



delete from payment
where paymentno = 2;

commit;
