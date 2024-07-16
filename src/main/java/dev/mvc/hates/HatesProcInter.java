package dev.mvc.hates;

import java.util.Map;

public interface HatesProcInter {
  
  /**
   * 싫어요 추가
   * insert id="increased_hates" parameterType="Map"
   * @param reviewno, memberno
   * @return 증가 여부
   */
  public int increased_hates(int reviewno, int memberno);

  /**
   * 싫어요 감소
   * update id="decreased_hates" parameterType="Map"
   * @param reviewno, memberno
   * @return 감소 여부
   */
  public int decreased_hates(int reviewno, int memberno);
  
  /**
   * review의 hates 삭제 (review 삭제 전)
   * update id="delete" parameterType="Integer"
   * @param reviewno
   * @return
   */
  public int delete(int reviewno);
  
  /**
   * 총 싫어요 수
   * select id="hates_count" parameterType="Integer"
   * @param reviewno
   * @return 총 싫어요 수
   */
  public int hates_count(int reviewno);
  
  /**
   * 싫어요 개수
   * insert id="myhates" parameterType="Map"
   * @param reviewno, memberno
   * @return 한 리뷰의 내 싫어요 개수 
   */
  public int myhates(int reviewno, int memberno);
}
