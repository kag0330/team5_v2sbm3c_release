package dev.mvc.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.member.MemberVO;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("delivery")
public class DeliveryCont {
  @Autowired
  @Qualifier("dev.mvc.delivery.DeliveryProc")
  private DeliveryProcInter deliveryProc;
  
  @GetMapping("NewAddress")
  public String newAddress(HttpSession session, Model model) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    model.addAttribute("deliveryList", this.deliveryProc.list_all(memberVO.getMemberno()));
    model.addAttribute("deliveryCount", this.deliveryProc.count(memberVO.getMemberno()));
    return "delivery/NewAddress";
  }
  
  @GetMapping("create")
  public String create() {
    return "delivery/create";
  }
  
  @PostMapping("create")
  public String createProc(HttpSession session, DeliveryVO deliveryVO) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    deliveryVO.setMemberno(memberVO.getMemberno());
    this.deliveryProc.create(deliveryVO);
    return "redirect:/delivery/NewAddress";
  }
  
  @PostMapping("delete")
  public ResponseEntity<String> delete(@RequestParam("deliveryno")Integer deliveryno) {
    if(this.deliveryProc.delete(deliveryno) == 0) {
      return ResponseEntity.status(500).body("삭제 실패");
    }
    return ResponseEntity.ok("삭제 완료");
    
  }
  
  @GetMapping("update/{deliveryno}")
  public String update(@PathVariable("deliveryno")Integer deliveryno, Model model) {
    model.addAttribute("deliveryVO", this.deliveryProc.read(deliveryno));
    return "delivery/update";
  }
  
  @PostMapping("update/{deliveryno}")
  public String updateProc(HttpSession session, @PathVariable("deliveryno")Integer deliveryno, DeliveryVO deliveryVO, Model model) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    deliveryVO.setDeliveryno(deliveryno);
    deliveryVO.setMemberno(memberVO.getMemberno());
    this.deliveryProc.update(deliveryVO);
    return "redirect:/delivery/NewAddress";
  }
}
