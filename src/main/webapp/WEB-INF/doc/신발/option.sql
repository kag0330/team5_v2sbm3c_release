DROP TABLE OPTIONS CASCADE CONSTRAINTS;
DROP SEQUENCE OPTION_SEQ;

/**********************************/
/* Table Name: 신발옵션 */
/**********************************/
CREATE TABLE OPTIONS (
		OPTIONNO                          		NUMBER(9)		 NULL 		 PRIMARY KEY,
		SIZES                        		NUMBER(4)		 NOT NULL,
		AMOUNT                      		NUMBER(9)		DEFAULT 0 NOT NULL,
		COLOR                       		VARCHAR2(30)		 NOT NULL,
		SHOESNO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO)
);

COMMENT ON TABLE OPTION is '신발옵션';
COMMENT ON COLUMN OPTIONS.OPTIONNO is '옵션 번호';
COMMENT ON COLUMN OPTIONS.SIZES is '신발 사이즈';
COMMENT ON COLUMN OPTIONS.AMOUNT is '신발 재고';
COMMENT ON COLUMN OPTIONS.COLOR is '신발 색상';
COMMENT ON COLUMN OPTIONS.SHOESNO is '신발 번호';


CREATE SEQUENCE OPTION_SEQ
    START WITH 1         -- 시작 번호
    INCREMENT BY 1       -- 증가값
    MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
    CACHE 2              -- 2번은 메모리에서만 계산
    NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
    
       
INSERT INTO OPTIONS(OPTIONNO, SIZES, COLOR, SHOESNO)
VALUES(OPTION_SEQ.nextval, 270, '회색/흰색', 1);

INSERT INTO OPTIONS(OPTIONNO, SIZES, COLOR, SHOESNO)
VALUES(OPTION_SEQ.nextval, 270, '파란색', 1);

INSERT INTO OPTIONS(OPTIONNO, SIZES, COLOR, SHOESNO)
VALUES(OPTION_SEQ.nextval, 270, '검정색', 1);
commit;

    SELECT *
    FROM options
    WHERE shoesno = 2
    ORDER BY optionno ASC;

    SELECT o.optionno, o.shoesno, o.sizes, o.amount, o.color, r
    FROM (
      SELECT optionno, shoesno, sizes, amount, color, rownum as r
      FROM options
      WHERE shoesno = 1
    ) o  
  JOIN shoes s ON o.shoesno = s.shoesno
  WHERE r >= 1 and r <= 5;
SELECT o.optionno, o.shoesno, o.sizes, o.amount, o.color
FROM Options o
JOIN Shoes s ON o.shoesno = s.shoesno
WHERE s.shoesno = 1;

    SELECT o.optionno, o.shoesno, o.sizes, o.amount, o.color, r
    FROM (
      SELECT optionno, shoesno, sizes, amount, color, rownum as r
      FROM options
    ) o
  JOIN shoes s ON o.shoesno = s.shoesno
  WHERE s.shoesno = 1;

  desc options;
    SELECT COUNT(*) as cnt
    FROM options
    WHERE shoesno = 4
    ORDER BY optionno ASC;
    
    SELECT count(*) as cnt FROM SHOES  WHERE SHOESNO = 12;