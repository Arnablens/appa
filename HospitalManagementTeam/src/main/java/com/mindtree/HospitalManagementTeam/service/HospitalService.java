package com.mindtree.HospitalManagementTeam.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mindtree.HospitalManagementTeam.entityDto.DoctorDto;
import com.mindtree.HospitalManagementTeam.exception.ControllerException;

@Service
public interface HospitalService {

	public String assignDoctorToPatient(String doctorName, String patientName) throws ControllerException;

	public List<DoctorDto> displayDoctorsForSalary() throws ControllerException;

	public Set<DoctorDto> displayDoctorsForPatientCount() throws ControllerException;

	public String addDoctorsToFile() throws ControllerException, IOException;

}
