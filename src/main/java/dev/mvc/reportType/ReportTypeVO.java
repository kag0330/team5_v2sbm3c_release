package dev.mvc.reportType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/*
CREATE TABLE REPORT_TYPE(
    TYPENO                            NUMBER(9)    NOT NULL    PRIMARY KEY,
    TYPE                              VARCHAR2(100)    NOT NULL
);
*/
@Getter
@Setter
@ToString
public class ReportTypeVO {
  /** 신고유형번호 */
  private Integer typeno;
  
  
  /** 신고 유형*/
  private String type;
}
