package com.ahmed.main.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahmed.main.model.Employee;
import com.ahmed.main.model.PunchInOut;
import com.ahmed.main.repo.PunchRepo;

@Service("punchService")
@Transactional
public class PunchServiceImpl implements PunchService {
	
	@Autowired
	private PunchRepo repo;
	
	public void addPunch(PunchInOut p) {
		// TODO Auto-generated method stub
		repo.save(p);
	}

	public void updatePunch(PunchInOut p) {
		// TODO Auto-generated method stub
		repo.save(p);
	}

	public List<PunchInOut> getPunches() {
		// TODO Auto-generated method stub
		return (List<PunchInOut>)repo.findAll();
	}

	public void removePunch(int pid) {
		// TODO Auto-generated method stub
		repo.deleteById(pid);
	}

	public PunchInOut getPunchById(int pid) {
		// TODO Auto-generated method stub
		System.out.println(repo.findById(pid).get().getEmployee().getRate());
		return repo.findById(pid).get();
	}

	public PunchInOut getPuncyByIdDate(Employee e, Date pDate) {
		// TODO Auto-generated method stub
		
		return repo.getPunchByIdDate(e, pDate);
	}

	public List<PunchInOut> punchesForEmployee(Employee e, Date start, Date end) {
		// TODO Auto-generated method stub
		return repo.punchesForEmployee(e, start, end);
	}

}
