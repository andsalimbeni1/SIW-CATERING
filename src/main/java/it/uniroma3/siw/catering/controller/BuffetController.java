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

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private PiattoService piattoService;

	@GetMapping("/allBuffet")
	private String allBuffet(Model model) {
		model.addAttribute("buffets", this.buffetService.getAllBuffet());
		return "allBuffet";
	}
	
	@GetMapping("/buffet/{id}")
	private String buffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = buffetService.buffetById(id);
		List<Piatto> listaPiatti = buffet.getPiatti();
		
		model.addAttribute("buffet", buffet);
		model.addAttribute("listaPiatti", listaPiatti);
		
		return "buffet";
	}
	
	@GetMapping("/admin/buffetForm")
	private String getbuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("listaChef", chefService.getAllChef());
        model.addAttribute("listaPiatti", piattoService.getAllPiatti());
        
        return "admin/buffetForm";
	}
	
	@GetMapping("/admin/editBuffetForm/{id}")
	private String editBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", buffetService.buffetById(id));
		model.addAttribute("listaChef", chefService.getAllChef());
		
		List<Piatto> piattiAttuali = buffetService.buffetById(id).getPiatti();
		
		model.addAttribute("piatto1Attuale", piattiAttuali.get(0));
		model.addAttribute("piatto2Attuale", piattiAttuali.get(1));
		model.addAttribute("piatto3Attuale", piattiAttuali.get(2));
		model.addAttribute("piatto4Attuale", piattiAttuali.get(3));
		
		model.addAttribute("listaPiatti", piattoService.getAllPiatti());
		
		return "admin/editBuffetForm";
	}
	
	@GetMapping("/admin/deleteBuffet/{id}")
	public String deleteBUffet(@PathVariable("id") Long id) {
		buffetService.deleteBuffetById(id);
		return "redirect:/allBuffet";
	}
	
	@PostMapping("/admin/buffetForm")
	private String postBuffetForm(@Valid @ModelAttribute("buffet") Buffet buffet, @RequestParam("piatto1") Piatto piatto1,
			@RequestParam("piatto2") Piatto piatto2, @RequestParam("piatto3") Piatto piatto3, @RequestParam("piatto4") Piatto piatto4,
			@RequestParam("chefSelezionato") Chef chef, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			buffet.setChef(chef);
			buffet.getPiatti().add(piatto1);
			buffet.getPiatti().add(piatto2);
			buffet.getPiatti().add(piatto3);
			buffet.getPiatti().add(piatto4);
			
			buffetService.saveBuffet(buffet);
			return "redirect:/allBuffet";
		} else {
			return "redirect:/admin/buffetForm";
		}
	}
	
	@PostMapping("/admin/editBuffetForm/{id}")
	private String editBuffetPost(@Valid @ModelAttribute("buffet") Buffet buffet, @PathVariable("id") Long id, @RequestParam("piatto1") Piatto piatto1,
			@RequestParam("piatto2") Piatto piatto2, @RequestParam("piatto3") Piatto piatto3, @RequestParam("piatto4") Piatto piatto4,
			@RequestParam("chefSelezionato") Chef chef, BindingResult bindingResult) {
		
		
		buffet.setChef(chef);
		buffet.getPiatti().clear();
		buffet.getPiatti().add(piatto1);
		buffet.getPiatti().add(piatto2);
		buffet.getPiatti().add(piatto3);
		buffet.getPiatti().add(piatto4);
			
		buffetService.editBuffet(buffet);
		
		return "redirect:/allBuffet";
		
	}
	
}
