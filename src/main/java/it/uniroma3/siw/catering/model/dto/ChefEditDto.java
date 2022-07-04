package it.uniroma3.siw.catering.model.dto;

import java.util.List;

import it.uniroma3.siw.catering.model.Buffet;

public class ChefEditDto {
	
	private String nome;
	
	private String cognome;
	
	private String nazionalita;
	
	private List<Buffet> buffet;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Buffet> getBuffet() {
		return buffet;
	}

	public void setBuffet(List<Buffet> buffet) {
		this.buffet = buffet;
	}
	

}
