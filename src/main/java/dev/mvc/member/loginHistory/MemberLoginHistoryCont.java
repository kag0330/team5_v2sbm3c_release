package dev.mvc.member.loginHistory;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.loginHistory.LoginHistoryProcInter;
import dev.mvc.loginHistory.LoginHistoryVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member/history")
public class MemberLoginHistoryCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  @Autowired
  @Qualifier("dev.mvc.loginHistory.LoginHistoryProc")
  private LoginHistoryProcInter lhProc;

  @GetMapping({"","/"})
  public String history(HttpSession session, Model model) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    if(this.lhProc.deleteOld(memberVO.getMemberno()) == 1) {
      System.out.println("delete ok");
    }
    ArrayList<LoginHistoryVO> lhList = this.lhProc.readByMembernoRdateDesc(memberVO.getMemberno());
    model.addAttribute("list", lhList);

    return "member/history";
  }
  
  @PostMapping("delete")
  @ResponseBody
  public ResponseEntity<String> delete(HttpSession session,
                                    @RequestParam("memberno")String memberno){
    int membernoInt = Integer.parseInt(memberno);
    MemberVO memberVO = (MemberVO)session.getAttribute("login");
    if(!memberno.equals(memberVO.getMemberno().toString())) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }
    if(this.lhProc.delete(membernoInt) == 0) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
    }
    return ResponseEntity.ok("Success");
  }

}
