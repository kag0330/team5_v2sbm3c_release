DROP TABLE 한승원 CASCADE CONSTRAINTS;
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
DROP TABLE 박성호 CASCADE CONSTRAINTS;
DROP TABLE TABLE_11 CASCADE CONSTRAINTS;
DROP TABLE 김현기 CASCADE CONSTRAINTS;
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
/* Table Name: 멤버 */
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

COMMENT ON TABLE MEMBER is '멤버';
COMMENT ON COLUMN MEMBER.MEMBERNO is '멤버 번호';
COMMENT ON COLUMN MEMBER.ID is '멤버 아이디';
COMMENT ON COLUMN MEMBER.PW is '멤버 비밀번호';
COMMENT ON COLUMN MEMBER.NAME is '멤버 이름';
COMMENT ON COLUMN MEMBER.NICKNAME is '멤버 닉네임';
COMMENT ON COLUMN MEMBER.PHONE is '멤버 전화번호';
COMMENT ON COLUMN MEMBER.EMAIL is '멤버 이메일';
COMMENT ON COLUMN MEMBER.THUMB is '멤버 섬네일';
COMMENT ON COLUMN MEMBER.ADDR1 is '멤버 주소1';
COMMENT ON COLUMN MEMBER.ADDR2 is '멤버 상세주소';
COMMENT ON COLUMN MEMBER.ZIPCODE is '멤버 우편번호';
COMMENT ON COLUMN MEMBER.DATE is '멤버 생년월일';
COMMENT ON COLUMN MEMBER.RDATE is '멤버 가입일';
COMMENT ON COLUMN MEMBER.POINT is '멤버 포인트';
COMMENT ON COLUMN MEMBER.GENDER is '멤버 성별';
COMMENT ON COLUMN MEMBER.GRADE is '멤버 등급';
COMMENT ON COLUMN MEMBER.ROLE is '멤버 권한';


/**********************************/
/* Table Name: 카테고리 */
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

COMMENT ON TABLE CATEGORY is '카테고리';
COMMENT ON COLUMN CATEGORY.CATEGORYNO is '카테고리 번호';
COMMENT ON COLUMN CATEGORY.NAME is '카테고리 이름';
COMMENT ON COLUMN CATEGORY.SUBNAME is '카테고리 서브명';
COMMENT ON COLUMN CATEGORY.SEQ is '카테고리 출력순서';
COMMENT ON COLUMN CATEGORY.CNT is '카테고리 항목수';
COMMENT ON COLUMN CATEGORY.PARENTNO is '카테고리 부모번호';


/**********************************/
/* Table Name: 신발 */
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


