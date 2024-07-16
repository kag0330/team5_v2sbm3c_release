package dev.mvc.paymentDetails;

public interface PaymentDetailsProcInter {
  /**
   * 주문 상세 정보 삭제
   * id="delete" parameterType="int"
   * @param paymentno
   * @return 삭제된 쿼리 갯수
   * */
  public int delete(int paymentno);
}
