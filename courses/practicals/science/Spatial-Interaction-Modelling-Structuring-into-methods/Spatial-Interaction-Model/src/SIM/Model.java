/*
 * This is the code to accompany the online Java practical
 * Spatial Interaction Modelling: Structuring into methods
 * 
 */
package SIM;

/**
 *
 * @author kirkharland
 */
public class Model {
    
    //private instance variables for the alpha and 
    //beta values
    private double alpha = 0.0;
    private double beta = 0.0;
    
    //private instance variables for the origin and 
    //destination index locations
    private int i = 0;
    private int j = 0;
    
    //instance variable for the ai balancing factor
    private double ai = 0.0;
    
    //private instance variable to hold the data
    private DataHandler data = null;
    
    //create the default constructor as private
    //so that it is not called accidentally
    private Model(){}
    
    //Create the constructor that we want people to 
    //use as public access and with the parameter
    //holding the data
    public Model(DataHandler data){
        //assign the data parameter to the instance
        //level variable data.  The scope of the parameter
        //is within the method and that is where the JVM 
        //looks first for a variable or parameter so we 
        //need to specify that the instance level variable 
        //is to be used by using the keyword 'this' followed 
        //by the '.' operator.
        this.data = data;
    }
    
    /**
     * Calculate the main spatial interaction model equation.
     * Cycles through the origins and destinations applying the 
     * spatial interaction model equation to each pair.  The 
     * balancing factor code is called when each origin or destination
     * changes.
     * 
     * @param alpha a double value representing the parameter applied to the
     * destination attractiveness
     * @param beta a double value representing the parameter applied to the 
     * distance between the origin and destination.
     */
    
    public void calculate(double alpha, double beta){
        
        this.alpha = alpha;
        this.beta = beta;
        
        //cycle the origins using instance counter
        for(; i < data.getOrigin().length; i++){
            
            //call the calculate balancing factor code as origin changes
            calculateBalancingFactors(true,false);
            
            //output text
            String output = "";
            //cycle the destinations  using instance counter
            for(; j < data.getDestination().length; j++){
                
                //call the calculate balancing factor code as destination 
                //changes
                calculateBalancingFactors(false,true);

                //get the distance value for this i,j pair
                double distance = data.getDistance(i, j);
                //declare the tij variable outside of the if block
                double tij = 0.0;
                
                //test and make sure our distances are valid
                if (distance > -1.0){
                    //Calculate model equation
                    tij = ai  
                            * data.getOrigin()[i] 
                            * Math.pow(data.getDestination()[j], alpha) 
                            * Math.exp(distance * beta);
                }else{
                    //tell the user about the discrepancy
                    System.out.println("Data discrepancy between origin, "
                            + "destination and distance dimensions");
                }
                
                //build up the output string
                if ( j > 0 ){ output += ", ";}
                output += tij;
                
            //end destination cycle
            }
            //reset j
            j = 0;
            
            //print the results to screen
            System.out.println(output);
        //end origin cycle
        }
                
    }
    
    /**
     * Code to calculate the balancing factor required for the spatial
     * interaction model equation.
     * 
     * @param originChanged boolean indicating the origin has changed
     * @param destinationChanged boolean indicating the destination has changed
     */
    private void calculateBalancingFactors(boolean originChanged, 
            boolean destinationChanged){

        //create a variable to hold the sum of denominator values
        double denominator = 0.0;
        //cycle the destinations
        for(int jLocal = 0; jLocal < data.getDestination().length; jLocal++){
            
            //Sum all of the destination calculations for 
            //this origin into the denominator variable
            denominator += ( Math.pow(data.getDestination()[jLocal], alpha) 
                    * Math.exp(data.getDistance(i, jLocal) * beta) );


        //end destination cycle
        }
        //finally calculate the Ai value
        ai = 0.0;
        //check to make sure denominator > 0 so no div by 0 error
        if ( denominator != 0.0 ){
            ai = 1/denominator;
        }
        
    }
    
}
