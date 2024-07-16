package dev.mvc.paymentDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.paymentDetails.PaymentDetailsProc")
public class PaymentDetailsProc implements PaymentDetailsProcInter {
  @Autowired
  private PaymentDetailsDAOInter paymentDetailsDAO;

  @Override
  public int delete(int paymentno) {
    return this.paymentDetailsDAO.delete(paymentno);
  }
}
