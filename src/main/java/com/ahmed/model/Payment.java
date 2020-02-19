package com.ahmed.model;
import java.sql.Date;
import java.util.List;
import org.joda.time.LocalDate;

public class Payment extends Process {
	@SuppressWarnings("static-access")
	public String getEmployeePunches() {
		end = new LocalDate().now();
		start = end.minusDays(14);
		Double totalHours = 0.0;
		List<PunchInOut> punches = puns.punchesForEmployee(e, new Date(start.toDate().getTime()), new Date(end.toDate().getTime()));
		for(PunchInOut p:punches) {
			if(p.getHoursWorked()-8.0 > 0.0) {
				totalHours += ((p.getHoursWorked() - 8.0) * 0.4);
			}
			totalHours += p.getHoursWorked();
		}
		return "" + totalHours * e.getRate() + "\n";
	}
	
	public String getEmployee() {
		String result = "";
		result += e.getLname() + ", " + e.getFname() + "\n";
		return result += "Payment: $" + getEmployeePunches();
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