/**********************************/
/* Table Name: 신발옵션 */
/**********************************/
CREATE TABLE OPTION(
		OPTIONNO                      		NUMBER(9)		 NULL 		 PRIMARY KEY,
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


/**********************************/
/* Table Name: 로그인내역 */
/**********************************/
CREATE TABLE LOGIN_HISTORY(
		LOGIN_HISTORY_NO              		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IP                            		VARCHAR2(15)		 NOT NULL,
		RDATE                         		DATE		 NULL ,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE LOGIN_HISTORY is '로그인내역';
COMMENT ON COLUMN LOGIN_HISTORY.LOGIN_HISTORY_NO is '로그인내역 번호';
COMMENT ON COLUMN LOGIN_HISTORY.IP is '로그인내역 아이피';
COMMENT ON COLUMN LOGIN_HISTORY.RDATE is '로그인내역 접속일';
COMMENT ON COLUMN LOGIN_HISTORY.MEMBERNO is '멤버 번호';


/**********************************/
/* Table Name: 주문 */
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

COMMENT ON TABLE PAYMENT is '주문';
COMMENT ON COLUMN PAYMENT.PAYMENTNO is '주문 번호';
COMMENT ON COLUMN PAYMENT.RDATE is '주문 일자';
COMMENT ON COLUMN PAYMENT.STATUS is '주문 상태';
COMMENT ON COLUMN PAYMENT.PAYMANT_STATUS is '결제 상태';
COMMENT ON COLUMN PAYMENT.CSSTATUS is 'CS 상태';
COMMENT ON COLUMN PAYMENT.PRICE is '상품금액';
COMMENT ON COLUMN PAYMENT.TOTAL_PRICE is '총 상품금액';
COMMENT ON COLUMN PAYMENT.DELIVERY is '배송비';
COMMENT ON COLUMN PAYMENT.TOTAL_PAYMENT is '총 주문금액';
COMMENT ON COLUMN PAYMENT.MEMBERNO is '멤버 번호';


/**********************************/
/* Table Name: 주문상세 */
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

COMMENT ON TABLE PAYMENT_DETAILS is '주문상세';
COMMENT ON COLUMN PAYMENT_DETAILS.PAYMENT_DETAILS_NO is '주문 상세번호';
COMMENT ON COLUMN PAYMENT_DETAILS.PAYMOUNT is '주문 수량';
COMMENT ON COLUMN PAYMENT_DETAILS.NO is '주문 번호';
COMMENT ON COLUMN PAYMENT_DETAILS.OPTIONNO is '옵션 번호';
COMMENT ON COLUMN PAYMENT_DETAILS.PAYMENTNO is '주문 번호';


/**********************************/
/* Table Name: 후기 */
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

COMMENT ON TABLE REVIEW is '후기';
COMMENT ON COLUMN REVIEW.REVIEWNO is '후기 번호';
COMMENT ON COLUMN REVIEW.COMMENT is '후기 내용';
COMMENT ON COLUMN REVIEW.GRADE is '후기 점수';
COMMENT ON COLUMN REVIEW.RDATE is '후기 작성일';
COMMENT ON COLUMN REVIEW.SHOESNO is '신발 번호';
COMMENT ON COLUMN REVIEW.MEMBERNO is '멤버 번호';


/**********************************/
/* Table Name: 김현기 */
/**********************************/
CREATE TABLE 김현기(

);

COMMENT ON TABLE 김현기 is '김현기';


/**********************************/
/* Table Name: Table11 */
/**********************************/
CREATE TABLE TABLE_11(

);

COMMENT ON TABLE TABLE_11 is 'Table11';


/**********************************/
/* Table Name: 박성호 */
/**********************************/
CREATE TABLE 박성호(

);

COMMENT ON TABLE 박성호 is '박성호';


/**********************************/
/* Table Name: 공지사항 */
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

COMMENT ON TABLE NOTICE is '공지사항';
COMMENT ON COLUMN NOTICE.NOTICENO is '공지사항 번호';
COMMENT ON COLUMN NOTICE.COMMENT is '공지사항 내용';
COMMENT ON COLUMN NOTICE.RDATE is '공지사항 작성일';
COMMENT ON COLUMN NOTICE.FILE is '첨부 파일';
COMMENT ON COLUMN NOTICE.SIZE is '첨부 파일 크기';
COMMENT ON COLUMN NOTICE.MEMBERNO is '멤버 번호';


/**********************************/
/* Table Name: 신발 문의 */
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

COMMENT ON TABLE SHOES_INQUIRY is '신발 문의';
COMMENT ON COLUMN SHOES_INQUIRY.SHOES_INQUIRY_NO is '신발 문의 번호';
COMMENT ON COLUMN SHOES_INQUIRY.COMMENT is '문의 내용';
COMMENT ON COLUMN SHOES_INQUIRY.RDATE is '문의 작성일';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_COMMENT is '답변 내용';
COMMENT ON COLUMN SHOES_INQUIRY.OPTIONNO is '옵션 번호';
COMMENT ON COLUMN SHOES_INQUIRY.MEMBERNO is '멤버 번호';


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


/**********************************/
/* Table Name: 신고 */
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

COMMENT ON TABLE REPORT is '신고';
COMMENT ON COLUMN REPORT.REPORTNO is '신고 번호';
COMMENT ON COLUMN REPORT.COMMENT is '신고 내용';
COMMENT ON COLUMN REPORT.TYPENO is '신고 유형번호 ';
COMMENT ON COLUMN REPORT.MEMBERNO is '멤버 번호';
COMMENT ON COLUMN REPORT.REVIEWNO is '후기 번호';


/**********************************/
/* Table Name: 주문 문의 */
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

COMMENT ON TABLE PAYMENT_INQUIRY is '주문 문의';
COMMENT ON COLUMN PAYMENT_INQUIRY.PAYMENT_INQUIRY_NO is '주문 문의 번호';
COMMENT ON COLUMN PAYMENT_INQUIRY.COMMENT is '문의 내용';
COMMENT ON COLUMN PAYMENT_INQUIRY.RDATE is '문의 작성일';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_COMMENT is '답변 내용';
COMMENT ON COLUMN PAYMENT_INQUIRY.MEMBERNO is '멤버 번호';
COMMENT ON COLUMN PAYMENT_INQUIRY.PAYMENTNO is '주문 번호';


/**********************************/
/* Table Name: 기타 문의 */
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

COMMENT ON TABLE OTHER_INQUIRY is '기타 문의';
COMMENT ON COLUMN OTHER_INQUIRY.ORDER_INQUIRY_NO is '기타 문의 번호';
COMMENT ON COLUMN OTHER_INQUIRY.COMMENT is '문의 내용';
COMMENT ON COLUMN OTHER_INQUIRY.RDATE is '문의 작성일';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_COMMENT is '답변 내용';
COMMENT ON COLUMN OTHER_INQUIRY.MEMBERNO is '멤버 번호';


/**********************************/
/* Table Name: 신발 첨부파일 */
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

COMMENT ON TABLE SHOES_FILE is '신발 첨부파일';
COMMENT ON COLUMN SHOES_FILE.SHOES_FILE_NO is '파일 번호';
COMMENT ON COLUMN SHOES_FILE.NAME is '첨부파일명';
COMMENT ON COLUMN SHOES_FILE.SIZE is '첨부파일 크기';
COMMENT ON COLUMN SHOES_FILE.EX is '확장자명';
COMMENT ON COLUMN SHOES_FILE.SRC is '첨부파일 주소';
COMMENT ON COLUMN SHOES_FILE.SHOESNO is '신발 번호';


/**********************************/
/* Table Name: 후기 첨부파일 */
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

COMMENT ON TABLE REVIEW_FILE is '후기 첨부파일';
COMMENT ON COLUMN REVIEW_FILE.REVIEW_FILE_NO is '파일 번호';
COMMENT ON COLUMN REVIEW_FILE.NAME is '첨부파일명';
COMMENT ON COLUMN REVIEW_FILE.SIZE is '첨부파일 크기';
COMMENT ON COLUMN REVIEW_FILE.EX is '확장자명';
COMMENT ON COLUMN REVIEW_FILE.SRC is '첨부파일 주소';
COMMENT ON COLUMN REVIEW_FILE.REVIEWNO is '후기 번호';


/**********************************/
/* Table Name: 공지 첨부파일 */
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

COMMENT ON TABLE NOTICE_FILE is '공지 첨부파일';
COMMENT ON COLUMN NOTICE_FILE.NOTICE_FILE_NO is '파일 번호';
COMMENT ON COLUMN NOTICE_FILE.NAME is '첨부파일명';
COMMENT ON COLUMN NOTICE_FILE.SIZE is '첨부파일 크기';
COMMENT ON COLUMN NOTICE_FILE.EX is '확장자명';
COMMENT ON COLUMN NOTICE_FILE.R_NO is '후기 번호';
COMMENT ON COLUMN NOTICE_FILE.SRC is '첨부파일 주소';
COMMENT ON COLUMN NOTICE_FILE.NOTICENO is '공지사항 번호';


/**********************************/
/* Table Name: 좋아요 */
/**********************************/
CREATE TABLE LIKE(
		LIKENO                        		NUMBER(9)		 NULL 		 PRIMARY KEY,
		LIKE                          		VARCHAR2(2)		 NOT NULL,
		REVIEWNO                      		VARCHAR2(255)		 NULL ,
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
);

COMMENT ON TABLE LIKE is '좋아요';
COMMENT ON COLUMN LIKE.LIKENO is '좋아요 번호';
COMMENT ON COLUMN LIKE.LIKE is '좋아요';
COMMENT ON COLUMN LIKE.REVIEWNO is '후기 번호';


/**********************************/
/* Table Name: 한승원 */
/**********************************/
CREATE TABLE 한승원(

);

COMMENT ON TABLE 한승원 is '한승원';


