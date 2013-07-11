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
public class DataHandler {
    
    //array holding the weights for the origins
    private double[] origin = null;
    //array holding the weights for the destinations
    private double[] destination = null;
    //array holding the distances between the origin and 
    //destinations
    private double[][] distance = null;
    
    /**
    * Loads the data into the array for processing
    * 
    */
    public void loadData(){
        
        //load the origins
        double[] origins = {20.0, 15.0, 71.0, 32.0, 
            56.0, 43.0, 85.0, 32.0, 87.0, 65.0};
        this.origin = origins;

        //load the destinations
        double[] destinations = {33.0, 40.0, 43.0, 36.0, 
            37.0, 45.0, 33.0, 20.0, 28.0, 21.0};
        this.destination = destinations;

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
        this.distance = distances;
    
    }

    /**
     * Accessor method for returning the loaded origin weights
     * 
     * @return a double array containing the origin weights or
     * null if data has not been loaded
     */
    public double[] getOrigin() {
        return origin;
    }

    /**
     * Accessor method for returning the loaded destination weights
     * 
     * @return a double array containing the destination weights or
     * null if data has not been loaded
     */
    public double[] getDestination() {
        return destination;
    }

    /**
     * Accessor method to get the distance between the origin and 
     * destination identified by the index values.
     * 
     * @param originIndex a valid integer value between 0 and the length 
     * of the origin array.
     * @param destinationIndex a valid integer value between 0 and the length 
     * of the destination array.
     * @return a double value representing the distance between the specified 
     * origin and destination.  If an invalid index is entered -1.0 is 
     * returned.
     */
    public double getDistance(int originIndex, int destinationIndex) {
        
        //check the origin index is valid
        if (originIndex >= 0 && originIndex < distance.length){
            //check that the destination index is valid
            if (destinationIndex >= 0 && destinationIndex < 
                    distance[originIndex].length){
                //if everything is correct return the value from the array
                return distance[originIndex][destinationIndex];
            }
        }
        
        //if the index values are not correct we will fall through to 
        //here and return -1.0
        return - 1.0;
        
    }
    
}
