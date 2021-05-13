/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author adamel
 */
public class Toolbar extends JPanel implements ActionListener
{
    
    private JButton helloButton;
    private JButton goodbyeButton;
   
    private StringListener textListener;
    
    public Toolbar()
    {
        setBorder(BorderFactory.createEtchedBorder());
        helloButton = new JButton("Hello");
        goodbyeButton = new JButton("Goodbye");
        
        helloButton.addActionListener(this);
        goodbyeButton.addActionListener(this);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        add(helloButton);
        add(goodbyeButton);
    }
    
   public void setStringListener(StringListener listener)
   {
       this.textListener = listener;
   }
    
    public void actionPerformed(ActionEvent e)
    {
       // System.out.println("A button has been clicked");
        JButton clicked = (JButton)e.getSource();
        
        if (clicked == helloButton)
        {
            if( textListener != null)
            {
                textListener.textEmitted("Hello\n");
            }
           
        }
        if( clicked == goodbyeButton)
        {
            if ( textListener != null)
            {
                textListener.textEmitted("GoodBye\n");
            }
        }
    }
    

}