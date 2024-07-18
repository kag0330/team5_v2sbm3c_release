package dev.mvc.admin.analysis;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService {
  
  @Autowired
  private AnalysisRepository analysisRepository;

  public AnalysisEntity read(Integer analysisno) {
    Optional<AnalysisEntity> analysis = this.analysisRepository.findById(analysisno);
    return analysis.get();
  }
  
  public List<AnalysisEntity> list(int page){
    int pageSize = 5;
    int startRow = (page-1) * pageSize;
    int endRow = startRow + pageSize;
    return this.analysisRepository.findAllByOrderByAnalysisnoDesc(startRow, endRow);
  }
  
  public long count() {
    return this.analysisRepository.count();
  }
  
  public void delete(Integer analysisno) {
    this.analysisRepository.deleteById(analysisno);
  }
  
  public boolean create(AnalysisDTO analysisDTO) {
    AnalysisEntity analysisEntity = AnalysisEntity.builder()
                                          .code(analysisDTO.getCode())
                                          .title(analysisDTO.getTitle())
                                          .content(analysisDTO.getContent())
                                          .chart(analysisDTO.getChart())
                                          .data(analysisDTO.getData())
                                          .xlabel(analysisDTO.getXlabel())
                                          .ylabel(analysisDTO.getYlabel())
                                          .legend(analysisDTO.getLegend())
                                          .rdate(new Date())
                                          .build();
    
    AnalysisEntity ae = this.analysisRepository.save(analysisEntity);
    if(ae != null) {
      return true;
    }else {
      return false;
    }
  }
  
  public boolean update(AnalysisDTO analysisDTO) {
    Optional<AnalysisEntity> originAnalysisEntity =  this.analysisRepository.findById(analysisDTO.getAnalysisno());
    if(originAnalysisEntity.isPresent()) {
      AnalysisEntity analysisEntity = AnalysisEntity.builder()
          .analysisno(analysisDTO.getAnalysisno())
          .code(analysisDTO.getCode())
          .title(analysisDTO.getTitle())
          .content(analysisDTO.getContent())
          .chart(analysisDTO.getChart())
          .data(analysisDTO.getData())
          .xlabel(analysisDTO.getXlabel())
          .ylabel(analysisDTO.getYlabel())
          .legend(analysisDTO.getLegend())
          .rdate(analysisDTO.getRdate())
          .build();
      this.analysisRepository.save(analysisEntity);
      return true;
    }
    return false;
  }
}

