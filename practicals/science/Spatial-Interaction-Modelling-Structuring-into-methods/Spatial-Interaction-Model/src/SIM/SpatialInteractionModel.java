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
public class SpatialInteractionModel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //equation parameters
        double alpha = 1.0;
        double beta = -1.0;
        
        //create an instance of the DataHandler
        DataHandler dh = new DataHandler();
        //load the data
        dh.loadData();
        
        //Create a new instance of the model passing
        //the DataHandler instance in as a parameter
        Model model = new Model(dh);
        //Run the model calculation passing the equation
        //parameters into the method
        model.calculate(alpha, beta);

    }
}
