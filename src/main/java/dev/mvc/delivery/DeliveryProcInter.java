package dev.mvc.delivery;

import java.util.ArrayList;

public interface DeliveryProcInter {
  /**
   * 배송지 목록 출력
   * id="list_all" parameterType="int" resultType="dev.mvc.delivery.DeliveryVO"
   * @param memberno 검색할 회원의 memberno 
   * @return ArrayList&lt;DeliveryVO&gt; 객체
   * */
  public ArrayList<DeliveryVO> list_all(int memberno);
  
  /**
   * 배송지 목록 카운트 출력
   * id="count" parameterType="int" resultType="int"
   * @param memberno 검색할 회원의 memberno
   * @return 검색된 회원의 배송지 목록 갯수
   * */
  public int count(int memberno);
  
  /**
   * 배송지 목록 삭제
   * id="delete" parameterType="int"
   * @param deliveryno 삭제할 deliveryno 기본키
   * @return 삭제된 쿼리 갯수
   * */
  public int delete(int deliveryno);
  
  /**
   * 배송지 등록
   * id="create" parameterType="dev.mvc.delivery.DeliveryVO"
   * @param DeliveryVO 객체
   * @return 성공한 쿼리 갯수
   * */
  public int create(DeliveryVO deliveryVO);
  
  /**
   * 배송지 조회
   * id="read" parameterType="int" resultType="dev.mvc.delivery.DeliveryVO"
   * @param deliveryno 조회할 deliveryno 기본키
   * @return DeliveryVO 객체
   * */
  public DeliveryVO read(int deliveryno);
  
  /**
   * 배송지 업데이트
   * id="update" parameterType="dev.mvc.delivery.DeliveryVO"
   * @param DeliveryVO 객체
   * @return 성공한 쿼리 갯수
   * */
  public int update(DeliveryVO deliveryVO);
  
  /**
   * 어드민이 회원 삭제 처리
   * id="deleteAdmin" parameterType="int"
   * @param memberno 삭제할 회원의 memberno 기본키
   * @return 삭제된 쿼리 갯수
   * */
  public int deleteAdmin(int memberno);
}
