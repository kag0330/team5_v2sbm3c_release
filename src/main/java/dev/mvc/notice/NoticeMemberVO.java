package dev.mvc.notice;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/** notice, member join VO */
@Setter
@Getter
public class NoticeMemberVO {

  /** 공지사항 번호 */
  private Integer noticeno;

  /** 공지사항 제목 */
  private String title;

  /** 공지사항 내용 */
  private String contents;

  /** 공지사항 작성일 */
  private Date rdate;

  /** 조회수 */
  private Integer views;
  /** 작성자 닉네임 */
  private String nickname;
}
