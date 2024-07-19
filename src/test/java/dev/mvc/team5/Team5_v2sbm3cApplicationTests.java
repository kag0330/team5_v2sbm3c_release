package dev.mvc.team5;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.admin.analysis.AnalysisEntity;
import dev.mvc.admin.analysis.AnalysisRepository;
import dev.mvc.paymentTotal.PaymentTotalProcInter;


@SpringBootTest
class Team5_v2sbm3cApplicationTests {
  
  @Autowired
  @Qualifier("dev.mvc.paymentTotal.PaymentTotalProc")
  private PaymentTotalProcInter paymentTotalProc;
	
  @Autowired
  private AnalysisRepository analysisRepository;
  
  @Test
  public void test1() {
    AnalysisEntity a = AnalysisEntity.builder()
                              .code(1)
                              .title("test1")
                              .content("testContent")
                              .build();
    analysisRepository.save(a);
  }
}
