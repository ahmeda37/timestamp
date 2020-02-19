package com.ahmed.main.model;
import java.sql.Date;
import java.util.List;
import org.joda.time.LocalDate;

public class Report extends Process {
	
	@SuppressWarnings("static-access")
	public String getEmployeePunches() {
		end = new LocalDate().now();
		start = end.minusDays(14);
		Double totalHours = 0.0;
		List<PunchInOut> punches = puns.punchesForEmployee(e, new Date(start.toDate().getTime()), new Date(end.toDate().getTime()));
		String result = "";
		for(PunchInOut p:punches) {
			result += p.getDay();
			totalHours += p.getHoursWorked();
		}
		return result += "Total Hours: " + totalHours + "\n";
	}
	
	public String getEmployee() {
		String result = "";
		result += e.getLname() + ", " + e.getFname() + "\n";
		return result += getEmployeePunches();
	}
	
	public String getEmployees() {
		String result = "";
		List <Employee> employees = emps.getEmployees();
		for(Employee ep: employees) {
			this.e = ep;
			result += getEmployee() + "\n";
		}
		return result;
	}
}
