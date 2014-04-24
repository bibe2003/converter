package javaapplication2;

	/*************************************************************
         * @file: TextPanel.java 
	 * @source: adapted from Horstmann and Cornell, Core Java
	 * @history: Visualization Course, Spring 2003, Chee Yap
	 *************************************************************/

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	
	/*************************************************************
         *	Panel Class (with main method)
	 *************************************************************/
	
	class Panel extends JPanel implements ActionListener {
	  // override the paintComponent method
	  // THE MAIN DEMO OF THIS EXAMPLE:
	 // members:
	  private JButton convertButton;
          JMenu m = new JMenu("Color");
          public static JMenuBar menuBar;
          public static JMenuItem menuItem;
          public static JMenuItem menuItem2;
          public static JMenuItem menuItem3;
          public static JMenu menu;
          public static JTextField convertFromTextField;
          public static JTextField convertToTextField;
          
         
          
          public Panel ()
          {
            // first create font for text fields
            Font f1 = new Font("Helvetica", Font.ITALIC, 14);
            
            add(new JLabel("United States Dollar "));
            convertFromTextField = new JTextField("                ");
            convertFromTextField.setFont(f1); // set font
            convertFromTextField.setAlignmentY(TOP_ALIGNMENT);
            convertFromTextField.setAlignmentX(LEFT_ALIGNMENT);
            add(convertFromTextField);
            
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            // use flex JSON for these
            model.addElement("USD");
            model.addElement("Euro");
            model.addElement("Yen");
            JComboBox comboBox = new JComboBox(model);
            add(comboBox);
            
            add(new JLabel("       Canadian Dollar       "));
              
            convertToTextField = new JTextField("                ");
            convertToTextField.setFont(f1);
            convertToTextField.setAlignmentY(TOP_ALIGNMENT);
            convertToTextField.setAlignmentX(LEFT_ALIGNMENT);
            // convertToTextField.setPrefferedSize;
            convertToTextField.setAlignmentY(BOTTOM_ALIGNMENT);
            convertToTextField.setAlignmentX(BOTTOM_ALIGNMENT);
            convertToTextField.disable();
          //  convertToTextField.isShowing();
            add(convertToTextField);
            
            DefaultComboBoxModel model2 = new DefaultComboBoxModel();
            model2.addElement("CND");
            model2.addElement("Euro");
            model2.addElement("Yen");
            JComboBox comboBox2 = new JComboBox(model2);
            add(comboBox2);
           
            add(new JLabel("Exchange rate is: 1.5 "));
            
             /* CREATE CONVERT BUTTON */  
	    convertButton = new JButton("CONVERT"); 
	    add(convertButton, BorderLayout.CENTER);  // add button to current panel
	    // register the current panel as listener for the buttons
            convertButton.setActionCommand("Convert");
	    convertButton.addActionListener(this); 

            /* CREATE MENU */
            menu = new JMenu("Menu");
            menu.setMnemonic(KeyEvent.VK_A);
            menu.getAccessibleContext().setAccessibleDescription("Menu");
            menuBar.add(menu);
            
            //a group of JMenuItems
            
            /* menu about 
               - dialog box containing: App title, your name, "OK" button*/
            menuItem = new JMenuItem("About",
                                     KeyEvent.VK_A);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_A, ActionEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription("About");
            menuItem.setActionCommand("About");
            menu.add(menuItem); // add "About" to menu
            /* menu Update 
               - Read RSS feed for FromCurrency
               - Build HashMap "ToCurrency/FromCurrency to Exchange Rate */
            menuItem2 = new JMenuItem("Update",
                                     KeyEvent.VK_U);
            menuItem2.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_U, ActionEvent.ALT_MASK));
            menuItem2.getAccessibleContext().setAccessibleDescription("Update");
            menuItem2.setActionCommand("Update");
            menu.add(menuItem2); // add "Update" to menu
            /* menu Exit 
               - Clean Up application
               - Exit Application*/
            menuItem3 = new JMenuItem("Exit",KeyEvent.VK_E);
            menuItem3.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_E, ActionEvent.ALT_MASK));
            menuItem3.getAccessibleContext().setAccessibleDescription("Exit application");
            menuItem3.setActionCommand("Exit"); // command called Exit
            menu.add(menuItem3); // add "Exit" to menu
            
            // action listener for each menu item
            menuItem.addActionListener(this);
            menuItem2.addActionListener(this);
            menuItem3.addActionListener(this);
          }
          
	 /*   public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Font f = new Font("Helvetica", Font.BOLD, 16);
	    Font fi = new Font("Helvetica", Font.BOLD + Font.ITALIC, 16);
	    FontMetrics fm = g.getFontMetrics(f);
	    FontMetrics fim = g.getFontMetrics(fi);
	    int cx = 20; int cy = 200;
	    g.setFont(f);
            
	    g.drawString("Converted:", cx, cy);
            
            
	   /* for italics
            cx += fm.stringWidth("Enter ");
	    g.setFont(fi);
	    g.drawString("currency:", cx, cy);
            
	  } //paintComponent*/


	  //=============================================
	  ///////////// main ////////////////////////////

	  public static void main(String[] args) {
            // create a JFrame instance of our interface
	    JFrame f = new Interface("Currency Converter");
            // show interface
	    f.show();
	  } //main

          /* Function onExit () no argument, void return value
             - Cleans Up application
             - Exits Application safely */
          public void onExit ()
          {
                System.exit(0);
          }

          @Override
          public void actionPerformed(ActionEvent e) {
              if ("Convert".equals(e.getActionCommand())) { // if Convert pressed
                  
              }
              else if ("Update".equals(e.getActionCommand())) // if Update pressed
              {
                  
              }
              else if ("About".equals(e.getActionCommand())) // if About pressed
              {
                  //Interface.aboutDialog();
              }
              else // if user pressed Exit from menu
              {
                  onExit(); // calls onExit()
              }
          }
         
} //class Panel