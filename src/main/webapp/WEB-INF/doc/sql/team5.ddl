DROP TABLE �ѽ¿� CASCADE CONSTRAINTS;
DROP TABLE LIKE CASCADE CONSTRAINTS;
DROP TABLE NOTICE_FILE CASCADE CONSTRAINTS;
DROP TABLE REVIEW_FILE CASCADE CONSTRAINTS;
DROP TABLE SHOES_FILE CASCADE CONSTRAINTS;
DROP TABLE OTHER_INQUIRY CASCADE CONSTRAINTS;
DROP TABLE PAYMENT_INQUIRY CASCADE CONSTRAINTS;
DROP TABLE REPORT CASCADE CONSTRAINTS;
DROP TABLE REPORT_TYPE CASCADE CONSTRAINTS;
DROP TABLE SHOES_INQUIRY CASCADE CONSTRAINTS;
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP TABLE �ڼ�ȣ CASCADE CONSTRAINTS;
DROP TABLE TABLE_11 CASCADE CONSTRAINTS;
DROP TABLE ������ CASCADE CONSTRAINTS;
DROP TABLE REVIEW CASCADE CONSTRAINTS;
DROP TABLE PAYMENT_DETAILS CASCADE CONSTRAINTS;
DROP TABLE PAYMENT CASCADE CONSTRAINTS;
DROP TABLE LOGIN_HISTORY CASCADE CONSTRAINTS;
DROP TABLE BASKET CASCADE CONSTRAINTS;
DROP TABLE OPTION CASCADE CONSTRAINTS;
DROP TABLE SHOES CASCADE CONSTRAINTS;
DROP TABLE CATEGORY CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ��� */
/**********************************/
CREATE TABLE MEMBER(
		MEMBERNO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		ID                            		VARCHAR2(100)		 NOT NULL,
		PW                            		VARCHAR2(100)		 NOT NULL,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		NICKNAME                      		VARCHAR2(100)		 NOT NULL,
		PHONE                         		VARCHAR2(13)		 NOT NULL,
		EMAIL                         		VARCHAR2(50)		 NOT NULL,
		THUMB                         		VARCHAR2(100)		 NULL ,
		ADDR1                         		VARCHAR2(100)		 NULL ,
		ADDR2                         		VARCHAR2(100)		 NULL ,
		ZIPCODE                       		NUMBER(5)		 NULL ,
		DATE                          		DATE		 NULL ,
		RDATE                         		DATE		 NOT NULL,
		POINT                         		NUMBER(9)		 DEFAULT 0		 NULL ,
		GENDER                        		VARCHAR2(20)		 NOT NULL,
		GRADE                         		NUMBER(2)		 NULL ,
		ROLE                          		VARCHAR2(30)		 DEFAULT USER		 NOT NULL
);

COMMENT ON TABLE MEMBER is '���';
COMMENT ON COLUMN MEMBER.MEMBERNO is '��� ��ȣ';
COMMENT ON COLUMN MEMBER.ID is '��� ���̵�';
COMMENT ON COLUMN MEMBER.PW is '��� ��й�ȣ';
COMMENT ON COLUMN MEMBER.NAME is '��� �̸�';
COMMENT ON COLUMN MEMBER.NICKNAME is '��� �г���';
COMMENT ON COLUMN MEMBER.PHONE is '��� ��ȭ��ȣ';
COMMENT ON COLUMN MEMBER.EMAIL is '��� �̸���';
COMMENT ON COLUMN MEMBER.THUMB is '��� ������';
COMMENT ON COLUMN MEMBER.ADDR1 is '��� �ּ�1';
COMMENT ON COLUMN MEMBER.ADDR2 is '��� ���ּ�';
COMMENT ON COLUMN MEMBER.ZIPCODE is '��� �����ȣ';
COMMENT ON COLUMN MEMBER.DATE is '��� �������';
COMMENT ON COLUMN MEMBER.RDATE is '��� ������';
COMMENT ON COLUMN MEMBER.POINT is '��� ����Ʈ';
COMMENT ON COLUMN MEMBER.GENDER is '��� ����';
COMMENT ON COLUMN MEMBER.GRADE is '��� ���';
COMMENT ON COLUMN MEMBER.ROLE is '��� ����';


