package javaapplication2;

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
          public static JMenuItem about;
          public static JMenuItem update;
          public static JMenuItem exit;
          public static JMenu menu;
          public static JTextField convertFromTextField;
          public static JTextField convertToTextField;
          public String info = "\n          Currency Converter™ "
                  + "\n\nDeveloped by Biljana Miloshevska            "
                  + "\n\n      Arkansas State University\n\n";
          
          public Panel ()
          {
            // first create font for text fields
            Font f1 = new Font("Helvetica", Font.ITALIC, 14);
            /************************************************
            ** Text Field for entering base currency value **
            ************************************************/
            add(new JLabel("United States Dollar "));
            convertFromTextField = new JTextField("                ");
            convertFromTextField.setFont(f1); // set font
            add(convertFromTextField);
            
            /****************************************************
            **        Pull down menu for base currency         **
            ****************************************************/
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            // use flex JSON for these
            model.addElement("USD");
            model.addElement("Euro");
            model.addElement("Yen");
            JComboBox comboBox = new JComboBox(model);
            add(comboBox);
          
            /****************************************************
            ** Text field for entering the base currency value **
            ****************************************************/
            add(new JLabel("Canadian Dollar"));
            convertToTextField = new JTextField("                ");
            convertToTextField.setFont(f1);
            convertToTextField.disable();
            add(convertToTextField);
            
            /****************************************************
            ** Pull down menu for currency to which we convert **
            ****************************************************/
            DefaultComboBoxModel model2 = new DefaultComboBoxModel();
            model2.addElement("CND"); 
            model2.addElement("Euro");
            model2.addElement("Yen");
            JComboBox comboBox2 = new JComboBox(model2);
            add(comboBox2);
            
            add(new JLabel("Exchange rate is: 1.5 ")); // print out exchange rate
            
            /****************************************************
            **                 Convert Button                  **
            ****************************************************/ 
	    convertButton = new JButton("CONVERT"); 
	    add(convertButton, BorderLayout.CENTER);  // add button to current panel
            convertButton.setActionCommand("Convert"); // name of the event action
	    convertButton.addActionListener(this); // listen for an event (click)
	    // register the current panel as listener for the buttons
            convertButton.setActionCommand("Convert");
	    convertButton.addActionListener(this); 

            /****************************************************
            **                 Create Menu                     **
            ****************************************************/ 
            menu = new JMenu("Menu");
            menu.setMnemonic(KeyEvent.VK_A);
            menu.getAccessibleContext().setAccessibleDescription("Menu");
            menuBar.add(menu); // add menu to panel
            /**************************************************
            **         Create a group of JMenu items         **
            **************************************************/ 
            /* menu about                                                **
            ** - dialog box containing: App title, your name, "OK" button*/
            about = new JMenuItem("About",KeyEvent.VK_A);
            about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
            about.getAccessibleContext().setAccessibleDescription("About");
            about.setActionCommand("About");
            menu.add(about); // add "About" to menu
            /* menu Update                                               **
            ** - Read RSS feed for FromCurrency                          **
            ** - Build HashMap "ToCurrency/FromCurrency to Exchange Rate */
            update = new JMenuItem("Update",KeyEvent.VK_U);
            update.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
            update.getAccessibleContext().setAccessibleDescription("Update");
            update.setActionCommand("Update");
            menu.add(update); // add "Update" to menu
            /* menu Exit                                                  **
            ** - Clean Up application
            ** - Exit Application                                         */
            exit = new JMenuItem("Exit",KeyEvent.VK_E);
            exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
            exit.getAccessibleContext().setAccessibleDescription("Exit application");
            exit.setActionCommand("Exit"); // command called Exit
            menu.add(exit); // add "Exit" to menu
            
            /**************************************************
            **      Action listener for each menu item       **
            **************************************************/ 
            about.addActionListener(this);
            update.addActionListener(this);
            exit.addActionListener(this);
          }
          
	  //=============================================
	  //////////////////// main /////////////////////

	  public static void main(String[] args) {
            // create a JFrame instance of our interface
	    JFrame f = new Interface("Currency Converter");
	    f.show();  // show interface
            // show interface
	    f.show();
	  } //main
          
          /******************************************************
          ** Function onExit () no argument, void return value **
          ** - Cleans Up application                           **
          ** - Exits Application safely                        **
          *******************************************************/ 


          /* Function onExit () no argument, void return value
             - Cleans Up application
             - Exits Application safely */
          public void onExit ()
          {
              // clean up application here
                System.exit(0); // exit
          }

          /******************************************************
          ** Get action performed from menu items and call     **
          ** appropriate functions.                            **
          *******************************************************/ 
          @Override
          public void actionPerformed(ActionEvent e) {
              // if Convert pressed
              if ("Convert".equals(e.getActionCommand())) { 
                  
              }
              // if Update pressed
              else if ("Update".equals(e.getActionCommand())) 
              {
                  
              }
              // if About pressed
              else if ("About".equals(e.getActionCommand())) 
              {
                  // show about dialog 
                  JOptionPane.showMessageDialog(null, info, "About", 1);
              }
              // if Exit pressed
              else 
              {
                  onExit(); // calls onExit()
              }
          }
         
} //class Panel
