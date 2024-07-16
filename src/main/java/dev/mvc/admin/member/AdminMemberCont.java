package dev.mvc.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.delivery.DeliveryProcInter;
import dev.mvc.loginHistory.LoginHistoryProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberRole;
import dev.mvc.member.MemberVO;
import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/member")
public class AdminMemberCont {

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  /* =========================================== ModelAttribute START =========================================== */
  /**
   * 목록(List) 출력 <br>
   * http://localhost:9093/admin/member/** <br>
   * http://54.180.175.50:9093/admin/member/**
   * 
   * @param now_page 현재 페이지
   * @param word     검색어
   * @param key      키워드 선택
   */
  @ModelAttribute("list")
  public ArrayList<MemberVO> list(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page, 
                                  @RequestParam(name = "word",     required = false, defaultValue = "")  String word, 
                                  @RequestParam(name = "key",      required = false, defaultValue = "")  String key) {
    
    ArrayList<MemberVO> list = this.memberProc.list_search_paging(word, key, now_page, MemberVO.RECORD_PER_PAGE);
    return list;
  }

  
  
  /**
   * 페이징(Pagination) 출력 <br> 
   * http://localhost:9093/admin/member/** <br>
   * http://54.180.175.50:9093/admin/member/**
   * 
   * @param request  HttpServletRequest 객체
   * @param now_page 현재 페이지
   * @param word     검색어
   * @param key      키워드 선택
   * @param memberno 선택된 회원의 memberno 번호
   */
  @ModelAttribute("paging")
  public String paging(HttpServletRequest request, 
                       @RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
                       @RequestParam(name = "word",     required = false, defaultValue = "")  String word, 
                       @RequestParam(name = "key",      required = false, defaultValue = "")  String key, 
                       @RequestParam(name = "memberno", required = false, defaultValue = "0") Integer memberno) {
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    String path = request.getServletPath();
    
    map.put("word", word);
    map.put("key", key);
    
    int search_count = this.memberProc.list_search_count(map);
    return this.memberProc.pagingBox(memberno, now_page, word, key, path, search_count);
  }

  
  
  /**
   * 검색어 검색 갯수 조회 <br>
   * http://localhost:9093/admin/member/** <br>
   * http://54.180.175.50:9093/admin/member/**
   * 
   * @param now_page 현재 페이지
   * @param word     검색어
   * @param key      키워드 선택
   */
  @ModelAttribute("count")
  public int count(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page, 
                   @RequestParam(name = "word",     required = false, defaultValue = "")  String word, 
                   @RequestParam(name = "key",      required = false, defaultValue = "")  String key) {

    HashMap<String, Object> map = new HashMap<String, Object>();
    
    map.put("word", word);
    map.put("key", key);
    
    return this.memberProc.list_search_count(map);
  }
  /* =========================================== ModelAttribute END =========================================== */
  /**
   * 목록 출력 <br>
   * http://localhost:9093/admin/member/list <br>
   * http://54.180.175.50:9093/admin/member/list
   */
  @GetMapping("list")
  public String list() {
    return "admin/member/list";
  }
  
  
  
  /**
   * 회원 정보 출력 <br>
   * http://localhost:9093/admin/member/read?memberno= <br>
   * http://54.180.175.50:9093/admin/member/read?memberno=
   * 
   * @param model    org.springframework.ui.Model 객체
   * @param memberno 선택된 회원의 memberno 번호
   */
  @GetMapping("read")
  public String read(Model model, @RequestParam("memberno") Integer memberno) {
    MemberVO memberVO = this.memberProc.readByMemberno(memberno);
    model.addAttribute("memberVO", memberVO);
    return "admin/member/read";
  }
  
  
  
  /**
   * 회원 정보 출력 <br>
   * http://localhost:9093/admin/member/update?memberno= <br>
   * http://54.180.175.50:9093/admin/member/update?memberno=
   * 
   * @param model    org.springframework.ui.Model 객체
   * @param memberno 선택된 회원의 memberno 번호
   */
  @GetMapping("update")
  public String update(Model model, @RequestParam("memberno") Integer memberno) {
    MemberVO memberVO = this.memberProc.readByMemberno(memberno);
    model.addAttribute("memberVO", memberVO);
    return "admin/member/update";
  }
  
  
  
