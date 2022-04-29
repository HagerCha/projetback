package com.MissionNDF.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                        
                         roles, null, null, null));
  }

  @PostMapping("/signup")
  public User registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


    // Create new user's account
    User user = new User(signUpRequest.getEmail(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()), signUpRequest.getNom(), signUpRequest.getPrenom(), signUpRequest.getPassport());

    Set<String> strRoles = signUpRequest.getRole();
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

    user.setRoles(roles);
    return userRepository.save(user);


   // return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
