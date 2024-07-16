/**********************************/
/* Table Name: 멤버 */
/**********************************/
DROP TABLE member;

DROP TABLE member CASCADE CONSTRAINTS; 

CREATE TABLE MEMBER(
		MEMBERNO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		ID                            		VARCHAR2(100)		 UNIQUE       NOT NULL,
		PW                            		VARCHAR2(100)		 NOT NULL,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		NICKNAME                      		VARCHAR2(100)		 NOT NULL,
		PHONE                         		VARCHAR2(13)		 NOT NULL,
		EMAIL                         		VARCHAR2(50)		 NOT NULL,
		THUMB                         		VARCHAR2(100)		 NULL ,
		ADDR1                         		VARCHAR2(100)		 NULL ,
		ADDR2                         		VARCHAR2(100)		 NULL ,
		ZIPCODE                       		NUMBER(5)		 NULL ,
		MDATE                          		DATE		 NULL ,
		RDATE                         		DATE		 NOT NULL,
		POINT                         		NUMBER(9)		 DEFAULT 0		 NULL ,
		GENDER                        		VARCHAR2(20)		 NOT NULL,
		GRADE                         		NUMBER(2)		 NULL ,
		ROLE                          		VARCHAR2(30)		 DEFAULT 'USER'		 NOT NULL
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
COMMENT ON COLUMN MEMBER.MDATE is '멤버 생년월일';
COMMENT ON COLUMN MEMBER.RDATE is '멤버 가입일';
COMMENT ON COLUMN MEMBER.POINT is '멤버 포인트';
COMMENT ON COLUMN MEMBER.GENDER is '멤버 성별';
COMMENT ON COLUMN MEMBER.GRADE is '멤버 등급';
COMMENT ON COLUMN MEMBER.ROLE is '멤버 권한';


DROP SEQUENCE member_seq;

CREATE SEQUENCE member_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

-- C
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (0, 'master', '$2a$10$6r1Qb6u/jyTYIfldEhg.b.5LQ9k3E9GQZf1xT1OQFgYsy36x0A1IG', 'master', 'master', '010-0000-0000', 'master@master.com', '', '', '', 0, '1900-01-01', sysdate, 0, '비공개', 1, 'MASTER');

INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'admin1', '$2a$10$J/wufcsmDCDRQIlwqwRfbug0MWj/t/fS85QBOf94kfpaAM2KT9SeS', 'adminname', 'adminnick', '010-0000-0000', 'email@email.com', '', '', '', 0, '1900-01-01', sysdate, 0, '남성', 1, 'USER');

-- R

SELECT memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role
FROM member
ORDER BY memberno ASC;

-- 아이디 중복 검사
select count(id) as cnt
from member
where id='9';

--레코드 수 검색
SELECT COUNT(*) as cnt
FROM member;

--레코드 수 검색 + ID NICKNAME 검색항목
SELECT COUNT(*) as cnt
FROM member
WHERE UPPER(id) LIKE UPPER('%test%') OR UPPER(nickname) LIKE UPPER('%test%');

--레코드 수 검색 + NAME 검색항목
SELECT COUNT(*) as cnt
FROM member
WHERE UPPER(name) LIKE UPPER('%test%');

--레코드 수 검색 + 선택 검색항목
SELECT COUNT(*) as cnt
FROM member
WHERE UPPER(#{Key}) LIKE '%' || (#{Value}) || '%'
ORDER BY memberno ASC;

--레코드 수 검색 + 선택 검색항목
SELECT COUNT(*) as cnt
FROM member
WHERE UPPER(id) LIKE '%' || (#{Value}) || '%' OR UPPER(nickname) LIKE '%' || (#{Value}) || '%';

-- U
UPDATE member
SET pw='pw', name='name', nickname='nickname', phone='010-1234-5678', email='email@email.com', thumb='', addr1='addr1', addr2='addr2', zipcode=12345, mdate='1900-01-01', gender='비공개'
WHERE memberno=1;

--관리자 페이지 update
UPDATE member
SET nickname='nickname', phone='010-1234-5678', email='email@email.com', thumb='', addr1='addr1', addr2='addr2', zipcode=12345, mdate='1900-01-01', point=0, gender='비공개', grade='1',role='USER'
WHERE memberno=1;

--회원이 탈퇴할 때
UPDATE member
SET grade=99
WHERE memberno=5;

--D
DELETE FROM member
WHERE memberno=5;

SELECT memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role
FROM (
    SELECT memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role, ROWNUM AS r
    FROM (
        SELECT memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role
        FROM member
        WHERE (
            UPPER(id) LIKE '%' || UPPER('test') || '%' OR UPPER(nickname) LIKE '%' || UPPER('test') || '%'
        )
        ORDER BY memberno ASC
    )
    WHERE ROWNUM <= 15
)
WHERE r >= 5;

-- id 찾기용
SELECT *
FROM member
WHERE email='admin@admin.com';

-- pw 찾기용
UPDATE MEMBER
SET pw='$2a$10$wIhgjF1F6PBh.4iHvAd8euYpOf2ofXL.rkbkBJZP/xWh8/0Wvg7vK'
WHERE id='admin1' and email='admin@admin.com';


select count(role) as cnt
from member
where UPPER(role)= UPPER('master');

DELETE FROM member
WHERE memberno = 13;

commit;

rollback;

savepoint test1;

rollback savepoint to test1;




