package com.ahmed.dao;

import java.util.List;

import com.ahmed.model.Employee;

public interface EmployeeDao {
	public void addEmployee(Employee e);
	public void updateEmployee(Employee e);
	public List<Employee> listEmployees();
	public void deleteEmployee(int eid);
	public Employee getEmployeeById(int eid);
}
