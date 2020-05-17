package fr.vdelll.springmvc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.vdelll.springmvc.dao.PatientRepository;
import fr.vdelll.springmvc.entities.Patient;

@SpringBootApplication
public class SpringmvcApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null, "Pierre", new Date(), false));
		patientRepository.save(new Patient(null, "Paul", new Date(), false));
		patientRepository.save(new Patient(null, "Jacques", new Date(), false));
		
		patientRepository.findAll().forEach(p->{
			System.out.println(p.getName());
		});
	}

}
