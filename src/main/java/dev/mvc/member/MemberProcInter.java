package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MemberProcInter {
  /**
   * 멤버 생성 <br>
   * id="create" parameterType="dev.mvc.member.MemberVO" <br>
   * 
   * @param memberVO 객체
   * @return 성공한 쿼리 갯수
   */
  public int create(MemberVO memberVO);

  /**
   * 중복 아이디 검사<br>
   * id="checkId" parameterType="String" resultType="int"<br>
   * 
   * @param 검색할 Id
   * @return 검색된 쿼리 갯수
   */
  public int checkId(String id);
  
  /**
   * Master 권한 체크
   * id="checkRoleMaster" resultType="int"<br>
   * @return 검색된 쿼리 갯수
   * */
  public int checkRoleMaster();
  
  /**
   * 아이디로 회원 찾기<br>
   * id="readById" parameterType="String" resultType="dev.mvc.member.MemberVO"<br>
   * 
   * @param 검색할 id명
   * @return MemberVO 객체
   * */
  public MemberVO readById(String id);
  
  /**
   * Memberno로 회원 찾기<br>
   * id="readByMemberno" parameterType="int"
   * resultType="dev.mvc.member.MemberVO"<br>
   * 
   * @param 검색할 id명
   * @return MemberVO 객체
   */
  public MemberVO readByMemberno(int id);
  
  /**
   * 회원정보 수정<br>
   * id="update" parameterType="dev.mvc.member.MemberVO"
   * 
   * @param MemberVO 객체
   * @return 성공한 쿼리 갯수
   * */
  public int update(MemberVO memberVO);
  
  /**
   * 회원정보 수정 관리자 페이지<br>
   * id="updateAdmin" parameterType="dev.mvc.member.MemberVO"
   * @param MemberVO 객체
   * @return 성공한 쿼리 갯수
   * */
  public int updateAdmin(MemberVO memberVO);
  
  /**
   * 회원 목록 출력
   * id="list_all" resultType="dev.mvc.member.memberVO"
   * @return MemberVO 객체
   * */
  public ArrayList<MemberVO> list_all();
  
  /**
   * 일반 회원이 회원 삭제
   * id="delete" parameterType="int"
   * @param memberno
   * @return 성공한 쿼리 갯수
   * */
  public int delete(int memberno);
  
  /**
   * 관리자가 회원 삭제
   * id="deleteAdmin" parameterType="int"
   * @param memberno
   * @return 성공한 쿼리 갯수
   * */
  public int deleteAdmin(int memberno);
  
  /**
   * 선택 항목 검색된 레코드 수
   * id="list_search_count" resultType="int" parameterType="Map"
   * @param Map 
   * @return 검색된 레코드 수
   * */
  public int list_search_count(Map<String, Object> map);
  
  /**
   * 검색목록 페이징
   * select id="list_search_paging" resultType="dev.mvc.cate.CateVO" parameterType="Map"
   * @param map
   * @return 조회한 레코드 목록
   */
  public ArrayList<MemberVO> list_search_paging(String word, String key, int now_page, int record_per_page);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param memberno 카테고리번호 
   * @param now_page  현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명
   * @param search_count 검색 레코드수    
   * @return 페이징 생성 문자열
   */
  String pagingBox(int memberno, int now_page, String word, String key, String list_file, int search_count);

  /**
   * 아이디 찾기 email이 일치하는 id 찾기
   * id="findid" resultType="String" parameterType="String"
   * @param email
   * @return id
   * */
  public ArrayList<String> findid(String email);
  
  /**
   * 비밀번호 찾기(변경) id, email이 일치하는 pw 변경하기
   * id="findpw" parameterType="Map"
   * @param Map
   * @return 성공한 쿼리 갯수
   * */
  public HashMap<String, Object> findpw(HashMap<String, Object> map);
  
  /**
   * Role 변경 아이콘 클릭
   * id="changeRole" parameterType="Map"
   * @param MemberVO 객체
   * @param isIncrease 1:UP, 0:DOWN
   * @return 성공한 쿼리 갯수
   * */
  public int changeRole(MemberVO memberVO, Boolean isIncrease);
  
}