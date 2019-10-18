package com.example.demo.test;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeHealthInsurance;
import com.example.demo.service.OrganizationService;

@Component
public class TestTxnIsolation {

	@Autowired
	private OrganizationService organizationService;

	public void joinOrg_Isolation_Txn_Serializable() {
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);

		final EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
		employeeHealthInsurance.setEmpId(empId);
		employeeHealthInsurance.setHealthInsuranceSchemeName("premium");
		employeeHealthInsurance.setCoverageAmount(20000);

		organizationService.joinOrg_Isolation_Txn_Serializable(employee, employeeHealthInsurance);
	}

	

}
