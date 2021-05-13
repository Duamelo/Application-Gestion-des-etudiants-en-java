/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 *
 * @author adamel
 */
public class MainFrame extends JFrame
{
 
    
    private TextPanel textPanel;
    private Toolbar toolbar;
    private SaveEtuPanel saveEtuPanel;
    private UpdateEtuPanel updateEtuPanel;
    private Controller controller;
    private TablePanel tablePanel;
    private JFileChooser fileChooser;
    
    
    
    public MainFrame()
    {
        super("Application de Gestion des Etudiants");
        
        setLayout(new BorderLayout());
        
       
        textPanel = new TextPanel();
        toolbar = new Toolbar();
        saveEtuPanel = new SaveEtuPanel();
        updateEtuPanel = new UpdateEtuPanel();
        
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new EtudiantFileFilter());
        tablePanel = new TablePanel();
        
        controller = new Controller();
        tablePanel.setData(controller.getEtudiant());
        
        setJMenuBar(createMenuBar());
        
        toolbar.setStringListener(new StringListener(){
            public void textEmitted(String text)
            {
                //System.out.println(text);
                textPanel.appendText(text);  
            }
        });
        
        saveEtuPanel.setFormListener(new FormListener() {
           public void formEventOccurred(FormEvent e)
           {
              controller.addEtudiant(e);
              tablePanel.refresh();
               
              
           }
        });
     
         updateEtuPanel.setFormListener(new FormListener() {
           public void formEventOccurred(FormEvent e)
           {
               String nom = e.getNom();
               String prenom = e.getPrenom();
               int age = e.getAge();
               String sexe = e.getSexe();
               String filiere = e.getFiliere();
               
               
               textPanel.appendText(nom + ": " + prenom +  ": " + age + ": " + sexe + ": " + filiere +  "\n");
               
              
           }
        });
     
      intialisation();
    }
    
    private void intialisation()
    {
          
        add(saveEtuPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        
      
        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu actionMenu = new JMenu("Actions");
        JMenu helpMenu = new JMenu("Help");
        //JMenu quitMenu = new JMenu("Quit");
        
        
        JCheckBoxMenuItem saveItem = new JCheckBoxMenuItem("Enregistrer");
        JCheckBoxMenuItem updateItem = new JCheckBoxMenuItem("Modifier");
        JCheckBoxMenuItem deleteItem = new JCheckBoxMenuItem("Supprimer");
        JCheckBoxMenuItem checkItem = new JCheckBoxMenuItem("Rechercher");
        JCheckBoxMenuItem statistiqueItem = new JCheckBoxMenuItem("Statisque");
        JCheckBoxMenuItem afficheItem = new JCheckBoxMenuItem("Listes Etudiants");
        JCheckBoxMenuItem importItem = new JCheckBoxMenuItem("Importer... ");
        JCheckBoxMenuItem exportItem = new JCheckBoxMenuItem("Exporter...");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        
        
        saveItem.setSelected(true);
        
        
        actionMenu.add(saveItem);
        actionMenu.add(updateItem);
        actionMenu.add(deleteItem);
        actionMenu.add(checkItem);
        actionMenu.add(statistiqueItem);
        actionMenu.add(afficheItem);
        actionMenu.add(importItem);
        actionMenu.add(exportItem);
        actionMenu.add(exitMenuItem);
        
        
        
        menuBar.add(actionMenu);
        menuBar.add(helpMenu);
        
        
        
        saveItem.addActionListener(new ActionListener(){
         
            public void actionPerformed(ActionEvent ae) 
            {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ae.getSource();
                
                saveEtuPanel.setVisible(menuItem.isSelected()); 
            }
            
        });
        
        
        updateItem.addActionListener(new ActionListener(){
         
            public void actionPerformed(ActionEvent ae) 
            {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ae.getSource();
                
                 String text =   JOptionPane.showInputDialog(MainFrame.this,
                "Matricule de l'étudiant.", 
                "Vérification", JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE);
                
                
                add(updateEtuPanel, BorderLayout.WEST);
                updateEtuPanel.setVisible(menuItem.isSelected()); 
            }
            
        });
        
         deleteItem.addActionListener(new ActionListener(){
         
            public void actionPerformed(ActionEvent ae) 
            {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ae.getSource();
                
                 String text =   JOptionPane.showInputDialog(MainFrame.this,
                "Matricule de l'étudiant.", 
                "Vérification", JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE);
                
                
                add(updateEtuPanel, BorderLayout.WEST);
                updateEtuPanel.setVisible(menuItem.isSelected()); 
            }
            
        });
        
          checkItem.addActionListener(new ActionListener(){
         
            public void actionPerformed(ActionEvent ae) 
            {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ae.getSource();
                //add(updateEtuPanel, BorderLayout.WEST);
               // updateEtuPanel.setVisible(menuItem.isSelected()); 
               
                 String text =   JOptionPane.showInputDialog(MainFrame.this,
                "Entrez le matricule.", 
                "Vérification", JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE);
            }
            
        });

        
        actionMenu.setMnemonic(KeyEvent.VK_A);
        exitMenuItem.setMnemonic(KeyEvent.VK_Q);
        
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        
        
        
        importItem.addActionListener(new ActionListener(){
          
            public void actionPerformed(ActionEvent ae) {
               if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
               {
                   try {
                       controller.loadFromFile(fileChooser.getSelectedFile());
                       tablePanel.refresh();
                   } catch (IOException ex) {
                      JOptionPane.showMessageDialog(MainFrame.this, 
                    "Chargement impossible depuis le fichier", "Error", 
                    JOptionPane.ERROR_MESSAGE);
                   }
                   
               }
            }
            
        });
        
        exportItem.addActionListener(new ActionListener(){
          
            public void actionPerformed(ActionEvent ae) {
               if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
               {
                   try {
                       controller.saveToFile(fileChooser.getSelectedFile());;
                   } catch (IOException ex) {
                      JOptionPane.showMessageDialog(MainFrame.this, 
                    "Impossible d'enregistrer les données", "Error", 
                    JOptionPane.ERROR_MESSAGE);
                   }
                   
               }
            }
            
        });
        
        
        exitMenuItem.addActionListener(new ActionListener(){
          
            
            public void actionPerformed(ActionEvent arg0) {
                
                
               int action =  JOptionPane.showConfirmDialog(MainFrame.this,
                "Voulez-vous vraiment quitter l'application ?", 
                "Confirmer la fermeture", JOptionPane.OK_OPTION);
                
                if ( action == JOptionPane.OK_OPTION)
                    System.exit(0);
                
                    
            }
            
        });
        
        return menuBar;
    }
    
    
}
