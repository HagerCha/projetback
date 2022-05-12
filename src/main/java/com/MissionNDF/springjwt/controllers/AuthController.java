package com.MissionNDF.springjwt.controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.MissionNDF.springjwt.models.DemandeMiss;
import com.MissionNDF.springjwt.repository.DemandeMissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.MissionNDF.springjwt.models.ERole;
import com.MissionNDF.springjwt.models.Role;
import com.MissionNDF.springjwt.models.User;
import com.MissionNDF.springjwt.payload.request.LoginRequest;
import com.MissionNDF.springjwt.payload.request.SignupRequest;
import com.MissionNDF.springjwt.payload.response.JwtResponse;
import com.MissionNDF.springjwt.payload.response.MessageResponse;
import com.MissionNDF.springjwt.repository.RoleRepository;
import com.MissionNDF.springjwt.repository.UserRepository;
import com.MissionNDF.springjwt.security.jwt.JwtUtils;
import com.MissionNDF.springjwt.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  //login
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(),
                         userDetails.getEmail(),
                         roles, null, null, null, jwt));
  }

  @PostMapping("/signup")
  public User registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


    // Create new user's account
    User user = new User(signUpRequest.getEmail(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
    		signUpRequest.getNom(), signUpRequest.getPrenom(), signUpRequest.getPassport(), signUpRequest.getRoleUtilisateur());
    
    String strRole = signUpRequest.getRoleUtilisateur();
    Set<Role> roles = new HashSet<>();

    
    switch (strRole.toUpperCase()) {
    case "ADMIN":
      Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(adminRole);

      break;
  
    case "MANAGER":
        Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(managerRole);

        break;
    case "GESTIONNAIRE DE PAIE":
        Role paieRole = roleRepository.findByName(ERole.ROLE_PAIE)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(paieRole);

        break;
    case "ASSISTANT DE RESERVATION":
      Role assistRole = roleRepository.findByName(ERole.ROLE_ASSIST)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(assistRole);

      break;
      case "COLLABORATEUR":
        Role collaborateurRolee = roleRepository.findByName(ERole.ROLE_COLLABORATEUR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(collaborateurRolee);
        break;
    
    };
      
    
    
    /*Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role collaborateurRole = roleRepository.findByName(ERole.ROLE_COLLABORATEUR)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(collaborateurRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
      
        case "manager":
            Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(managerRole);

            break;
        case "paie":
            Role paieRole = roleRepository.findByName(ERole.ROLE_PAIE)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(paieRole);

            break;
        case "assist":
          Role assistRole = roleRepository.findByName(ERole.ROLE_ASSIST)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(assistRole);

          break;
          case "collaborateur":
            Role collaborateurRolee = roleRepository.findByName(ERole.ROLE_COLLABORATEUR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(collaborateurRolee);

            break;
        default:
          Role collaborateurRole = roleRepository.findByName(ERole.ROLE_COLLABORATEUR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(collaborateurRole);
        }
      });
    }

    user.setRoles(roles);*/
      
    return userRepository.save(user);


   // return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }




  //afficher tous les utilisateurs
  @GetMapping("/AllUser")
  @PreAuthorize("hasRole('ROLE_ADMIN')")


  public Collection<User> getAllUser() throws ChangeSetPersister.NotFoundException {

    return userRepository.findAll();
  }
  

//foundById

  @GetMapping("/UserByIdUser/{idUser}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Optional<User> getUserByIdUser(@PathVariable Long idUser) throws NotFoundException{

      if(!userRepository.existsById(idUser)) {
          throw new RuntimeException("Le membre de commition avec l'id="+idUser+" n'existe pas!");
      }
      return userRepository.findById(idUser);
  }

//supprimer user
  @DeleteMapping ("/DeleteUser/{idUser}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deleteUser(@PathVariable Long idUser) throws NotFoundException {
      return userRepository.findById(idUser)
              .map(mission -> {
            	  userRepository.deleteById(idUser);
                  return "Utilisateur  a été supprimé avec succés!";
              }).orElseThrow(() -> new RuntimeException(" Mission avec  id=" + idUser + " n'existe pas!"));
  }
  
  //modifier user
  @PutMapping("/modifierUser/{idUser}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public User updateUser(@PathVariable Long idUser, @Valid  @RequestBody User userUpdate) throws NotFoundException{

      if(!userRepository.existsById(idUser)) {
          throw new RuntimeException("La mission  avec l'id="+idUser+" n'existe pas!");
      }
      return userRepository.findById(idUser)
              .map(user -> {
                  user.setNom(userUpdate.getNom());
                  user.setPrenom(userUpdate.getPrenom());
                  user.setEmail(userUpdate.getEmail());
                  user.setPassport(userUpdate.getPassport());
                  user.setPassword(userUpdate.getPassword());
            
                  
                  return userRepository.save(user);
              }).orElseThrow(() -> new NotFoundException());
  }
}
