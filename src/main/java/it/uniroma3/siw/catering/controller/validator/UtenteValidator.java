package it.uniroma3.siw.catering.controller.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Utente;
import it.uniroma3.siw.catering.service.UtenteService;

@Component
public class UtenteValidator implements Validator {
	
	@Autowired
	private UtenteService utenteService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.utenteService.alreadyExists((Utente)o)) {
			errors.reject("username", "Username già esistente");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Utente.class.equals(clazz);
	}

}
