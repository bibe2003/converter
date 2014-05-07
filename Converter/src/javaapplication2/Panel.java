package javaapplication2;

	import java.awt.*;
	import java.awt.event.*;
import java.text.NumberFormat;
	import javax.swing.*;
	
	/*************************************************************
         *	Panel Class (with main method)                       *
	 *************************************************************/
	class Panel extends JPanel implements ActionListener {
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
          public static DefaultComboBoxModel model; // "From" pull down menu
          public static DefaultComboBoxModel model2; // "To" pull down menu
          public static JComboBox To;
          public static JComboBox From;
          public static ExchangeRates fromRate = new ExchangeRates ();
          public static String from = "USD";
          public static String to = "CAD";
          public static double amount = 1;
          public static JLabel myLabel;
          // string for about menu 
          public String info = "\n          Currency Converter™ "
                  + "\n\nDeveloped by Biljana Miloshevska            "
                  + "\n\n      Arkansas State University\n\n";
          
          public Panel ()
          {
            // first create font for text fields
            Font f1 = new Font("Helvetica", Font.ITALIC, 14);
            /************************************************
            *  Text Field for entering base currency value  *
            ************************************************/
            add(new JLabel("Amount         Currency"));
            convertFromTextField = new JTextField("");
            convertFromTextField.setFont(f1); // set font
            convertFromTextField.setPreferredSize(new Dimension(72,25));
            add(convertFromTextField);
            
            
            /****************************************************
            *         Pull down menu for base currency          *
            ****************************************************/
            model = new DefaultComboBoxModel();
            // use flex JSON for these
            // give default values for these or check if they r not null 
            // make sure they r initialized properly
            
            JComboBox comboBox = new JComboBox(model);
            comboBox.setActionCommand("From");
            comboBox.addActionListener(this);
            add(comboBox);
          
            /****************************************************
            *  Text field for entering the base currency value  *
            ****************************************************/
            add(new JLabel("Amount         Currency"));
            convertToTextField = new JTextField("");
            convertToTextField.setFont(f1);
            convertToTextField.setForeground(Color.black);
            convertToTextField.setBackground(Color.white);
            convertToTextField.setEditable(false);
            convertToTextField.setPreferredSize(new Dimension(72,25));
            add(convertToTextField);
            
            /****************************************************
            *  Pull down menu for currency to which we convert  *
            ****************************************************/
            model2 = new DefaultComboBoxModel();
            JComboBox comboBox2 = new JComboBox(model2);
            
            comboBox2.setActionCommand("To");
            comboBox2.addActionListener(this);
            add(comboBox2);
            
            /************************************************
            *  Read JSON file to populate pull down menus   *
            ************************************************/
            CCCodes myFile = new CCCodes();
            myFile.readFile("config.json");
            
            // Populate menus
            for (int i = 0; i < 90; i++)
            {
                String[] tokens = CCCodes.stdMap.get(i).toString().split("=");
                model.addElement(tokens[2].replace("}",""));
                model2.addElement(tokens[2].replace("}",""));
                
                // System.out.println(tokens[2].replace("}",""));
            }
            // initialize them to "USD" and "CAD"
            comboBox.setSelectedIndex(81);
            comboBox2.setSelectedIndex(13);
            
            /************************************************
            *           Display the exchange rate           *
            ************************************************/
            myLabel = new JLabel("Exchange rate is:");
            add(myLabel); // print out exchange rate
            
            /****************************************************
            *                  Convert Button                   *
            ****************************************************/ 
	    convertButton = new JButton("CONVERT"); 
	    add(convertButton, BorderLayout.CENTER);  // add button to current panel
            convertButton.setActionCommand("Convert"); // name of the event action
	    convertButton.addActionListener(this); // listen for an event (click)
	    // register the current panel as listener for the buttons
            convertButton.setActionCommand("Convert");
	    convertButton.addActionListener(this); 

            /****************************************************
            *                  Create Menu                      *
            ****************************************************/ 
            menu = new JMenu("Menu");
            menu.setMnemonic(KeyEvent.VK_A);
            menu.getAccessibleContext().setAccessibleDescription("Menu");
            menuBar.add(menu); // add menu to panel
            
            /**************************************************
            *          Create a group of JMenu items          *
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
            *       Action listener for each menu item        *
            **************************************************/ 
            about.addActionListener(this);
            update.addActionListener(this);
            exit.addActionListener(this);
          }
          
          /******************************************************
          *  Function onExit () no argument, void return value  *
          *  - Cleans Up application                            *
          *  - Exits Application safely                         *
          *******************************************************/ 
          public void onExit ()
          {
              // clean up application here
                System.exit(0); // exit
          }

          /******************************************************
          *   Get action performed from menu items and call     *
          *   appropriate functions.                            *
          *******************************************************/ 
          @Override
          public void actionPerformed(ActionEvent e) {

              // if Convert pressed
              if ("Convert".equals(e.getActionCommand())) { 
                  // get the rate
                  fromRate.update(from);
                  double rate = fromRate.get(to, from);
                  // get the amount the user entered (default to 1 if field empty)
                  if (convertFromTextField.getText().equals(""))
                     amount = 1;
                  else
                      amount = Double.parseDouble(convertFromTextField.getText());
                  // get result
                  double result = amount * rate;
                  // round the rate to 4 decimals
                  rate = Math.round(rate * 10000);
                  rate = rate/10000;
                  // display the rate to screen
                  myLabel.setText("Exchange rate is: " + rate);
                  // prepare the font and show the result to screen
                  Font font = new Font("Helvetica", Font.ITALIC, 14);
                  convertToTextField.setFont(font);
                  convertToTextField.setText(NumberFormat.getNumberInstance().format(result));
              }
              // if Update pressed
              else if ("Update".equals(e.getActionCommand())) 
              {
                   /** - Read RSS feed for FromCurrency                          **
                   ** - Build HashMap "ToCurrency/FromCurrency to Exchange Rate */
                  from = (String)From.getSelectedItem();
                  fromRate.update(from);
                  
              }
              // if About pressed
              else if ("About".equals(e.getActionCommand())) 
              {
                  /** - dialog box containing: App title, your name, "OK" button*/
                  JOptionPane.showMessageDialog(null, info, "About", 1);
              }
             
              else if ("From".equals(e.getActionCommand())) {
                  // get value for from drop down menu
                  From = (JComboBox)e.getSource();
                  from = (String)From.getSelectedItem();
              }
              else if ("To".equals(e.getActionCommand()))
              {
                  // get value for to drop down menu
                  To = (JComboBox)e.getSource();
                  to = (String)To.getSelectedItem();
              }
               // if Exit pressed
              else 
              {
                  onExit(); // calls onExit()
              }
          }
          
          //=============================================
	  //////////////////// main /////////////////////

	  public static void main(String[] args) {
            // create a JFrame instance of our interface
              
	    JFrame f = new Interface("Currency Converter");
	    f.show();  // show interface
	  } //main
         
} //class Panel
