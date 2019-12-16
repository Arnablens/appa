package com.mindtree.HospitalManagementTeam.entityDto;

public class DoctorDto {
	private int doctorId;

	private String doctorName;

	private double doctorSalary;

	public DoctorDto() {
		super();
	}

	public DoctorDto(int doctorId, String doctorName, float doctorSalary) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorSalary = doctorSalary;
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

	public void setDoctorSalary(double doctorSalary) {
		this.doctorSalary = doctorSalary;
	}

}
