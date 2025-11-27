package com.cs1530.coursereview.repository;

import com.cs1530.coursereview.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByConfirmed(Boolean confirmed);
}
