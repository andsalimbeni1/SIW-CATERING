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

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;

	@GetMapping("/allChefs")
	private String allChefs(Model model) {
		model.addAttribute("chefs", this.chefService.getAllChef());
		return "allChefs";
	}
	
	@GetMapping("/chef/{id}")
	private String chef(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.chefById(id);
		List<Buffet> buffets = chef.getBuffet();
				
		model.addAttribute("chef", chef);
		model.addAttribute("buffets", buffets);
		
		return "chef";
	}
	
	@GetMapping("/admin/chefForm")
	private String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
        
        return "admin/chefForm";
	}
	
	@GetMapping("/admin/deleteChef/{id}")
	public String deleteChef(@PathVariable("id") Long id) {
		chefService.deleteChefById(id);
		return "redirect:/allChefs";
	}
	
	@PostMapping("/admin/chefForm")
	private String postChefForm(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			chefService.saveChef(chef);
			return "redirect:/allChefs";
		} else {
			return "admin/chefForm";
		}
	}
	
}
