package dev.mvc.paymentDetails;

import java.util.Map;

public interface PaymentDetailsDAOInter {
  /**
   * 주문 상세 정보 삭제
   * id="delete" parameterType="int"
   * @param paymentno
   * @return 삭제된 쿼리 갯수
   * */
  public int delete(int paymentno);
  
  /**
   * 
   * id="create" parameterType="Map"
   * */
  public int create(Map<String, Object> map);
}