/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE CATEGORY(
		CATEGORYNO                    		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SUBNAME                       		VARCHAR2(100)		 NOT NULL,
		SEQ                           		NUMBER(9)		 NULL ,
		CNT                           		NUMBER(9)		 NOT NULL,
		PARENTNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (PARENTNO) REFERENCES CATEGORY (CATEGORYNO)
);

COMMENT ON TABLE CATEGORY is 'ī�װ�';
COMMENT ON COLUMN CATEGORY.CATEGORYNO is 'ī�װ� ��ȣ';
COMMENT ON COLUMN CATEGORY.NAME is 'ī�װ� �̸�';
COMMENT ON COLUMN CATEGORY.SUBNAME is 'ī�װ� �����';
COMMENT ON COLUMN CATEGORY.SEQ is 'ī�װ� ��¼���';
COMMENT ON COLUMN CATEGORY.CNT is 'ī�װ� �׸��';
COMMENT ON COLUMN CATEGORY.PARENTNO is 'ī�װ� �θ��ȣ';


/**********************************/
/* Table Name: �Ź� */
/**********************************/
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

COMMENT ON TABLE SHOES is '�Ź�';
COMMENT ON COLUMN SHOES.SHOESNO is '�Ź� ��ȣ';
COMMENT ON COLUMN SHOES.CATEGORYNO is 'ī�װ� ��ȣ';
COMMENT ON COLUMN SHOES.MEMBERNO is '���� ��ȣ';
COMMENT ON COLUMN SHOES.TITLE is '�Ź� ����';
COMMENT ON COLUMN SHOES.BRAND is '�Ź� �귣���';
COMMENT ON COLUMN SHOES.RATING is '�Ź� ����';
COMMENT ON COLUMN SHOES.PRICE is '�Ź� ����';
COMMENT ON COLUMN SHOES.DISCOUNT is '�Ź� ������';
COMMENT ON COLUMN SHOES.CONTENTS is '�Ź� ����';
COMMENT ON COLUMN SHOES.VISIBLE is '�Ź� �Ǹ� ����';


/**********************************/
/* Table Name: �Ź߿ɼ� */
/**********************************/
CREATE TABLE OPTION(
		OPTIONNO                      		NUMBER(9)		 NULL 		 PRIMARY KEY,
		SIZE                          		NUMBER(4)		 NOT NULL,
		AMOUNT                        		NUMBER(9)		 NOT NULL,
		COLOR                         		VARCHAR2(30)		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO)
);

COMMENT ON TABLE OPTION is '�Ź߿ɼ�';
COMMENT ON COLUMN OPTION.OPTIONNO is '�ɼ� ��ȣ';
COMMENT ON COLUMN OPTION.SIZE is '�Ź� ������';
COMMENT ON COLUMN OPTION.AMOUNT is '�Ź� ���';
COMMENT ON COLUMN OPTION.COLOR is '�Ź� ����';
COMMENT ON COLUMN OPTION.SHOESNO is '�Ź� ��ȣ';


/**********************************/
/* Table Name: ��ٱ��� */
/**********************************/
CREATE TABLE BASKET(
		BASKETNO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		AMOUNT                        		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
		OPTIONNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (OPTIONNO) REFERENCES OPTION (OPTIONNO)
);

COMMENT ON TABLE BASKET is '��ٱ���';
COMMENT ON COLUMN BASKET.BASKETNO is '��ٱ��� ��ȣ';
COMMENT ON COLUMN BASKET.AMOUNT is '�Ź� ����';
COMMENT ON COLUMN BASKET.MEMBERNO is '��� ��ȣ';
COMMENT ON COLUMN BASKET.OPTIONNO is '�ɼ� ��ȣ';


