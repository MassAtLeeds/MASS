/*
 * This is the code to accompany the online Java practical
 * Data Input / Output and JUnit
 * 
 */
package uk.ac.leeds.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author kirkharland
 */
public class CSVReader extends CSV{
    //create instance level data variable
    ArrayList data = new ArrayList();
    
    public void loadData(File file)throws IOException{
        //create a string to hold the line we read
        String line;
        //create a BufferedReader using our file parameter 
        //we have to create a FileReader with the File first because the
        //constructor for BufferedReader does not accept a File directly
        BufferedReader br = new BufferedReader(new FileReader(file));
        //create variable to hold number of fields
        int fields = 0;

        //cycle through the file until the line returned is null
        while ( (line = br.readLine()) != null ){
            //call parseLine to parse the fields from the line
            String[] s = parseLine(line);
            //check to see if this is first line
            if (data.isEmpty()){
                    //if it is set the fields variable
                    fields = s.length;
                }else{
                    //if not check that the current field count is correct
                    if (s.length != fields){
                        //if it isn't throw a new IOException with the desired text
                        throw new IOException("Field count not consistent in all records");
                    }
                }
            //assign parsed String array into the ArrayList
            data.add(s);
        }

        //close the BufferedReader object when we have finished with it
        br.close(); 
    }
    
    public String[] parseLine(String line){
        //return the split values to a local array
        String s[] = line.split(this.getDelimiter().delimiter,-1);
        //cycle each value in the local array
        for (int i = 0; i < s.length; i++) {
            //trim the values of any white space and assign them back
            //into the array
            s[i] = s[i].trim();
        }
        //return the array to the calling method
        return s;
    }
    
    public String[][] getData(){
        String[][] arrayData = new String[data.size()][];
        data.toArray(arrayData);
        return arrayData;
    }
    
}

