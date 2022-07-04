package it.uniroma3.siw.catering.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Utente;
import it.uniroma3.siw.catering.model.dto.UtenteEditDto;
import it.uniroma3.siw.catering.repository.UtenteRepository;

@Service
@Transactional
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	protected PasswordEncoder passwordEncoder;
	
	public Utente getUtenteById(Long id) {
		Optional<Utente> optional = utenteRepository.findById(id);
		return optional.orElse(null);
	}
	
	public Utente getUtenteByUsername(String username){
		Optional<Utente> optional = utenteRepository.findByUsername(username);
		return optional.orElse(null);
	}
	
	public Utente saveUtente(Utente utente) {
		Long count = this.utenteRepository.count();
		if (count == 0) {
			utente.setRole(Utente.ADMIN_ROLE);
		} else {
			utente.setRole(Utente.DEFAULT_ROLE);
		}
		utente.setPassword(this.passwordEncoder.encode(utente.getPassword()));
		return utenteRepository.save(utente);
	}
	
	public Utente editUtente(UtenteEditDto utenteEditDto, Utente utente) {
	utente.setNome(utenteEditDto.getNome());
	utente.setCognome(utenteEditDto.getCognome());
	utente.setEmail(utenteEditDto.getEmail());
	utente.setUsername(utenteEditDto.getUsername());
	utente.setBirthday(utenteEditDto.getBirthday());
	
	return utenteRepository.save(utente);
	}
	
	public List<Utente> allUtenti(){
		return(List<Utente>) utenteRepository.findAll();
	}
}
