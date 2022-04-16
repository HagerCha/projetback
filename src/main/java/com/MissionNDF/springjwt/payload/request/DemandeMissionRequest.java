package com.MissionNDF.springjwt.payload.request;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class DemandeMissionRequest {
	@NotBlank
	  private long IdMission;
	
	@NotBlank
	  private String nom;
	@NotBlank
	  private String passport;
	@NotBlank
	  private String Description;
	@NotBlank
	  private LocalDate DateDeDebut;
	@NotBlank
	  private LocalDate DateDeFin;
	public long getIdMission() {
		return IdMission;
	}
	public void setIdMission(long idMission) {
		IdMission = idMission;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public LocalDate getDateDeDebut() {
		return DateDeDebut;
	}
	public void setDateDeDebut(LocalDate dateDeDebut) {
		DateDeDebut = dateDeDebut;
	}
	public LocalDate getDateDeFin() {
		return DateDeFin;
	}
	public void setDateDeFin(LocalDate dateDeFin) {
		DateDeFin = dateDeFin;
	}
	public String getPays() {
		return Pays;
	}
	public void setPays(String pays) {
		Pays = pays;
	}
	public String getVille() {
		return Ville;
	}
	public void setVille(String ville) {
		Ville = ville;
	}
	public String getEtat() {
		return Etat;
	}
	public void setEtat(String etat) {
		Etat = etat;
	}
	@NotBlank
	  private String Pays;
	@NotBlank
	  private String Ville;
	@NotBlank
	  private String Etat;
		
}
