package com.eventvol.app;

import com.eventvol.bean.Shift;
import com.eventvol.bean.Volunteer;
import com.eventvol.service.EventService;
import com.eventvol.util.OverlappingShiftAssignmentException;
import com.eventvol.util.ValidationException;

public class EventMain { 
private static EventService eventService; 
public static void main(String[] args) { 
eventService = new EventService(); 
java.util.Scanner sc = new java.util.Scanner(System.in); 
System.out.println("--- Event Volunteer Scheduling----"); 

try { 
Volunteer v = new Volunteer(); 
v.setVolunteerID("VOL2000");
v.setFullName("Krithika"); 
v.setSkillCategory("REGISTRATION"); 
v.setPrimaryPhone("9876599922"); 
v.setEmail("krithi@gmail.com"); 
v.setStatus("ACTIVE"); 
boolean ok = eventService.registerNewVolunteer(v); 
System.out.println(ok ? "VOLUNTEER REGISTERED" : 
"VOLUNTEER REGISTRATION FAILED"); 
} catch (ValidationException e) { 
System.out.println("Validation Error: " + e.toString()); 
} catch (Exception e) { 
System.out.println("System Error: " + e.getMessage()); 
} 

try { 
Shift s = new Shift(); 
s.setShiftID(410020); 
s.setShiftDate(new java.sql.Date(System.currentTimeMillis())); 
s.setStartTime("09:00"); 
s.setEndTime("12:00"); 
s.setLocation("MAIN_HALL"); 
s.setRequiredHeadcount(7); 
s.setStatus("OPEN_FOR_ASSIGNMENT"); 
boolean ok = eventService.createShift(s); 
System.out.println(ok ? "SHIFT CREATED" : "SHIFT CREATION FAILED"); 
} catch (ValidationException e) { 
System.out.println("Validation Error: " + e.toString()); 
} catch (Exception e) { 
System.out.println("System Error: " + e.getMessage()); 
} 

try { 
boolean ok = 
eventService.assignVolunteerToShift("VOL1013", 410020, "ENTRY_CHECK");
System.out.println(ok ? "ASSIGNMENT CREATED" : "ASSIGNMENT FAILED"); 
} catch (OverlappingShiftAssignmentException e) { 
System.out.println("Assignment Error: " + e.toString()); 
} catch (ValidationException e) { 
System.out.println("Validation Error: " + e.toString()); 
} catch (Exception e) { 
System.out.println("System Error: " + e.getMessage()); 
} 
sc.close(); 
} 
}
