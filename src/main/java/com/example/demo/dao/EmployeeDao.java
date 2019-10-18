package com.example.demo.dao;

import com.example.demo.model.Employee;

public interface EmployeeDao {
	void insertEmployee(Employee cus);

	void deleteEmployeeById(String empid);
}