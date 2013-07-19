/*
 * This is the code to support the online 
 * inheritance practical
 */
package basicagents;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.continuous.Continuous2D;
import sim.util.Bag;
import sim.util.Double2D;

/**
 *
 * @author kirkharland
 */
public class Agent implements Steppable{
    //variable to hold how happy the agent is
    private double happiness;
    //varaiable to hold the type the agent is
    private int agentType;

    /**
     * Method overridden from the interface Steppable.  This is called 
     * at each step of the model.
     * @param ss 
     */
    @Override
    public void step(SimState ss) {
        
        //get the current location of the agent
        
        //cast the SimState object back to our BasicAgents object so that we
        //can access the fields we have inside
        BasicAgents agents = (BasicAgents) ss;
        //get a local reference to our environment for convienience
        Continuous2D environment = agents.environment;
        //from the environment get a point for the location of this agent
        //the keyword this refers to 'this' object
        Double2D me = environment.getObjectLocation(this);
        
        //find its closest x neighbours
        
        //get a collection of agents closest to this agent which is at least
        //as many as we require.  Bag is a MASON bespoke collection and not 
        //part of the Java core language
        Bag neighbours = environment.getNearestNeighbors(me, 
                agents.getNumberNeighbours(), false, false, true, null);
        //create two arrays, the length of the number of neighbours needed
        //one to hold agents
        Agent[] nearest = new Agent[agents.getNumberNeighbours()];
        //one to hold the distance to them
        double[] distances = new double[agents.getNumberNeighbours()];
        //cycle over the Bag of neighbours
        for (Object o : neighbours) {
            //calculate the distance to the current neighbour
            double d = me.distance(environment.getObjectLocation(o));
            //create two varaibles one to record the index of the neighbour
            //and one for the maximum distance so far discovered
            int index = -1;
            double greatestDistance = -1.0;
            //cycle over the arrays
            for (int i = 0; i < nearest.length; i++) {
                //if there is a null spot use this position
                if (nearest[i]==null){
                    index = i;
                    //because we have found an empty location end the search
                    break;
                //if we have found the furthest point and this one is closer
                //use that index
                }else if ( d < distances[i] && distances[i] > greatestDistance ){
                    index = i;
                    greatestDistance = distances[i];
                }
            }
            //if the index has been populated we have an agent to store
            if ( index > -1 ){
                //store the agent in the Agent array at the index location
                nearest[index] = (Agent)o;
                //store the corresponding distance in the double array at the
                //corresponding index location
                distances[index] = d;
            }  
        }
        
        //calculate the happiness
        
        //create a variable to count how many agents of the same type
        //as this one are neighbours
        //this variable could be an int but because we are going to 
        //use it in a calculation returning a double we can delacare it 
        //as a double rather than cast it in the calculation
        double sameTypeCounter = 0.0;
        //cycle through all of the nearest neighbours
        for (int i = 0; i < nearest.length; i++) {
            //if the neighbour is the same type as this agent increment
            //the same counter
            if ( nearest[i].agentType == this.agentType ){
                sameTypeCounter++;
            }
        }
        //calculate the happiness as the proportion of nearest neighbours 
        //that are the same type as this agent
        happiness = sameTypeCounter / agents.getNumberNeighbours();
        
        //move if the agent is not happy
        
        //not feeling happy so move towards own type of agent
        if ( happiness < agents.getAttractionToOwnType() ){
            //find most secure agent location of my type
            //create a variable to track the most happy agents of this type
            double mostHappy = 0.0;
            //create a variable to store the point where we want to travel to
            Double2D toPoint = null;
            //cycle through all of the agents in the environment
            for (Object o : environment.getAllObjects()) {
                //if the agent is not this agent
                if ( !o.equals(this) && 
                        //and it is of the same type
                        ((Agent)o).getAgentType() == this.getAgentType() && 
                        //and it is happier than the most happy agent yet found
                        ((Agent)o).getHappiness() >= mostHappy){
                    //store the happiness value as the mostHappy
                    mostHappy =  ((Agent)o).getHappiness();
                    //store the agent as the toPoint
                    toPoint = environment.getObjectLocation(o);
                }
            }
            //create a double value for x and store our current location 
            //plus a movement of 1 in the direction of the to point
            double x = (me.x > toPoint.x) ? me.x - 1 : me.x + 1;
            //create a double value for y and store our current location 
            //plus a movement of 1 in the direction of the to point
            double y = (me.y > toPoint.y) ? me.y - 1 : me.y + 1;
            //set the new location of this agent using the new x and y
            environment.setObjectLocation(this, new Double2D(x,y));
                
        }
        
    }
    /**
     * @return the happiness
     */
    public double getHappiness() {
        return happiness;
    }
    /**
     * @return the agentType
     */
    public int getAgentType() {
        return agentType;
    }
    /**
     * @param agentType the agentType to set
     */
    public void setAgentType(int agentType) {
        this.agentType = agentType;
    }
    
}
