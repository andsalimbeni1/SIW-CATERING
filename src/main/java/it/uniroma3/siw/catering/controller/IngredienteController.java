package it.uniroma3.siw.catering.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.service.IngredienteService;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService; 

	@GetMapping("/allIngredienti")
	private String allIngredienti(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.getAllIngredienti());
		return "allIngredienti";
	}
	
	@GetMapping("/ingrediente/{id}")
	private String ingrediente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.ingredienteById(id));
		return "ingrediente";
	}
	
	@GetMapping("/admin/ingredienteForm")
	private String getIngredienteForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
        
        return "/admin/ingredienteForm";
	}
	
	@GetMapping("/admin/deleteIngrediente/{id}")
	public String deleteIngrediente(@PathVariable("id") Long id) {
		ingredienteService.deleteIngredienteById(id);
		return "redirect:/allIngredienti";
	}
	
	@PostMapping("/admin/ingredienteForm")
	private String postIngredienteForm(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			ingredienteService.saveIngrediente(ingrediente);
			return "redirect:/allIngredienti";
		} else {
			return "admin/ingredienteForm";
		}
	}
	
}
