/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adamel
 */
public class Database 
{
    private List<Etudiant> etudiant;
    
    public Database()
    {
        etudiant = new LinkedList<Etudiant>();
        
    }
    
    
    public void addEtudiant( Etudiant etu)                
    {
        etudiant.add(etu);
    }
 
    
    public void removeEtudiant(int index)
    {
        etudiant.remove(index);
    }
    
    /**
     *
     * @return
     */
    public List<Etudiant> getEtudiant()
    {
        return Collections.unmodifiableList(etudiant);
    }
  
    
    
    public void saveToFile(File file) throws FileNotFoundException, IOException
    {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        Etudiant[] etus = etudiant.toArray(new Etudiant[etudiant.size()]);
        
        oos.writeObject(etus);
        
        oos.close();
    }
    
    public void loadFromFile(File file) throws FileNotFoundException, IOException
    {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            Etudiant[] etus = (Etudiant[])ois.readObject();
            
            etudiant.clear();
            
            etudiant.addAll(Arrays.asList(etus));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ois.close();
        
        
    }
}
