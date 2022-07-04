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

import it.uniroma3.siw.catering.model.Utente;
import it.uniroma3.siw.catering.service.UtenteService;

@Controller
public class AuthenticationController {

	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/register")
	public String registrazioneUtente(Model model) {
		
		model.addAttribute("utente", new Utente());
		
		return "utenteForm";
	}
	
	@GetMapping("/login")
	public String loginUtente(Model model) {
		return "loginForm";
	}
	
	@GetMapping(value={"/","index", "/logout"})
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping(value={"/default", "home"})
	public String defaultAfterLogin(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utente utente = utenteService.getUtenteByUsername(userDetails.getUsername());
		if (utente.getRole().equals(Utente.ADMIN_ROLE)) {
			return "admin/home";
		} else {
			return "home";
		}
	}
	
	@PostMapping("/register")
	public String nuovoUtente(@Valid @ModelAttribute("utente") Utente utente, BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			utenteService.saveUtente(utente);
			return "redirect:/login";
		} else {
			return "utenteForm";
		}
	}
	
}
