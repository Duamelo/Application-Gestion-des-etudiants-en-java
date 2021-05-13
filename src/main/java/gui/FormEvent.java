/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.EventObject;

/**
 *
 * @author adamel
 */
public class FormEvent extends EventObject
{
    
    private String nom;
    private String prenom;
    private int age;
    private String filiere;
    private String sexe;
    private String pathProfil;
    
    public FormEvent(Object source) 
    {
        super(source);
    }

    public FormEvent(String nom, String prenom, int age, String filiere, String sexe, String pathProfil, Object source) {
        super(source);
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.filiere = filiere;
        this.sexe = sexe;
        this.pathProfil = pathProfil;
    }

    
    

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge()
    {
        return age;
    }
    
    
    public String getSexe()
    {
        return sexe;
    }
    
    public String getFiliere()
    {
        return filiere;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public void setSexe(String sexe)
    {
        this.sexe = sexe;
    }
    
    public void setFiliere(String filiere)
    {
        this.filiere = filiere;
    }

    public String getPathProfil() {
        return pathProfil;
    }

    public void setPathProfil(String pathProfil) {
        this.pathProfil = pathProfil;
    }
}
