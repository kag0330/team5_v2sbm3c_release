package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface PaymentTotalDAOInter {
  /**
   * 주문정보 가져오기(CS NULL)
   * id="list" parameterType="Map" resultMap="PaymentTotalMap"
   * @param  map memberno, search, startDate, endDate
   * @return ArrayList&lt;PaymentTotalVO&gt; 객체
   * */
  public ArrayList<PaymentTotalVO> list(HashMap<String, Object> map);
  
  /**
   * 주문정보 가져오기(CS NOT NULL)
   * id="cslist" parameterType="map" resultMap="PaymentTotalMap" resultOrdered="true"*/
  public ArrayList<PaymentTotalVO> cslist(HashMap<String, Object> map);
  
  /**
   * 
   * id="selectPaymentDetailsByPaymentNo" parameterType="map" resultMap="PaymentDetailsOptionMap"
   * */
  public ArrayList<PaymentDetailsOptionVO> selectPaymentDetailsByPaymentNo(Map<String, Object> map);
  
  
  
  /**
   * 주문목록 count 가져오기
   * id="count" resultType="int" parameterType="word"
   * @return 주문정보가 있는 멤버들의 count
   * */
  public int count(String word);
  
  
  /**
   * 페이징처리를 위한 admin 주문목록
   * id="listAdminPaging" resultType="dev.mvc.paymentTotal.PaymentTotalVO" parameterType="Map"
   * @param map word, start_num, end_num
   * @return ArrayList&lt;PaymentTotalVO&gt; 객체 
   * */
  public ArrayList<PaymentTotalVO> listAdminPaging(Map<String, Object> map);
  
  
  /**
   * admin 주문목록 상세정보 가져오기 (Ajax)
   * id="listAdminPDO" parameterType="Map" resultMap="PaymentTotalMap"
   * @param memberno 조회할 memberno
   * @return ArrayList&lt;PaymentTotalVO&gt; 객체
   * */
  public ArrayList<PaymentTotalVO> listAdminPDO(Map<String, Object> map);
  
  
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
