/**********************************/
/* Table Name: 멤버 */
/**********************************/
CREATE TABLE MEMBER(
		M_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		M_ID                          		VARCHAR2(100)		 NOT NULL,
		M_PW                          		VARCHAR2(100)		 NOT NULL,
		M_NAME                        		VARCHAR2(100)		 NOT NULL,
		M_NICKNAME                    		VARCHAR2(100)		 NOT NULL,
		M_PHONE                       		VARCHAR2(13)		 NOT NULL,
		M_EMAIL                       		VARCHAR2(50)		 NOT NULL,
		M_THUMB                       		VARCHAR2(100)		 NULL ,
		M_ADDR1                       		VARCHAR2(100)		 NULL ,
		M_ADDR2                       		VARCHAR2(100)		 NULL ,
		M_ZIPCODE                     		NUMBER(5)		 NULL ,
		M_DATE                        		DATE		 NULL ,
		M_RDATE                       		DATE		 NOT NULL,
		M_POINT                       		NUMBER(9)		 DEFAULT 0		 NULL ,
		M_GENDER                      		VARCHAR2(20)		 NOT NULL,
		M_GRADE                       		NUMBER(2)		 NULL ,
		M_ROLE                        		VARCHAR2(30)		 DEFAULT USER		 NOT NULL
);

COMMENT ON TABLE MEMBER is '멤버';
COMMENT ON COLUMN MEMBER.M_NO is '멤버 번호';
COMMENT ON COLUMN MEMBER.M_ID is '멤버 아이디';
COMMENT ON COLUMN MEMBER.M_PW is '멤버 비밀번호';
COMMENT ON COLUMN MEMBER.M_NAME is '멤버 이름';
COMMENT ON COLUMN MEMBER.M_NICKNAME is '멤버 닉네임';
COMMENT ON COLUMN MEMBER.M_PHONE is '멤버 전화번호';
COMMENT ON COLUMN MEMBER.M_EMAIL is '멤버 이메일';
COMMENT ON COLUMN MEMBER.M_THUMB is '멤버 섬네일';
COMMENT ON COLUMN MEMBER.M_ADDR1 is '멤버 주소1';
COMMENT ON COLUMN MEMBER.M_ADDR2 is '멤버 상세주소';
COMMENT ON COLUMN MEMBER.M_ZIPCODE is '멤버 우편번호';
COMMENT ON COLUMN MEMBER.M_DATE is '멤버 생년월일';
COMMENT ON COLUMN MEMBER.M_RDATE is '멤버 가입일';
COMMENT ON COLUMN MEMBER.M_POINT is '멤버 포인트';
COMMENT ON COLUMN MEMBER.M_GENDER is '멤버 성별';
COMMENT ON COLUMN MEMBER.M_GRADE is '멤버 등급';
COMMENT ON COLUMN MEMBER.M_ROLE is '멤버 권한';


/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE CATEGORY(
		C_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		C_NAME                        		VARCHAR2(100)		 NOT NULL,
		C_SUBNAME                     		VARCHAR2(100)		 NOT NULL,
		C_SEQ                         		NUMBER(9)		 NULL ,
		C_CNT                         		NUMBER(9)		 NOT NULL,
		C_PARENT                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (C_PARENT) REFERENCES CATEGORY (C_NO)
);

COMMENT ON TABLE CATEGORY is '카테고리';
COMMENT ON COLUMN CATEGORY.C_NO is '카테고리 번호';
COMMENT ON COLUMN CATEGORY.C_NAME is '카테고리 이름';
COMMENT ON COLUMN CATEGORY.C_SUBNAME is '카테고리 서브명';
COMMENT ON COLUMN CATEGORY.C_SEQ is '카테고리 출력순서';
COMMENT ON COLUMN CATEGORY.C_CNT is '카테고리 항목수';
COMMENT ON COLUMN CATEGORY.C_PARENT is '카테고리 부모번호';


/**********************************/
/* Table Name: 신발 */
/**********************************/
CREATE TABLE SHOSE(
		S_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		C_NO                          		NUMBER(9)		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
		S_TITLE                       		VARCHAR2(100)		 NOT NULL,
		S_BRAND                       		VARCHAR2(100)		 NOT NULL,
		S_RATING                      		NUMBER		 NOT NULL,
		S_PRICE                       		NUMBER		 NOT NULL,
		S_DISCOUNT                    		NUMBER		 NOT NULL,
		S_CONTENTS                    		VARCHAR2(1000)		 NOT NULL,
		S_VISIBLE                     		CHAR(1)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (C_NO) REFERENCES CATEGORY (C_NO)
);

