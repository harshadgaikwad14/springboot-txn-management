package com.example.demo.test;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.exception.InvalidInsuranceAmountException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeHealthInsurance;
import com.example.demo.service.OrganizationService;

@Component
public class TestTransactionRollback {

	@Autowired
	private OrganizationService organizationService;

	public void positive_JoinOrgRollbackTxn_ExceptionRaised() {
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);

		final EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
		employeeHealthInsurance.setEmpId(empId);
		employeeHealthInsurance.setHealthInsuranceSchemeName("premium");
		employeeHealthInsurance.setCoverageAmount(20000);

		organizationService.joinOrgBaiscTxn_Positive1(employee, employeeHealthInsurance);
	}

	public void negative_JoinOrgRollbackTxn_ExceptionRaised() throws InvalidInsuranceAmountException {
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);

		final EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
		employeeHealthInsurance.setEmpId(empId);
		employeeHealthInsurance.setHealthInsuranceSchemeName("premium");
		employeeHealthInsurance.setCoverageAmount(0);

		organizationService.joinOrgRollbackTxn_ExceptionRaised(employee, employeeHealthInsurance);

	}

}
