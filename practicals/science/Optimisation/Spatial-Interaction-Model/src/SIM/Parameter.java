/*
 * This is the code to accompany the online Java practical
 * Model Optimisation
 * 
 */
package SIM;

import java.util.Random;
import uk.ac.leeds.mass.optimisation.genetic.Gene;

/**
 *
 * @author kirkharland
 */
public class Parameter extends Gene {
    
    Random random = new Random();
    private double parUpper = 10.0;
    private double value = 1.0;

    //three overloaded constructors required to create instances of 
    //Parameter in different circumstances
    Parameter(){}
    
    Parameter(double upperValue){
        parUpper = upperValue;
        //call mutate to generate a random number
        mutate();
    }
    
    Parameter(double upperValue, double value){
        parUpper = upperValue;
        this.value = value;
    }
    
    /**
     * @return returns the value of this parameter as a double
     */
    public double getValue(){
        return value;
    }
       
    //generates a random number up to the upper limit of the parameter
    @Override
    public void mutate() {
        double val = random.nextDouble()*parUpper;
        if (random.nextBoolean()){
            value = 0 - val;
        }else{
            value = val;
        }
    }

    //create an identical Parameter object
    @Override
    public Gene clone() {
        return new Parameter(this.parUpper, this.value);
    }
    
    //return the String value of this object
    @Override
    public String toString(){
        return Double.toString(value);
    }
    
    //return whether this Parameter object is meaningfully equal to the one
    //passed in
    @Override
    public boolean equals(Object o){
        if ( !(o instanceof Parameter) ){
            return false;
        }
        double places = 1000000.0;
        double oVal = Math.floor(((Parameter)o).getValue() * places);
        double val = Math.floor(value * places);
        return oVal == val;
    }

    //default generated hashcode method don't worry about this you can read
    //about object hashing when you are happy with the basics
    @Override
    public int hashCode() {
        int hash = 5;
        return 47 * hash + new Double(value).intValue();
    } 
}
