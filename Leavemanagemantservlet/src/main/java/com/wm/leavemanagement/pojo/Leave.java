package com.wm.leavemanagement.pojo;

import com.wm.leavemanagement.constants.LeaveName;
import com.wm.leavemanagement.constants.LeaveStatus;

import java.time.LocalDate;

public class Leave {
    private int id;
    private int appliedBy;
    private String employeeName;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int leaveId;
    private LeaveName leaveName;
    private LeaveStatus status;
    private String reason;
    private LocalDate createdAt;
    private LocalDate appliedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppliedBy() {
        return appliedBy;
    }

    public void setAppliedBy(int appliedBy) {
        this.appliedBy = appliedBy;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public LeaveName getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(LeaveName leaveName) {
        this.leaveName = leaveName;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    @Override
    public String toString() {
        return "EmployeeNameLeave{" +
                "id=" + id +
                ", appliedBy=" + appliedBy +
                ", employeeName='" + employeeName + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", leaveId=" + leaveId +
                ", leaveName=" + leaveName +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                ", createdAt=" + createdAt +
                ", appliedDate=" + appliedDate +
                '}';
    }


}