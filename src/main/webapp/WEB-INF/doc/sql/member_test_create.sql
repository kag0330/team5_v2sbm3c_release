-- 테스트용 계정 1
-- 원래 비밀번호: password1
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser1', '$2a$10$WzDpQm.YayAl3tO0XOdL9Ouxocb/G.POQzPteCIMH5a5VrZXrH81i', 'Test User1', 'testnick1', '010-1234-5678', 'test1@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '여성', 1, 'USER');

-- 테스트용 계정 2
-- 원래 비밀번호: password2
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser2', '$2a$10$O0zFcj5.2CO.JNcBd1vPru1YRuWvdW5v4PlWn45h4tSfpN6CJ1YYG', 'Test User2', 'testnick2', '010-8765-4321', 'test2@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

-- 테스트용 계정 3
-- 원래 비밀번호: password3
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser3', '$2a$10$1b0tr1PxFzUjK8gW4P7Be.VrKHs7Vto3eHtPuhhgbmt1f9h8k59Ae', 'Test User3', 'testnick3', '010-1111-2222', 'test3@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '비공개', 1, 'USER');

-- 테스트용 계정 4
-- 원래 비밀번호: password4
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser4', '$2a$10$Ko17TpylNY02JYbUQn0OcuH.EUuZTxe7TfZr9uJkxsdSD.OJvF.eW', 'Test User4', 'testnick4', '010-3333-4444', 'test4@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

-- 테스트용 계정 5
-- 원래 비밀번호: password5
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser5', '$2a$10$9Zp6WquGDR9A.PW6p5G8iOF0I/V/3vcjZDlH56pJX4wvW.y/9OcsK', 'Test User5', 'testnick5', '010-5555-6666', 'test5@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '여성', 1, 'USER');

-- 테스트용 계정 6
-- 원래 비밀번호: password6
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser6', '$2a$10$QZpA3ZhrY5dpnFQ9Ya3Exu8I1lUmFEGR6B0Zo8g7P3UuGImyPSrSO', 'Test User6', 'testnick6', '010-6666-7777', 'test6@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

-- 테스트용 계정 7
-- 원래 비밀번호: password7
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser7', '$2a$10$cRucwqk8bfh2kR2Iz/SyY.O21qRcPOn9aS8l1AoRT5L6F0o5LCluy', 'Test User7', 'testnick7', '010-7777-8888', 'test7@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '비공개', 1, 'USER');

-- 테스트용 계정 8
-- 원래 비밀번호: password8
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser8', '$2a$10$kLwD/NY5alBVHRxnqdx5POKN2pExUtN.o1ub7m4yWCeQ7qkSrdau6', 'Test User8', 'testnick8', '010-8888-9999', 'test8@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

-- 테스트용 계정 9
-- 원래 비밀번호: password9
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser9', '$2a$10$7XWGeUi.l0ZR6eAXwNGZtuCxHRG3s0AL3b8J6/6l5F5wnwBfiZ3IC', 'Test User9', 'testnick9', '010-9999-0000', 'test9@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '여성', 1, 'USER');

-- 테스트용 계정 10
-- 원래 비밀번호: password10
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser10', '$2a$10$D8lg.NXazHcBv7pPPf4tPeUnDdCUc3XFAO5bgdj/MzMxO.AFFGmde', 'Test User10', 'testnick10', '010-0000-1111', 'test10@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

-- 테스트용 계정 11
-- 원래 비밀번호: password11
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser11', '$2a$10$LrUiU7VO/NUtC7a5bKlS6ONh.GYOdo3Xha2XU.p3me5g/fu5nRUSG', 'Test User11', 'testnick11', '010-1111-2222', 'test11@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '여성', 1, 'USER');

-- 테스트용 계정 12
-- 원래 비밀번호: password12
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser12', '$2a$10$DBMYkFPg9S/Zo9IljJUsj.eDULNcOZMJdfAZy0EOch4d4v2ONVflW', 'Test User12', 'testnick12', '010-2222-3333', 'test12@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

-- 테스트용 계정 13
-- 원래 비밀번호: password13
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser13', '$2a$10$6Dys16sBvMZVzSB8vQxs7eiUt6q48Pf8S.5S.d0/B89pDj0.C/0Ku', 'Test User13', 'testnick13', '010-3333-4444', 'test13@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '여성', 1, 'USER');

-- 테스트용 계정 14
-- 원래 비밀번호: password14
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser14', '$2a$10$EjK26Oyz8jZd6GyOHyhHu.gd9HFyx/58ghM2Rc2/RrI5N1RoCByA.', 'Test User14', 'testnick14', '010-4444-5555', 'test14@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

-- 테스트용 계정 15
-- 원래 비밀번호: password15
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser15', '$2a$10$YfoqU.8fl/CGm7kITcIaKeDfYaTOzOVorE3h0o23zX7bzVg/2p.Cm', 'Test User15', 'testnick15', '010-5555-6666', 'test15@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '여성', 1, 'USER');

-- 테스트용 계정 16
-- 원래 비밀번호: password16
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser16', '$2a$10$s8FBN4zzJj8qRmspV5fSAuvHivgSnn9IxOFSrbJ7aI4vTfbJ2ZBha', 'Test User16', 'testnick16', '010-6666-7777', 'test16@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

-- 테스트용 계정 17
-- 원래 비밀번호: password17
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser17', '$2a$10$4Xvcl4.dW0uj6sZ0msK4/O8vA5vFELkKpu6Uu21uSk9X/v1d0kjP6', 'Test User17', 'testnick17', '010-7777-8888', 'test17@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '여성', 1, 'USER');

-- 테스트용 계정 18
-- 원래 비밀번호: password18
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser18', '$2a$10$PaDbRo5onE0GH9bivwZT8OCSybSrr1lC4/DdQSGF.z9OBaN/2.XFm', 'Test User18', 'testnick18', '010-8888-9999', 'test18@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

-- 테스트용 계정 19
-- 원래 비밀번호: password19
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser19', '$2a$10$FY9DVoAay4vMa7hRRUtQFeREpLV09vlH4Kn0tY3ic0k4PLWBmsdz2', 'Test User19', 'testnick19', '010-9999-0000', 'test19@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '비공개', 1, 'USER');

-- 테스트용 계정 20
-- 원래 비밀번호: password20
INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, mdate, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, 'testuser20', '$2a$10$/i/BnlmPLU1W/jJ8V4bJe.SF4lzAtY6xrb9A/XjZ5BfwL46X4.BkO', 'Test User20', 'testnick20', '010-0000-1111', 'test20@test.com', '', '', '', 0, '2022-01-01', sysdate, 0, '남성', 1, 'USER');

commit;