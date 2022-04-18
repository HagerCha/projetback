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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/mission/")
public class DemandeMissionControle {








    @Autowired

    DemandeMissionRepository demandeMissionRepositorysitory;
//affiche tout les mission
    @GetMapping("/AllMission")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public Collection<DemandeMiss> getAllAllMission() throws NotFoundException {

        return demandeMissionRepositorysitory.findAll();
    }

//suprimer mission
    @DeleteMapping ("/DeleteMission/{IdMission}")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public String deleteMMission(@PathVariable Long IdMission) throws NotFoundException {
        return demandeMissionRepositorysitory.findById(IdMission)
                .map(mission -> {
                    demandeMissionRepositorysitory.delete(mission);
                    return "Mission  a été supprimé avec succés!";
                }).orElseThrow(() -> new RuntimeException(" Mission avec  id=" + IdMission + " n'existe pas!"));
    }


   //ajouter mission


    /*public ResponseEntity<DemandeMiss> createTutorial(@RequestBody DemandeMiss demandeMiss) {
        try {
            DemandeMiss mission = DemandeMissionRepository
                    .save(new DemandeMiss(demandeMiss.getDateDeDebut(), demandeMiss.getDescription(),demandeMiss.getDateDeFin(),
                            demandeMiss.getEtat(),demandeMiss.getNom(),demandeMiss.getPassport(),demandeMiss.getVille(),
                            demandeMiss.getPays()));
            return new ResponseEntity<>(mission, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }
    }*/











    @PostMapping("/addMission")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public String  c (@RequestBody  DemandeMiss mission){

         demandeMissionRepositorysitory.save(mission) ;

        return "Mission  a été ajoute avec succés!";

    }
    @PutMapping("/modifierMembre/{IdMission}")
    @PreAuthorize("hasRole('ROLE_COLLABORATEUR')")
    public DemandeMiss updateMission(@PathVariable Long IdMission, @Valid  @RequestBody DemandeMiss missionUpdate) throws NotFoundException{

        if(!demandeMissionRepositorysitory.existsById(IdMission)) {
            throw new RuntimeException("La mission  avec l'id="+IdMission+" n'existe pas!");
        }
        return demandeMissionRepositorysitory.findById(IdMission)
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


