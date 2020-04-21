package com.parveztest.admissionsservicems.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.parveztest.admissionsservicems.models.DiseasesList;
import com.parveztest.admissionsservicems.models.EmployeesList;
import com.parveztest.admissionsservicems.models.Patient;

@RestController
@RequestMapping("/admissions")
public class AdmissionsResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	List<Patient> patients = Arrays.asList(
			new Patient ("P1","Vinay","India"),
			new Patient ("P2","James","USA"),
			new Patient ("P3","Dory","Australia")
			);

	@RequestMapping("/physicians")
	public EmployeesList getPhysicians() {
		
		EmployeesList employeesList = restTemplate.getForObject("http://hr-service/hr/employees", EmployeesList.class);
		
		return employeesList;
		
	}
	
	@RequestMapping("/diseases")
	public DiseasesList getDiseases() {
		
		DiseasesList diseasesList = restTemplate.getForObject("http://pathology-service/pathology/diseases", DiseasesList.class);
		
		return diseasesList;
		
	}
	
	
	@RequestMapping("/patients")
	public List<Patient> getPatient(){
		
	
		return patients;
	}

	@RequestMapping("/patients/{Id}")
	public Patient getPatientById(@PathVariable("Id") String Id) {
		
		Patient p = patients.stream()
				
					.filter(patient -> Id.equals(patient.getId()))
					.findAny()
					.orElse(null);
		return p;
	}
}
