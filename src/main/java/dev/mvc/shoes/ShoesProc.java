package dev.mvc.shoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.category.CategoryVO;
import dev.mvc.option.OptionVO;
import dev.mvc.review.ReviewVO;

//@Component("dev.mvc.shoes.ShoesProc")
@Service("dev.mvc.shoes.ShoesProc")
public class ShoesProc implements ShoesProcInter {
  @Autowired
  private ShoesDAOInter shoesDAO;

  public ShoesProc() {
    // System.out.println("-> ShoesProc created.");
  }

  @Override
  public int admin_create(ShoesVO shoesVO, ArrayList<Integer> categorylist) {
    
    Map<String, Object> map = new HashMap<>();
    map.put("shoesVO", shoesVO);
    map.put("categorylist", categorylist);
    int cnt = this.shoesDAO.admin_create(map);
    return cnt;
  }

  @Override
  public int admin_update(ShoesVO shoesVO) {
    int cnt = this.shoesDAO.admin_update(shoesVO);
    return cnt;
  }

  @Override
  public int admin_delete(int shoesno) {
    int cnt = this.shoesDAO.admin_delete(shoesno);
    return cnt;
  }

  @Override
  public int list_search_count(int categoryno, String word) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("categoryno", categoryno);
    map.put("word", word);

    int cnt = this.shoesDAO.list_search_count(map);
    return cnt;
  }

  @Override
  public ArrayList<ShoesVO> list_search_paging(int categoryno, String word) {

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("categoryno", categoryno);
    map.put("word", word);

    ArrayList<ShoesVO> list = this.shoesDAO.list_search_paging(map);
    return list;
  }

  @Override
  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block) {
    int total_page = (int) (Math.ceil((double) search_count / record_per_page));
    int total_grp = (int) (Math.ceil((double) total_page / page_per_block));
    int now_grp = (int) (Math.ceil((double) now_page / page_per_block));
    int start_page = ((now_grp - 1) * page_per_block) + 1;
    int end_page = (now_grp * page_per_block);
    
    StringBuffer str = new StringBuffer();

    str.append("<style type='text/css'>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    text-align: center;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    text-align: center;");
    str.append("    background-color: #668db4;");
    str.append("    color: #FFFFFF;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");

    int _now_page = (now_grp - 1) * page_per_block;
    if (now_grp >= 2) {
      str.append("<span class='span_box_1'><A href='" + list_file + "?word=" + word + "&now_page=" + _now_page
          + "'>이전</A></span>");
    }

    for (int i = start_page; i <= end_page; i++) {
      if (i > total_page) {
        break;
      }

      if (now_page == i) {
        str.append("<span class='span_box_2'>" + i + "</span>");
      } else {
        str.append("<span class='span_box_1'><A href='" + list_file + "?word=" + word + "&now_page=" + i + "'>" + i
            + "</A></span>");
      }
    }

    _now_page = (now_grp * page_per_block) + 1; // 최대 페이지수 + 1
    if (now_grp < total_grp) {
      str.append("<span class='span_box_1'><A href='" + list_file + "?word=" + word + "&now_page=" + _now_page
          + "'>다음</A></span>");
    }
    str.append("</DIV>");

    return str.toString();
  }

  @Override
  public ShoesAllVO read(int shoesno) {
    ShoesAllVO shoesAllVO = this.shoesDAO.read(shoesno);
    return shoesAllVO;
  }


  @Override
  public ShoesVO admin_read(int shoesno) {
    ShoesVO shoesVO = this.shoesDAO.admin_read(shoesno);
    return shoesVO;
  }

  @Override
  public int admin_list_search_count(String word) {
    int cnt = this.shoesDAO.admin_list_search_count(word);
    return cnt;
  }

  @Override
  public ArrayList<ShoesVO> admin_list_search_paging(String word, int now_page, int record_per_page) {
    int begin_of_page = (now_page - 1) * record_per_page;

    int start_num = begin_of_page + 1;

    int end_num = begin_of_page + record_per_page;

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("word", word);
    map.put("start_num", start_num);
    map.put("end_num", end_num);

    ArrayList<ShoesVO> list = this.shoesDAO.admin_list_search_paging(map);
    return list;
  }

  @Override
  public ArrayList<ShoesVO> admin_list_all() {
    ArrayList<ShoesVO> list = this.shoesDAO.admin_list_all();
    return list;
  }

  @Override
  public int parent_count(int shoesno) {
    int cnt = this.shoesDAO.parent_count(shoesno);
    return cnt;
  }

  @Override
  public ArrayList<ShoesVO> inquiry_select(String word) {
    ArrayList<ShoesVO> list = this.shoesDAO.inquiry_select(word);
    return list;
  }

  @Override
  public ArrayList<ReviewVO> Shoes_reviews(int shoesno) {
    ArrayList<ReviewVO> list = this.shoesDAO.Shoes_reviews(shoesno);
    return list;
  }

  @Override
  public ArrayList<ShoesVO> Shoes_discount() {
    ArrayList<ShoesVO> list = this.shoesDAO.Shoes_discount();
    return list;
  }

  @Override
  public ArrayList<CategoryVO> Shoes_man(int categoryno) {
    ArrayList<CategoryVO> list = this.shoesDAO.Shoes_man(categoryno);
    return list;
  }

  @Override
  public ArrayList<CategoryVO> Shoes_girl(int categoryno) {
    ArrayList<CategoryVO> list = this.shoesDAO.Shoes_girl(categoryno);
    return list;
  }
  
  
}
