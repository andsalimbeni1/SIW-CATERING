package it.uniroma3.siw.catering.model.dto;

import javax.persistence.ManyToOne;

import it.uniroma3.siw.catering.model.Piatto;


public class IngredienteEditDto {

	private String nome;
	
	private String origine;
	
	private String descrizione;
	
	@ManyToOne
	private Piatto piatto;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Piatto getPiatto() {
		return piatto;
	}

	public void setPiatto(Piatto piatto) {
		this.piatto = piatto;
	}
	
}
