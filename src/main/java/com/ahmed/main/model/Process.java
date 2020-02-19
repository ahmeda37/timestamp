package com.ahmed.main.model;
import org.joda.time.LocalDate;

import com.ahmed.main.service.EmployeeService;
import com.ahmed.main.service.PunchService;

public abstract class Process {
	
	protected EmployeeService emps;

	protected PunchService puns;
	
	protected Employee e;
	protected double total;
	protected double hoursWorked;
	protected LocalDate start;
	protected LocalDate end;
	
	
	public EmployeeService getEmps() {
		return emps;
	}
	public void setEmps(EmployeeService emps) {
		this.emps = emps;
	}
	public PunchService getPuns() {
		return puns;
	}
	public void setPuns(PunchService puns) {
		this.puns = puns;
	}
	public Employee getE() {
		return e;
	}
	public void setE(Employee e) {
		this.e = e;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	public abstract String getEmployeePunches();
	
	public abstract String getEmployee();
	
	public abstract String getEmployees();
}
