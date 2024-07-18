package dev.mvc.admin.analysis;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Integer> {
  @Query(value = "SELECT * FROM ( " +
      "  SELECT a.*, ROWNUM rnum FROM ( " +
      "    SELECT analysisno, chart, cnt, code, rdate, content, data, legned, title, x_label, y_label " +
      "    FROM analysis " +
      "    ORDER BY analysisno DESC " +
      "  ) a WHERE ROWNUM <= :endRow " +
      ") WHERE rnum > :startRow", nativeQuery = true)
  List<AnalysisEntity> findAllByOrderByAnalysisnoDesc(@Param("startRow") int startRow, @Param("endRow") int endRow);
}