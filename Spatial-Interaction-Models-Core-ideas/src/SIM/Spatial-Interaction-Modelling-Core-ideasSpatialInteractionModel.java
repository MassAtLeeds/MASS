/*
 * This is the code to accompany the online Java practical
 * Spatial Interaction Modelling: Core ideas
 * 
 */
package SIM;

/**
 *
 * @author kirkharland
 */
public class SpatialInteractionModel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //equation parameters
        double alpha = 1.0;
        double beta = -1.0;
        
        //load the origins
        double[] origins = {20.0, 15.0, 71.0, 32.0, 
            56.0, 43.0, 85.0, 32.0, 87.0, 65.0};
        
        //load the destinations
        double[] destinations = {33.0, 40.0, 43.0, 36.0, 
            37.0, 45.0, 33.0, 20.0, 28.0, 21.0};
        
        //load the distances
        double[][] distances = 
            {{26.0,50.0,67.0,84.0,41.0,27.0,26.0,37.0,8.0,24.0},
            {27.0,83.0,16.0,59.0,24.0,35.0,57.0,17.0,80.0,59.0},
            {67.0,3.0,74.0,82.0,59.0,97.0,21.0,70.0,29.0,50.0},
            {6.0,62.0,1.0,89.0,94.0,8.0,43.0,9.0,91.0,2.0},
            {98.0,76.0,76.0,46.0,50.0,89.0,22.0,62.0,61.0,91.0},
            {15.0,55.0,89.0,74.0,32.0,48.0,30.0,7.0,61.0,24.0},
            {62.0,76.0,83.0,86.0,44.0,49.0,8.0,22.0,3.0,83.0},
            {72.0,4.0,91.0,20.0,6.0,79.0,23.0,14.0,79.0,40.0},
            {40.0,88.0,50.0,11.0,84.0,8.0,95.0,46.0,35.0,57.0},
            {17.0,4.0,58.0,36.0,22.0,7.0,53.0,45.0,57.0,22.0}};
        
        //cycle the origins
        for( int i = 0; i < origins.length; i++){
            
            //create a variable to hold the sum of denominator values
            double denominator = 0.0;
            //cycle the destinations
            for(int j = 0; j < destinations.length; j++){

                //Sum all of the destination calculations for 
                //this origin into the denominator variable
                denominator += ( Math.pow(destinations[j], alpha) 
                        * Math.exp(distances[i][j] * beta) );

            //end destination cycle
            }
            //finally calculate the Ai value
            double ai = 0.0;
            //check to make sure denominator > 0 so no div by 0 error
            if ( denominator != 0.0 ){
                ai = 1/denominator;
            }
            
            //output text
            String output = "";
            //cycle the destinations
            for(int j = 0; j < destinations.length; j++){

                //Calculate model equation
                double tij = ai 
                        * origins[i] 
                        * Math.pow(destinations[j], alpha) 
                        * Math.exp(distances[i][j] * beta);
                
                //build up the output string
                if ( j > 0 ){ output += ", ";}
                output += tij;
                
            //end destination cycle
            }
        
            //print the results to screen
            System.out.println(output);
        //end origin cycle
        }
        
    }
}
