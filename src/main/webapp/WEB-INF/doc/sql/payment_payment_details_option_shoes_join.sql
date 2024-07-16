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

CREATE TABLE PAYMENT_DETAILS(
		PAYMENT_DETAILS_NO            		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		PAYMENT_AMOUNT                      NUMBER(9)		 NOT NULL,
		OPTIONNO                      		NUMBER(9)		 NOT NULL,
		PAYMENTNO                     		NUMBER(9)		 NULL ,
  FOREIGN KEY (OPTIONNO) REFERENCES OPTIONS (OPTIONNO),
  FOREIGN KEY (PAYMENTNO) REFERENCES PAYMENT (PAYMENTNO)
);

CREATE TABLE OPTIONs(
		OPTIONNO                      		NUMBER(9)		 NOT NULL 		 PRIMARY KEY,
		SIZE                          		NUMBER(4)		 NOT NULL,
		AMOUNT                        		NUMBER(9)		 NOT NULL,
		COLOR                         		VARCHAR2(30)		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO)
);

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

CREATE TABLE SHOES_FILE(
		SHOES_FILE_NO                 		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SIZES                          		NUMBER		 NOT NULL,
		EX                            		VARCHAR2(100)		 NOT NULL,
		SRC                           		VARCHAR2(100)		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NULL ,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO)
);
-----------------------------------------------------------------------------------------------------------------------

    SELECT DISTINCT
      m.memberno,
      m.id AS memberid,
      m.nickname,
      p.paymentno
    FROM 
      member m 
      INNER JOIN payment p ON m.memberno = p.memberno

      WHERE UPPER(m.id) LIKE '%' || UPPER('') || '%' OR UPPER(m.nickname) LIKE '%' || UPPER('') || '%'

    ORDER BY 
      p.rdate DESC;
      



WITH PaymentRank AS (
  SELECT
    m.memberno,
    m.id AS memberid,
    m.nickname,
    p.paymentno,
    p.rdate,
    ROW_NUMBER() OVER (PARTITION BY m.memberno ORDER BY p.rdate DESC) AS rn
  FROM 
    member m 
    INNER JOIN payment p ON m.memberno = p.memberno
  WHERE 
    UPPER(m.id) LIKE '%' || UPPER('') || '%' 
    OR UPPER(m.nickname) LIKE '%' || UPPER('') || '%'
)
SELECT
  memberno,
  memberid,
  nickname,
  paymentno
FROM
  PaymentRank
WHERE
  rn = 1
ORDER BY 
  rdate DESC;



	  SELECT
      m.memberno,
      p.paymentno,
      P.rdate,
      p.total_price,
      p.cs_status,
      p.payment_status,
      pd.payment_details_no,
      pd.payment_amount,
      o.optionno,
      o.sizes,
      o.amount,
      o.color,
      s.shoesno,
      s.title,
      s.brand,
      s.rating,
      s.price,
      s.discount,
      s.contents,
      s.visible,
      sf.shoes_file_no,
      sf.name,
      sf.sizes as shoes_file_sizes,
      sf.ex,
      sf.src
    FROM
      member m
      INNER JOIN payment p ON p.memberno = m.memberno
      INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
      INNER JOIN options o ON pd.optionno = o.optionno
      INNER JOIN shoes s ON o.shoesno = s.shoesno
      LEFT JOIN shoes_file sf ON s.shoesno = sf.shoesno 
    WHERE
       payment_status = '결제완료'
    ORDER BY
      pd.payment_details_no;
      


INSERT INTO payment(paymentno, rdate, status, payment_status, total_price, delivery, total_payment, memberno)
values(payment_seq.nextval, sysdate, '상품준비중', '입금전', 0, 2500, total_price + delivery, 1);

UPDATE options
SET amount = 0
WHERE optionsno = 1;

INSERT INTO payment_details(payment_details_no, payment_amount, optionno, paymentno)
VALUES (payment_details_seq.nextval, 1, 1,1);


--list1
SELECT * FROM (
  SELECT DISTINCT
    m.memberno,
    p.paymentno,
    p.rdate,
    p.status,
    p.payment_status,
    p.cs_status,
    p.total_price,
    p.delivery,
    p.total_payment,
    ROWNUM AS rn
  FROM
    member m
    INNER JOIN payment p ON m.memberno = p.memberno
    INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
  WHERE
    m.memberno = 1 AND p.cs_status IS NULL
  --  AND p.rdate BETWEEN #{startDate} AND #{endDate}
  ORDER BY
    p.rdate DESC
)
WHERE rn BETWEEN 1 AND 3

--count

SELECT DISTINCT
COUNT(*)

FROM
member m
INNER JOIN payment p ON m.memberno = p.memberno
INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
WHERE
m.memberno = 1 AND p.cs_status IS NULL
ORDER BY
p.rdate DESC










--payment count

SELECT
  COUNT(DISTINCT m.memberno) AS cnt
FROM
  member m
  INNER JOIN payment p ON p.memberno = m.memberno
  INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
  INNER JOIN options o ON pd.optionno = o.optionno
  INNER JOIN shoes s ON o.shoesno = s.shoesno
  LEFT JOIN shoes_file sf ON s.shoesno = sf.shoesno;

SELECT
  COUNT(DISTINCT p.paymentno) AS cnt
FROM
  member m
  INNER JOIN payment p ON p.memberno = m.memberno
  INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
  INNER JOIN options o ON pd.optionno = o.optionno
  INNER JOIN shoes s ON o.shoesno = s.shoesno
  LEFT JOIN shoes_file sf ON s.shoesno = sf.shoesno;


--주문상세
		SELECT
		m.memberno,
		p.paymentno,
		p.rdate,
		p.payment_status,
		p.status,
		p.cs_status,
		p.total_price,
		p.delivery,
		p.total_payment,
		pd.payment_details_no,
		pd.payment_amount,
		o.optionno,
		o.sizes,
		o.amount,
		o.color,
		s.shoesno,
		s.title,
		s.brand,
		s.rating,
		s.price,
		s.discount,
		s.contents,
		s.visible,
		sf.shoes_file_no,
		sf.name,
		sf.sizes as shoes_file_sizes,
		sf.ex,
		sf.src
		FROM
		member m
		INNER JOIN payment p ON p.memberno = m.memberno
		INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
		INNER JOIN options o ON pd.optionno = o.optionno
		INNER JOIN shoes s ON o.shoesno = s.shoesno
		LEFT JOIN shoes_file sf ON s.shoesno = sf.shoesno
		WHERE
		m.memberno = 1
          AND p.payment_status IN ('입금완료(수동)');
        



  SELECT *
  FROM (
    WITH PaymentRank AS (
      SELECT
        m.memberno,
        m.id AS memberid,
        m.nickname,
        p.paymentno,
        p.rdate,
        ROW_NUMBER() OVER (PARTITION BY m.memberno ORDER BY p.rdate DESC) AS rn
      FROM
        member m
        INNER JOIN payment p ON m.memberno = p.memberno
      WHERE
        p.payment_status IN '입금완료(수동)')
    SELECT
      memberno,
      memberid,
      nickname,
      paymentno,
      rdate,
      ROW_NUMBER() OVER (ORDER BY rdate DESC) AS rownum_
    FROM
      PaymentRank
    WHERE
      rn = 1
  )
  WHERE rownum_ BETWEEN 1 AND 3
  ORDER BY rdate DESC


