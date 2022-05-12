package com.MissionNDF.springjwt.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name ="Mission")
public class DemandeMiss  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idMission;
	private String nom;
	private String passport;
	private String description;
	private LocalDate dateDeDebut;
	private LocalDate dateDeFin;
	private String pays;
	private String ville;
	private String etat;

	public Long getIdMission() {
		return idMission;
	}

	public void setIdMission(Long idMission) {
		this.idMission = idMission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDeDebut() {
		return dateDeDebut;
	}

	public void setDateDeDebut(LocalDate dateDeDebut) {
		this.dateDeDebut = dateDeDebut;
	}

	public LocalDate getDateDeFin() {
		return dateDeFin;
	}

	public void setDateDeFin(LocalDate dateDeFin) {
		this.dateDeFin = dateDeFin;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
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


}

