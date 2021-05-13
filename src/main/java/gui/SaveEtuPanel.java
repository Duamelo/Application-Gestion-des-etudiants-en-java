/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author adamel
 */
public class SaveEtuPanel extends JPanel
{
    
    private JLabel nomLabel ;
    private JLabel prenomLabel ;
    private JTextField nomField;
    private JTextField prenomField;
    private JButton saveButton;
    private FormListener formListener;
    private JList filiereList;
    private JComboBox sexeCombo;
    private JTextField ageField;
    private JLabel ageLabel;
    private JLabel filiereLabel;
    private JPanel photoPanel;
    private JButton btParcourir;
    
    private JFileChooser fileChooser;
    
    public SaveEtuPanel()
    {
        Dimension dim = getPreferredSize();
        //System.out.println(dim);
        dim.width = 250;
        setPreferredSize(dim);
        
        nomLabel = new JLabel("Nom: ");
        prenomLabel = new JLabel("Prénom(s): ");
        ageLabel = new JLabel("Age: ");
        filiereLabel = new JLabel("Filiere: ");
        
        nomField = new JTextField(10);
        prenomField = new JTextField(10);
        filiereList = new JList();
        sexeCombo = new JComboBox();
        ageField = new JTextField(10);
       
        //parcourirLabel = new JLabel("Parcourir");
        btParcourir = new JButton("Parcourir");
        photoPanel = new JPanel();
        
        
        fileChooser  = new JFileChooser();
        fileChooser.addChoosableFileFilter(new ImageFileFilter());
        
        btParcourir.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent ae) {
             
                if ( fileChooser.showOpenDialog(SaveEtuPanel.this) == JFileChooser.APPROVE_OPTION)
                {
                    System.out.println(fileChooser.getSelectedFile());
                }
            }
            
        });
        // Set up list Box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new TypeFiliere(0, "Maths"));
        ageModel.addElement(new TypeFiliere(1, "Physique"));
        ageModel.addElement(new TypeFiliere(2, "Informatique"));


        filiereList.setModel(ageModel);
        
        filiereList.setPreferredSize(new Dimension(110, 66));
        filiereList.setBorder(BorderFactory.createEtchedBorder());
        filiereList.setSelectedIndex(1);
        

        
        // Set up combo box
        
        DefaultComboBoxModel sexeModel = new DefaultComboBoxModel();
        sexeModel.addElement("Masculin");
        sexeModel.addElement("Feminin");
        sexeCombo.setModel(sexeModel);
        sexeCombo.setSelectedIndex(0);
        //sexeCombo.setEditable(true);
        
        
        saveButton = new JButton("Enregistrer");
        
        // set up Mnemonic
        
        
        saveButton.setMnemonic(KeyEvent.VK_O);
        nomLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nomLabel.setLabelFor(nomField);
        
        prenomLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        prenomLabel.setLabelFor(prenomField);
        
        ageLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        ageLabel.setLabelFor(ageField);
        
        filiereLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        filiereLabel.setLabelFor(filiereList);
        
        
        
        ///////////////////////
        saveButton.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent ae) {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                TypeFiliere filiere =(TypeFiliere)filiereList.getSelectedValue();
                String sexe = (String)sexeCombo.getSelectedItem();
                int age =   Integer.parseInt((String)ageField.getText());
                String path = "image";
                
                
                System.out.println(sexe);
               FormEvent ev = new FormEvent( nom, prenom, age, filiere.toString(), sexe, path, this);
                
                if ( formListener != null)
                {
                    formListener.formEventOccurred(ev);
                }
                
            }
            
        });
        
        
        Border innerBorder = BorderFactory.createTitledBorder("Ajouter un étudiant");
        
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        layoutComponent();
    }
    
    
    public void layoutComponent()
    {
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
      
        
        //////////////////First row ////////////////////////////
       
         gc.gridy = 0;
        
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0;
       
       
        gc.fill = GridBagConstraints.NONE;
        
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(nomLabel, gc);
        
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nomField, gc);
     
           //////////////////Second row ///////////////////////////
     
         gc.gridy++;
           
        gc.weightx = 1;
        gc.weighty = 0.1;
    
           
       
        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(prenomLabel, gc);
        
        
        
        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(prenomField, gc);
        
        
          //////////////////Age row ///////////////////////////
     
         gc.gridy++;
           
        gc.weightx = 1;
        gc.weighty = 0.2;
    
           
        
        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(ageLabel, gc);
        
        
        
        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(ageField, gc);
        
             //////////////////Next row ///////////////////////////
           
           
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.2;
    
            
        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(filiereLabel, gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(filiereList, gc);

        
          //////////////////Next row ///////////////////////////
           
           
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.2;
    
        
        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Sexe: "), gc);
        
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(sexeCombo, gc);
        
           //////////////////Next row ///////////////////////////
           
           
        gc.gridy++;
           
        gc.weightx = 1;
        gc.weighty = 2.0;
    
           
       
        gc.gridx = 1;
        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(saveButton, gc);
        
        
         //////////////////Next row ///////////////////////////
           
           
        gc.gridy++;
           
        gc.weightx = 1;
        gc.weighty = 2.0;
    
           
       
        gc.gridx = 1;
        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(photoPanel, gc);
        photoPanel.setVisible(true);
        photoPanel.setSize(new Dimension(200, 300));
        
        
        
         //////////////////Next row ///////////////////////////
           
           
        gc.gridy++;
           
        gc.weightx = 1;
        gc.weighty = 2.0;
    
           
       
        gc.gridx = 1;
        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(btParcourir, gc);
        
      
    }
    
    public void setFormListener(FormListener listener)
    {
        this.formListener = listener;
    }
        
}


class TypeFiliere
{
    private int id;
    private String text;
    
    public TypeFiliere(int id, String text)
    {
        this.id = id;
        this.text = text;
    }
    
    public String toString()
    {
        return text;
    }
    
    public int getId()
    {
        return id;
    }
}