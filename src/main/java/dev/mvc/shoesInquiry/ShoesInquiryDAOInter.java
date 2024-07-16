package dev.mvc.shoesInquiry;

import java.util.ArrayList;
import java.util.Map;

public interface ShoesInquiryDAOInter {

  /**
   * 멤버 생성
   * id="create" parameterType="dev.mvc.shoesInquiry.ShoesInquiryVO"
   * 
   * @param ShoesInquiryVO 객체
   * @return 성공한 쿼리 갯수
   */
  public int create(ShoesInquiryVO shoesInquiryVO);
  
  /**
   * 검색된 레코드 수
   * id="list_search_count" resultType="int" parameterType="String"
   * 
   * @param word 검색어
   * @return 성공한 쿼리 갯수
   */
  public int list_search_count(String word);
  
  /**
   * 페이징
   * id="list_search_paging" resultMap="InquiryResult" parameterType="Map"
   * 
   * @param Map
   * @return ArrayList<ShoesInquiryInfoVO>
   */
  public ArrayList<ShoesInquiryInfoVO> list_search_paging(Map<String, Object>map);
  
  /**
   * 신발 문의 상세
   * id="read" resultMap="ReadInquiry" parameterType="Integer"
   * 
   * @param shoes_inquiry_no
   * @return ShoesInquiryInfoVO
   */
  public ShoesInquiryInfoVO read(int shoes_inquiry_no);
  
  /**
   * 답변 등록
   * id="answer" parameterType="Map"
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
  
  /**
   * 내 문의
   * select id="myInquiry" resultMap="myInquiry" parameterType="Map"
   * @param memberno
   * @return 내 문의
   */
  public ArrayList<ShoesInquiryInfoVO> myInquiry(Map<String, Object> map);
}
