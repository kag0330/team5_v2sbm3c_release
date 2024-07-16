package dev.mvc.delivery;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.delivery.DeliveryProc")
public class DeliveryProc implements DeliveryProcInter {
  @Autowired
  private DeliveryDAOInter deliveryDAO;

  @Override
  public ArrayList<DeliveryVO> list_all(int memberno) {
    return this.deliveryDAO.list_all(memberno); 
  }

  @Override
  public int count(int memberno) {
    return this.deliveryDAO.count(memberno);
  }

  @Override
  public int delete(int deliveryno) {
    return this.deliveryDAO.delete(deliveryno);
  }

  @Override
  public int create(DeliveryVO deliveryVO) {
    if(deliveryVO.getRequests().equals("none"))
      deliveryVO.setRequests("");
    return this.deliveryDAO.create(deliveryVO);
  }

  @Override
  public DeliveryVO read(int deliveryno) {
    return this.deliveryDAO.read(deliveryno);
  }

  @Override
  public int update(DeliveryVO deliveryVO) {
    return this.deliveryDAO.update(deliveryVO);
  }

  @Override
  public int deleteAdmin(int memberno) {
    return this.deliveryDAO.deleteAdmin(memberno);
  }

  
}
