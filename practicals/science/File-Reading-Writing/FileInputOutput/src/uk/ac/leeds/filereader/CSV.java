/*
 * This is the code to accompany the online Java practical
 * Data Input / Output and JUnit
 * 
 */
package uk.ac.leeds.filereader;

/**
 *
 * @author kirkharland
 */
public abstract class CSV {
    /**
     * enum to provide a strict list of valid delimiter characters.  The enum holds
     * both the actual character sequence for the delimiter but also a text description.
     */
    public enum Delimiter {
        //delimiter types specified
        COMMA(",", "comma"), HAMMER("¬","hammer"), PIPE("|","pipe"), 
        TAB("\t","tab"), COLON(":","colon"), SEMI_COLON(";","semi-colon");
        //enum constructor
        Delimiter(String delimiter, String description){
            this.delimiter = delimiter;
            this.description = description;
        }
        /**
         * Variable to holding the specified delimiter
         */
        public String delimiter;
        /**
         * Variable containing the description of the delimiter
         */
        public String description;
    }   //end of enum
    
    //set the default delimiter for CSV as a comma
    private Delimiter currentDelimiter = Delimiter.COMMA;
    
    /**
     * Mutator to access the delimiter.
     * @return A Delimiter data type representing the current set delimiter
     */
    public Delimiter getDelimiter(){
        return currentDelimiter;
    }
    
    /**
     * Mutator to set the delimiter
     * @param A Delimiter data type specifying the delimiter to use
     */
    public void setDelimiter(Delimiter delimiterToUse){
        currentDelimiter = delimiterToUse;
    }
    
}
