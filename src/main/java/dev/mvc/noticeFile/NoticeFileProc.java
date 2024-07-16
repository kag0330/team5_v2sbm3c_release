package dev.mvc.noticeFile;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.noticeFile.NoticeFileProc")
public class NoticeFileProc implements NoticeFileProcInter {
  
  @Autowired
  private NoticeFileDAOInter noticeFileDAO;

  @Override
  public int create(NoticeFileVO noticeFileVO) {
    return this.noticeFileDAO.create(noticeFileVO);
  }

  @Override
  public int deleteByNoticeno(int noticeno) {
    return this.noticeFileDAO.deleteByNoticeno(noticeno);
  }

  @Override
  public int deleteByNoticefileno(int noticeFileno) {
    return this.noticeFileDAO.deleteByNoticefileno(noticeFileno);
  }

  @Override
  public ArrayList<NoticeFileVO> findByNoticeno(int noticeno) {
    return this.noticeFileDAO.findByNoticeno(noticeno);
  }


}
