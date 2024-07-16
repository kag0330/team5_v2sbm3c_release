package dev.mvc.notice;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import dev.mvc.noticeFile.NoticeFileVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NoticeMemberFileVO {
  
  private MultipartFile fileSelect;
  
  /** 공지사항 번호 */
  private Integer noticeno;
  
  /** 작성자 번호 */
  private Integer memberno;

  
  /** 공지사항 제목 */
  private String title;

  /** 공지사항 내용 */
  private String contents;

  /** 공지사항 작성일 */
  private Date rdate;

  /** 조회수 */
  private String views;

  /** 작성자 닉네임 */
  private String nickname;
  
//  /** 공지사항 파일 번호 */
//  private Integer notice_file_no;
//  
//  /** 파일명 */
//  private String name;
//  
//  /** 파일크기 */
//  private Long sizes;
//  
//  /** 확장자명 */
//  private String ex;
//  
//  /** 파일 주소 */
//  private String src;
  
  private ArrayList<NoticeFileVO> noticeFileVOs;
  
}
