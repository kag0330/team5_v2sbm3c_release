package dev.mvc.notice;

import java.util.ArrayList;
import java.util.Map;

import dev.mvc.noticeFile.NoticeFileVO;

public interface NoticeDAOInter {

  /** 공지사항 생성 */
  public int create(Map<String,Object> map);
  
  /** 공지사항 목록 */
  public ArrayList<NoticeMemberVO> list_all();
  
  
  /** 검색 개수 */
  public int list_search_count(String word);
  
  public ArrayList<NoticeMemberFileVO> list_search_paging(Map<String, Object> map);
  
  /** 파일 유무 */
  public int file_count(int noticeno);
  
  /** 공지사항 상세 */
  public NoticeMemberFileVO read(int noticeno);
  
  /** 공지사항 조회수 증가 */
  public int increased_views(int noticeno);
  
  /** 공지사항 조회한 후 조회 수 */
  public int views(int noticeno);
  
  /** 공지사항 수정 */
  public int update(Map<String, Object> map);
  
  /** 공지사항 삭제 */
  public int delete(int noticeno);
  
  /** 공지사항-파일 삭제 */
  public int delete_file(int noticeno);
  
  public int insert_notice_file(Map<String,Object> map);
  
}
