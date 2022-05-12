package com.MissionNDF.springjwt.controllers;

import com.MissionNDF.springjwt.models.DemandeMiss;
import com.MissionNDF.springjwt.repository.DemandeMissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/mission/")
public class DemandeMissionControle {

    @Autowired

    DemandeMissionRepository demandeMissionRepositorysitory;
    
//afficher toutes les missions
    @GetMapping("/AllMission")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')" + " || hasRole('ROLE_MANAGER')")
    

    public Collection<DemandeMiss> getAllAllMission() throws NotFoundException {

        return demandeMissionRepositorysitory.findAll();
    }

//supprimer mission
    @DeleteMapping ("/DeleteMission/{idMission}")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')" + " || hasRole('ROLE_MANAGER')")
    public String deleteMMission(@PathVariable Long idMission) throws NotFoundException {
        return demandeMissionRepositorysitory.findById(idMission)
                .map(mission -> {
                    demandeMissionRepositorysitory.delete(mission);
                    return "Mission  a été supprimé avec succés!";
                }).orElseThrow(() -> new RuntimeException(" Mission avec  id=" + idMission + " n'existe pas!"));
    }





//foundById

    @GetMapping("/MissionByIdUser/{idMission}")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')" + " || hasRole('ROLE_MANAGER')")
    public Optional<DemandeMiss> getMissionByIdUser(@PathVariable Long idMission) throws NotFoundException{

        if(!demandeMissionRepositorysitory.existsById(idMission)) {
            throw new RuntimeException("Le membre de commition avec l'id="+idMission+" n'existe pas!");
        }
        return demandeMissionRepositorysitory.findById(idMission);
    }




    //ajouter mission


    @PostMapping("/addMission")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')" + " || hasRole('ROLE_MANAGER')")
    public String  c (@RequestBody  DemandeMiss mission){

         demandeMissionRepositorysitory.save(mission) ;

        return "Mission  a été ajoute avec succés!";

    }
    
    //modifier mission
    @PutMapping("/modifierMission/{idMission}")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public DemandeMiss updateMission(@PathVariable Long idMission, @Valid  @RequestBody DemandeMiss missionUpdate) throws NotFoundException{

        if(!demandeMissionRepositorysitory.existsById(idMission)) {
            throw new RuntimeException("La mission  avec l'id="+idMission+" n'existe pas!");
        }
        return demandeMissionRepositorysitory.findById(idMission)
                .map(mission -> {
                    mission.setNom(missionUpdate.getNom());
                    mission.setDescription(missionUpdate.getDescription());
                    mission.setDateDeDebut(missionUpdate.getDateDeDebut());
                    mission.setDateDeFin(missionUpdate.getDateDeFin());
                    mission.setEtat(missionUpdate.getEtat());
                    mission.setPassport(missionUpdate.getPassport());
                    mission.setPays(missionUpdate.getPays());
                    mission.setVille(missionUpdate.getVille());


                    return demandeMissionRepositorysitory.save(mission);
                }).orElseThrow(() -> new NotFoundException());
    }
    
    
    }


