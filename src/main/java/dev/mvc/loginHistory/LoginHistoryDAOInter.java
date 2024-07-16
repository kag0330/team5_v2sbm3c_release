package dev.mvc.loginHistory;

import java.util.ArrayList;

public interface LoginHistoryDAOInter {
  /**
   * 로그인 IP정보 기록<br>
   * id="create" parameterType="dev.mvc.loginHistory.LoginHistoryVO"<br>
   * 
   * @param LoginHistory 객체
   * @return 성공한 쿼리 갯수
   */
  public int create(LoginHistoryVO loginHistoryVO);

  /**
   * member에 저장된 로그인정보 갯수<br>
   * id="countByMemberno" parameterType="int" resultType="int"<br>
   * 
   * @param memberno
   * @return 성공한 쿼리 갯수
   */
  public int countByMemberno(int memberno);

  /**
   * 회원 로그인정보 기록일 최근순으로 정렬<br>
   * id="readByMembernoRdateDesc" parameterType="int" resultType="dev.mvc.loginHistory.LoginHistoryVO"
   * 
   * @param memberno
   * @return LoginHistoryVO 객체
   */
  public ArrayList<LoginHistoryVO> readByMembernoRdateDesc(int memberno);
  
  /**
   * member의 모든 데이터 삭제
   * id="delete" parameterType="int"
   * @param memberno
   * @return 성공한 쿼리 갯수
   * */
  public int delete(int memberno);
  
  /**
   * 최근 10건을 제외한 나머지 기록 삭제
   * id="deleteOld" parameterType="int"
   * @param memberno
   * @return 성공한 쿼리 갯수
   * */
   public int deleteOld(int memberno);
   
  
}