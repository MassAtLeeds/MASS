/*
 * This is the code to accompany the online Java practical
 * GUI
 * 
 */
package SIM;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author kirkharland
 */
public class SpatialInteractionModel extends JFrame implements ActionListener{
    
    //create some instance level variables for our text areas
    private JFormattedTextField alpha = new JFormattedTextField(NumberFormat.getNumberInstance());
    private JFormattedTextField beta = new JFormattedTextField(NumberFormat.getNumberInstance());
    private JTextArea reportingArea = new JTextArea(20, 30);
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create a new instance of this class
        new SpatialInteractionModel();
    }

    public SpatialInteractionModel(){
        //set the window title displayed at the top of the window
        this.setTitle("Spatial Interaction Model");
        //make the application exit when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Add contents to the window
        this.add(setUpFrame());
        //Display the window. Pack makes sure the window can fit all 
        //of the components on it
        this.pack();
        this.setVisible(true);
    }
    
    
    private JPanel setUpFrame(){
        //create a new JPanel to hold all of the components
        JPanel panel = new JPanel(new GridBagLayout());
        //set up the input text boxes for alpha and beta
        alpha.setColumns(5);
        beta.setColumns(5);
        //set default values for alpha and beta
        alpha.setValue(new Double(1.0));
        beta.setValue(new Double(-1.0));
        //create a button to run the model
        JButton run = new JButton("run model");
        //add this as an action listener to the button
        run.addActionListener(this);
        //add the a label and a textbox for alpha and beta plus our run button 
        //to a JPanel in the correct order to keep them together
        JPanel p = new JPanel();
        p.add(new JLabel("alpha = "));
        p.add(alpha);
        p.add(new JLabel("beta = "));
        p.add(beta);
        p.add(run);
        //set up a layout constraint object
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        //add the panel with our text boxes and buttons to the main panel        
        panel.add(p,c);
        //set up the area for reporting not to be typed into
        reportingArea.setEditable(false);
        //add it to a scroll panel in case it grows too big
        JScrollPane scrollPane = new JScrollPane(reportingArea);
        //adjust the settings for our layout constraints
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        //add the scroll pane to the main panel with the constraints
        panel.add(scrollPane, c);
        //return the main panel
        return panel;
    }
    
    //provide a way to report messages to the screen
    public void report(String message){
        //append the message passed into the method to the reporting area 
        //followed by a line separator
        reportingArea.append(message + System.getProperty("line.separator"));
        //make sure the last entered text is visible
        reportingArea.setCaretPosition(reportingArea.getText().length());
    }

    //detect an action when the button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        //create an instance of the DataHandler
        DataHandler dh = new DataHandler();
        //load the data
        dh.loadData();
        //Create a new instance of the model passing
        //the DataHandler instance in as a parameter
        Model model = new Model(dh);
        //Run the model calculation passing the equation
        //parameters into the method
        model.calculate(Double.parseDouble(alpha.getValue().toString()), 
                Double.parseDouble(beta.getValue().toString()));
        
        //load the observed data
        dh.loadObserved();
        //create the new chart object passing in the results and the 
        //observed data
        Chart chart = new Chart(model.getResults(),dh.getObserved());
        //report the parameters and fit statistic to the screen
        report("alpha: " + alpha.getValue() + " beta: " + beta.getValue() + 
                " SRMSE: " + 
                new SRMSE().calculate(dh.getObserved(), model.getResults()));
        
    }
    
    

    
}

