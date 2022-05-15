package com.MissionNDF.springjwt.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class DemandeNDFRequest {
	@NotBlank
	private Long idNDF;
	
	@NotBlank
	private String nom;
	
	@NotBlank
	private int numMission;
	
	@NotBlank
	private float nbNuit;
	
	@NotBlank
	private float montantNuit;
	
	@NotBlank
	private String transport;
	
	@NotBlank
	private float montantTransport;
	
	@NotBlank
	private LocalDate dateArrivee;
	
	@NotBlank
	private LocalDate dateRetour;
	
	@NotBlank
	private String compagnie;
	
	@NotBlank
	private float montantVoyage;
	
	@NotBlank
	private float total;
	
	@NotBlank
	private String etat ;

	public Long getIdNDF() {
		return idNDF;
	}

	public void setIdNDF(Long idNDF) {
		this.idNDF = idNDF;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNumMission() {
		return numMission;
	}

	public void setNumMission(int numMission) {
		this.numMission = numMission;
	}


	public float getNbNuit() {
		return nbNuit;
	}

	public void setNbNuit(float nbNuit) {
		this.nbNuit = nbNuit;
	}

	public float getMontantNuit() {
		return montantNuit;
	}

	public void setMontantNuit(float montantNuit) {
		this.montantNuit = montantNuit;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public float getMontantTransport() {
		return montantTransport;
	}

	public void setMontantTransport(float montantTransport) {
		this.montantTransport = montantTransport;
	}

	public LocalDate getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(LocalDate dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public LocalDate getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}

	public String getCompagnie() {
		return compagnie;
	}

	public void setCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}

	public float getMontantVoyage() {
		return montantVoyage;
	}

	public void setMontantVoyage(float montantVoyage) {
		this.montantVoyage = montantVoyage;
	}

	public float getTotal() {
		return ((nbNuit*montantNuit) + (montantTransport) +(montantVoyage));
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
}
