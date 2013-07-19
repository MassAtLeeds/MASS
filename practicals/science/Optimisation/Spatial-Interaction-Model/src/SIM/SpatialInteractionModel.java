/*
 * This is the code to accompany the online Java practical
 * Model Optimisation
 * 
 */
package SIM;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import uk.ac.leeds.mass.optimisation.genetic.GeneticAlgorithm;

/**
 *
 * @author kirkharland
 */
public class SpatialInteractionModel extends JFrame implements ActionListener{
    
    //create some instance level variables for our text areas
    private JFormattedTextField alpha = new JFormattedTextField(NumberFormat.getNumberInstance());
    private JFormattedTextField beta = new JFormattedTextField(NumberFormat.getNumberInstance());
    private JTextArea reportingArea = new JTextArea(20, 30);
        
    //instance level variables created to make the GA work
    private JPanel pane = new JPanel();
    private JFormattedTextField generations = new JFormattedTextField(NumberFormat.getNumberInstance());
    private JFormattedTextField breedPopulationSize = new JFormattedTextField(NumberFormat.getNumberInstance());
    private JFormattedTextField candidatePopulationSize = new JFormattedTextField(NumberFormat.getNumberInstance());
    private JFormattedTextField mutationRate = new JFormattedTextField(NumberFormat.getNumberInstance());
    
    
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
        
        //create the JMenuBar and add some items to it
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Model");
        //create the calibrate menu option using the constants from the listener as the name
        JMenuItem calibrateMenu = new JMenuItem(ModelMenuItemListener.CALIBRATE_MENU_ITEM);
        //add a new instance of the listener to the menu item
        calibrateMenu.addActionListener(new ModelMenuItemListener(this));
        //create the run menu option using the constants from the listener as the name
        JMenuItem runMenu = new JMenuItem(ModelMenuItemListener.RUN_MENU_ITEM);
        //add a new instance of the listener to the menu item
        runMenu.addActionListener(new ModelMenuItemListener(this));
        //add the menu items to the menu
        menu.add(runMenu);
        menu.add(calibrateMenu);
        //add the menu to the menu bar
        menuBar.add(menu);
        //set the menu bar in 'this' frame
        this.setJMenuBar(menuBar);
        
        //make the application exit when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Add contents to the window
        this.setMinimumSize(new Dimension(300, 300));
        this.add(pane);
        this.add(setUpFrame());
        //Display the window. Pack makes sure the window can fit all 
        //of the components on it
        this.pack();
        this.setVisible(true);
    }
    
    private JPanel setUpRunFrame(){
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
        //return the main panel
        return p;
    }
    
    
    private JPanel setUpFrame(){
        //create a new JPanel to hold all of the components
        JPanel panel = new JPanel(new GridBagLayout());
        //set up a layout constraint object
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        //add the panel with our text boxes and buttons to the main panel        
        panel.add(pane,c);
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
    
    private JPanel setUpCalibrationFrame(){
        //create a new instance of the genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(new Model());
        //set parameter field sizes
        generations.setColumns(5);
        breedPopulationSize.setColumns(5);
        candidatePopulationSize.setColumns(5);
        mutationRate.setColumns(5);
        //set default values for the input controls read from the ga
        generations.setValue(ga.getGenerations());
        breedPopulationSize.setValue(ga.getBreedPopulationSize());
        candidatePopulationSize.setValue(ga.getCandidatePopulationSize());
        mutationRate.setValue(ga.getMutationRate());
        //create a button to run the optimisation process and add this as the listener
        JButton run = new JButton("run GA");
        run.addActionListener(this);
        //add a label and each input control to the panel using a grid layout
        JPanel panel = new JPanel(new GridBagLayout());
        //set up a constraints object to position the controls
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        //position the controls
        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("generations: "),c);
        c.gridx = 1;
        panel.add(generations,c);
        c.gridy = 1;
        panel.add(mutationRate,c);
        c.gridx = 0;
        panel.add(new JLabel("mutation rate: "),c);
        c.gridy = 2;
        panel.add(new JLabel("breed population: "),c);
        c.gridx = 1;
        panel.add(breedPopulationSize,c);
        c.gridy = 3;
        panel.add(candidatePopulationSize,c);
        c.gridx = 0;
        panel.add(new JLabel("candidate population: "),c);
        c.gridy = 4;
        c.gridwidth = 2;
        panel.add(run,c);
        //return the main panel
        return panel;
    }
    
    public void setPane(int paneType){
        //remove all of the current controls from the pane
        pane.removeAll();
        //depending on which menu has been actioned add either the run controls
        //or the calibration controls
        switch(paneType){
            case 1:
                pane.add(setUpRunFrame());
                break;
            case 2:
                pane.add(setUpCalibrationFrame());
        }
        //pack all of the controls so that the window displays correctly
        this.pack();
    }
    
    //provide a way to report messages to the screen
    public void report(String message){
        //append the message passed into the method to the reporting area 
        //followed by a line separator
        reportingArea.append(message + System.getProperty("line.separator"));
        //make sure the last entered text is visible
        reportingArea.setCaretPosition(reportingArea.getText().length());
        //force updates before processes finish
        reportingArea.update(reportingArea.getGraphics());
    }

    //detect an action when the button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        //load the data
        DataHandler.getDataHandler().loadData();
        //load the observed data
        DataHandler.getDataHandler().loadObserved();
        //either run GA or model
        switch (e.getActionCommand()) {
            case "run model":
                runModel();
                break;
            case "run GA":
                calibrateModel();
                break;
        }
    }
    
    
        
    private void runModel(){
        //Create a new instance of the model passing
        Model model = new Model();
        //Run the model calculation passing the equation
        //parameters into the method
        model.calculate(Double.parseDouble(alpha.getValue().toString()), 
                Double.parseDouble(beta.getValue().toString()));
        //create the new chart object passing in the results and the 
        //observed data
        Chart chart = new Chart(model.getResults(),
                DataHandler.getDataHandler().getObserved());
        //report the parameters and fit statistic to the screen
        report("alpha: " + alpha.getValue() + " beta: " + beta.getValue() + 
                " SRMSE: " + 
                new SRMSE().calculate(DataHandler.getDataHandler().getObserved(), 
                model.getResults()));
        
    }
    
    
    private void calibrateModel(){
        //create an instance of the genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(new Model());
        //set up the parameters to those in the text boxes
        ga.setBreedPopulationSize(Integer.parseInt(breedPopulationSize.getValue().toString()));
        ga.setCandidatePopulationSize(Integer.parseInt(candidatePopulationSize.getValue().toString()));
        ga.setGenerations(Integer.parseInt(generations.getValue().toString()));
        ga.setMutationRate(Double.parseDouble(mutationRate.getValue().toString()));
        
        //print our parameter setup to the reporting area
        report("GA running with parameters:");
        report("breed population = " + breedPopulationSize.getValue().toString());
        report("candidate population = " + candidatePopulationSize.getValue().toString());
        report("generations = " + generations.getValue().toString());
        report("mutation rate = " + mutationRate.getValue().toString());
        //tell the user we are running
        report("running...");
        //run the ga
        ga.run();
        //get the best performing model at the end
        Model model = (Model)ga.getBreedPopulation().get(0);
        //report the fit found
        report("best fit found = " + model.calculateFitness(ga.getGof()));
        report("alpha = " + model.getGenes().get(0).toString());
        report("beta = " + model.getGenes().get(1).toString());
        //create a chart with the results
        Chart chart = new Chart(model.getResults(),
                DataHandler.getDataHandler().getObserved());
        //tell the user we have finished
        report("Finished.");
    }
    
    

    
}

