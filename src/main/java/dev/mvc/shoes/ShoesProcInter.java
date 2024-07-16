package dev.mvc.shoes;

import java.util.ArrayList;

import dev.mvc.category.CategoryVO;
import dev.mvc.option.OptionVO;
import dev.mvc.review.ReviewVO;

public interface ShoesProcInter {
  /**
   * 등록 insert id="create" parameterType="Map"
   * 
   * @param shoesVO, ArrayList<Integer>
   * @return 등록 성공 여부
   */
  public int admin_create(ShoesVO shoesVO, ArrayList<Integer> categorylist);

  /**
   * 수정 update id="update" parameterType="dev.mvc.shoes.ShoesVO"
   * 
   * @param shoesVO
   * @return 수정 성공 여부
   */
  public int admin_update(ShoesVO shoesVO);

  /**
   * 삭제 delete id="delete" parameterType="Integer"
   * 
   * @param Integer
   * @return 삭제 성공 여부
   */
  public int admin_delete(int shoesno);
  

  /**
   * 검색된 레코드 수 id="list_search_count" resultType="int" parameterType="Map"
   * 
   * @param map
   * @return 검색된 레코드 수
   */
  public int list_search_count(int categoryno, String word);

  /**
   * 신발 목록 정보 id="list_search_paging" resultType="dev.mvc.shoes.ShoesVO"
   * parameterType="Map"
   * 
   * @param map
   * @return 신발 목록 정보
   */
  
  public ArrayList<ShoesVO> list_search_paging(int categoryno, String word);

  
  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block);

  /**
   * 조회 id="read" resultMap="shoesRead" parameterType="Integer"
   * 
   * @param shoesno
   * @return ShoesAllVO
   */
  
  public ShoesAllVO read(int shoesno);

  
  
  public ShoesVO admin_read(int shoesno);
  
  public int admin_list_search_count(String word);

  public ArrayList<ShoesVO> admin_list_search_paging(String word, int now_page, int record_per_page);
  
  
  
  /**
   * 전체 목록 select id="list_all" resultType="dev.mvc.shoes.ShoesVO"
   * 
   * @return 레코드 목록
   */
  public ArrayList<ShoesVO> admin_list_all();
  
  public int parent_count(int shoesno);
  
  public ArrayList<ShoesVO> inquiry_select(String word);


  public ArrayList<ReviewVO> Shoes_reviews(int shoesno);
  
  public ArrayList<ShoesVO> Shoes_discount();
  
  public ArrayList<CategoryVO> Shoes_man(int categoryno);
  
  public ArrayList<CategoryVO> Shoes_girl(int categoryno);
  
  
}
  
