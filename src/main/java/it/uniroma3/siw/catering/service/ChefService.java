package it.uniroma3.siw.catering.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
@Transactional
public class ChefService {
	
	@Autowired
	private ChefRepository chefRepository;
	
	public Chef saveChef(Chef chef) {
		return chefRepository.save(chef);
	}

	public void deleteChefById(Long id) {
		chefRepository.deleteById(id);
	}
	
	public List<Chef> getAllChef(){
		return(List<Chef>) chefRepository.findAll();
	}
	
	public Chef chefById(Long id) {
		Optional<Chef> optional = chefRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public boolean alreadyExists(Chef chef) {
		List<Chef> list = this.chefRepository.findByNomeAndCognome(chef.getNome(), chef.getCognome());
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
