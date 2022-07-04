package it.uniroma3.siw.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private IngredienteService ingredienteService; 
	
	@Autowired
	private PiattoService piattoService;

	@GetMapping("/allPiatti")
	private String allPiatti(Model model) {
		model.addAttribute("piatti", this.piattoService.getAllPiatti());
		return "allPiatti";
	}
	
	@GetMapping("/piatto/{id}")
	private String piatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto = piattoService.piattoById(id);
		List<Ingrediente> ingredienti = piatto.getIngredienti();
				
		model.addAttribute("piatto", piatto);
		model.addAttribute("ingredienti", ingredienti);
		
		return "piatto";
	}
	
	@GetMapping("/admin/piattoForm")
	private String getPiattoForm(Model model) {
		model.addAttribute("piatto", new Piatto());
        model.addAttribute("ingredienti", ingredienteService.getAllIngredienti());
        
        return "/admin/piattoForm";
	}
	
	@GetMapping("/admin/deletePiatto/{id}")
	public String deletePiatto(@PathVariable("id") Long id) {
		piattoService.deletePiattoById(id);
		return "redirect:/allPiatti";
	}
	
	@PostMapping("/admin/piattoForm")
	private String postPiattoForm(@Valid @ModelAttribute("piatto") Piatto piatto, @RequestParam("ingrediente1") Ingrediente ingrediente1,
			@RequestParam("ingrediente2") Ingrediente ingrediente2, @RequestParam("ingrediente3") Ingrediente ingrediente3,
			@RequestParam("ingrediente4") Ingrediente ingrediente4, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			piatto.getIngredienti().add(ingrediente1);
			piatto.getIngredienti().add(ingrediente2);
			piatto.getIngredienti().add(ingrediente3);
			piatto.getIngredienti().add(ingrediente4);
			
			piattoService.createPiatto(piatto);
			return "redirect:/allPiatti";
		} else {
			return "admin/piattoForm";
		}
	}
	
}
