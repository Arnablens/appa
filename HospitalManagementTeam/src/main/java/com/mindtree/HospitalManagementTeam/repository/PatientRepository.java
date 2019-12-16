package com.mindtree.HospitalManagementTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.HospitalManagementTeam.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
