/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author adamel
 */
public class CheckEtuPanel extends JPanel
{
    private JLabel jlnom;
    private JLabel jlprenom;
    private JLabel jlage;
    private JLabel jlfiliere;
    private JLabel jlsexe;
    
    private JLabel jtnom;
    private JLabel jtprenom;
    private JLabel jtage;
    private JLabel jtfiliere;
    private JLabel jtsexe;

    public CheckEtuPanel() 
    {
        jlnom = new JLabel("Nom: ");
        jlprenom = new JLabel("Prénom(s): ");
        jlage = new JLabel("Age: ");
        jlsexe = new JLabel("Sexe: ");
        jlfiliere = new JLabel("Filière: ");
        
        
        
    }
    
    
    
}
