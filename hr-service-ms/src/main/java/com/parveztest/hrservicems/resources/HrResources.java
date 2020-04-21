package com.parveztest.hrservicems.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parveztest.hrservicems.modles.Employee;
import com.parveztest.hrservicems.modles.EmployeesList;

@RestController
@RequestMapping("/hr")
public class HrResources {

	List<Employee> employees = Arrays.asList(
			new Employee ("E1","Karan","Mehta","MediTech"),
			new Employee ("E2","Ravi","Singh", "Surgeon"),
			new Employee ("E3","Boris","Wilson", "Dentistry")
			);

	@RequestMapping("/employees")
	public EmployeesList getEmployees(){      //was returning List<Employee> earlier
		
	EmployeesList employeesList = new EmployeesList();
	
	employeesList.setEmployees(employees);
		return employeesList;
	}
	
	@RequestMapping("/employees/{Id}")
	public Employee getEmployeeById (@PathVariable("Id") String Id) {
	Employee e = employees.stream()
				.filter(employee -> Id.equals(employee.getId()))
				.findAny()
				.orElse(null);

		return e;
	}
}
