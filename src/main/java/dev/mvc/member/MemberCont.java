package dev.mvc.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  @Autowired
  private PasswordEncoder pe;

  @GetMapping("checkpw")
  public String checkpw() {
    return "member/checkpw";
  }

  @PostMapping("checkpw")
  public String checkpwProc(RedirectAttributes ra, HttpSession session, Model model, @RequestParam("pw") String pw) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    memberVO = memberProc.readById(memberVO.getId());

    if (!pe.matches(pw, memberVO.getPw())) {
      Alert message = new Alert("틀린 비밀번호입니다.", "/member/checkpw", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }

    ra.addFlashAttribute("auth", "success");
    return "redirect:/member/update";
  }

  @GetMapping("update")
  public String update(HttpSession session, Model model, @ModelAttribute("auth") String auth) {
    if (auth == null || auth != "success") {
      Alert message = new Alert("잘못된 접근입니다. 다시 접속하세요.", "/member/checkpw", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    memberVO = memberProc.readById(memberVO.getId());
    model.addAttribute("memberVO", memberVO);
    return "member/update";
  }

  @PostMapping("update")
  public String updateProc(MemberVO memberVO, Model model, HttpSession session) {
    MemberVO memberVOorigin = memberProc.readById(memberVO.getId());
    MultipartFile mf = memberVO.getMf();
    String file = mf.getOriginalFilename();
    String filename = "";

    // 비밀번호 암호화
    if (memberVO.getPw().isEmpty()) {
      memberVO.setPw(memberVOorigin.getPw());
    }else {
      memberVO.setPw(pe.encode(memberVO.getPw()));
    }

    // 파일 검사 및 저장
    if (!file.isEmpty() && !Tool.isImage(file)) {
      Alert message = new Alert("업로드가 불가능한 파일입니다. 이미지 파일을 등록해주세요.", "signup", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    } else if (!file.isEmpty() && Tool.isImage(file)) {
      filename = Tool.saveFileSpring(mf);
      memberVO.setThumb(filename); // 새로운 파일이 저장된 경우에만 thumb 값을 설정
    }

    // 회원 생성 처리
    if (memberProc.update(memberVO) == 0) {
      Alert message = new Alert("알 수 없는 에러", "member/update", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }

    // 성공 메시지
    session.invalidate();
    Alert message = new Alert("회원정보 수정 성공\n다시 로그인 해주세요.", "/", RequestMethod.GET, null);
    return DefaultCont.showMessageAndRedirect(message, model);
  }

  @GetMapping("info")
  public String info() {
    return "member/info";
  }

  @GetMapping("delete")
  public String delete() {
    return "member/delete";
  }

  @PostMapping("delete")
  public String deleteProc(Model model, @RequestParam("pw") String pw, HttpSession session, RedirectAttributes ra) {

    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    int memberno = memberVO.getMemberno();

    if (pe.matches(pw, memberVO.getPw())) {
      if (this.memberProc.delete(memberno) == 0) {
        Alert message = new Alert("알 수 없는 에러", "/member/delete", RequestMethod.GET, null);
        return DefaultCont.showMessageAndRedirect(message, model);
      }
    } else {
      ra.addFlashAttribute("code", 0);
      return "redirect:/member/delete";
    }
    session.invalidate();
    Alert message = new Alert("회원탈퇴처리가 완료되었습니다.", "/", RequestMethod.GET, null);
    return DefaultCont.showMessageAndRedirect(message, model);
  }

}