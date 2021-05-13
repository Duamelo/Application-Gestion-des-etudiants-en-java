/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author adamel
 */
public class Etudiant implements Serializable
{
 
    private static final long serialVersionUID = -8219218627533074108L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String filiere;
    private String sexe;
    private String pathProfil;
    
    public Etudiant(){}

    private static int count = 0;
    
    public Etudiant(/*int id,*/ String nom, String prenom, int age, String filiere, String sexe, String pathProfil) {
        //this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.filiere = filiere;
        this.sexe = sexe;
        this.pathProfil = pathProfil;
        
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPathProfil() {
        return pathProfil;
    }

    public void setPathProfil(String pathProfil) {
        this.pathProfil = pathProfil;
    }
    
    
}