/**********************************/
/* Table Name: �α��γ��� */
/**********************************/
CREATE TABLE LOGIN_HISTORY(
		LOGIN_HISTORY_NO              		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IP                            		VARCHAR2(15)		 NOT NULL,
		RDATE                         		DATE		 NULL ,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE LOGIN_HISTORY is '�α��γ���';
COMMENT ON COLUMN LOGIN_HISTORY.LOGIN_HISTORY_NO is '�α��γ��� ��ȣ';
COMMENT ON COLUMN LOGIN_HISTORY.IP is '�α��γ��� ������';
COMMENT ON COLUMN LOGIN_HISTORY.RDATE is '�α��γ��� ������';
COMMENT ON COLUMN LOGIN_HISTORY.MEMBERNO is '��� ��ȣ';


/**********************************/
/* Table Name: �ֹ� */
/**********************************/
CREATE TABLE PAYMENT(
		PAYMENTNO                     		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		RDATE                         		DATE		 NOT NULL,
		STATUS                        		VARCHAR2(50)		 NOT NULL,
		PAYMANT_STATUS                		VARCHAR2(50)		 NOT NULL,
		CSSTATUS                      		VARCHAR2(50)		 NULL ,
		PRICE                         		NUMBER		 NOT NULL,
		TOTAL_PRICE                   		NUMBER		 NULL ,
		DELIVERY                      		NUMBER		 NOT NULL,
		TOTAL_PAYMENT                 		NUMBER		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE PAYMENT is '�ֹ�';
COMMENT ON COLUMN PAYMENT.PAYMENTNO is '�ֹ� ��ȣ';
COMMENT ON COLUMN PAYMENT.RDATE is '�ֹ� ����';
COMMENT ON COLUMN PAYMENT.STATUS is '�ֹ� ����';
COMMENT ON COLUMN PAYMENT.PAYMANT_STATUS is '���� ����';
COMMENT ON COLUMN PAYMENT.CSSTATUS is 'CS ����';
COMMENT ON COLUMN PAYMENT.PRICE is '��ǰ�ݾ�';
COMMENT ON COLUMN PAYMENT.TOTAL_PRICE is '�� ��ǰ�ݾ�';
COMMENT ON COLUMN PAYMENT.DELIVERY is '��ۺ�';
COMMENT ON COLUMN PAYMENT.TOTAL_PAYMENT is '�� �ֹ��ݾ�';
COMMENT ON COLUMN PAYMENT.MEMBERNO is '��� ��ȣ';


/**********************************/
/* Table Name: �ֹ��� */
/**********************************/
CREATE TABLE PAYMENT_DETAILS(
		PAYMENT_DETAILS_NO            		VARCHAR2(255)		 NOT NULL		 PRIMARY KEY,
		PAYMOUNT                      		NUMBER(9)		 NOT NULL,
		NO                            		NUMBER(9)		 NOT NULL,
		OPTIONNO                      		NUMBER(9)		 NOT NULL,
		PAYMENTNO                     		NUMBER(9)		 NULL ,
  FOREIGN KEY (OPTIONNO) REFERENCES OPTION (OPTIONNO),
  FOREIGN KEY (PAYMENTNO) REFERENCES PAYMENT (PAYMENTNO)
);

COMMENT ON TABLE PAYMENT_DETAILS is '�ֹ���';
COMMENT ON COLUMN PAYMENT_DETAILS.PAYMENT_DETAILS_NO is '�ֹ� �󼼹�ȣ';
COMMENT ON COLUMN PAYMENT_DETAILS.PAYMOUNT is '�ֹ� ����';
COMMENT ON COLUMN PAYMENT_DETAILS.NO is '�ֹ� ��ȣ';
COMMENT ON COLUMN PAYMENT_DETAILS.OPTIONNO is '�ɼ� ��ȣ';
COMMENT ON COLUMN PAYMENT_DETAILS.PAYMENTNO is '�ֹ� ��ȣ';


/**********************************/
/* Table Name: �ı� */
/**********************************/
CREATE TABLE REVIEW(
		REVIEWNO                      		VARCHAR2(255)		 NOT NULL		 PRIMARY KEY,
		COMMENT                       		VARCHAR2(1000)		 NOT NULL,
		GRADE                         		NUMBER		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE REVIEW is '�ı�';
COMMENT ON COLUMN REVIEW.REVIEWNO is '�ı� ��ȣ';
COMMENT ON COLUMN REVIEW.COMMENT is '�ı� ����';
COMMENT ON COLUMN REVIEW.GRADE is '�ı� ����';
COMMENT ON COLUMN REVIEW.RDATE is '�ı� �ۼ���';
COMMENT ON COLUMN REVIEW.SHOESNO is '�Ź� ��ȣ';
COMMENT ON COLUMN REVIEW.MEMBERNO is '��� ��ȣ';


/**********************************/
/* Table Name: ������ */
/**********************************/
CREATE TABLE ������(

);

COMMENT ON TABLE ������ is '������';


/**********************************/
/* Table Name: Table11 */
/**********************************/
CREATE TABLE TABLE_11(

);

COMMENT ON TABLE TABLE_11 is 'Table11';


/**********************************/
/* Table Name: �ڼ�ȣ */
/**********************************/
CREATE TABLE �ڼ�ȣ(

);

COMMENT ON TABLE �ڼ�ȣ is '�ڼ�ȣ';


/**********************************/
/* Table Name: �������� */
/**********************************/
CREATE TABLE NOTICE(
		NOTICENO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		COMMENT                       		VARCHAR2(1000)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		FILE                          		VARCHAR2(100)		 NOT NULL,
		SIZE                          		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE NOTICE is '��������';
COMMENT ON COLUMN NOTICE.NOTICENO is '�������� ��ȣ';
COMMENT ON COLUMN NOTICE.COMMENT is '�������� ����';
COMMENT ON COLUMN NOTICE.RDATE is '�������� �ۼ���';
COMMENT ON COLUMN NOTICE.FILE is '÷�� ����';
COMMENT ON COLUMN NOTICE.SIZE is '÷�� ���� ũ��';
COMMENT ON COLUMN NOTICE.MEMBERNO is '��� ��ȣ';


/**********************************/
/* Table Name: �Ź� ���� */
/**********************************/
CREATE TABLE SHOES_INQUIRY(
		SHOES_INQUIRY_NO              		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		COMMENT                       		VARCHAR2(1000)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		ANSWER_VISIBLE                		CHAR(1)		 NOT NULL,
		ANSWER_COMMENT                		VARCHAR2(1000)		 NOT NULL,
		OPTIONNO                      		NUMBER(9)		 NULL ,
		MEMBERNO                      		NUMBER(9)		 NULL ,
  FOREIGN KEY (OPTIONNO) REFERENCES OPTION (OPTIONNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE SHOES_INQUIRY is '�Ź� ����';
COMMENT ON COLUMN SHOES_INQUIRY.SHOES_INQUIRY_NO is '�Ź� ���� ��ȣ';
COMMENT ON COLUMN SHOES_INQUIRY.COMMENT is '���� ����';
COMMENT ON COLUMN SHOES_INQUIRY.RDATE is '���� �ۼ���';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_VISIBLE is '�亯 ����';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_COMMENT is '�亯 ����';
COMMENT ON COLUMN SHOES_INQUIRY.OPTIONNO is '�ɼ� ��ȣ';
COMMENT ON COLUMN SHOES_INQUIRY.MEMBERNO is '��� ��ȣ';


/**********************************/
/* Table Name: �Ű� ���� */
/**********************************/
CREATE TABLE REPORT_TYPE(
		TYPENO                        		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		TYPE                          		VARCHAR2(100)		 NOT NULL
);

COMMENT ON TABLE REPORT_TYPE is '�Ű� ����';
COMMENT ON COLUMN REPORT_TYPE.TYPENO is '�Ű� ������ȣ';
COMMENT ON COLUMN REPORT_TYPE.TYPE is '�Ű� ����';


/**********************************/
/* Table Name: �Ű� */
/**********************************/
CREATE TABLE REPORT(
		REPORTNO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		COMMENT                       		VARCHAR2(1000)		 NOT NULL,
		TYPENO                        		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
		REVIEWNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO),
  FOREIGN KEY (TYPENO) REFERENCES REPORT_TYPE (TYPENO)
);

COMMENT ON TABLE REPORT is '�Ű�';
COMMENT ON COLUMN REPORT.REPORTNO is '�Ű� ��ȣ';
COMMENT ON COLUMN REPORT.COMMENT is '�Ű� ����';
COMMENT ON COLUMN REPORT.TYPENO is '�Ű� ������ȣ ';
COMMENT ON COLUMN REPORT.MEMBERNO is '��� ��ȣ';
COMMENT ON COLUMN REPORT.REVIEWNO is '�ı� ��ȣ';


/**********************************/
/* Table Name: �ֹ� ���� */
/**********************************/
CREATE TABLE PAYMENT_INQUIRY(
		PAYMENT_INQUIRY_NO            		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		COMMENT                       		VARCHAR2(1000)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		ANSWER_VISIBLE                		CHAR(1)		 NOT NULL,
		ANSWER_COMMENT                		VARCHAR2(1000)		 NULL ,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
		PAYMENTNO                     		NUMBER(9)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (PAYMENTNO) REFERENCES PAYMENT (PAYMENTNO)
);

COMMENT ON TABLE PAYMENT_INQUIRY is '�ֹ� ����';
COMMENT ON COLUMN PAYMENT_INQUIRY.PAYMENT_INQUIRY_NO is '�ֹ� ���� ��ȣ';
COMMENT ON COLUMN PAYMENT_INQUIRY.COMMENT is '���� ����';
COMMENT ON COLUMN PAYMENT_INQUIRY.RDATE is '���� �ۼ���';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_VISIBLE is '�亯 ����';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_COMMENT is '�亯 ����';
COMMENT ON COLUMN PAYMENT_INQUIRY.MEMBERNO is '��� ��ȣ';
COMMENT ON COLUMN PAYMENT_INQUIRY.PAYMENTNO is '�ֹ� ��ȣ';


/**********************************/
/* Table Name: ��Ÿ ���� */
/**********************************/
CREATE TABLE OTHER_INQUIRY(
		ORDER_INQUIRY_NO              		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		COMMENT                       		VARCHAR2(1000)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		ANSWER_VISIBLE                		CHAR(1)		 NOT NULL,
		ANSWER_COMMENT                		VARCHAR2(1000)		 NULL ,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE OTHER_INQUIRY is '��Ÿ ����';
COMMENT ON COLUMN OTHER_INQUIRY.ORDER_INQUIRY_NO is '��Ÿ ���� ��ȣ';
COMMENT ON COLUMN OTHER_INQUIRY.COMMENT is '���� ����';
COMMENT ON COLUMN OTHER_INQUIRY.RDATE is '���� �ۼ���';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_VISIBLE is '�亯 ����';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_COMMENT is '�亯 ����';
COMMENT ON COLUMN OTHER_INQUIRY.MEMBERNO is '��� ��ȣ';


/**********************************/
/* Table Name: �Ź� ÷������ */
/**********************************/
CREATE TABLE SHOES_FILE(
		SHOES_FILE_NO                 		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SIZE                          		NUMBER		 NOT NULL,
		EX                            		VARCHAR2(100)		 NOT NULL,
		SRC                           		VARCHAR2(100)		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NULL ,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO)
);

COMMENT ON TABLE SHOES_FILE is '�Ź� ÷������';
COMMENT ON COLUMN SHOES_FILE.SHOES_FILE_NO is '���� ��ȣ';
COMMENT ON COLUMN SHOES_FILE.NAME is '÷�����ϸ�';
COMMENT ON COLUMN SHOES_FILE.SIZE is '÷������ ũ��';
COMMENT ON COLUMN SHOES_FILE.EX is 'Ȯ���ڸ�';
COMMENT ON COLUMN SHOES_FILE.SRC is '÷������ �ּ�';
COMMENT ON COLUMN SHOES_FILE.SHOESNO is '�Ź� ��ȣ';


/**********************************/
/* Table Name: �ı� ÷������ */
/**********************************/
CREATE TABLE REVIEW_FILE(
		REVIEW_FILE_NO                		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SIZE                          		NUMBER		 NOT NULL,
		EX                            		VARCHAR2(100)		 NOT NULL,
		SRC                           		VARCHAR2(100)		 NOT NULL,
		REVIEWNO                      		VARCHAR2(255)		 NULL ,
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
);

COMMENT ON TABLE REVIEW_FILE is '�ı� ÷������';
COMMENT ON COLUMN REVIEW_FILE.REVIEW_FILE_NO is '���� ��ȣ';
COMMENT ON COLUMN REVIEW_FILE.NAME is '÷�����ϸ�';
COMMENT ON COLUMN REVIEW_FILE.SIZE is '÷������ ũ��';
COMMENT ON COLUMN REVIEW_FILE.EX is 'Ȯ���ڸ�';
COMMENT ON COLUMN REVIEW_FILE.SRC is '÷������ �ּ�';
COMMENT ON COLUMN REVIEW_FILE.REVIEWNO is '�ı� ��ȣ';


/**********************************/
/* Table Name: ���� ÷������ */
/**********************************/
CREATE TABLE NOTICE_FILE(
		NOTICE_FILE_NO                		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SIZE                          		NUMBER		 NOT NULL,
		EX                            		VARCHAR2(100)		 NOT NULL,
		R_NO                          		VARCHAR2(255)		 NULL ,
		SRC                           		VARCHAR2(100)		 NOT NULL,
		NOTICENO                      		NUMBER(9)		 NULL ,
  FOREIGN KEY (NOTICENO) REFERENCES NOTICE (NOTICENO)
);

COMMENT ON TABLE NOTICE_FILE is '���� ÷������';
COMMENT ON COLUMN NOTICE_FILE.NOTICE_FILE_NO is '���� ��ȣ';
COMMENT ON COLUMN NOTICE_FILE.NAME is '÷�����ϸ�';
COMMENT ON COLUMN NOTICE_FILE.SIZE is '÷������ ũ��';
COMMENT ON COLUMN NOTICE_FILE.EX is 'Ȯ���ڸ�';
COMMENT ON COLUMN NOTICE_FILE.R_NO is '�ı� ��ȣ';
COMMENT ON COLUMN NOTICE_FILE.SRC is '÷������ �ּ�';
COMMENT ON COLUMN NOTICE_FILE.NOTICENO is '�������� ��ȣ';


/**********************************/
/* Table Name: ���ƿ� */
/**********************************/
CREATE TABLE LIKE(
		LIKENO                        		NUMBER(9)		 NULL 		 PRIMARY KEY,
		LIKE                          		VARCHAR2(2)		 NOT NULL,
		REVIEWNO                      		VARCHAR2(255)		 NULL ,
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
);

COMMENT ON TABLE LIKE is '���ƿ�';
COMMENT ON COLUMN LIKE.LIKENO is '���ƿ� ��ȣ';
COMMENT ON COLUMN LIKE.LIKE is '���ƿ�';
COMMENT ON COLUMN LIKE.REVIEWNO is '�ı� ��ȣ';


/**********************************/
/* Table Name: �ѽ¿� */
/**********************************/
CREATE TABLE �ѽ¿�(

);

COMMENT ON TABLE �ѽ¿� is '�ѽ¿�';


