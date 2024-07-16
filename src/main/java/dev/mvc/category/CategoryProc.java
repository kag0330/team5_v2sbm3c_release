package dev.mvc.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.category.CategoryProc")
public class CategoryProc implements CategoryProcInter {

  @Autowired
  private CategoryDAOInter categoryDAO;

  @Override
  public ArrayList<String> name_list() {
    ArrayList<String> name = this.categoryDAO.name_list();
    return name;
  }

  @Override
  public int parent_count(int categoryno) {
    int cnt = this.categoryDAO.parent_count(categoryno);
    return cnt;
  }

  @Override
  public int parent_categoryno(String name) {
    int categoryno = this.categoryDAO.parent_categoryno(name);
    return categoryno;
  }

  @Override
  public ArrayList<String> subname_list(int categoryno) {
    ArrayList<String> name = this.categoryDAO.subname_list(categoryno);
    return name;
  }

  @Override
  public int create(CategoryVO categoryVO) {
    int cnt = this.categoryDAO.create(categoryVO);
    return cnt;
  }

  @Override
  public ArrayList<CategoryVO> list_all() {
    ArrayList<CategoryVO> list = this.categoryDAO.list_all();
    return list;
  }

  @Override
  public CategoryVO read(int categoryno) {
    CategoryVO list = this.categoryDAO.read(categoryno);
    return list;
  }

  @Override
  public int update(CategoryVO categoryVO) {
    int cnt = this.categoryDAO.update(categoryVO);
    return cnt;
  }

  @Override
  public int delete(int categoryno) {
    int cnt = this.categoryDAO.delete(categoryno);
    return cnt;
  }

  @Override
  public ArrayList<CategoryVO> list_all_name() {
    ArrayList<CategoryVO> list = this.categoryDAO.list_all_name();
    return list;
  }

  @Override
  public ArrayList<CategoryVO> list_all_subname(String name) {
    ArrayList<CategoryVO> list = this.categoryDAO.list_all_subname(name);
    return list;
  }

  @Override
  public ArrayList<CategoryVO> list_search(String word) {
    ArrayList<CategoryVO> list = this.categoryDAO.list_search(word);
    return list;
  }

  @Override
  public ArrayList<CategoryVO> list_search_paging(String word, int now_page, int record_per_page) {
    int begin_of_page = (now_page - 1) * record_per_page;

    int start_num = begin_of_page + 1;

    int end_num = begin_of_page + record_per_page;

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("word", word);
    map.put("start_num", start_num);
    map.put("end_num", end_num);

    ArrayList<CategoryVO> list = this.categoryDAO.list_search_paging(map);
    return list;
  }

  @Override
  public int list_search_count(String word) {
    int cnt = this.categoryDAO.list_search_count(word);
    return cnt;
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
  public CategoryVO category_select(int categoryno) {
    CategoryVO categoryVO = this.categoryDAO.category_select(categoryno);
    return categoryVO;
  
  }
  
  @Override
  public ArrayList<CategoryVO> select_name(ArrayList<Integer> categorynoList) {
    
    ArrayList<CategoryVO> categoryVO = this.categoryDAO.select_name(categorynoList);
    return categoryVO;
  }

  @Override
  public ArrayList<CategoryVO> select_subname(int categoryno) {
    ArrayList<CategoryVO> subname = this.categoryDAO.select_subname(categoryno);
    return subname;
  }

}
