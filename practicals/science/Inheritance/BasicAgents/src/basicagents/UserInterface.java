/*
 * This is the code to support the online 
 * inheritance practical
 */
package basicagents;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.Inspector;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;


/**
 *
 * @author kirkharland
 */
public class UserInterface  extends GUIState {
    
    private Display2D display;
    private JFrame displayFrame;
    private ContinuousPortrayal2D environmentPortrayal = new ContinuousPortrayal2D();
    
    /**
     * Default constructor to create a new GUIState sub class
     */
    public UserInterface() { 
        //call the super class constructor with a new BasicAgents object 
        //passed to the super constructor using the system current
        //time as the seed parameter
        super(new BasicAgents(System.currentTimeMillis())); 
    } 
    
    /**
     * main method to start the program running using the UserInterface.
     * @param args 
     */
    public static void main(String[] args){
        //create a new instance of UserInterface 
        UserInterface ui = new UserInterface();
        //Create a new console object (provides lots of default sliders and 
        //controls for adjusting the model.  Pass the console the UserInterface
        //object we just created
        Console c = new Console(ui);
        //make the console visible
        c.setVisible(true);
    }
    
    //Return the name of this particular model configuration
    public static String getName() { 
        return "Example Segregation"; 
    }
    
    @Override
    public void start(){
        //call the start method in the super class
        super.start(); 
        //call the code to set up the display of the agents (below)
        setupPortrayals(); 
    }
    @Override
    public void load(SimState state){
        //call the start method in the super class
        super.load(state); 
        //call the code to set up the display of the agents (below)
        setupPortrayals(); 
    }
    
    private void setupPortrayals(){
        //Get a convienience reference from our SimState reference in the 
        //super class and cast it as BasicAgents
        BasicAgents basicAgents = (BasicAgents) state;
        // tell the portrayal what to portray
        environmentPortrayal.setField( basicAgents.environment );
        //tell the portrayal that we will use a 2D oval shape for all agents
        environmentPortrayal.setPortrayalForAll(new OvalPortrayal2D() {
            //inside out new portrayal we are going to override the draw method
            @Override
            public void draw(Object object, Graphics2D graphics, DrawInfo2D info){
                //cast the object parameter to be an Agent (that is the class it is)
                Agent agent = (Agent)object;
                //get the type from the agent and determine the colour from the type
                if (agent.getAgentType() == 0){
                    paint = Color.BLUE;
                }else{
                    paint = Color.RED;
                }
                //call the draw method in the super (the one we have just overridden
                //to get it to draw the complex bits
                super.draw(object, graphics, info);
            } 
        });
        //reset the display and set the background colour white
        display.reset();
        display.setBackdrop(Color.white);
    }
        
    @Override
    public void init(Controller c){
        //call the super init method to initialise all other controls
        super.init(c);
        //set up the display dimensions for our display area and attach 
        //it to this object
        display = new Display2D(600,600,this);
        //create the display in the JFrame displayFrame
        displayFrame = display.createFrame();
        //give the display a title
        displayFrame.setTitle("Environment Display");
        //add the frame to the display list in the controller
        c.registerFrame(displayFrame);
        //set the display frame visible
        displayFrame.setVisible(true);
        //attach the environmentPortrayal to the display with the name Environment
        display.attach( environmentPortrayal, "Environment" );
    }
    
    
    public void quit(){
        //call the super quite method
        super.quit();
        //if the displayFrame is not null dispose of it.  If it was null
        //and we made a method call we would get a error at run time
        if (displayFrame!=null){ 
            displayFrame.dispose();
        } 
        //set the display objects to null
        displayFrame = null;
        display = null;
    }
    
    //provide an accessor method to our model state object
    @Override
    public Object getSimulationInspectedObject() { 
        return state; 
    }
    
    @Override
    public Inspector getInspector(){
        //provide access to the inspector from the super class through 
        //this class
        Inspector i = super.getInspector(); 
        i.setVolatile(true);
        return i;
    }
    //provide some information on using the model in the About tab
    public static Object getInfo(){
        return "<h2>Simple Segregation</h2>" +
            "<p>A very simple segregation model" + 
            "<p>Try adjusting the parameters in the Model tab and see what happens";
    }

}
