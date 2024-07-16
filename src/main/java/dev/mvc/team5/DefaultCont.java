package dev.mvc.team5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.mvc.tool.Alert;
import jakarta.servlet.http.HttpSession;

@Controller
public class DefaultCont {
  
  /** Alert 창 띄우기 
   * 
   * @param params alert에 띄울 데이터들
   * @param model  org.springframework.ui.Model 객체
   * */
  public static String showMessageAndRedirect(final Alert params, Model model) {
    model.addAttribute("params", params);
    return "alert";
  }

  
  
  @GetMapping({"", "/"}) // http://localhost:9091/
  public String index(HttpSession session, Model model) {
    return "index"; // /templates/index.html
  }
  

  @GetMapping(value = "/admin") //http://localhost:9091/admin 
  public String admin(Model model) {  
    return "admin/index"; // /templates/admin/index.html
  }
  
  
  @GetMapping("authentication") // http://localhost:9091/authentication
  public String authentication() {
    return "authentication"; // /templates/authentication.html
  }
  
  
  @GetMapping("authorization") // http://localhost:9091/authorization
  public String authorization() {
    return "authorization"; // /templates/authorization.html
  }
  
  

}
