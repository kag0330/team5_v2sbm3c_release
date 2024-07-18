package dev.mvc.team5;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.paymentTotal.PaymentTotalProcInter;


@SpringBootTest
class Team5_v2sbm3cApplicationTests {
  
  @Autowired
  @Qualifier("dev.mvc.paymentTotal.PaymentTotalProc")
  private PaymentTotalProcInter paymentTotalProc;
}
