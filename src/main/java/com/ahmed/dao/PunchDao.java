package com.ahmed.dao;

import java.sql.Date;
import java.util.List;

import com.ahmed.model.Employee;
import com.ahmed.model.PunchInOut;

public interface PunchDao {
	public void addPunch (PunchInOut p);
	public void updatePunch(PunchInOut p);
	public List<PunchInOut> listPunches();
	public void deletePunch(int pid);
	public PunchInOut getPunchById(int pid);
	public PunchInOut getPunchByIdDate(Employee e, Date punchDate);
	public List<PunchInOut> punchesForEmployee(Employee e, Date start, Date end);
}
