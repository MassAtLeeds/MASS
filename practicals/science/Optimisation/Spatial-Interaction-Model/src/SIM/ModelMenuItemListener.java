/*
 * This is the code to accompany the online Java practical
 * Model Optimisation
 * 
 */
package SIM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kirkharland
 */
public class ModelMenuItemListener implements ActionListener{
    //create two constants to hold the information about whether this is a 
    //model run or a calibration run
    public final static String RUN_MENU_ITEM = "Run Model...";
    public final static String CALIBRATE_MENU_ITEM = "Calibrate...";
    //have an instance level variable to the SpatailInteractionModel class
    //so we can make calls back to it
    private SpatialInteractionModel sim = null;
    //make the default constructor private so it is not called by accident
    private ModelMenuItemListener(){}
    //have a public constructor to take in our sim variable and set the 
    //instance level one to equal the parameter
    public ModelMenuItemListener(SpatialInteractionModel sim){
        this.sim = sim;
    }

    //overridden method from ActionListener interface called when an event happens
    @Override
    public void actionPerformed(ActionEvent e) {
        //decide which menu item fired the event
        switch (e.getActionCommand()) {
            case ModelMenuItemListener.RUN_MENU_ITEM:
                sim.setPane(1);
                break;
            case ModelMenuItemListener.CALIBRATE_MENU_ITEM:
                sim.setPane(2);
                break;
        }
    }
    
}
