package com.eventvol.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.eventvol.bean.Assignment;
import com.eventvol.util.DBUtil;

public class AssignmentDAO {
    public int generateAssignmentID() throws Exception {
        Connection con = DBUtil.getDBConnection();
       PreparedStatement ps=con.prepareStatement("SELECT NVL(MAX(ASSIGNMENT_ID),0)+1 FROM ASSIGNMENT_TBL");
       ResultSet rs=ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public boolean insertAssignment(Assignment a) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("INSERT INTO ASSIGNMENT_TBL VALUES(?,?,?,?,?,?,?)");
        ps.setInt(1, a.getAssignmentID());
        ps.setString(2, a.getVolunteerID());
        ps.setInt(3, a.getShiftID());
        ps.setString(4, a.getAssignedRole());
        ps.setString(5, a.getAttendanceStatus());
        ps.setString(6, a.getCheckinTime());
        ps.setString(7, a.getCheckoutTime());
        return ps.executeUpdate() > 0;
    }

    public boolean updateAttendance(int assignmentID, String status,String checkinTime, String checkoutTime) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps=con.prepareStatement("UPDATE ASSIGNMENT_TBL SET ATTENDANCE_STATUS=?, CHECKIN_TIME=?, CHECKOUT_TIME=? WHERE ASSIGNMENT_ID=?");
        ps.setString(1, status);
        ps.setString(2, checkinTime);
        ps.setString(3, checkoutTime);
        ps.setInt(4, assignmentID);
        return ps.executeUpdate() > 0;
    }

    public List<Assignment> findAssignmentsByVolunteer(String volunteerID) throws Exception {
        List<Assignment> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("SELECT * FROM ASSIGNMENT_TBL WHERE VOLUNTEER_ID=?");
        ps.setString(1, volunteerID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Assignment a = new Assignment();
            a.setAssignmentID(rs.getInt(1));
            a.setVolunteerID(rs.getString(2));
            a.setShiftID(rs.getInt(3));
            a.setAssignedRole(rs.getString(4));
            a.setAttendanceStatus(rs.getString(5));
            a.setCheckinTime(rs.getString(6));
            a.setCheckoutTime(rs.getString(7));
            list.add(a);
        }
        return list;
    }

    public List<Assignment> findAssignmentsByShift(int shiftID) throws Exception {
        List<Assignment> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM ASSIGNMENT_TBL WHERE SHIFT_ID=?");
        ps.setInt(1, shiftID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Assignment a = new Assignment();
            a.setAssignmentID(rs.getInt(1));
            a.setVolunteerID(rs.getString(2));
            a.setShiftID(rs.getInt(3));
            a.setAssignedRole(rs.getString(4));
            a.setAttendanceStatus(rs.getString(5));
            a.setCheckinTime(rs.getString(6));
            a.setCheckoutTime(rs.getString(7));
            list.add(a);
        }
        return list;
    }

    public List<Assignment> findActiveAssignmentsForVolunteer(String volunteerID) throws Exception {
        List<Assignment> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("SELECT * FROM ASSIGNMENT_TBL WHERE VOLUNTEER_ID=? AND ATTENDANCE_STATUS='PENDING'");
        ps.setString(1, volunteerID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Assignment a = new Assignment();
            a.setAssignmentID(rs.getInt(1));
            a.setVolunteerID(rs.getString(2));
            a.setShiftID(rs.getInt(3));
            a.setAssignedRole(rs.getString(4));
            a.setAttendanceStatus(rs.getString(5));
            list.add(a);
        }
        return list;
    }

    public List<Assignment> findActiveAssignmentsForShift(int shiftID) throws Exception {
        List<Assignment> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("SELECT * FROM ASSIGNMENT_TBL WHERE SHIFT_ID=? AND ATTENDANCE_STATUS='PENDING'");
        ps.setInt(1, shiftID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Assignment a = new Assignment();
            a.setAssignmentID(rs.getInt(1));
            a.setVolunteerID(rs.getString(2));
            a.setShiftID(rs.getInt(3));
            a.setAssignedRole(rs.getString(4));
            a.setAttendanceStatus(rs.getString(5));
            list.add(a);
        }
        return list;

}

	public boolean findOverlappingAssignments(String volunteerID, Date shiftDate, String startTime, String endTime) {
		return false;
	}
}