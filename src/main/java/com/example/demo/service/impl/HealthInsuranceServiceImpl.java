package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.HealthInsuranceDao;
import com.example.demo.exception.InvalidInsuranceAmountException;
import com.example.demo.model.EmployeeHealthInsurance;
import com.example.demo.service.HealthInsuranceService;

@Service
public class HealthInsuranceServiceImpl implements HealthInsuranceService {

	@Autowired
	HealthInsuranceDao healthInsuranceDao;

	@Override
	public void saveHalthInsuBasicTxn(EmployeeHealthInsurance employeeHealthInsurance) {
		healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);
	}

	@Override
	public void deleteHalthInsuBasicTxn(String empid) {
		healthInsuranceDao.deleteEmployeeHealthInsuranceById(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveHalthInsuPropagationRequired(EmployeeHealthInsurance employeeHealthInsurance) {
		healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public void saveHalthInsuPropagationSupport(EmployeeHealthInsurance employeeHealthInsurance) {
		healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);

	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public void saveHalthInsuPropagationNonSupport(EmployeeHealthInsurance employeeHealthInsurance) {
		// TODO Auto-generated method stub
		healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void saveHalthInsuPropagationRequiredNew(EmployeeHealthInsurance employeeHealthInsurance) {
		// TODO Auto-generated method stub
		healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);
	}

	@Transactional(propagation = Propagation.NEVER)
	@Override
	public void saveHalthInsuPropagationNever(EmployeeHealthInsurance employeeHealthInsurance) {
		// TODO Auto-generated method stub
		healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public void saveHalthInsuPropagationMandatory(EmployeeHealthInsurance employeeHealthInsurance) {
		// TODO Auto-generated method stub
		healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);
	}

	@Override
	public void saveHalthInsuWithOutTxn(EmployeeHealthInsurance employeeHealthInsurance) {
		// TODO Auto-generated method stub
		healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);
	}

	@Override
	public void saveHalthInsuExceptionRaised(EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException {
		if (employeeHealthInsurance.getCoverageAmount() <= 0) {
			throw new InvalidInsuranceAmountException("Coverage Amount Should not be negative");
		}
		healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);
		
	}

}