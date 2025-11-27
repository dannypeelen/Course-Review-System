package com.cs1530.coursereview.controller;

import com.cs1530.coursereview.model.Report;
import com.cs1530.coursereview.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Report> submitReport(@RequestBody Map<String, Object> payload) {
        try {
            Long reviewId = Long.valueOf(payload.get("reviewId").toString());
            String content = payload.get("content").toString();

            Report report = reportService.createReport(reviewId, content);
            return ResponseEntity.status(HttpStatus.CREATED).body(report);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Report>> getPendingReports() {
        List<Report> reports = reportService.getPendingReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/confirmed")
    public ResponseEntity<List<Report>> getConfirmedReports() {
        List<Report> reports = reportService.getConfirmedReports();
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Report> confirmReport(@PathVariable Long id) {
        try {
            Report report = reportService.confirmReport(id);
            return ResponseEntity.ok(report);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        try {
            reportService.deleteReport(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
