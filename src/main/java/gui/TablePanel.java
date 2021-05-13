/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
    private JPopupMenu popup;
    private EtudiantTableListener etudiantTableListener;        
    
    public TablePanel()
    {
        
        tableModel = new EtudiantTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();
        
        JMenuItem removeItem = new  JMenuItem("Supprimer la ligne");
        popup.add(removeItem);
        
        table.addMouseListener(new MouseAdapter(){
          
            
            public void mousePressed(MouseEvent e) {
               // super.mousePressed(e);
            
               int row = table.rowAtPoint(e.getPoint());
               
               
               table.getSelectionModel().setSelectionInterval(row, row);
               
               if ( e.getButton() == MouseEvent.BUTTON3)
               {
                   popup.show(table, e.getX(), e.getY());
               }
                
            }
            
        });
        
        removeItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int row = table.getSelectedRow();
                
                if ( etudiantTableListener != null)
                {
                    etudiantTableListener.rowDeleted(row);
                    tableModel.fireTableRowsDeleted(row, row);
                }
                
                System.out.println(row);
                
                
                
            }
            
        });
        
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
    
    public void setEtudiantTableListener(EtudiantTableListener listener)
    {
        this.etudiantTableListener = listener;
    }
}
