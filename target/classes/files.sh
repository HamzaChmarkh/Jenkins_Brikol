#!/bin/bash

# Define the base package name
PACKAGE_NAME="m2i.ma.Brikol"

# Create directory structure
mkdir -p src/main/java/${PACKAGE_NAME//./\/}/model

# Create Utilisateur.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/model/Utilisateur.java
package ${PACKAGE_NAME}.model;

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

    public void sInscrire() {
        // Registration logic
    }

    public void seConnecter() {
        // Login logic
    }

    public void modifierProfil(String nom, String username, String email) {
        this.nom = nom;
        this.username = username;
        this.email = email;
    }

    public abstract boolean verifierEmail();
}
EOL

# Create Client.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/model/Client.java
package ${PACKAGE_NAME}.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client extends Utilisateur {
    public List<Service> chercherService(String critere) {
        // Logic to search services
        return null;
    }

    public void contacterFreelancer(Freelancer freelancer) {
        // Logic to contact freelancer
    }

    public void laisserAvis(Service service, String avis) {
        // Logic to leave a review
    }
}
EOL

# Create Freelancer.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/model/Freelancer.java
package ${PACKAGE_NAME}.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Freelancer extends Utilisateur {
    private String specialisation;

    @OneToMany(mappedBy = "freelancer")
    private List<Service> servicesProposes;

    public void ajouterService(Service service) {
        // Logic to add a service
    }

    public void modifierService(Service service) {
        // Logic to modify a service
    }

    public void supprimerService(Service service) {
        // Logic to remove a service
    }
}
EOL

# Create Admin.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/model/Admin.java
package ${PACKAGE_NAME}.model;

import javax.persistence.*;

@Entity
public class Admin extends Utilisateur {
    public void gérerUtilisateurs() {
        // Logic to manage users
    }

    public void gérerService() {
        // Logic to manage services
    }

    public void voirStatistiques() {
        // Logic to view statistics
    }

    public void suspendreUtilisateur(Utilisateur user) {
        // Logic to suspend a user
    }

    public void validerService(Service service) {
        // Logic to validate a service
    }

    public void reinitialiserMotDePasse(Utilisateur user) {
        // Logic to reset a user's password
    }
}
EOL

# Create Service.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/model/Service.java
package ${PACKAGE_NAME}.model;

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

    public void afficherDetails() {
        // Logic to display service details
    }

    public void modifierPrix(Double nouveauPrix) {
        this.prix = nouveauPrix;
    }
}
EOL

# Create Categorie.java
cat <<EOL > src/main/java/${PACKAGE_NAME//./\/}/model/Categorie.java
package ${PACKAGE_NAME}.model;

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
}
EOL

echo "Java classes created successfully!"
