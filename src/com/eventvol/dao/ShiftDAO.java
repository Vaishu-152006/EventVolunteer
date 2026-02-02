package com.eventvol.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.eventvol.bean.Shift;
import com.eventvol.util.DBUtil;

public class ShiftDAO {

    public Shift findShift(int shiftID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("SELECT * FROM SHIFT_TBL WHERE SHIFT_ID=?");
        ps.setInt(1, shiftID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Shift s = new Shift();
            s.setShiftID(rs.getInt(1));
            s.setShiftDate(rs.getDate(2));
            s.setStartTime(rs.getString(3));
            s.setEndTime(rs.getString(4));
            s.setLocation(rs.getString(5));
            s.setRequiredHeadcount(rs.getInt(6));
            s.setStatus(rs.getString(7));
            return s;
        }
        return null;
    }

    public List<Shift> viewAllShifts() throws Exception {
        List<Shift> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps=con.prepareStatement("SELECT * FROM SHIFT_TBL");
        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            Shift s = new Shift();
            s.setShiftID(rs.getInt(1));
            s.setShiftDate(rs.getDate(2));
            s.setStartTime(rs.getString(3));
            s.setEndTime(rs.getString(4));
            s.setLocation(rs.getString(5));
            s.setRequiredHeadcount(rs.getInt(6));
            s.setStatus(rs.getString(7));
            list.add(s);
        }
        return list;
    }

    public boolean insertShift(Shift shift) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("INSERT INTO SHIFT_TBL VALUES(?,?,?,?,?,?,?)");
        ps.setInt(1, shift.getShiftID());
        ps.setDate(2, shift.getShiftDate());
        ps.setString(3, shift.getStartTime());
        ps.setString(4, shift.getEndTime());
        ps.setString(5, shift.getLocation());
        ps.setInt(6, shift.getRequiredHeadcount());
        ps.setString(7, shift.getStatus());
        return ps.executeUpdate() > 0;
    }

    public boolean updateShiftStatus(int shiftID, String status) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("UPDATE SHIFT_TBL SET STATUS=? WHERE SHIFT_ID=?");
        ps.setString(1, status);
        ps.setInt(2, shiftID);
        return ps.executeUpdate() > 0;
    }

    public int countAssignmentsForShift(int shiftID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps =con.prepareStatement("SELECT COUNT(*) FROM ASSIGNMENT_TBL WHERE SHIFT_ID=?");
        ps.setInt(1, shiftID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
