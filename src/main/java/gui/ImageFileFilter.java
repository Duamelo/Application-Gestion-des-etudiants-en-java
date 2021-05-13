/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author adamel
 */
public class ImageFileFilter extends FileFilter
{

   
    public boolean accept(File file) 
    {
        
        if (file.isDirectory() ) 
            return true;
        String name = file.getName();
        
        String extension = Utils.getFileExtension(name);
        
        if (extension == null)
            return false;
        if ( extension.equals("jpg") || extension.equals("png"))
            return true;
        
        
        return false;   
    }

    
    public String getDescription() 
    {
        
        
        return "Image database files( *.jpg) or (*.png)";
    }
    
}
