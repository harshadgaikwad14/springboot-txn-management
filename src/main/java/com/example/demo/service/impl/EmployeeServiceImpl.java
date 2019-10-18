package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Transactional
	@Override
	public void saveEmpBasicTxn(Employee employee) {
		employeeDao.insertEmployee(employee);
	}

	@Override
	public void deleteEmpBasicTxn(String empid) {
		employeeDao.deleteEmployeeById(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveEmpPropagationRequired(Employee employee) {
		employeeDao.insertEmployee(employee);

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public void saveEmpPropagationSupport(Employee employee) {
		employeeDao.insertEmployee(employee);
		//throw new RuntimeErrorException(null, "TestError");
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public void saveEmpPropagationNotSupport(Employee employee) {
		employeeDao.insertEmployee(employee);

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void saveEmpPropagationRequiredNew(Employee employee) {
		employeeDao.insertEmployee(employee);

	}

	@Transactional(propagation = Propagation.NEVER)
	@Override
	public void saveEmpPropagationNever(Employee employee) {
		employeeDao.insertEmployee(employee);

	}

	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public void saveEmpPropagationMandatory(Employee employee) {
		employeeDao.insertEmployee(employee);
	}

	@Override
	public void saveEmpWithOutTxn(Employee employee) {
		employeeDao.insertEmployee(employee);

	}

}