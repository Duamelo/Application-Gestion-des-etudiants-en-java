/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.FormEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import model.Database;
import model.Etudiant;

/**
 *
 * @author adamel
 */
public class Controller 
{
    Database db = new Database();
    
    public List<Etudiant> getEtudiant()
    {
        return db.getEtudiant();
    }
    
    
    public Controller() 
    {
        
    }
    
    public void addEtudiant(FormEvent e)
    {
        String nom = e.getNom();
        String prenom = e.getPrenom();
        int age = e.getAge();
        String filiere = e.getFiliere();
        String sexe = e.getSexe();
        String pathProfil = e.getPathProfil();
        
        Etudiant etu = new Etudiant(nom, prenom, age, filiere, sexe, pathProfil);
        
        db.addEtudiant(etu);
        
               
              // textPanel.appendText(nom + ": " + prenom +  ": " + age + ": " + sexe + ": " + filiere +  "\n");
    }
    
    public void removeEtudiant(int index)
    {
        db.removeEtudiant(index);
    }
    
    public void saveToFile(File file) throws IOException
    {
        db.saveToFile(file);
    }
    
    public void loadFromFile(File file) throws IOException
    {
        db.loadFromFile(file);
    }
    
}
