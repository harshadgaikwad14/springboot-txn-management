package com.example.demo.service;

import com.example.demo.model.Employee;

public interface EmployeeService {
	void saveEmpBasicTxn(Employee emp);

	void deleteEmpBasicTxn(String empid);
	
	void saveEmpPropagationRequired(Employee employee);
	void saveEmpPropagationSupport(Employee employee);
	void saveEmpPropagationNotSupport(Employee employee);
	void saveEmpPropagationRequiredNew(Employee employee);
	void saveEmpPropagationNever(Employee employee);
	void saveEmpPropagationMandatory(Employee employee);
	void saveEmpWithOutTxn(Employee employee);
}