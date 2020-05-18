package fr.vdelll.springmvc.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	/**
	 * Affiche les patients.
	 * 
	 * @param model
	 * @param page  : sélectionnée
	 * @param size  : nombre de patient par page
	 * @param kw    : nom du champ de recherche
	 * @return
	 */
	@GetMapping(path = "/patients")
	public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyword", defaultValue = "") String kw) {

		Page<Patient> pagePatients = patientRepository.findByNameContains(kw, PageRequest.of(page, size));

		// Test lorsque tous les patients d'une page ont été supprimés
		if (pagePatients.getTotalPages() - 1 < page) {
			page--;
			pagePatients = patientRepository.findByNameContains(kw, PageRequest.of(page, size));
		}

		model.addAttribute("patients", pagePatients.getContent());
		model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword", kw);

		return "patients";
	}

	/**
	 * Permet de supprimer un patient.
	 * 
	 * @param id
	 * @param keyword
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(path = "/deletePatient")
	public String delete(Long id, String keyword, int page, int size) {
		patientRepository.deleteById(id);
		return "redirect:/patients?page=" + page + "&keyword=" + keyword + "&size=" + size;
	}
	
	/**
	 * Redirige vers le formulaire permettant l'ajout d'un patient
	 * 
	 * @return
	 */
	@GetMapping(path = "/formPatient")
	public String formPatient(Model model) {
		model.addAttribute("patient", new Patient());
		return "formPatient";
	}
	
	@PostMapping("/savePatient")
	public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) return "formPatient";
		
		patientRepository.save(patient);
		return "formPatient";
	}

}
