package dev.mvc.admin.analysis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;

@Controller
@RequestMapping("admin/analysis")
public class AnalysisCont {
  @Autowired
  private AnalysisService analysisService;
  
  @GetMapping("read")
  public String payment(Model model, 
                        @RequestParam(name = "analysisno", required = false, defaultValue = "0")int analysisno) {
    AnalysisEntity analysisEntity = this.analysisService.read(analysisno);
    
    model.addAttribute("analysis", analysisEntity);
    
    if(analysisEntity.getChart() == 1) {
      return "admin/analysis/read_column_simple";
    }else if(analysisEntity.getChart() == 2) {
      return "admin/analysis/read_column_time";
    }else if(analysisEntity.getChart() == 3) {
      return "admin/analysis/read_pie";
    }else {
      return "admin/analysis/read_line";
    }
    
  }
  
  @GetMapping("create")
  public String create(Model model, AnalysisDTO analysisDTO) {
    return "admin/analysis/create";
  }
  
  @PostMapping("create")
  public String createProc(AnalysisDTO analysisDTO, Model model) {
    if(!this.analysisService.create(analysisDTO)) {
      Alert message = new Alert("알 수 없는 오류로 차트 생성에 실패하였습니다.", "/admin/analysis/list", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model); 
    }
    return "redirect:/admin/analysis/list";
  }
  
  @GetMapping("list")
  public String list(Model model, @RequestParam(name = "page", defaultValue = "1", required = false)int page) {
    List<AnalysisEntity> list = this.analysisService.list(page);
    
    int totalItems = (int) this.analysisService.count();
    int totalPages = (int) Math.ceil((double) totalItems / 5);
    
    model.addAttribute("list", list);
    model.addAttribute("page", page);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("hasPrevious", page > 1);
    model.addAttribute("hasNext", page < totalPages);
    return "admin/analysis/list";
  }
  
  @GetMapping("delete")
  public String delete(Model model, @RequestParam(name = "analysisno")Integer analysisno) {
    model.addAttribute("analysis", this.analysisService.read(analysisno));
    return "admin/analysis/delete";
  }
  
  @PostMapping("delete")
  public String deleteProc(@RequestParam(name = "analysisno")Integer analysisno) {
    this.analysisService.delete(analysisno);
    return "redirect:/admin/analysis/list";
  }
  
  @GetMapping("update")
  public String update(Model model, @RequestParam(name = "analysisno")Integer analysisno) {
    model.addAttribute("analysisDTO", this.analysisService.read(analysisno));
    return "admin/analysis/update";
  }
  
  @PostMapping("update")
  public String updateProc(AnalysisDTO analysisDTO, Model model) {
    if(!this.analysisService.update(analysisDTO)) {
      Alert message = new Alert("알 수 없는 오류로 차트 수정에 실패하였습니다.", "/admin/analysis/read?analysisno="+analysisDTO.getAnalysisno(), RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model); 
    }
   return "redirect:/admin/analysis/read?analysisno="+analysisDTO.getAnalysisno(); 
  }
}
