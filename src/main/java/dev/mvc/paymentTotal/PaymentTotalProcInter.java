package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface PaymentTotalProcInter {
  /**
   * 주문정보 가져오기
   * id="list" parameterType="int" resultType="Map"
   * @param memberno
   * */
  public ArrayList<PaymentTotalVO> list(int memberno, int dates, String search);
  
  /**
   * 주문정보 가져오기(CS NOT NULL)
   * id="cslist" parameterType="map" resultMap="PaymentTotalMap" resultOrdered="true"*/
  public ArrayList<PaymentTotalVO> cslist(int memberno, int date, String search);
  
  
  

  /**
   * 페이징처리를 위한 admin 주문목록
   * id="listAdminPaging" resultType="dev.mvc.paymentTotal.PaymentTotalVO" parameterType="Map"
   * @param word             검색할 word
   * @param now_page         현재 페이지
   * @param record_per_page  검색할 레코드 갯수
   * @return ArrayList&lt;PaymentTotalVO&gt; 객체 
   * */
  public ArrayList<PaymentTotalVO> listAdminPaging(String word, int now_page, int record_per_page, Map<String,Object> map);
  
  
  /**
   * admin 주문목록 상세정보 가져오기 (Ajax)
   * id="listAdminPDO" parameterType="int" resultMap="PaymentTotalMap"
   * @param memberno 조회할 memberno
   * @return ArrayList&lt;PaymentTotalVO&gt; 객체
   * */
  public ArrayList<PaymentTotalVO> listAdminPDO(Map<String,Object> map);
  
  public String paymentAjax(Map<String,Object> map);

  public String paymentDetailsAjax(int memberno, Map<String,Object> map);


  public boolean create(Map<String, Object> map);
  
  /**
   * admin 목록 카운트(멤버)
   * id="member_cnt" parameterType="Map" resultType="int"
   * @param map
   * @return 멤버목록 count
   * */
  public int member_cnt(Map<String, Object> map);
  
  /**
   * admin 목록 카운트(주문)
   * id="payment_cnt" parameterType="Map" resultType="int"
   * @param map
   * @return 주문목록 count
   * */
  public int payment_cnt(Map<String, Object> map);
}
