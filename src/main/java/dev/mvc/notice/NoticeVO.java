package dev.mvc.notice;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/*
CREATE TABLE NOTICE(
    NOTICENO                          NUMBER(9)    NOT NULL    PRIMARY KEY,
    TITLE                             VARCHAR2(100)    NOT NULL,
    CONTENTS                          VARCHAR2(1000)     NOT NULL,
    RDATE                             DATE     NOT NULL,
    VIEWS                             NUMBER(9)    DEFAULT 0     NOT NULL,
    MEMBERNO                          NUMBER(9)    NOT NULL
);
*/
@Setter @Getter
public class NoticeVO {
  
  /** 공지사항 번호 */
  private Integer noticeno = 0;

  /** 공지사항 제목 */
  @NotEmpty(message="공지사항 제목은 필수 입력 항목입니다.")
  @Size(min=1, message="최소 1자이상 입력해주세요.")
  private String title;
  
  /** 공지사항 내용 */
  @NotEmpty(message="공지사항 내용은 필수 입력 항목입니다.")
  @Size(min=1, message="최소 1자이상 입력해주세요.")
  private String contents;
  
  /** 공지사항 작성일 */
  private Date rdate;
  
  /** 조회수 */
  private Integer views;
  
  /** 작성자 번호 */
  private Integer memberno;

  
}
