/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
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
    private Component currentInjectedComponent = null;
    private PrefsDialog prefsDialog;
    
    
    public MainFrame()
    {
        super("Application de Gestion des Etudiants");
        
        setLayout(new BorderLayout());
        
       
        textPanel = new TextPanel();
        toolbar = new Toolbar();
        saveEtuPanel = new SaveEtuPanel();
        updateEtuPanel = new UpdateEtuPanel();       
        fileChooser = new JFileChooser();
        tablePanel = new TablePanel();
        controller = new Controller();
        prefsDialog = new PrefsDialog(this);
        
       
        
        fileChooser.addChoosableFileFilter(new EtudiantFileFilter());
        
        tablePanel.setData(controller.getEtudiant());
        
        tablePanel.setEtudiantTableListener(new EtudiantTableListener(){
           public void rowDeleted(int row)
           {
               System.out.println(row);
               controller.removeEtudiant(row);
           }
        });
        
        setJMenuBar(createMenuBar());
        
        toolbar.setStringListener(new StringListener(){
            public void textEmitted(String text)
            {
                //System.out.println(text);
                textPanel.appendText(text);  
            }
        });
        
        intialisation();
    }
    
    private void intialisation()
    {
          
       
        add(toolbar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        
      
        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu actionMenu = new JMenu("Actions");
        JMenu helpMenu = new JMenu("Help");
        JMenu window = new JMenu("Window");
        //JMenu quitMenu = new JMenu("Quit");
        
        
        JMenuItem saveItem = new JMenuItem("Enregistrer");
        JMenuItem updateItem = new JMenuItem("Modifier");
        JMenuItem deleteItem = new JMenuItem("Supprimer");
        JMenuItem checkItem = new JMenuItem("Rechercher");
        JMenuItem statistiqueItem = new JMenuItem("Statisque");
        JMenuItem afficheItem = new JMenuItem("Listes Etudiants");
        JMenuItem importItem = new JMenuItem("Importer... ");
        JMenuItem exportItem = new JMenuItem("Exporter...");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem prefsItem = new JMenuItem("Preferences..");
        
        
        //saveItem.setSelected(true);
      
        
        
        actionMenu.add(saveItem);
        actionMenu.add(updateItem);
        actionMenu.add(deleteItem);
        actionMenu.add(checkItem);
        actionMenu.add(statistiqueItem);
        actionMenu.add(afficheItem);
        actionMenu.add(importItem);
        actionMenu.add(exportItem);
        actionMenu.add(exitMenuItem);
        
        window.add(prefsItem);
        
        
        menuBar.add(actionMenu);
        menuBar.add(helpMenu);
        menuBar.add(window);
          
     
        
        
        
        // change panel
        saveItem.addActionListener(new ActionListener(){
         
            public void actionPerformed(ActionEvent ae) 
            {
                JMenuItem menuItem = (JMenuItem)ae.getSource();
                
               // saveEtuPanel.setVisible(true); 
                
               
              //  MainFrame.this.rootPane.revalidate();
              //  MainFrame.this.rootPane.repaint();
              if ( currentInjectedComponent != null)
              {
                remove(currentInjectedComponent);  
              }
              
                currentInjectedComponent = saveEtuPanel;
                add(saveEtuPanel, BorderLayout.WEST);
               
                saveEtuPanel.setFormListener(new FormListener() {
                    public void formEventOccurred(FormEvent e)
                    {
                       controller.addEtudiant(e);
                       tablePanel.refresh();
                    }
                });
                
                MainFrame.this.revalidate();
                currentInjectedComponent.repaint();
            }
            
            
            
        });     
        
        
        updateItem.addActionListener(new ActionListener(){
         
            public void actionPerformed(ActionEvent ae) 
            {
                JMenuItem menuItem = (JMenuItem)ae.getSource();
                
                 String text =   JOptionPane.showInputDialog(MainFrame.this,
                "Matricule de l'étudiant.", 
                "Vérification", JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE);
                
                //updateEtuPanel.setVisible(true); 
                
              //  MainFrame.this.rootPane.revalidate();
                //MainFrame.this.rootPane.repaint();
                
                
                if ( currentInjectedComponent != null)
                  {
                    remove(currentInjectedComponent);  
                  }

                currentInjectedComponent = updateEtuPanel;
                add(updateEtuPanel, BorderLayout.WEST);
              
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
                
                 MainFrame.this.revalidate();
                currentInjectedComponent.repaint();
            }
            
        });
        
        
        deleteItem.addActionListener(new ActionListener(){
         
            public void actionPerformed(ActionEvent ae) 
            {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ae.getSource();
                
                 String text =   JOptionPane.showInputDialog(MainFrame.this,
                "Matricule de l'étudiant.", 
                "Vérification", JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE);
                
                
                 
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
        
        prefsItem.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent ae) {
               prefsDialog.setVisible(true);
               
            }
            
        });
        //end
        
        
        
        
        // set Mnemonic to Menu and JMenuItem
        actionMenu.setMnemonic(KeyEvent.VK_A);
        exitMenuItem.setMnemonic(KeyEvent.VK_Q);
        //end
        
        
        // set Accelerators to JCheckBoxItem
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        updateItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        checkItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        statistiqueItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        afficheItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        importItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        //end of accelerator
        
        
        
        // set action to import file
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
        //set action to export file
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
        
        // set action to quit the application
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
