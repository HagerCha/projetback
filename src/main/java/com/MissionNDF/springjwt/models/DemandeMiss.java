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
    private Long IdMission;
    private String nom;
    private String passport;
    private String Description;
    private LocalDate DateDeDebut;
    private LocalDate DateDeFin;
    private String Pays;
    private String Ville;
    private String Etat;
    

	
	//@OneToMany(mappedBy = "Mission")
    //private List<Reservation> ListReservation;
	
	//@OneToOne(cascade = CascadeType.ALL)
    //private NDF NFDMission;
	
	
	public Long getIdMission() {
		return IdMission;
	}
	public void setIdMission(Long idMission) {
		IdMission = idMission;
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
}

