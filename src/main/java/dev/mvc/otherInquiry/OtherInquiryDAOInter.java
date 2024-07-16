package dev.mvc.otherInquiry;

import java.util.ArrayList;
import java.util.Map;

public interface OtherInquiryDAOInter {
  
  /**
   * 멤버 생성 id="create" parameterType="dev.mvc.otherInquiry.OtherInquiryVO"
   * 
   * @param OtherInquiryVO 객체
   * @return 성공한 쿼리 갯수
   */
  public int create(OtherInquiryVO otherInquiryVO);

  /**
   * 검색된 레코드 수 id="list_search_count" resultType="int" parameterType="String"
   * 
   * @param word 검색어
   * @return 성공한 쿼리 갯수
   */
  public int list_search_count(String word);

  /**
   * 페이징 id="list_search_paging" resultMap="InquiryResult" parameterType="Map"
   * 
   * @param Map
   * @return ArrayList<OtherInquiryInfoVO>
   */
  public ArrayList<OtherInquiryInfoVO> list_search_paging(Map<String, Object> map);

  /**
   * 기타 문의 상세 id="read" resultMap="InquiryResult" parameterType="Integer"
   * 
   * @param other_inquiry_no
   * @return OtherInquiryInfoVO
   */
  public OtherInquiryInfoVO read(int other_inquiry_no);

  /**
   * 답변 등록 id="answer" parameterType="Map"
   * 
   * @param Map<String, Object> map
   * @return int
   */
  public int answer(Map<String, Object> map);
  
  /**
   * 내 문의 개수
   * select id="myInquiryCount" parameterType="Integer"
   * @param memberno
   * @return 내 문의 개수
   */
  public int myInquiryCount(int memberno);
  
  public ArrayList<OtherInquiryVO> myInquiry(Map<String, Object> map);
}
