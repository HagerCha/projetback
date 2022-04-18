package com.MissionNDF.springjwt.payload.request;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class DemandeMissionRequest {
	@NotBlank
	  private long idMission;
	
	@NotBlank
	  private String nom;
	@NotBlank
	  private String passport;
	@NotBlank
	  private String description;
	@NotBlank
	  private LocalDate dateDeDebut;
	@NotBlank
	  private LocalDate dateDeFin;
	@NotBlank
	private String pays;
	@NotBlank
	private String ville;
	@NotBlank
	private String etat;

	public long getIdMission() {
		return idMission;
	}
	public void setIdMission(long idMission) {
		idMission = idMission;
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
		return description;
	}
	public void setDescription(String description) {
		description = description;
	}
	public LocalDate getDateDeDebut() {
		return dateDeDebut;
	}
	public void setDateDeDebut(LocalDate dateDeDebut) {
		dateDeDebut = dateDeDebut;
	}
	public LocalDate getDateDeFin() {
		return dateDeFin;
	}
	public void setDateDeFin(LocalDate dateDeFin) {
		dateDeFin = dateDeFin;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		ville = ville;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		etat = etat;
	}

}
