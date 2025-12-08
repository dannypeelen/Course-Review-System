package com.cs1530.coursereview.service;

import com.cs1530.coursereview.model.Report;
import com.cs1530.coursereview.model.Review;
import com.cs1530.coursereview.repository.ReportRepository;
import com.cs1530.coursereview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, ReviewRepository reviewRepository) {
        this.reportRepository = reportRepository;
        this.reviewRepository = reviewRepository;
    }

    public Report createReport(Integer reviewId, String content) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Report report = new Report();
        report.setReportReview(review);
        report.setContent(content);
        report.setConfirmed(false);

        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<Report> getPendingReports() {
        return reportRepository.findByConfirmed(false);
    }

    public List<Report> getConfirmedReports() {
        return reportRepository.findByConfirmed(true);
    }

    public Report confirmReport(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        report.confirmReport();
        return reportRepository.save(report);
    }

    public void deleteReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }
}
