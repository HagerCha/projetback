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
@Table(name ="NDF")
public class DemandeNDF  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idNDF;
	private String nom;
	private String numMission;
	private float nbNuit;
	private float montantNuit;
	private float montantDP;
	private float montantPC;
	private String transport;
	private float montantTransport;
	private LocalDate dateArrivee;
	private LocalDate dateRetour;
	private String compagnie;
	private float montantVoyage;
	
	
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
	public String getNumMission() {
		return numMission;
	}
	public void setNumMission(String numMission) {
		this.numMission = numMission;
	}
	
	public float getNbNuit() {
		return nbNuit;
	}
	public void setNbNuit(float nbNuit) {
		this.nbNuit = nbNuit;
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
	public float getMontantNuit() {
		return montantNuit;
	}
	public void setMontantNuit(float montantNuit) {
		this.montantNuit = montantNuit;
	}
	public float getMontantDP() {
		return montantDP;
	}
	public void setMontantDP(float montantDP) {
		this.montantDP = montantDP;
	}
	public float getMontantPC() {
		return montantPC;
	}
	public void setMontantPC(float montantPC) {
		this.montantPC = montantPC;
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
	
	
}