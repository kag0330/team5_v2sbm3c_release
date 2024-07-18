package dev.mvc.shoesFile;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.shoesFile.ShoesFileProc")
public class ShoesFileProc implements ShoesFileProcInter {
  
  @Autowired
  private ShoesFileDAOInter shoesFileDAO;

  @Override
  public ArrayList<ShoesFileVO> list() {
    System.out.println(shoesFileDAO.list());
    return this.shoesFileDAO.list();
  }

}
