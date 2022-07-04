package it.uniroma3.siw.catering.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.dto.IngredienteEditDto;
import it.uniroma3.siw.catering.repository.IngredienteRepository;

@Service
@Transactional
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public Ingrediente saveIngrediente(Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}
	
	public void deleteIngredienteById(Long id) {
		ingredienteRepository.deleteById(id);
	}
	
	public Ingrediente ingredienteById(Long id) {
		Optional<Ingrediente> optional = ingredienteRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public boolean alreadyExists(Ingrediente ingrediente) {
		List<Ingrediente> list = this.ingredienteRepository.findByNome(ingrediente.getNome());
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	} 
	
	public List<Ingrediente> getAllIngredienti(){
		return(List<Ingrediente>) ingredienteRepository.findAll();
	}
}
