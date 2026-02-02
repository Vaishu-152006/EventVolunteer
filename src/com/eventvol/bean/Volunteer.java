package com.eventvol.bean;

public class Volunteer {

    private String volunteerID;
    private String fullName;
    private String skillCategory;
    private String primaryPhone;
    private String email;
    private String status;

    public String getVolunteerID() {
        return volunteerID;
    }
    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getSkillCategory() {
        return skillCategory;
    }
    public void setSkillCategory(String skillCategory) {
        this.skillCategory = skillCategory;
    }
    public String getPrimaryPhone() {
        return primaryPhone;
    }
    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
