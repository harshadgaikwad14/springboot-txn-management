package com.example.demo.service;

import com.example.demo.exception.InvalidInsuranceAmountException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeHealthInsurance;

public interface OrganizationService {

	public void joinOrgBaiscTxn_Positive1(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

	public void joinOrgBaiscTxn_Negative1(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);
	
	public void joinOrgRollbackTxn_ExceptionRaised(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException;

	public void leaveOrgBaiscTxn(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

	public void joinOrg_ParentPropagationRequired_ChildPropagationRequired(Employee employee);

	public void joinOrg_ParentWithOutTxn_ChildPropagationRequired(Employee employee);

	public void joinOrg_ParentWithOutTxn_ChildPropagationSupports(Employee employee);

	public void joinOrg_ParentPropagationRequired_ChildPropagationSupports(Employee employee);

	public void joinOrg_ParentWithOutTxn_ChildPropagationNotSupports(Employee employee);

	public void joinOrg_ParentPropagationRequired_ChildPropagationNotSupports(Employee employee);

	public void joinOrg_ParentWithOutTxn_ChildPropagationRequiresNew(Employee employee);

	public void joinOrg_ParentPropagationRequired_ChildPropagationRequiresNew(Employee employee);

	public void joinOrg_ParentWithOutTxn_ChildPropagationNever(Employee employee);

	public void joinOrg_ParentPropagationRequired_ChildPropagationNever(Employee employee);

	public void joinOrg_ParentWithOutTxn_ChildPropagationMandatory(Employee employee);

	public void joinOrg_ParentPropagationRequired_ChildPropagationMandatory(Employee employee);


}
