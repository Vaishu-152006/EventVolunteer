package com.eventvol.service;

import java.sql.Connection;
import java.util.List;

import com.eventvol.bean.Assignment;
import com.eventvol.bean.Shift;
import com.eventvol.bean.Volunteer;
import com.eventvol.dao.AssignmentDAO;
import com.eventvol.dao.ShiftDAO;
import com.eventvol.dao.VolunteerDAO;
import com.eventvol.util.ActiveAssignmentsExistException;
import com.eventvol.util.DBUtil;
import com.eventvol.util.OverlappingShiftAssignmentException;
import com.eventvol.util.ValidationException;

public class EventService {

    private VolunteerDAO volunteerDAO = new VolunteerDAO();
    private ShiftDAO shiftDAO = new ShiftDAO();
    private AssignmentDAO assignmentDAO = new AssignmentDAO();

    public Volunteer viewVolunteerDetails(String volunteerID) throws Exception {
        if (volunteerID == null || volunteerID.trim().isEmpty()) {
            throw new ValidationException();
        }
        return volunteerDAO.findVolunteer(volunteerID);
    }

    public List<Volunteer> viewAllVolunteers() throws Exception {
        return volunteerDAO.viewAllVolunteers();
    }
    public boolean registerNewVolunteer(Volunteer v) throws Exception {
        if (v == null ||
            v.getVolunteerID() == null || v.getVolunteerID().trim().isEmpty() ||
            v.getFullName() == null || v.getFullName().trim().isEmpty() ||
            v.getPrimaryPhone() == null || v.getPrimaryPhone().trim().isEmpty()) {
            throw new ValidationException();
        }
        if (volunteerDAO.findVolunteer(v.getVolunteerID()) != null) {
            return false;
        }
        v.setStatus("ACTIVE");
        return volunteerDAO.insertVolunteer(v);
    }
    public Shift viewShiftDetails(int shiftID) throws Exception {
        if (shiftID <= 0) {
            throw new ValidationException();
        }
        return shiftDAO.findShift(shiftID);
    }
    public List<Shift> viewAllShifts() throws Exception {
        return shiftDAO.viewAllShifts();
    }
    public boolean createShift(Shift s) throws Exception {
        if (s == null ||
            s.getShiftDate() == null ||
            s.getStartTime() == null || s.getStartTime().trim().isEmpty() ||
            s.getEndTime() == null || s.getEndTime().trim().isEmpty()) {
            throw new ValidationException();
        }
        return shiftDAO.insertShift(s);
    }
    public boolean assignVolunteerToShift(String volunteerID, int shiftID, String role)
            throws ValidationException, OverlappingShiftAssignmentException, Exception {
        if (volunteerID == null || volunteerID.trim().isEmpty() ||
            role == null || role.trim().isEmpty() ||
            shiftID <= 0) {
            throw new ValidationException();
        }
        Volunteer v = volunteerDAO.findVolunteer(volunteerID);
        Shift s = shiftDAO.findShift(shiftID);
        if (v == null || s == null) {
            return false;
        }
        if ("INACTIVE".equals(v.getStatus())) {
            return false;
        }
        if (!"OPEN_FOR_ASSIGNMENT".equals(s.getStatus())) {
            return false;
        }

        boolean overlap = assignmentDAO.findOverlappingAssignments(
                volunteerID,
                s.getShiftDate(),
                s.getStartTime(),
                s.getEndTime()
        );
        if (overlap) {
            throw new OverlappingShiftAssignmentException();
        }
        Connection con = null;
        try {
            con = DBUtil.getDBConnection();
            con.setAutoCommit(false);
            Assignment a = new Assignment();
            a.setAssignmentID(assignmentDAO.generateAssignmentID());
            a.setVolunteerID(volunteerID);
            a.setShiftID(shiftID);
            a.setAssignedRole(role);
            a.setAttendanceStatus("PENDING");
            assignmentDAO.insertAssignment(a);
            con.commit();
            return true;
        } catch (Exception e) {
            if (con != null) con.rollback();
            return false;
        } finally {
            if (con != null) con.close();
        }
    }
    
    public boolean markAttendance(int assignmentID, String status,String inTime, String outTime) throws Exception {
        if (assignmentID <= 0 ||
            status == null ||
            (!status.equals("PRESENT") && !status.equals("ABSENT"))) {
            throw new ValidationException();
        }
        Connection con = null;
        try {
            con = DBUtil.getDBConnection();
            con.setAutoCommit(false);
            assignmentDAO.updateAttendance(assignmentID, status, inTime, outTime);
            con.commit();
            return true;
        } catch (Exception e) {
            if (con != null) con.rollback();
            return false;
        } finally {
            if (con != null) con.close();
        }
    }
    public boolean removeVolunteer(String volunteerID)
            throws ValidationException, ActiveAssignmentsExistException, Exception {
        if (volunteerID == null || volunteerID.trim().isEmpty()) {
            throw new ValidationException();
        }
        if (!assignmentDAO.findActiveAssignmentsForVolunteer(volunteerID).isEmpty()) {
            throw new ActiveAssignmentsExistException();
        }
        return volunteerDAO.deleteVolunteer(volunteerID);
    }
    public boolean removeShift(int shiftID)
            throws ValidationException, ActiveAssignmentsExistException, Exception {
        if (shiftID <= 0) {
            throw new ValidationException();
        }
        if (!assignmentDAO.findActiveAssignmentsForShift(shiftID).isEmpty()) {
            throw new ActiveAssignmentsExistException();
        }
        return shiftDAO.updateShiftStatus(shiftID, "CANCELLED");
    }
}
