package com.ahmed.main.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahmed.main.model.Employee;

@Repository
public interface EmployeeRepo<E> extends CrudRepository<Employee, Integer> {

}
