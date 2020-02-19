package com.ahmed.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.*;


@Entity
@Table(name="punch_in_out")
public class PunchInOut {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pid;
	
	private Date pDate; 
	private Timestamp pIn, pOut;
	
	@ManyToOne
	@JoinColumn(name="FK_empid")
	private Employee employee;
	
	private int dayOfWeek;
	
	private Double hoursWorked;

	public Double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(Double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}

	public Timestamp getpIn() {
		return pIn;
	}

	public void setpIn(Timestamp pIn) {
		this.pIn = pIn;
	}

	public Timestamp getpOut() {
		return pOut;
	}

	public void setpOut(Timestamp pOut) {
		this.pOut = pOut;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String getDay() {
		return new SimpleDateFormat("E, d MMMM").format(this.pDate ) + ", " + this.hoursWorked + " Hours\n"; 
	}

	@Override
	public String toString() {
		String result = "";
		result += "Employee ID: " + employee.getEid();
		result += "\nEmployee Name: " + employee.getFname() + " " + employee.getLname();
		result += "\nPunch Date: " + this.pDate;
		result += "\nDay of Week: " + this.dayOfWeek;
		result += "\nPunch In: " + this.pIn;
		result += "\nPunch Out: " + this.pOut;
		return result;
	}


}