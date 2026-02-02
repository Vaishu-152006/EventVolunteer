package com.eventvol.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.eventvol.bean.Volunteer;
import com.eventvol.util.DBUtil;

public class VolunteerDAO {

    public Volunteer findVolunteer(String volunteerID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM VOLUNTEER_TBL WHERE VOLUNTEER_ID=?");
        ps.setString(1, volunteerID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Volunteer v = new Volunteer();
            v.setVolunteerID(rs.getString(1));
            v.setFullName(rs.getString(2));
            v.setSkillCategory(rs.getString(3));
            v.setPrimaryPhone(rs.getString(4));
            v.setEmail(rs.getString(5));
            v.setStatus(rs.getString(6));
            return v;
        }
        return null;
    }
    
    public List<Volunteer> viewAllVolunteers() throws Exception {
        List<Volunteer> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM VOLUNTEER_TBL WHERE VOLUNTEER_ID=?");
        ResultSet rs=ps.executeQuery();
		while (rs.next()) {
            Volunteer v = new Volunteer();
            v.setVolunteerID(rs.getString(1));
            v.setFullName(rs.getString(2));
            v.setSkillCategory(rs.getString(3));
            v.setPrimaryPhone(rs.getString(4));
            v.setEmail(rs.getString(5));
            v.setStatus(rs.getString(6));
            list.add(v);
        }
        return list;
    }

    public boolean insertVolunteer(Volunteer volunteer) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("INSERT INTO VOLUNTEER_TBL VALUES(?,?,?,?,?,?)");
        ps.setString(1, volunteer.getVolunteerID());
        ps.setString(2, volunteer.getFullName());
        ps.setString(3, volunteer.getSkillCategory());
        ps.setString(4, volunteer.getPrimaryPhone());
        ps.setString(5, volunteer.getEmail());
        ps.setString(6, volunteer.getStatus());
        return ps.executeUpdate() >0;
    }

    public boolean updateVolunteerStatus(String volunteerID, String status) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("UPDATE VOLUNTEER_TBL SET STATUS=? WHERE VOLUNTEER_ID=?");
        ps.setString(1, status);
        ps.setString(2, volunteerID);
        return ps.executeUpdate() > 0;
    }

    public boolean deleteVolunteer(String volunteerID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("DELETE FROM VOLUNTEER_TBL WHERE VOLUNTEER_ID=?");
        ps.setString(1, volunteerID);
        return ps.executeUpdate() > 0;
    }
}
