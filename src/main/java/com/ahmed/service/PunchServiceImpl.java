package com.ahmed.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahmed.dao.PunchDao;
import com.ahmed.model.Employee;
import com.ahmed.model.PunchInOut;

@Service("punchService")
@Transactional
public class PunchServiceImpl implements PunchService {
	
	@Autowired
	private PunchDao dao;
	
	public void addPunch(PunchInOut p) {
		// TODO Auto-generated method stub
		dao.addPunch(p);
	}

	public void updatePunch(PunchInOut p) {
		// TODO Auto-generated method stub
		dao.updatePunch(p);
	}

	public List<PunchInOut> getPunches() {
		// TODO Auto-generated method stub
		return dao.listPunches();
	}

	public void removePunch(int pid) {
		// TODO Auto-generated method stub
		dao.deletePunch(pid);
	}

	public PunchInOut getPunchById(int pid) {
		// TODO Auto-generated method stub
		return dao.getPunchById(pid);
	}

	public PunchInOut getPuncyByIdDate(Employee e, Date pDate) {
		// TODO Auto-generated method stub
		return dao.getPunchByIdDate(e, pDate);
	}

	public List<PunchInOut> punchesForEmployee(Employee e, Date start, Date end) {
		// TODO Auto-generated method stub
		return dao.punchesForEmployee(e, start, end);
	}

}
