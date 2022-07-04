package it.uniroma3.siw.catering.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.repository.BuffetRepository;

@Service
@Transactional
public class BuffetService {
	
	@Autowired
	private BuffetRepository buffetRepository;
	
	public Buffet saveBuffet(Buffet buffet) {
		return buffetRepository.save(buffet);
	}
	
	public Buffet editBuffet(Buffet buffet) {
		return buffetRepository.save(buffet);
	}
	
	public void deleteBuffetById(Long id) {
		buffetRepository.deleteById(id);
	}
	
	public List<Buffet> getAllBuffet(){
		return(List<Buffet>) buffetRepository.findAll();
	}
	
	public Buffet buffetById(Long id) {
		Optional<Buffet> optional = buffetRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Boolean alreadyExists(Buffet buffet) {
		List<Buffet> list = this.buffetRepository.findByNome(buffet.getNome());
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
