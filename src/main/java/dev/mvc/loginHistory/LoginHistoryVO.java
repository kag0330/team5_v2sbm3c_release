package dev.mvc.loginHistory;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE LOGIN_HISTORY(
//    LOGINNO                 NUMBER(9)    NOT NULL    PRIMARY KEY,
//    IP                                VARCHAR2(50)     NOT NULL,
//    RDATE                             DATE     NULL ,
//    MEMBERNO                          NUMBER(9)    NOT NULL,
//  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
//);
public class LoginHistoryVO {
  /** 로그인내역 번호 */
  private Integer loginno;
  
  
  /** 로그인내역 아이피 */
  private String ip;
  
  
  /** 로그인내역 접속일*/
  private Date rdate;
  
  
  /** 멤버 번호 */
  private Integer memberno;
}
