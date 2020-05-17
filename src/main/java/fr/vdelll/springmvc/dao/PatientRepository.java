package fr.vdelll.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vdelll.springmvc.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
