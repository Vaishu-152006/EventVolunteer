package com.eventvol.bean;

import java.sql.Date;

public class Shift {

    private int shiftID;
    private Date shiftDate;
    private String startTime;
    private String endTime;
    private String location;
    private int requiredHeadcount;
    private String status;

    public int getShiftID() {
        return shiftID;
    }
    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }
    public Date getShiftDate() {
        return shiftDate;
    }
    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getRequiredHeadcount() {
        return requiredHeadcount;
    }
    public void setRequiredHeadcount(int requiredHeadcount) {
        this.requiredHeadcount = requiredHeadcount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
