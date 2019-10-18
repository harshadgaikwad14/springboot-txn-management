package com.example.demo.test;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.service.OrganizationService;

@Component
public class TestPropagationTxnMgment {

	@Autowired
	private OrganizationService organizationService;
	
	public void joinOrg_ParentWithOutTxn_ChildPropagationRequired()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);

		

		organizationService.joinOrg_ParentWithOutTxn_ChildPropagationRequired(employee);
	}
	
	public void joinOrg_ParentPropagationRequired_ChildPropagationRequired()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentPropagationRequired_ChildPropagationRequired(employee);
	}
	
	public void joinOrg_ParentPropagationRequired_ChildPropagationSupports()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentPropagationRequired_ChildPropagationSupports(employee);
	}
	
	public void joinOrg_ParentWithOutTxn_ChildPropagationSupports()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentWithOutTxn_ChildPropagationSupports(employee);
	}
	
	public void joinOrg_ParentWithOutTxn_ChildPropagationNotSupports()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentWithOutTxn_ChildPropagationNotSupports(employee);
	}
	
	public void joinOrg_ParentPropagationRequired_ChildPropagationNotSupports()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentPropagationRequired_ChildPropagationNotSupports(employee);
	}
	
	public void joinOrg_ParentWithOutTxn_ChildPropagationRequiresNew()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentWithOutTxn_ChildPropagationRequiresNew(employee);
	}
	
	public void joinOrg_ParentPropagationRequired_ChildPropagationRequiresNew()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentPropagationRequired_ChildPropagationRequiresNew(employee);
	}
	
	public void joinOrg_ParentWithOutTxn_ChildPropagationNever()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentWithOutTxn_ChildPropagationNever(employee);
	}
	
	public void joinOrg_ParentPropagationRequired_ChildPropagationNever()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentPropagationRequired_ChildPropagationNever(employee);
	}
	
	
	public void joinOrg_ParentWithOutTxn_ChildPropagationMandatory()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentWithOutTxn_ChildPropagationMandatory(employee);
	}
	
	public void joinOrg_ParentPropagationRequired_ChildPropagationMandatory()
	{
		final String empId = UUID.randomUUID().toString();
		final Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		organizationService.joinOrg_ParentPropagationRequired_ChildPropagationMandatory(employee);
	}
	
	
	
	
	
}
