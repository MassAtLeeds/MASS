/*
 * This is the code to support the online 
 * inheritance practical
 */

package basicagents;

import sim.engine.*;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

/**
 *
 * @author kirkharland
 */
public class BasicAgents extends SimState{
    
    //the environment for our agents to move around in
    public Continuous2D environment = new Continuous2D(1.0,100,100);
    //the number of agents we want in our model
    private int numAgents = 250;
    //the number of neighbours we want our agents to consider when
    //calculating whether they are happy
    private int numberNeighbours = 10;
    //number of types of agents
    private int numberOfTypes = 2;
    //The attraction to own type, initially set at 50%
    private double attractionToOwnType = 0.5;

    /**
     * Constructor required to build the object and make a call
     * to the super class constructor.  Not in MASON the SimState
     * object controls the model run and does not have a public 
     * constructor without a 'long seed' parameter.  The seed is used 
     * to initilise random number generation.
     * @param seed to initialise the random number generator
     */
    public BasicAgents(long seed){
        super(seed);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //executes the model schedule
        doLoop(BasicAgents.class, args); 
        //exit the model when it ends or you stop it
        System.exit(0);
    }
    
    /**
     * This is the method the simulation will call to initialise the model 
     * at the beginning of the model run.  All initialisation code goes in here.
     */
    @Override
    public void start(){
        //start the model in the super class
        super.start();
        
        // make sure the environment is clear 
        environment.clear();
        
        // add the number of agents to the environment specified by the 
        //numAgents instance variable
        for(int i = 0; i < getNumAgents(); i++){
            //create a new agent
            Agent agent = new Agent();
            //defint the agents type at random using the SimState random
            //number generator that we have inherited
            agent.setAgentType(random.nextInt(numberOfTypes));
            
            //locate the agent in the environment using a Double2D object 
            //from the MASON toolkit which holds two doubles for x and y 
            //and provides some helper methods for getting distances etc.
            //generate the x and y for the agents location at random, but
            //making sure that the maximum is the greatest extent of the 
            //width or height of the environment
            environment.setObjectLocation(agent,
                new Double2D(environment.getWidth() * random.nextDouble(),
                    environment.getHeight() * random.nextDouble()));
        
            //add the agent to the schedule for as long as the model is running
            //this will make the step method in Agent get called at each time
            //step.
            schedule.scheduleRepeating(agent);
            
        } 
        
    }
    /**
     * @return the numAgents
     */
    public int getNumAgents() {
        return numAgents;
    }
    /**
     * @param numAgents the numAgents to set
     */
    public void setNumAgents(int numAgents) {
        this.numAgents = numAgents;
    }
    /**
     * @return the numberNeighbours
     */
    public int getNumberNeighbours() {
        return numberNeighbours;
    }
    /**
     * @param numberNeighbours the numberNeighbours to set
     */
    public void setNumberNeighbours(int numberNeighbours) {
        this.numberNeighbours = numberNeighbours;
    }
    /**
     * @return the attractionToOwnType
     */
    public double getAttractionToOwnType() {
        return attractionToOwnType;
    }
    /**
     * @param attractionToOwnType the attractionToOwnType to set
     */
    public void setAttractionToOwnType(double attractionToOwnType) {
        this.attractionToOwnType = attractionToOwnType;
    }
    
    
}
