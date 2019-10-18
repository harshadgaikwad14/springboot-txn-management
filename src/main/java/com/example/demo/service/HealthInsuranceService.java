package com.example.demo.service;

import com.example.demo.exception.InvalidInsuranceAmountException;
import com.example.demo.model.EmployeeHealthInsurance;

public interface HealthInsuranceService {
	void saveHalthInsuBasicTxn(EmployeeHealthInsurance employeeHealthInsurance);
	
	void saveHalthInsuExceptionRaised(EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException;

	void deleteHalthInsuBasicTxn(String empid);

	void saveHalthInsuPropagationRequired(EmployeeHealthInsurance employeeHealthInsurance);

	void saveHalthInsuPropagationSupport(EmployeeHealthInsurance employeeHealthInsurance);

	void saveHalthInsuPropagationNonSupport(EmployeeHealthInsurance employeeHealthInsurance);

	void saveHalthInsuPropagationRequiredNew(EmployeeHealthInsurance employeeHealthInsurance);

	void saveHalthInsuPropagationNever(EmployeeHealthInsurance employeeHealthInsurance);

	void saveHalthInsuPropagationMandatory(EmployeeHealthInsurance employeeHealthInsurance);

	void saveHalthInsuWithOutTxn(EmployeeHealthInsurance employeeHealthInsurance);
}