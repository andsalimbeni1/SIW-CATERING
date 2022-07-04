package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.catering.model.Utente;
import it.uniroma3.siw.catering.model.dto.UtenteEditDto;
import it.uniroma3.siw.catering.service.UtenteService;

@Controller
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/profilo")
	public String getProfile(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utente utente = utenteService.getUtenteByUsername(userDetails.getUsername());
		model.addAttribute("utente", utente);
		
		return "profiloUtente";
	}
	
	@GetMapping("/editUtente")
	public String editProfile(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utente utente = utenteService.getUtenteByUsername(userDetails.getUsername());
		model.addAttribute("utente", utente);
		model.addAttribute("utenteEdit", new UtenteEditDto());
		
		return "editUtenteForm";
	}
	
	@PostMapping("/editUtente")
	public String editProfile(@Valid @ModelAttribute UtenteEditDto utenteEdit, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if(!bindingResult.hasErrors()){
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Utente utente = utenteService.getUtenteByUsername(userDetails.getUsername());
			utenteService.editUtente(utenteEdit, utente);
			return "redirect:/profilo";
		} else {
			return "redirect:/editUtente";
		}
	}

}
