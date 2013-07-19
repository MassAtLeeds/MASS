/*
 * This is the code to accompany the online Java practical
 * Model Optimisation
 * 
 */
package SIM;


import uk.ac.leeds.mass.optimisation.genetic.Chromosome;
import uk.ac.leeds.mass.statistics.gof.IGOF;

/**
 *
 * @author kirkharland
 */
public class Model extends Chromosome{    
    //instance variable to store the results
    private double[][] results = null;
    
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
    public Model(){}
    
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
        
        data = DataHandler.getDataHandler();
        
        this.alpha = alpha;
        this.beta = beta;
        //dimension the results array
        results = new double[data.getOrigin().length][data.getDestination().length];
        //cycle the origins using instance counter
        for(; i < data.getOrigin().length; i++){
            
            //call the calculate balancing factor code as origin changes
            calculateBalancingFactors(true,false);
            
            //cycle the destinations  using instance counter
            for(; j < data.getDestination().length; j++){
                
                //call the calculate balancing factor code as destination changes
                calculateBalancingFactors(false,true);

                //get the distance value for this i,j pair
                double distance = data.getDistance(i, j);
                
                //test and make sure our distances are valid
                if (distance > -1.0){
                    //Calculate model equation
                    results[i][j] = ai  
                            * data.getOrigin()[i] 
                            * Math.pow(data.getDestination()[j], alpha) 
                            * Math.exp(distance * beta);
                }else{
                    //tell the user about the discrepancy
                    System.out.println("Data discrepancy between origin, "
                            + "destination and distance dimensions");
                }
                
            //end destination cycle
            }
            //reset j
            j = 0;

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

    /**
     * @return the results
     */
    public double[][] getResults() {
        return results;
    }

    /**
     * Populate the Gene(s) for the Chromosome object.  In this case 
     * there are two genes one for each parameter in the model equation.
     */
    @Override
    protected void populateGenes() {
        //add the genes to the Chromosomes collection of genes
        super.getGenes().add(new Parameter(10.0));
        super.getGenes().add(new Parameter(10.0));
    }

    /**
     * Calculate the fitness for the Chromosome.  this method runs the 
     * model using the Genes from the genes collection.  The result is 
     * tested again observed data using the GOF test passed in as a parameter 
     * from the Chromosome class.
     * 
     * @param gof a valid goodness of fit test that implements the IGOF interface
     * @return a double value representing the fitness of this model calibration
     */
    @Override
    public double calculateFitness(IGOF gof) {
        i = 0;
        j = 0;
        calculate( ((Parameter)super.getGenes().get(0)).getValue(),
                ((Parameter)super.getGenes().get(1)).getValue() );
        
        double fitness = gof.test(results,data.getObserved());
        
        return fitness;
    }
    
}


