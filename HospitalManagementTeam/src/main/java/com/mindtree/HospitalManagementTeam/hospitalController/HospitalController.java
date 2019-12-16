package com.mindtree.HospitalManagementTeam.hospitalController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.HospitalManagementTeam.entityDto.DoctorDto;
import com.mindtree.HospitalManagementTeam.exception.ControllerException;
import com.mindtree.HospitalManagementTeam.service.HospitalService;

@RestController
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;
	
	@GetMapping("/assignDoctorToPatient/{doctorName}/{patientName}")
	public String assignDoctorToPatient(@PathVariable("doctorName") String doctorName, @PathVariable("patientName") String patientName) 
	{
		String response="";
		try
		{
			response=hospitalService.assignDoctorToPatient(doctorName, patientName);
		}
		catch (ControllerException e){
			System.out.println(e.getMessage());
		}
		return response;
	}
	
	@GetMapping("/displayDoctorsForSalary")
	public List<DoctorDto> displayDoctorsForSalary()
	{
		List<DoctorDto> doctors=new ArrayList<DoctorDto>();
		try {
			doctors = hospitalService.displayDoctorsForSalary();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return doctors;
	}
	@GetMapping("/displayDoctorsForPatientCount")
	public Set<DoctorDto> displayDoctorsForPatientCount()
	{
		Set<DoctorDto> doctors=new TreeSet<DoctorDto>();
		try {
			doctors=hospitalService.displayDoctorsForPatientCount();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return doctors;
		
	}
	
	@PostMapping("addDoctorsToFile")
	public String addDoctorsToFile()
	{
			String response="";
			try {
				response = hospitalService.addDoctorsToFile();
			} catch (ControllerException | IOException e) {
				System.out.println(e.getMessage());
			}
			
			return response;
		
	}
	
	
}
