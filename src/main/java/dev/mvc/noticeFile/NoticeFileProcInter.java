package dev.mvc.noticeFile;

import java.util.ArrayList;
import java.util.Map;

public interface NoticeFileProcInter {
  public int create(NoticeFileVO noticeFileVO);
  
  public int deleteByNoticeno(int noticeno);
  
  public int deleteByNoticefileno(int noticeFileno);
  
  public ArrayList<NoticeFileVO> findByNoticeno(int noticeno);
}
