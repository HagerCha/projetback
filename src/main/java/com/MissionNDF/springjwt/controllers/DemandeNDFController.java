package com.MissionNDF.springjwt.controllers;

import com.MissionNDF.springjwt.models.DemandeNDF;
import com.MissionNDF.springjwt.repository.DemandeNDFRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/NDF/")
public class DemandeNDFController {

    @Autowired

    DemandeNDFRepository demandeNDFRepositorysitory;
    
//affiche toutes les notes de frais
    @GetMapping("/AllNDF")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public Collection<DemandeNDF> getAllAllNDF() throws NotFoundException {

        return demandeNDFRepositorysitory.findAll();
    }

//suprimer NDF
    @DeleteMapping ("/DeleteNDF/{idNDF}")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public String deleteNDF(@PathVariable Long idNDF) throws NotFoundException {
        return demandeNDFRepositorysitory.findById(idNDF)
                .map(NDF -> {
                    demandeNDFRepositorysitory.delete(NDF);
                    return "La note de frais  a été supprimé avec succés!";
                }).orElseThrow(() -> new RuntimeException(" NDF avec  id=" + idNDF + " n'existe pas!"));
    }


   //ajouter NDF



//foundById

    @GetMapping("/NDFByIdUser/{idNDF}")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public Optional<DemandeNDF> getNDFByIdUser(@PathVariable Long idNDF) throws NotFoundException{

        if(!demandeNDFRepositorysitory.existsById(idNDF)) {
            throw new RuntimeException("Le membre de commition avec l'id="+idNDF+" n'existe pas!");
        }
        return demandeNDFRepositorysitory.findById(idNDF);
    }




    //ajouter NDF


    @PostMapping("/addNDF")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public String  c (@RequestBody  DemandeNDF NDF){

         demandeNDFRepositorysitory.save(NDF) ;

        return "Note de frais  a été ajoute avec succés!";

    }
    @PutMapping("/modifierNDF/{idNDF}")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public DemandeNDF updateNDF(@PathVariable Long idNDF, @Valid  @RequestBody DemandeNDF NDFUpdate) throws NotFoundException{

        if(!demandeNDFRepositorysitory.existsById(idNDF)) {
            throw new RuntimeException("La NDF  avec l'id="+idNDF+" n'existe pas!");
        }
        return demandeNDFRepositorysitory.findById(idNDF)
                .map(NDF -> {
                    NDF.setNom(NDFUpdate.getNom());
                    NDF.setNumMission(NDFUpdate.getNumMission());
                    NDF.setNbNuit(NDFUpdate.getNbNuit());
                    NDF.setMontantNuit(NDFUpdate.getMontantNuit());
                    NDF.setMontantDP(NDFUpdate.getMontantDP());
                    NDF.setMontantPC(NDFUpdate.getMontantPC());
                    NDF.setTransport(NDFUpdate.getTransport());
                    NDF.setMontantTransport(NDFUpdate.getMontantTransport());
                    NDF.setDateArrivee(NDFUpdate.getDateArrivee());
                    NDF.setDateRetour(NDFUpdate.getDateArrivee());
                    NDF.setCompagnie(NDFUpdate.getCompagnie());
                    NDF.setMontantVoyage(NDFUpdate.getMontantVoyage());

                    

                    return demandeNDFRepositorysitory.save(NDF);
                }).orElseThrow(() -> new NotFoundException());
    }
    }


