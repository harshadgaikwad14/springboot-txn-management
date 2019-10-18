DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS employeeHealthInsurance;


CREATE TABLE employee (
  empId VARCHAR(1000) NOT NULL,
  empName VARCHAR(1000) NOT NULL
);

CREATE TABLE employeeHealthInsurance (
  empId VARCHAR(1000) NOT NULL,
  healthInsuranceSchemeName VARCHAR(1000) NOT NULL,
  coverageAmount VARCHAR(1000) NOT NULL
);