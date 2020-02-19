package com.ahmed.main.service;

import java.util.List;

import com.ahmed.main.model.Employee;

public interface EmployeeService {
	public void addEmployee(Employee e);
	public void updateEmployee(Employee e);
	public List<Employee> getEmployees();
	public void removeEmployee(int eid);
	public Employee getEmployeeById(int eid);
}
