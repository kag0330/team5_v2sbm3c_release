package dev.mvc.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.apache.groovy.parser.antlr4.GroovyParser.ThisFormalParameterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.email.EmailProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("login")
public class LoginCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  private EmailProcInter emailProc;

  @Autowired
  private PasswordEncoder pe;

  @GetMapping("signin")
  public String login(HttpServletRequest request, Model model, @RequestParam(value = "error", required = false) String error) {
    if ("disabled".equals(error)) {
      Alert message = new Alert("회원탈퇴처리된 회원입니다.", "/signin", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("ck_id")) {
          model.addAttribute("ck_id", cookie.getValue());
        } else if (cookie.getName().equals("ck_pw")) {
          model.addAttribute("ck_pw", cookie.getValue());
        } else if (cookie.getName().equals("save")) {
          model.addAttribute("save", cookie.getValue());
        }
      }
    }
    return "login/signin";
  }

  @PostMapping("login")
  public String loginProc() {
    // Spring Security 에서 처리
    return "";
  }

  @GetMapping("signup")
  public String signUp(MemberVO memberVO) {
    return "login/signup";
  }

  @ResponseBody
  @GetMapping("checkId")
  public int checkId(@RequestParam("id") String id) {
    return this.memberProc.checkId(id);
  }

  @PostMapping("signup")
  public String signUpProc(MemberVO memberVO, Model model) {
    MultipartFile mf = memberVO.getMf();
    String file = mf.getOriginalFilename();
    String filename = "";
    
    System.out.println("file:" + file);

    memberVO.setPw(pe.encode(memberVO.getPw()));

    if (file != "" && !Tool.isImage(file)) {// 업로드 가능한 파일인지 검사
      Alert message = new Alert("업로드가 불가능한 파일입니다. 이미지 파일을 등록해주세요.", "signup", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    } else if (file != "" && Tool.isImage(file)) {
      filename = Tool.saveFileSpring(memberVO.getMf());
      memberVO.setThumb(filename);
    }

    if (memberProc.create(memberVO) == 0) {
      Alert message = new Alert("이미 존재하는 아이디입니다.", "signup", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    Alert message = new Alert("회원가입 성공.", "/", RequestMethod.GET, null);
    return DefaultCont.showMessageAndRedirect(message, model);
  }

  @GetMapping("findid")
  public String findid() {
    return "login/findid";
  }

  @PostMapping("findid")
  public String findidProc(@RequestParam("email") String email, RedirectAttributes ra) {
    ArrayList<String> ids = this.memberProc.findid(email);
    if (ids.isEmpty()) {
      ra.addFlashAttribute("code", "findidfail");
    } else {
      ra.addFlashAttribute("code", "findidsuccess");
      ra.addFlashAttribute("ids", ids);
    }
    return "redirect:/login/finddone";
  }

  @GetMapping("findpw")
  public String findpw() {
    return "login/findpw";
  }

  @PostMapping("findpw")
  public String findpwProc(@RequestParam("id") String id, @RequestParam("email") String email, RedirectAttributes ra) {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("email", email);
    if (this.memberProc.findpw(map) == null) {
      ra.addFlashAttribute("code", "findpwfail");
      return "redirect:/login/finddone";
    }else {
      try {
        if(!email.equals("pass")) {
          this.emailProc.send_pw(email, map.get("originpw").toString());
        } 
      } catch (Exception e) {
        e.printStackTrace();
      }
      ra.addFlashAttribute("code", "findpwsuccess");
      ra.addFlashAttribute("pw", map.get("originpw"));
    }
    return "redirect:/login/finddone";
  }

  @GetMapping("finddone")
  public String finddone() {
    return "login/finddone";
  }

}
