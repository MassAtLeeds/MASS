/*
 * This is the code to accompany the online Java practical
 * Data Input / Output and JUnit
 * 
 */
package uk.ac.leeds.filereader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author kirkharland
 */
public class CSVWriter extends CSV{
    
    public void saveData(String fullFileName, String[][] data) throws IOException{
        
        //create a new file with the full file name parameter
        File file = new File(fullFileName);
        //check to see if the file exists if it does delete it
        if (file.exists()){
            throw new IOException("File " + fullFileName + " already exists");
        }
        //create a new version of the file
        file.createNewFile();
        
        //create a new BufferedWriter
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        
        //cycle through the array and write out the constructed lines
        for (int i = 0; i < data.length; i++) {
            //append a new line to the file each time except the first
            if (i > 0){bw.newLine();}
            //write the constructed line to the file
            bw.write(constructLine(data[i]));
        }
        
        //flush everything to be written to the file
        bw.flush();
        //close the file
        bw.close();
        
    }
    
    public String constructLine(String[] data){
        //new string variable to hold constructed line
        String s = "";
        //cycle through the data values
        for (int i = 0; i < data.length; i++) {
            //if this is not the first value add a delimiter character
            if ( i>0 ){s+=this.getDelimiter().delimiter;}
            //add the data to the line
            s+=data[i].trim();
        }
        //return the constructed line
        return s;
    }
    
}
