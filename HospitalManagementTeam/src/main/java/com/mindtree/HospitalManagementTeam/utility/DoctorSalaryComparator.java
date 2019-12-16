package com.mindtree.HospitalManagementTeam.utility;

import java.util.Comparator;

import com.mindtree.HospitalManagementTeam.entityDto.DoctorDto;

public class DoctorSalaryComparator implements Comparator<DoctorDto> {
	public int compare(DoctorDto d1, DoctorDto d2)
	{
		if (d1.getDoctorSalary()==d2.getDoctorSalary()) {
			return 0;
		} else if (d1.getDoctorSalary()> d2.getDoctorSalary()) {
			return 1;
		} else {
			return -1;
		}
	}

}


