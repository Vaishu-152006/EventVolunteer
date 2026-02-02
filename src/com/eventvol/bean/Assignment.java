package com.eventvol.bean;

public class Assignment {

    private int assignmentID;
    private String volunteerID;
    private int shiftID;
    private String assignedRole;
    private String attendanceStatus;
    private String checkinTime;
    private String checkoutTime;

    public int getAssignmentID() {
        return assignmentID;
    }
    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }
    public String getVolunteerID() {
        return volunteerID;
    }
    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }
    public int getShiftID() {
        return shiftID;
    }
    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }
    public String getAssignedRole() {
        return assignedRole;
    }
    public void setAssignedRole(String assignedRole) {
        this.assignedRole = assignedRole;
    }
    public String getAttendanceStatus() {
        return attendanceStatus;
    }
    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
    public String getCheckinTime() {
        return checkinTime;
    }
    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }
    public String getCheckoutTime() {
        return checkoutTime;
    }
    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }
}
