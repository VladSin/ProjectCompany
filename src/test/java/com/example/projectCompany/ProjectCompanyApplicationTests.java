package com.example.projectCompany;

import com.example.projectCompany.entity.Employee;
import com.example.projectCompany.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Commit
class ProjectCompanyApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@Test
	void saveEmployee() {
		final Employee employeeToSave = new Employee(null, "Rita", "XXX@gmail.com", 500, true, null);
		employeeService.saveEmployee(employeeToSave);
		final Employee savedEmployee = employeeService.getEmployeeByEmail("XXX@gmail.com");

		assertNotNull(savedEmployee);
		assertEquals(employeeToSave.getUsername(), savedEmployee.getUsername());


	}
}
