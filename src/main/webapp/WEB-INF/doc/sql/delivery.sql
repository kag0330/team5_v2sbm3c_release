/**********************************/
/* Table Name: 배송지 */
/**********************************/
CREATE TABLE DELIVERY(
		DELIVERYNO                    		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		TITLE                         		VARCHAR2(100)		 NOT NULL,
		RECIPIENT                     		VARCHAR2(100)		 NOT NULL,
		PHONE                         		VARCHAR2(13)		 NOT NULL,
		ZIPCODE                       		NUMBER(5)		 NOT NULL,
		ADDR1                         		VARCHAR2(1000)		 NOT NULL,
		ADDR2                         		VARCHAR2(1000)		 NOT NULL,
		REQUESTS                      		VARCHAR2(1000)		 NULL ,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE DELIVERY is '배송지';
COMMENT ON COLUMN DELIVERY.DELIVERYNO is '배송지주소번호';
COMMENT ON COLUMN DELIVERY.TITLE is '배송지이름';
COMMENT ON COLUMN DELIVERY.RECIPIENT is '받는분';
COMMENT ON COLUMN DELIVERY.PHONE is '연락처';
COMMENT ON COLUMN DELIVERY.ZIPCODE is '우편번호';
COMMENT ON COLUMN DELIVERY.ADDR1 is '주소1';
COMMENT ON COLUMN DELIVERY.ADDR2 is '주소2';
COMMENT ON COLUMN DELIVERY.REQUESTS is '요청사항';
COMMENT ON COLUMN DELIVERY.MEMBERNO is '멤버 번호';

DROP SEQUENCE delivery_seq;

CREATE SEQUENCE delivery_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
--C
INSERT INTO delivery(deliveryno, title, recipient, phone, zipcode, addr1, addr2, requests, memberno)
VALUES (delivery_seq.nextval, '집', '아무개', '010-1234-1234', '12345', '서울시', '종로구', '택배함에 넣어주세요.', 1);

INSERT INTO delivery(deliveryno, title, recipient, phone, zipcode, addr1, addr2, requests, memberno)
VALUES (delivery_seq.nextval, '회사', '홍길동', '010-1234-1234', '12345', '서울시', '종로구', '택배함에 넣어주세요.', 1);

INSERT INTO delivery(deliveryno, title, recipient, phone, zipcode, addr1, addr2, requests, memberno)
VALUES (delivery_seq.nextval, '학교', '김길동', '010-1234-1234', '12345', '서울시', '종로구', '택배함에 넣어주세요.', 1);

INSERT INTO delivery(deliveryno, title, recipient, phone, zipcode, addr1, addr2, requests, memberno)
VALUES (delivery_seq.nextval, '등록1', '나길동', '010-1234-1234', '12345', '서울시', '종로구', '택배함에 넣어주세요.', 1);

--R
select deliveryno, title, recipient, phone, zipcode, addr1, addr2, requests, memberno
from delivery
where memberno = 1
order by deliveryno asc;

select COUNT(*) as cnt
from delivery
where memberno = 1;

--U
UPDATE delivery
SET title=#{title}, recipient=#{recipient}, phone=#{phone}, zipcode=#{zipcode}, addr1=#{addr1}, addr2=#{addr2}, requests=#{requests}
WHERE memberno=#{memberno} AND deliveryno = #{deliveryno}

--D
DELETE FROM delivery
WHERE deliveryno = 2;

DELETE FROM delivery
WHERE memberno = 1;

commit;
