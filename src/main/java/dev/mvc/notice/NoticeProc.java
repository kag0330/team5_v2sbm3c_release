package dev.mvc.notice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import dev.mvc.noticeFile.NoticeFileDAOInter;
import dev.mvc.noticeFile.NoticeFileVO;
import dev.mvc.tool.Tool;

@Service("dev.mvc.notice.NoticeProc")
public class NoticeProc implements NoticeProcInter {

  @Autowired
  private NoticeDAOInter noticeDAO;
  
  @Autowired
  private NoticeFileDAOInter noticeFileDAO;

  @Override
  public int create(Map<String, Object> map) {
    if (map.get("files") != null) {
      MultipartFile[] files = (MultipartFile[]) map.get("files");

      List<Map<String, Object>> fileList = new ArrayList<>();
      for (MultipartFile mf : files) {
        Map<String, Object> fileMap = new HashMap<>();
        String file = mf.getOriginalFilename();  
        fileMap.put("name", file);
        fileMap.put("ex", Tool.getFileExtension(file));
        fileMap.put("sizes", mf.getSize());
        fileMap.put("src", Tool.saveFileSpring(mf));
        fileList.add(fileMap);
      }
      map.put("files", fileList);
    }

    int cnt = this.noticeDAO.create(map);
    return cnt;
  }

  @Override
  public ArrayList<NoticeMemberVO> list_all() {
    ArrayList<NoticeMemberVO> list = this.noticeDAO.list_all();
    return list;
  }

  @Override
  public int file_count(int noticeno) {
    int cnt = this.noticeDAO.file_count(noticeno);
    return cnt;
  }

  @Override
  public int increased_views(int noticeno) {
    int cnt = this.noticeDAO.increased_views(noticeno);
    return cnt;
  }

  @Override
  public int views(int noticeno) {
    int views = this.noticeDAO.views(noticeno);
    return views;
  }

  @Override
  public NoticeMemberFileVO read(int noticeno) {
    NoticeMemberFileVO noticememberfileVO = this.noticeDAO.read(noticeno);
    return noticememberfileVO;
  }

  @Override
  public int list_search_count(String word) {
    int cnt = this.noticeDAO.list_search_count(word);
    return cnt;
  }

  @Override
  public ArrayList<NoticeMemberFileVO> list_search_paging(String word, int now_page, int record_per_page) {
    int begin_of_page = (now_page - 1) * record_per_page;

    int start_num = begin_of_page + 1;

    int end_num = begin_of_page + record_per_page;

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("word", word);
    map.put("start_num", start_num);
    map.put("end_num", end_num);

    ArrayList<NoticeMemberFileVO> list = this.noticeDAO.list_search_paging(map);

    return list;
  }

  @Override
  @Transactional
  public boolean update(Map<String, Object> map) {
    int noticeno = (int) map.get("noticeno");
    ArrayList<NoticeFileVO> originfiles = this.noticeFileDAO.findByNoticeno(noticeno);
    Integer[] deletefiles = (Integer[]) map.get("deletefiles");
    MultipartFile[] mpfs = (MultipartFile[]) map.get("files");
    
    if(this.noticeDAO.update(map) == 0)
      return false;
    
    if (mpfs != null) {
      for (MultipartFile mf : mpfs) {
        System.out.println(mf.getOriginalFilename());
        System.out.println(mf.getSize());
        NoticeFileVO nfVO = new NoticeFileVO();
        String file = mf.getOriginalFilename();  
        nfVO.setName(file);
        nfVO.setEx(Tool.getFileExtension(file));
        nfVO.setSizes(mf.getSize());
        nfVO.setSrc(Tool.saveFileSpring(mf));
        nfVO.setNoticeno(noticeno);
        
        if(!originfiles.contains(nfVO)) {
          if(this.noticeFileDAO.create(nfVO) == 0)
            return false;
        }
      }
    }
    
    if(deletefiles != null) {
      for(int deletefileno : deletefiles) {
        if(this.noticeFileDAO.deleteByNoticefileno(deletefileno) == 0)
          return false;
      }
    }
    
    return true;
  }

  @Override
  @Transactional
  public int delete(int noticeno) {
    if(this.noticeFileDAO.deleteByNoticeno(noticeno) == 0)
      return 0;
    if(this.noticeDAO.delete(noticeno) == 0)
      return 0;
    return 1;
  }

  @Override
  public int delete_file(int noticeno) {
    int cnt = this.noticeDAO.delete_file(noticeno);
    return cnt;
  }
  
  @Override
  public int insert_notice_file(Map<String,Object> map) {
    return this.noticeDAO.insert_notice_file(map);
  }

  @Override
  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page, int page_per_block) {
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
      str.append("<span class='span_box_1'><A href='" + list_file + "?word=" + word + "&now_page=" + _now_page + "'>이전</A></span>");
    }

    for (int i = start_page; i <= end_page; i++) {
      if (i > total_page) {
        break;
      }

      if (now_page == i) {
        str.append("<span class='span_box_2'>" + i + "</span>");
      } else {
        str.append("<span class='span_box_1'><A href='" + list_file + "?word=" + word + "&now_page=" + i + "'>" + i + "</A></span>");
      }
    }

    _now_page = (now_grp * page_per_block) + 1; // 최대 페이지수 + 1
    if (now_grp < total_grp) {
      str.append("<span class='span_box_1'><A href='" + list_file + "?word=" + word + "&now_page=" + _now_page + "'>다음</A></span>");
    }
    str.append("</DIV>");

    return str.toString();
  }

}
