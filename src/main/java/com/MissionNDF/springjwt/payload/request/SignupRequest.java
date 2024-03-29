package com.MissionNDF.springjwt.payload.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.*;

import net.bytebuddy.asm.Advice.Local;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String nom;
  
  @NotBlank
  @Size(min = 3, max = 20)
  private String prenom;
  

@NotBlank
  @Size(min = 3, max = 20)
  private String passport;



@NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @Size(min = 6, max = 40)
  private String password;

  
  public String getNom() {
	return nom;
}


public void setNom(String nom) {
	this.nom = nom;
}


public String getPrenom() {
	return prenom;
}


public void setPrenom(String prenom) {
	this.prenom = prenom;
}

  public String getPassport() {
	return passport;
}


public void setPassport(String passport) {
	this.passport = passport;
}
 

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }


  
}
