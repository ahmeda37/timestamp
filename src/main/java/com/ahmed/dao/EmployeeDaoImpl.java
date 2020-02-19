package com.ahmed.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.ahmed.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	public void addEmployee(Employee e) {
		// TODO Auto-generated method stub
		persist(e);
	}

	public void updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		getSession().update(e);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listEmployees() {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(Employee.class);
		return (List<Employee>) c.list();
	}

	public void deleteEmployee(int eid) {
		// TODO Auto-generated method stub
//		Query q = getSession().createQuery("delete from Employee where eid = :eid");
//		q.setInteger("eid", eid);
//		q.executeUpdate();
		delete(getEmployeeById(eid));
	}
	public Employee getEmployeeById(int eid) {
		return (Employee) getSession().get(Employee.class, eid);
	}
}
