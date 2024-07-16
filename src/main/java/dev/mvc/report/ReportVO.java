package dev.mvc.report;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
CREATE TABLE REPORT(
    REPORTNO                          NUMBER(9)    NOT NULL    PRIMARY KEY,
        TITLE                               VARCHAR2(100)    NOT NULL,
        CONTENTS                          VARCHAR2(1000)     NOT NULL,
        RDATE                           DATE             NOT NULL,
        REPORT_VISIBLE          CHAR(1)          DEFAULT 'N'   NOT NULL,
        REPORT_CONTENTS          VARCHAR2(1000)   DEFAULT '',
    TYPENO                            NUMBER(9)    NOT NULL,
    MEMBERNO                          NUMBER(9)    NOT NULL,
    REVIEWNO                          NUMBER(9)    NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO),
  FOREIGN KEY (TYPENO) REFERENCES REPORT_TYPE (TYPENO)
);
*/
@Getter
@Setter
@ToString
public class ReportVO {
  /** 신고 번호 */
  private Integer reportno;
  
  
  /** 신고 제목 */
  private String title;
  
  /** 신고 내용 */
  private String contents;
  
  /** 신고 날짜 */
  private Date rdate;
  
  /** 답변 여부 */
  private char report_visible;
  
  /** 답변 내용 */
  private String report_contents;
  
  /** 신고유형번호 */
  private Integer typeno;
  
  
  /** 멤버 번호 */
  private Integer memberno;
  
  
  /** 후기 번호 */
  private Integer reviewno;
}