COMMENT ON TABLE SHOSE is '신발';
COMMENT ON COLUMN SHOSE.S_NO is '신발 번호';
COMMENT ON COLUMN SHOSE.C_NO is '카테고리 번호';
COMMENT ON COLUMN SHOSE.M_NO is '유저 번호';
COMMENT ON COLUMN SHOSE.S_TITLE is '신발 제목';
COMMENT ON COLUMN SHOSE.S_BRAND is '신발 브랜드명';
COMMENT ON COLUMN SHOSE.S_RATING is '신발 평점';
COMMENT ON COLUMN SHOSE.S_PRICE is '신발 가격';
COMMENT ON COLUMN SHOSE.S_DISCOUNT is '신발 할인율';
COMMENT ON COLUMN SHOSE.S_CONTENTS is '신발 설명';
COMMENT ON COLUMN SHOSE.S_VISIBLE is '신발 판매 여부';


/**********************************/
/* Table Name: 신발옵션 */
/**********************************/
CREATE TABLE OPTION(
		O_NO                          		NUMBER(9)		 NULL 		 PRIMARY KEY,
		O_SIZE                        		NUMBER(4)		 NOT NULL,
		O_AMOUNT                      		NUMBER(9)		 NOT NULL,
		O_COLOR                       		VARCHAR2(30)		 NOT NULL,
		S_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO)
);

COMMENT ON TABLE OPTION is '신발옵션';
COMMENT ON COLUMN OPTION.O_NO is '옵션 번호';
COMMENT ON COLUMN OPTION.O_SIZE is '신발 사이즈';
COMMENT ON COLUMN OPTION.O_AMOUNT is '신발 재고';
COMMENT ON COLUMN OPTION.O_COLOR is '신발 색상';
COMMENT ON COLUMN OPTION.S_NO is '신발 번호';


/**********************************/
/* Table Name: 장바구니 */
/**********************************/
CREATE TABLE BASKET(
		B_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		B_AMOUNT                      		NUMBER(9)		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
		O_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (O_NO) REFERENCES OPTION (O_NO)
);

COMMENT ON TABLE BASKET is '장바구니';
COMMENT ON COLUMN BASKET.B_NO is '장바구니 번호';
COMMENT ON COLUMN BASKET.B_AMOUNT is '신발 수량';
COMMENT ON COLUMN BASKET.M_NO is '멤버 번호';
COMMENT ON COLUMN BASKET.O_NO is '옵션 번호';


