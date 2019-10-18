package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.InvalidInsuranceAmountException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeHealthInsurance;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.HealthInsuranceService;
import com.example.demo.service.OrganizationService;

@Service
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	HealthInsuranceService healthInsuranceService;

	@Override
	@Transactional
	public void joinOrgBaiscTxn_Positive1(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.saveEmpBasicTxn(employee);
		healthInsuranceService.saveHalthInsuBasicTxn(employeeHealthInsurance);
	}

	@Override
	@Transactional
	public void joinOrgBaiscTxn_Negative1(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.saveEmpBasicTxn(employee);
		if (employee.getEmpId().equals("emp1")) {
			throw new RuntimeException("thowing exception to test transaction rollback");
		}
		healthInsuranceService.saveHalthInsuBasicTxn(employeeHealthInsurance);
	}

	@Override
	@Transactional
	public void leaveOrgBaiscTxn(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmpBasicTxn(employee.getEmpId());
		healthInsuranceService.deleteHalthInsuBasicTxn(employeeHealthInsurance.getEmpId());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void joinOrg_ParentPropagationRequired_ChildPropagationRequired(Employee employee) {
		employeeService.saveEmpPropagationRequired(employee);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void joinOrg_ParentPropagationRequired_ChildPropagationSupports(Employee employee) {
		employeeService.saveEmpPropagationSupport(employee);

	}

	@Override
	public void joinOrg_ParentWithOutTxn_ChildPropagationSupports(Employee employee) {
		employeeService.saveEmpPropagationSupport(employee);

	}

	@Override
	public void joinOrg_ParentWithOutTxn_ChildPropagationNotSupports(Employee employee) {
		employeeService.saveEmpPropagationNotSupport(employee);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void joinOrg_ParentPropagationRequired_ChildPropagationNotSupports(Employee employee) {
		// TODO Auto-generated method stub
		employeeService.saveEmpPropagationNotSupport(employee);
	}

	@Override
	public void joinOrg_ParentWithOutTxn_ChildPropagationRequiresNew(Employee employee) {
		// TODO Auto-generated method stub
		employeeService.saveEmpPropagationRequiredNew(employee);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void joinOrg_ParentPropagationRequired_ChildPropagationRequiresNew(Employee employee) {
		// TODO Auto-generated method stub

		employeeService.saveEmpPropagationRequiredNew(employee);

	}

	@Override
	public void joinOrg_ParentWithOutTxn_ChildPropagationNever(Employee employee) {
		employeeService.saveEmpPropagationNever(employee);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void joinOrg_ParentPropagationRequired_ChildPropagationNever(Employee employee) {
		// TODO Auto-generated method stub
		employeeService.saveEmpPropagationNever(employee);
	}

	@Override
	public void joinOrg_ParentWithOutTxn_ChildPropagationMandatory(Employee employee) {
		// TODO Auto-generated method stub
		employeeService.saveEmpPropagationMandatory(employee);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void joinOrg_ParentPropagationRequired_ChildPropagationMandatory(Employee employee) {
		// TODO Auto-generated method stub
		employeeService.saveEmpPropagationMandatory(employee);

	}

	@Override
	public void joinOrg_ParentWithOutTxn_ChildPropagationRequired(Employee employee) {
		// TODO Auto-generated method stub
		employeeService.saveEmpPropagationRequired(employee);
	}

	@Override
	@Transactional(rollbackFor = InvalidInsuranceAmountException.class)
	public void joinOrgRollbackTxn_ExceptionRaised(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException {
		// TODO Auto-generated method stub
		employeeService.saveEmpBasicTxn(employee);
		try {
			healthInsuranceService.saveHalthInsuExceptionRaised(employeeHealthInsurance);
		} catch (InvalidInsuranceAmountException e) {
			throw new InvalidInsuranceAmountException("Exception is thrown");
		}
		
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void joinOrg_Isolation_Txn_Serializable(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		// TODO Auto-generated method stub
		
		employeeService.saveEmpWithOutTxn(employee);
		healthInsuranceService.saveHalthInsuWithOutTxn(employeeHealthInsurance);
		
		
	}
}