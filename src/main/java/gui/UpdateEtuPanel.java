/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.lang.model.SourceVersion;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author adamel
 */
public class UpdateEtuPanel extends JPanel
{
     private JLabel nomLabel ;
    private JLabel prenomLabel ;
    private JTextField nomField;
    private JTextField prenomField;
    private JButton updateButton;
    private FormListener formListener;
    private JList filiereList;
    private JComboBox sexeCombo;
    private JTextField ageField;
   

    public UpdateEtuPanel()
    {
      
         Dimension dim = getPreferredSize();
        //System.out.println(dim);
        dim.width = 250;
        setPreferredSize(dim);
        
        nomLabel = new JLabel("Nom: ");
        prenomLabel = new JLabel("Prénom : ");
        
        
        nomField = new JTextField(10);
        prenomField = new JTextField(10);
        filiereList = new JList();
        sexeCombo = new JComboBox();
        ageField = new JTextField(10);
       
        
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
        
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("Masculin");
        empModel.addElement("Feminin");
        sexeCombo.setModel(empModel);
        sexeCombo.setSelectedIndex(0);
        //sexeCombo.setEditable(true);
        
        updateButton = new JButton("Modifier");
        
        updateButton.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent ae) {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                TFiliere filiere =(TFiliere)filiereList.getSelectedValue();
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
        
        
        Border innerBorder = BorderFactory.createTitledBorder("Mettre à jour un étudiant");
        
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
        add(new JLabel("Age: "), gc);
        
        
        
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
        add(new JLabel("Filiere: "), gc);
        
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
        add(updateButton, gc);
      
        
      
    }
    
    public void setFormListener(FormListener listener)
    {
        this.formListener = listener;
    }
        
}


class TFiliere
{
    private int id;
    private String text;
    
    public TFiliere(int id, String text)
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