/**********************************/
/* Table Name: 로그인내역 */
/**********************************/
CREATE TABLE LOGIN_HISTORY(
		LH_NO                         		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		LH_IP                         		VARCHAR2(15)		 NOT NULL,
		LH_DATE                       		DATE		 NULL ,
		M_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE LOGIN_HISTORY is '로그인내역';
COMMENT ON COLUMN LOGIN_HISTORY.LH_NO is '로그인내역 번호';
COMMENT ON COLUMN LOGIN_HISTORY.LH_IP is '로그인내역 아이피';
COMMENT ON COLUMN LOGIN_HISTORY.LH_DATE is '로그인내역 접속일';
COMMENT ON COLUMN LOGIN_HISTORY.M_NO is '멤버 번호';


/**********************************/
/* Table Name: 주문 */
/**********************************/
CREATE TABLE PAYMENT(
		P_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		P_DATE                        		DATE		 NOT NULL,
		P_STATUS                      		VARCHAR2(50)		 NOT NULL,
		P_PAYMANT_STATUS              		VARCHAR2(50)		 NOT NULL,
		P_CS_STATUS                   		VARCHAR2(50)		 NULL ,
		P_PRICE                       		NUMBER		 NOT NULL,
		P_TOTAL_PRICE                 		NUMBER		 NULL ,
		P_DELICERY                    		NUMBER		 NOT NULL,
		P_TOTAL_PAYMENT               		NUMBER		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE PAYMENT is '주문';
COMMENT ON COLUMN PAYMENT.P_NO is '주문 번호';
COMMENT ON COLUMN PAYMENT.P_DATE is '주문 일자';
COMMENT ON COLUMN PAYMENT.P_STATUS is '주문 상태';
COMMENT ON COLUMN PAYMENT.P_PAYMANT_STATUS is '결제 상태';
COMMENT ON COLUMN PAYMENT.P_CS_STATUS is 'CS 상태';
COMMENT ON COLUMN PAYMENT.P_PRICE is '상품금액';
COMMENT ON COLUMN PAYMENT.P_TOTAL_PRICE is '총 상품금액';
COMMENT ON COLUMN PAYMENT.P_DELICERY is '배송비';
COMMENT ON COLUMN PAYMENT.P_TOTAL_PAYMENT is '총 주문금액';
COMMENT ON COLUMN PAYMENT.M_NO is '멤버 번호';


/**********************************/
/* Table Name: 주문상세 */
/**********************************/
CREATE TABLE PAYMENT_DETAILS(
		PD_NO                         		VARCHAR2(255)		 NOT NULL		 PRIMARY KEY,
		PD_AMOUNT                     		NUMBER(9)		 NOT NULL,
		P_NO                          		NUMBER(9)		 NOT NULL,
		O_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (P_NO) REFERENCES PAYMENT (P_NO),
  FOREIGN KEY (O_NO) REFERENCES OPTION (O_NO)
);

COMMENT ON TABLE PAYMENT_DETAILS is '주문상세';
COMMENT ON COLUMN PAYMENT_DETAILS.PD_NO is '주문 상세번호';
COMMENT ON COLUMN PAYMENT_DETAILS.PD_AMOUNT is '주문 수량';
COMMENT ON COLUMN PAYMENT_DETAILS.P_NO is '주문 번호';
COMMENT ON COLUMN PAYMENT_DETAILS.O_NO is '옵션 번호';


/**********************************/
/* Table Name: 후기 */
/**********************************/
CREATE TABLE REVIEW(
		R_NO                          		VARCHAR2(255)		 NOT NULL		 PRIMARY KEY,
		R_COMMENT                     		VARCHAR2(1000)		 NOT NULL,
		R_GRADE                       		NUMBER		 NOT NULL,
		S_NO                          		NUMBER(9)		 NOT NULL,
		U_NO                          		NUMBER(9)		 NOT NULL,
		R_DATE                        		DATE		 NOT NULL,
		LIKE_NO                       		NUMBER(9)		 NULL ,
  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO),
  FOREIGN KEY (U_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE REVIEW is '후기';
COMMENT ON COLUMN REVIEW.R_NO is '후기 번호';
COMMENT ON COLUMN REVIEW.R_COMMENT is '후기 내용';
COMMENT ON COLUMN REVIEW.R_GRADE is '후기 점수';
COMMENT ON COLUMN REVIEW.S_NO is '신발 번호';
COMMENT ON COLUMN REVIEW.U_NO is '멤버 번호';
COMMENT ON COLUMN REVIEW.R_DATE is '후기 작성일';
COMMENT ON COLUMN REVIEW.LIKE_NO is '좋아요 번호';


/**********************************/
/* Table Name: 공지사항 */
/**********************************/
CREATE TABLE NOTICE(
		N_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		N_COMMENT                     		VARCHAR2(1000)		 NOT NULL,
		N_DATE                        		DATE		 NOT NULL,
		N_FILE                        		VARCHAR2(100)		 NOT NULL,
		N_SIZE                        		NUMBER(9)		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE NOTICE is '공지사항';
COMMENT ON COLUMN NOTICE.N_NO is '공지사항 번호';
COMMENT ON COLUMN NOTICE.N_COMMENT is '공지사항 내용';
COMMENT ON COLUMN NOTICE.N_DATE is '공지사항 작성일';
COMMENT ON COLUMN NOTICE.N_FILE is '첨부 파일';
COMMENT ON COLUMN NOTICE.N_SIZE is '첨부 파일 크기';
COMMENT ON COLUMN NOTICE.M_NO is '멤버 번호';


/**********************************/
/* Table Name: 신발 문의 */
/**********************************/
CREATE TABLE SHOES_INQUIRY(
		IN_NO                         		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IN_COMMENT                    		VARCHAR2(1000)		 NOT NULL,
		IN_DATE                       		DATE		 NOT NULL,
		ANSWER_VISIBLE                		CHAR(1)		 NOT NULL,
		ANSWER_COMMENT                		VARCHAR2(1000)		 NOT NULL,
		O_NO                          		NUMBER(9)		 NULL ,
		M_NO                          		NUMBER(9)		 NULL ,
  FOREIGN KEY (O_NO) REFERENCES OPTION (O_NO),
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE SHOES_INQUIRY is '신발 문의';
COMMENT ON COLUMN SHOES_INQUIRY.IN_NO is '신발 문의 번호';
COMMENT ON COLUMN SHOES_INQUIRY.IN_COMMENT is '문의 내용';
COMMENT ON COLUMN SHOES_INQUIRY.IN_DATE is '문의 작성일';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_COMMENT is '답변 내용';
COMMENT ON COLUMN SHOES_INQUIRY.O_NO is '옵션 번호';
COMMENT ON COLUMN SHOES_INQUIRY.M_NO is '멤버 번호';


/**********************************/
/* Table Name: 신고 유형 */
/**********************************/
CREATE TABLE REPORT_TYPE(
		REPORT_TYPE_NO                		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		REPORT_TYPE                   		VARCHAR2(100)		 NOT NULL
);

COMMENT ON TABLE REPORT_TYPE is '신고 유형';
COMMENT ON COLUMN REPORT_TYPE.REPORT_TYPE_NO is '신고 유형번호';
COMMENT ON COLUMN REPORT_TYPE.REPORT_TYPE is '신고 유형';


/**********************************/
/* Table Name: 신고 */
/**********************************/
CREATE TABLE REPORT(
		REPORT_NO                     		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		REPORT_COMMENT                		VARCHAR2(1000)		 NOT NULL,
		REPORT_TYPE_NO                		NUMBER(9)		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
		R_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (R_NO) REFERENCES REVIEW (R_NO),
  FOREIGN KEY (REPORT_TYPE_NO) REFERENCES REPORT_TYPE (REPORT_TYPE_NO)
);

COMMENT ON TABLE REPORT is '신고';
COMMENT ON COLUMN REPORT.REPORT_NO is '신고 번호';
COMMENT ON COLUMN REPORT.REPORT_COMMENT is '신고 내용';
COMMENT ON COLUMN REPORT.REPORT_TYPE_NO is '신고 유형번호 ';
COMMENT ON COLUMN REPORT.M_NO is '멤버 번호';
COMMENT ON COLUMN REPORT.R_NO is '후기 번호';


/**********************************/
/* Table Name: 주문 문의 */
/**********************************/
CREATE TABLE PAYMENT_INQUIRY(
		IN_NO                         		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IN_COMMENT                    		VARCHAR2(1000)		 NOT NULL,
		IN_DATE                       		DATE		 NOT NULL,
		ANSWER_VISIBLE                		CHAR(1)		 NOT NULL,
		ANSWER_COMMENT                		VARCHAR2(1000)		 NULL ,
		M_NO                          		NUMBER(9)		 NOT NULL,
		P_NO                          		NUMBER(9)		 NULL ,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (P_NO) REFERENCES PAYMENT (P_NO)
);

COMMENT ON TABLE PAYMENT_INQUIRY is '주문 문의';
COMMENT ON COLUMN PAYMENT_INQUIRY.IN_NO is '주문 문의 번호';
COMMENT ON COLUMN PAYMENT_INQUIRY.IN_COMMENT is '문의 내용';
COMMENT ON COLUMN PAYMENT_INQUIRY.IN_DATE is '문의 작성일';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_COMMENT is '답변 내용';
COMMENT ON COLUMN PAYMENT_INQUIRY.M_NO is '멤버 번호';
COMMENT ON COLUMN PAYMENT_INQUIRY.P_NO is '주문 번호';


/**********************************/
/* Table Name: 기타 문의 */
/**********************************/
CREATE TABLE OTHER_INQUIRY(
		IN_NO                         		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IN_COMMENT                    		VARCHAR2(1000)		 NOT NULL,
		IN_DATE                       		DATE		 NOT NULL,
		ANSWER_VISIBLE                		CHAR(1)		 NOT NULL,
		ANSWER_COMMENT                		VARCHAR2(1000)		 NULL ,
		M_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE OTHER_INQUIRY is '기타 문의';
COMMENT ON COLUMN OTHER_INQUIRY.IN_NO is '기타 문의 번호';
COMMENT ON COLUMN OTHER_INQUIRY.IN_COMMENT is '문의 내용';
COMMENT ON COLUMN OTHER_INQUIRY.IN_DATE is '문의 작성일';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_COMMENT is '답변 내용';
COMMENT ON COLUMN OTHER_INQUIRY.M_NO is '멤버 번호';


/**********************************/
/* Table Name: 신발 첨부파일 */
/**********************************/
CREATE TABLE SHOES_FILE(
		PILE_NO                       		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		PILE_NAME                     		VARCHAR2(100)		 NOT NULL,
		PILE_SIZE                     		NUMBER		 NOT NULL,
		PILE_EX                       		VARCHAR2(100)		 NOT NULL,
		PILE_SRC                      		VARCHAR2(100)		 NOT NULL,
		S_NO                          		NUMBER(9)		 NULL ,
  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO)
);

COMMENT ON TABLE SHOES_FILE is '신발 첨부파일';
COMMENT ON COLUMN SHOES_FILE.PILE_NO is '파일 번호';
COMMENT ON COLUMN SHOES_FILE.PILE_NAME is '첨부파일명';
COMMENT ON COLUMN SHOES_FILE.PILE_SIZE is '첨부파일 크기';
COMMENT ON COLUMN SHOES_FILE.PILE_EX is '확장자명';
COMMENT ON COLUMN SHOES_FILE.PILE_SRC is '첨부파일 주소';
COMMENT ON COLUMN SHOES_FILE.S_NO is '신발 번호';


/**********************************/
/* Table Name: 후기 첨부파일 */
/**********************************/
CREATE TABLE REVIEW_FILE(
		PILE_NO                       		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		PILE_NAME                     		VARCHAR2(100)		 NOT NULL,
		PILE_SIZE                     		NUMBER		 NOT NULL,
		PILE_EX                       		VARCHAR2(100)		 NOT NULL,
		PILE_SRC                      		VARCHAR2(100)		 NOT NULL,
		R_NO                          		VARCHAR2(255)		 NULL ,
  FOREIGN KEY (R_NO) REFERENCES REVIEW (R_NO)
);

COMMENT ON TABLE REVIEW_FILE is '후기 첨부파일';
COMMENT ON COLUMN REVIEW_FILE.PILE_NO is '파일 번호';
COMMENT ON COLUMN REVIEW_FILE.PILE_NAME is '첨부파일명';
COMMENT ON COLUMN REVIEW_FILE.PILE_SIZE is '첨부파일 크기';
COMMENT ON COLUMN REVIEW_FILE.PILE_EX is '확장자명';
COMMENT ON COLUMN REVIEW_FILE.PILE_SRC is '첨부파일 주소';
COMMENT ON COLUMN REVIEW_FILE.R_NO is '후기 번호';


/**********************************/
/* Table Name: 공지 첨부파일 */
/**********************************/
CREATE TABLE NOTICE_FILE(
		PILE_NO                       		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		PILE_NAME                     		VARCHAR2(100)		 NOT NULL,
		PILE_SIZE                     		NUMBER		 NOT NULL,
		PILE_EX                       		VARCHAR2(100)		 NOT NULL,
		PILE_SRC                      		VARCHAR2(100)		 NOT NULL,
		R_NO                          		VARCHAR2(255)		 NULL ,
		N_NO                          		NUMBER(9)		 NULL ,
  FOREIGN KEY (N_NO) REFERENCES NOTICE (N_NO)
);

COMMENT ON TABLE NOTICE_FILE is '공지 첨부파일';
COMMENT ON COLUMN NOTICE_FILE.PILE_NO is '파일 번호';
COMMENT ON COLUMN NOTICE_FILE.PILE_NAME is '첨부파일명';
COMMENT ON COLUMN NOTICE_FILE.PILE_SIZE is '첨부파일 크기';
COMMENT ON COLUMN NOTICE_FILE.PILE_EX is '확장자명';
COMMENT ON COLUMN NOTICE_FILE.PILE_SRC is '첨부파일 주소';
COMMENT ON COLUMN NOTICE_FILE.R_NO is '후기 번호';
COMMENT ON COLUMN NOTICE_FILE.N_NO is '공지사항 번호';


/**********************************/
/* Table Name: 좋아요 */
/**********************************/
CREATE TABLE LIKE(
		LIKE_NO                       		NUMBER(9)		 NULL 		 PRIMARY KEY,
		LIKE                          		VARCHAR2(2)		 NOT NULL,
		R_NO                          		VARCHAR2(255)		 NULL ,
  FOREIGN KEY (R_NO) REFERENCES REVIEW (R_NO)
);

COMMENT ON TABLE LIKE is '좋아요';
COMMENT ON COLUMN LIKE.LIKE_NO is '좋아요 번호';
COMMENT ON COLUMN LIKE.LIKE is '좋아요';
COMMENT ON COLUMN LIKE.R_NO is '후기 번호';


