package com.example.demo.test;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeHealthInsurance;
import com.example.demo.service.OrganizationService;

@Component
public class TestBasicTxnMgment {

	@Autowired
	private OrganizationService organizationService;

	public void pTestScenario1() {
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

	public void nTestScenario1() {
		final Employee employee = new Employee();
		employee.setEmpId("emp1");
		employee.setEmpName("emp1");

		final EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
		employeeHealthInsurance.setEmpId("emp1");
		employeeHealthInsurance.setHealthInsuranceSchemeName("premium");
		employeeHealthInsurance.setCoverageAmount(20000);

		organizationService.joinOrgBaiscTxn_Negative1(employee, employeeHealthInsurance);

	}

}
