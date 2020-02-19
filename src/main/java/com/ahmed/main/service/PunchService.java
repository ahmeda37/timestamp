package com.ahmed.main.service;

import java.sql.Date;
import java.util.List;

import com.ahmed.main.model.Employee;
import com.ahmed.main.model.PunchInOut;

public interface PunchService {
	public void addPunch(PunchInOut p);
	public void updatePunch(PunchInOut p);
	public List<PunchInOut> getPunches();
	public void removePunch(int pid);
	public PunchInOut getPunchById(int pid);
	public PunchInOut getPuncyByIdDate(Employee e, Date pDate);
	public List<PunchInOut> punchesForEmployee(Employee e, Date start, Date end);
}
