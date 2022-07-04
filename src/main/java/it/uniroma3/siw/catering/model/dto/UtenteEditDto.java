package it.uniroma3.siw.catering.model.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class UtenteEditDto {
	
	@Size(min = 3, max = 20, message = "Inserire un nome valido")
	private String nome;
	
	@NotBlank(message="Inserire un cognome")
	private String cognome;
	
	@NotBlank(message="Inserire un indirizzo e-mail")
	@Email(message = "Inserire un indirizzo e-mail valido")
	private String email;
	
	@NotBlank(message="Inserire un username")
	@Column(unique = true)
	private String username;
	
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

}
