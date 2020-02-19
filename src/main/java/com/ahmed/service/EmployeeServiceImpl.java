package com.ahmed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahmed.dao.EmployeeDao;
import com.ahmed.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao dao;
	public void addEmployee(Employee e) {
		// TODO Auto-generated method stub
		dao.addEmployee(e);
	}

	public void updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		dao.updateEmployee(e);
	}

	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return dao.listEmployees();
	}

	public void removeEmployee(int eid) {
		// TODO Auto-generated method stub
		dao.deleteEmployee(eid);
	}

	public Employee getEmployeeById(int eid) {
		// TODO Auto-generated method stub
		return dao.getEmployeeById(eid);
	}

}
