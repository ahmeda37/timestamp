package com.ahmed.main.model;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int eid;
	
	@Column(nullable = false)
	private String fname, lname; 
	
	private String phone;
	
	private double rate;

	private Date hiredDate;
	
	@OneToMany(mappedBy="employee", cascade = CascadeType.ALL)
	private Set<PunchInOut> punches = new HashSet<PunchInOut>();

	public Set<PunchInOut> getPunches() {
		return punches;
	}

	public void setPunches(Set<PunchInOut> punches) {
		this.punches = punches;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double d) {
		this.rate = d;
	}

	public Date getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(Date localDate) {
		this.hiredDate = localDate;
	}

	@Override
	public boolean equals(Object obj) {
		
		return false;
	}
	
	@Override
	public int hashCode() {
		
		return -1;
	}
	
	@Override
	public String toString() {
		String result = "";
		result += "Emp ID: " + this.eid;
		result += "\nEmp Name: " + this.fname + " " + this.lname;
		result += "\nPhone: " + this.phone;
		result += "\nHired Date: " + this.hiredDate;
		result += "\nRate (Hourly): $" + this.rate + "\n";
		return result;
	}
	
	
}
