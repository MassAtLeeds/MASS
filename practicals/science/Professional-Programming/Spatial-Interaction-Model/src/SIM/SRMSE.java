/*
 * This is the code to accompany the online Java practical
 * Model Optimisation
 * 
 */
package SIM;

/**
 *
 * @author kirkharland
 */
public class SRMSE {
    
    /**
     * Calculates the SRMSE for the two matricies passed in as parameters.
     * After Knudsen and Fotheringham (1986).
     * 
     * @param observed double[][] array of observed values
     * @param result double[][] array of modelled values
     * @return double value representing the SRMSE between the 
     */
    public double calculate(double[][] observed, double[][] result){

        double m = (double)observed.length * (double)observed[0].length;
        double ss = 0;
        double T = 0;

        for (int i=0;i<observed.length;i++){
            for (int j=0;j<observed[i].length;j++){
                ss += (Math.pow(observed[i][j]-result[i][j],2)/m);
                T += (observed[i][j]/m);
            }
        }
        double d = Math.pow(ss,0.5)/T;

        if ( Double.isNaN(d) || Double.isInfinite(d)){
            return 99999.9999;
        } 
        return d;
    }
    
}
