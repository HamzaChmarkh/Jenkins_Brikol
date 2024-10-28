#!/bin/bash

# Define the base package name
PACKAGE_NAME="com.example.serviceplatform"

# Create directory structure
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/client
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/admin
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/utilisateur
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/security
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/freelancer
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/visitor
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/service
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/categorie
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/repository
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/controller
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/config

# Create Utilisateur.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/utilisateur/Utilisateur.java
package ${PACKAGE_NAME}.utilisateur;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String username;
    private String email;
    protected String motDePasse;
    private boolean emailVerifier;

    public void sInscrire() {}
    public void seConnecter() {}
    public void modifierProfil(String nom, String username, String email) {}
    public abstract boolean verifierEmail();

    // Getters and setters
}
EOL

# Create Client.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/client/Client.java
package ${PACKAGE_NAME}.client;

import javax.persistence.*;
import java.util.List;
import ${PACKAGE_NAME}.service.Service;

@Entity
public class Client extends Utilisateur {
    public List<Service> chercherService(String critere) {
        return null;
    }

    public void contacterFreelancer(Freelancer freelancer) {}
    public void laisserAvis(Service service, String avis) {}

    // Getters and setters
}
EOL

# Create Admin.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/admin/Admin.java
package ${PACKAGE_NAME}.admin;

import javax.persistence.*;

@Entity
public class Admin extends Utilisateur {
    public void gérerUtilisateurs() {}
    public void gérerService() {}
    public void voirStatistiques() {}
    public void suspendreUtilisateur(Utilisateur user) {}
    public void validerService(Service service) {}
    public void reinitialiserMotDePasse(Utilisateur user) {}

    // Getters and setters
}
EOL

# Create Freelancer.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/freelancer/Freelancer.java
package ${PACKAGE_NAME}.freelancer;

import javax.persistence.*;
import java.util.List;

@Entity
public class Freelancer extends Utilisateur {
    private String specialisation;

    @OneToMany(mappedBy = "freelancer")
    private List<Service> servicesProposes;

    public void ajouterService(Service service) {}
    public void modifierService(Service service) {}
    public void supprimerService(Service service) {}

    // Getters and setters
}
EOL

# Create Service.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/service/Service.java
package ${PACKAGE_NAME}.service;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Double prix;

    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    private Freelancer freelancer;

    public void afficherDetails() {}
    public void modifierPrix(Double nouveauPrix) {}

    // Getters and setters
}
EOL

# Create Categorie.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/categorie/Categorie.java
package ${PACKAGE_NAME}.categorie;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @OneToMany(mappedBy = "categorie")
    private List<Service> services;

    // Getters and setters
}
EOL

# Create UtilisateurRepository.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/repository/UtilisateurRepository.java
package ${PACKAGE_NAME}.repository;

import ${PACKAGE_NAME}.utilisateur.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {}
EOL

# Create ClientRepository.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/repository/ClientRepository.java
package ${PACKAGE_NAME}.repository;

import ${PACKAGE_NAME}.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}
EOL

# Create AdminRepository.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/repository/AdminRepository.java
package ${PACKAGE_NAME}.repository;

import ${PACKAGE_NAME}.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {}
EOL

# Create FreelancerRepository.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/repository/FreelancerRepository.java
package ${PACKAGE_NAME}.repository;

import ${PACKAGE_NAME}.freelancer.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {}
EOL

# Create ServiceRepository.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/repository/ServiceRepository.java
package ${PACKAGE_NAME}.repository;

import ${PACKAGE_NAME}.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {}
EOL

# Create CategorieRepository.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/repository/CategorieRepository.java
package ${PACKAGE_NAME}.repository;

import ${PACKAGE_NAME}.categorie.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {}
EOL

# Create AdminService.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/service/AdminService.java
package ${PACKAGE_NAME}.service;

import ${PACKAGE_NAME}.admin.Admin;
import ${PACKAGE_NAME}.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public void gérerUtilisateurs() {}
    public void gérerService() {}
    public void voirStatistiques() {}
    public void suspendreUtilisateur(Long userId) {}
    public void validerService(Long serviceId) {}
    public void reinitialiserMotDePasse(Long userId) {}
}
EOL

# Create ClientService.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/service/ClientService.java
package ${PACKAGE_NAME}.service;

import ${PACKAGE_NAME}.client.Client;
import ${PACKAGE_NAME}.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    // Implement methods related to client functionality
}
EOL

# Create FreelancerService.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/service/FreelancerService.java
package ${PACKAGE_NAME}.service;

import ${PACKAGE_NAME}.freelancer.Freelancer;
import ${PACKAGE_NAME}.repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreelancerService {
    @Autowired
    private FreelancerRepository freelancerRepository;

    // Implement methods related to freelancer functionality
}
EOL

# Create ServiceService.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/service/ServiceService.java
package ${PACKAGE_NAME}.service;

import ${PACKAGE_NAME}.service.Service;
import ${PACKAGE_NAME}.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    // Implement methods related to service functionality
}
EOL

# Create CategorieService.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/service/CategorieService.java
package ${PACKAGE_NAME}.service;

import ${PACKAGE_NAME}.categorie.Categorie;
import ${PACKAGE_NAME}.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    // Implement methods related to category functionality
}
EOL

# Create SecurityConfig.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/config/SecurityConfig.java
package ${PACKAGE_NAME}.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
            .and().httpBasic();
    }
}
EOL

# Create AdminController.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/controller/AdminController.java
package ${PACKAGE_NAME}.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    // Define endpoints for admin functionalities
}
EOL

# Create ClientController.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/controller/ClientController.java
package ${PACKAGE_NAME}.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    // Define endpoints for client functionalities
}
EOL

# Create FreelancerController.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/controller/FreelancerController.java
package ${PACKAGE_NAME}.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreelancerController {
    // Define endpoints for freelancer functionalities
}
EOL

# Create ServiceController.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/controller/ServiceController.java
package ${PACKAGE_NAME}.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
    // Define endpoints for service functionalities
}
EOL

# Create CategorieController.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/controller/CategorieController.java
package ${PACKAGE_NAME}.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieController {
    // Define endpoints for category functionalities
}
EOL

echo "All files and directories created successfully!"
