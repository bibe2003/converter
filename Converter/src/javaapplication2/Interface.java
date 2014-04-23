/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;
import java.awt.Container;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import static javaapplication2.Panel.menuBar;


/**
 *
 * @author Biljana
 */
public class Interface extends JFrame {
        
	  public Interface(String s) {
		setTitle(s);
		setSize(180 ,200); // default size is 0,0
		setLocation(100,200); // default is 0,0 (top left corner)
                
                // Window Listeners
                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                       System.exit(0);
                    } //windowClosing
                } );
                
                /* Initialize MENU */
                menuBar = new JMenuBar();
                setJMenuBar(Panel.menuBar);
              
                /* Add Panels */
		Container contentPane = getContentPane();
		contentPane.add(new Panel());
                
	  }
          
    
          
}


/*   /**
     * @param args the command line arguments
     
      public static void main(String[] args) {
        // TODO code application logic here
	    JFrame f = new Interface();
	    f.show();
	}*/

