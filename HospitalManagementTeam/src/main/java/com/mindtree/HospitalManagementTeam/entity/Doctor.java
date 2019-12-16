package com.mindtree.HospitalManagementTeam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;

	private String doctorName;
	
	@Column (columnDefinition = "double default 0")
	private double doctorSalary;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
	private List<Patient> patients;

	public Doctor() {
		super();
	}

	public Doctor(int doctorId, String doctorName, float doctorSalary, List<Patient> patients) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorSalary = doctorSalary;
		this.patients = patients;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public double getDoctorSalary() {
		return doctorSalary;
	}

	public void setDoctorSalary(double salary) {
		this.doctorSalary = salary;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorSalary=" + doctorSalary
				+ ", patients=" + patients + "]";
	}
	

}
