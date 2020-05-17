package fr.vdelll.springmvc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.vdelll.springmvc.dao.PatientRepository;
import fr.vdelll.springmvc.entities.Patient;

@Controller
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping(path = "/index")
	public String index() {
		return "index";
	}
	
	@GetMapping(path = "/patients")
	public String list(Model model) {
		List<Patient> patients = patientRepository.findAll();
		model.addAttribute("patients", patients);
		return "patients";
	}

}
