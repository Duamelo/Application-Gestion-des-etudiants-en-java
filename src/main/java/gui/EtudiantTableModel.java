/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Etudiant;

/**
 *
 * @author adamel
 */
public class EtudiantTableModel extends AbstractTableModel
{

    private List<Etudiant> db;
    private String[] colNames = {"ID", "Nom", "Prénom(s)", "Age", "Sexe", "Filiere", "Profil"};
    
    
    public EtudiantTableModel()
    {
      
    }

    @Override
    public String getColumnName(int column)
    {
        return colNames[column];
    }
    
    
    
    
    public void setData(List<Etudiant> db)
    {
        this.db = db;
    }
    
    public int getRowCount() 
    {
     
        return db.size();
    }

 
    public int getColumnCount() 
    {
        
        return 7;
    }

 
    public Object getValueAt(int row, int col) 
    {
       Etudiant etu = db.get(row);
       
       switch(col)
       {
           case 0:
               return etu.getId();
           case 1: 
               return etu.getNom();
           case 2:
               return etu.getPrenom();
           case 3:
               return etu.getAge();
           case 4:
               return etu.getSexe();
           case 5: 
               return etu.getFiliere();
           case 6:
               return etu.getPathProfil();
       }
       
       return null;
    }
    
}