  /**
   * 회원 정보 출력 Proc <br>
   * 
   * @param request HttpServletRequest 객체
   * @param model   org.springframework.ui.Model 객체
   * @param memberno 선택된 회원의 memberno 번호
   */
  @PostMapping("update")
  public String update(HttpServletRequest request, Model model, MemberVO memberVO) {
    MemberVO memberVOorigin = this.memberProc.readByMemberno(memberVO.getMemberno());
    String referer = request.getHeader("Referer");
    MultipartFile mf = memberVO.getMf();
    String file = mf.getOriginalFilename();
    String filename = "";
    
    if(memberVOorigin.getRole() == MemberRole.MASTER && memberVO.getRole() != MemberRole.MASTER) {
      if(this.memberProc.checkRoleMaster() <= 1) {
        Alert message = new Alert("MASTER 권한을 가진 계정은 최소 1개이상 존재해야 합니다.", referer, RequestMethod.GET, null);
        return DefaultCont.showMessageAndRedirect(message, model);
      }
    }
    
    if (!file.isEmpty() && !Tool.isImage(file)) {
      Alert message = new Alert("업로드가 불가능한 파일입니다. 이미지 파일을 등록해주세요.", referer, RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    } else if (!file.isEmpty() && Tool.isImage(file)) {
      filename = Tool.saveFileSpring(mf);
      memberVO.setThumb(filename); // 새로운 파일이 저장된 경우에만 thumb 값을 설정
    }
    
    if(this.memberProc.updateAdmin(memberVO) == 0) {
      Alert message = new Alert("알 수 없는 오류입니다.\n다시 시도해주세요.", referer, RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }

    return "redirect:" + referer;
  }

  
  
  /**
   * 회원 정보 삭제 <br>
   * http://localhost:9093/admin/member/delete?memberno= <br>
   * http://54.180.175.50:9093/admin/member/delete?memberno=
   * 
   * @param model    org.springframework.ui.Model 객체
   * @param memberno 선택된 회원의 memberno 번호
   */
  @GetMapping("delete")
  public String delete(Model model, @RequestParam("memberno") Integer memberno) {
    MemberVO memberVO = this.memberProc.readByMemberno(memberno);
    model.addAttribute("memberVO", memberVO);
    return "admin/member/delete";
  }

  
  
  /**
   * 회원 정보 삭제 Proc<br>
   * 
   * @param request  HttpServletRequest 객체
   * @param session  HttpSession session 객체
   * @param model    org.springframework.ui.Model 객체
   * @param memberno 선택된 회원의 memberno 번호
   */
  @PostMapping("delete")
  public String deleteProc(HttpServletRequest request, HttpSession session, Model model, 
                           @RequestParam("memberno") Integer memberno) {
    
    MemberVO memberVOlogin = (MemberVO) session.getAttribute("login");
    MemberVO memberVO = this.memberProc.readByMemberno(memberno);
    String referer = request.getHeader("Referer");
    
    //회원정보를 가져오지 못한경우
    if (memberVO == null) {
      Alert message = new Alert("알 수 없는 오류입니다.\n다시 시도해주세요.", referer, RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    
    //회원이 MASTER Role를 가지고 있을 경우
    if (memberVO.getRole() == MemberRole.MASTER) {
      if (this.memberProc.checkRoleMaster() <= 1) {
        Alert message = new Alert("MASTER 권한을 가진 계정은 최소 1개이상 존재해야 합니다.", referer, RequestMethod.GET, null);
        return DefaultCont.showMessageAndRedirect(message, model);
      }
    }
    
    //회원이 자신의 로그인 정보와 같을 경우
    if (memberVOlogin.getMemberno() == memberno) {
      Alert message = new Alert("자신의 계정은 삭제할 수 없습니다.\nMASTER계정 또는 다른 ADMIN 계정을 이용하세요.", referer, RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    
    //삭제 쿼리가 실패한 경우
    if (this.memberProc.deleteAdmin(memberno) == 0) {
      Alert message = new Alert("삭제 실패!\n다시 시도해주세요.", referer, RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    
    //삭제 성공
    Alert message = new Alert("삭제 성공!\n삭제된 회원ID: " + memberVO.getId(), "/admin/member/list", RequestMethod.GET, null);
    return DefaultCont.showMessageAndRedirect(message, model);
  }
  
  

  /**
   * 회원 권한 상승 <br>
   * http://localhost:9093/admin/member/decrease?memberno=
   * http://54.180.175.50:9093/admin/member/decrease?memberno=
   * 
   * @param request  HttpServletRequest 객체
   * @param model    org.springframework.ui.Model 객체
   * @param memberno 선택된 회원의 memberno 번호
   */
  @GetMapping("decrease")
  public String decrease(HttpServletRequest request, Model model, @RequestParam("memberno") Integer memberno) {
    String referer = request.getHeader("Referer");
    MemberVO memberVO = this.memberProc.readByMemberno(memberno);
    
    
    if (memberVO == null) {
      Alert message = new Alert("알 수 없는 오류입니다.\n다시 시도해주세요.", referer, RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    
    
    this.memberProc.changeRole(memberVO, true);
    return "redirect:" + referer;
  }

  
  
  /**
   * 회원 권한 하락 <br>
   * http://localhost:9093/admin/member/increase?memberno=
   * http://54.180.175.50:9093/admin/member/increase?memberno=
   * 
   * @param request  HttpServletRequest 객체
   * @param model    org.springframework.ui.Model 객체
   * @param memberno 선택된 회원의 memberno 번호
   */
  @GetMapping("increase")
  public String increase(HttpServletRequest request, Model model, @RequestParam("memberno") Integer memberno) {
    String referer = request.getHeader("Referer");
    MemberVO memberVO = this.memberProc.readByMemberno(memberno);
    
    
    if (memberVO == null) {
      Alert message = new Alert("알 수 없는 오류입니다.\n다시 시도해주세요.", referer, RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    
    
    this.memberProc.changeRole(memberVO, false);
    return "redirect:" + referer;
  }
}
