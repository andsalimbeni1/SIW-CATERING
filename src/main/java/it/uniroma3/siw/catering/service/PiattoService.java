package it.uniroma3.siw.catering.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@Service
@Transactional
public class PiattoService {
	
	@Autowired
	private PiattoRepository piattoRepository;
	
	public Piatto createPiatto(Piatto piatto) {
		return piattoRepository.save(piatto);
	}
	
	public void deletePiattoById(Long id) {
		piattoRepository.deleteById(id);
	}
	
	public Piatto piattoById(Long id) {
		Optional<Piatto> optional = piattoRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public List<Piatto> getAllPiatti(){
		return(List<Piatto>) piattoRepository.findAll();
	}
}
