/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Etudiant;

/**
 *
 * @author adamel
 */
public class TablePanel extends JPanel
{
    private JTable table;
    private EtudiantTableModel tableModel;
            
            
    public TablePanel()
    {
        
        tableModel = new EtudiantTableModel();
        table = new JTable(tableModel);
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        
    }
    
    
    public void setData(List<Etudiant> db)
    {
        tableModel.setData(db);
    }
    
    public void refresh()
    {
        tableModel.fireTableDataChanged();
    }
}