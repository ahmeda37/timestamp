package com.ahmed.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahmed.main.model.Employee;
import com.ahmed.main.repo.EmployeeRepo;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepo<Employee> repo;
	
	public void addEmployee(Employee e) {
		// TODO Auto-generated method stub
		System.out.println("E again: " + e);
		System.out.println("Repo: " + repo);
		repo.save(e);	
		
	}

	public void updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		repo.save(e);
	}

	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return (List<Employee>)repo.findAll();
	}

	public void removeEmployee(int eid) {
		// TODO Auto-generated method stub
		repo.deleteById(eid);
	}

	public Employee getEmployeeById(int eid) {
		// TODO Auto-generated method stub
		return repo.findById(eid).get();
	}

}
