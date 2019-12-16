package com.mindtree.HospitalManagementTeam.service.serviceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.HospitalManagementTeam.entity.Doctor;
import com.mindtree.HospitalManagementTeam.entity.Patient;
import com.mindtree.HospitalManagementTeam.entityDto.DoctorDto;
import com.mindtree.HospitalManagementTeam.exception.ControllerException;
import com.mindtree.HospitalManagementTeam.exception.NoDoctorFoundException;
import com.mindtree.HospitalManagementTeam.exception.NoPatientFoundException;
import com.mindtree.HospitalManagementTeam.repository.DoctorRepository;
import com.mindtree.HospitalManagementTeam.repository.PatientRepository;
import com.mindtree.HospitalManagementTeam.service.HospitalService;
import com.mindtree.HospitalManagementTeam.utility.DoctorSalaryComparator;

@Service
public class HospitalServiceImpl implements HospitalService {
	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	PatientRepository patientRepository;

	@Override
	public String assignDoctorToPatient(String doctorName, String patientName) throws ControllerException {
		List<Doctor> doctors = doctorRepository.findAll();
		List<Patient> patients = patientRepository.findAll();
		int doctorCount=0;
		int patientCount=0;
		Doctor doctor=new Doctor();
		Patient patient=new Patient();
		for (Patient patientt : patients) {
			if(patientt.getPatientName().compareToIgnoreCase(patientName)==0)
			{
				patient=patientt;
				patientCount++;
			}
			else
			{
				continue;
			}		
		}
		for (Doctor doctorr : doctors) {
			if(doctorr.getDoctorName().compareToIgnoreCase(doctorName)==0)
			{
				doctor=doctorr;
				doctorCount++;
			}
			else
			{
				continue;
			}
		}
		List <Patient> listOfPatients=new ArrayList<Patient>();
		if(doctor.getPatients()==null)
		{
			
			double salary=0;
			salary=patient.getBillAmount();
			listOfPatients.add(patient);
			patient.setDoctor(doctor);
			doctor.setPatients(listOfPatients);
			doctorRepository.save(doctor);
		}
		else
		{
			listOfPatients=doctor.getPatients();
			listOfPatients.add(patient);
			double salary=0;
			for (Patient patient2 : listOfPatients) {
				salary=salary+patient2.getBillAmount();
			}
			doctor.setDoctorSalary(salary);
			List<Patient> newListOfPatients=new ArrayList<Patient>();
			for (Patient patient2 : listOfPatients) {
				patient2.setDoctor(doctor);
				newListOfPatients.add(patient2);	
			}
			doctor.setPatients(newListOfPatients);
			doctorRepository.save(doctor);
		}
		if(doctorCount==0)
		{
			throw new NoDoctorFoundException("No Doctor is present");
		}
		if(patientCount==0)
		{
			throw new NoPatientFoundException("No patient is present");
		}
		return "Data Added Successfully";

	}
	
	@Override
	public List<DoctorDto> displayDoctorsForSalary() throws ControllerException {
		// TODO Auto-generated method stub
		List<Doctor> doctors=doctorRepository.findAll();
		int doctorCount=0;
		List<DoctorDto> listOfDoctors=new ArrayList<DoctorDto>();
		for (Doctor doctor : doctors) {
			if(doctor.getPatients()!=null)
			{
				ModelMapper modelMapper = new ModelMapper();
				DoctorDto newDoctor=modelMapper.map(doctor, DoctorDto.class);
				listOfDoctors.add(newDoctor);
				doctorCount++;
			}	
		}
		Collections.sort(listOfDoctors, new DoctorSalaryComparator());
		if(doctorCount==0)
		{
			throw new NoDoctorFoundException("No Doctor Found ");
		}
		return listOfDoctors;
	}

	@Override
	public Set<DoctorDto> displayDoctorsForPatientCount() throws ControllerException {
		List<Doctor> Doctors=doctorRepository.findAll();
		Set<DoctorDto> listOfDoctors=new TreeSet<DoctorDto>();
		int doctorCount=0;
		for (Doctor doctor : Doctors) {
			List<Patient> patients=doctor.getPatients();
			if(patients.size()>3)
			{
				ModelMapper modelMapper = new ModelMapper();
				DoctorDto newDoctor=modelMapper.map(doctor, DoctorDto.class);
				listOfDoctors.add(newDoctor);
				doctorCount++;
			}
			else
			{
				continue;
			}
		}
		if(doctorCount==0)
		{
			throw new NoDoctorFoundException("No Doctor found");
		}
		
		return listOfDoctors;
	}

	@Override
	public String addDoctorsToFile() throws ControllerException, IOException {
		File file =new File("C:\\Users\\m1056196\\Desktop\\write.txt"); 
		BufferedWriter wr=new BufferedWriter(new FileWriter(file));
		List<Doctor> doctors=doctorRepository.findAll();
		int doctorCount=0;
		for (Doctor doctor : doctors) {
			if(doctor.getPatients()!=null)
			{
				wr.write(doctor.toString());
				wr.newLine();
				doctorCount++;
			}
		}
		wr.close();
		if(doctorCount==0)
		{
			throw new NoDoctorFoundException("No Doctor available");
		}
		return "Added Succesfully";
	}
	

}







