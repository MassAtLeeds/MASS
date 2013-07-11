/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.filereader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kirkharland
 */
public class CSVReaderTest {
    
    public CSVReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadData method, of class CSVReader.
     */
    @Test
    public void testLoadData() {
        System.out.println("loadData");
        //create the file object
        File file = new File("/users/kirkharland/"+
                "desktop/code/testarea/data/test_2.csv");
        //create an instance of the CSVReader
        CSVReader instance = new CSVReader();
        //try to load the data, the method should throw an error to let
        //us know that the fields are inconsistent
        try{
            instance.loadData(file);
        }catch(IOException ex){
            //check it is the correct error message
            Assert.assertEquals("Field count not consistent in all records", 
                    ex.getMessage());
            return;
        }
        //If we get to here fail the test because the error has not been thrown
        Assert.fail("Error is not thrown.");
    }
        
    /**
     * Test of parseLine method, of class CSVReader.
     */
    @Test
    public void testParseLine() {
        System.out.println("parseLine");
        //create a line of code to be parsed
        String line = "val1, 2,3  ,val 4";
        //create an instance of the CSVReader
        CSVReader instance = new CSVReader();
        //create the expected return value
        String[] expResult = {"val1","2","3","val 4"};
        //parse the line
        String[] result = instance.parseLine(line);
        //test the results are as expected
        assertArrayEquals(expResult, result);
    }
    
    
    /**
     * Test of parseLine method, of class CSVReader.
     */
    @Test
    public void testParseLineEmptyValues() {
        System.out.println("parseLine - Empty Values");
        //create a line of code to be parsed
        String line = ",,val1, ,2,,";
        //create an instance of the CSVReader
        CSVReader instance = new CSVReader();
        //create the expected return value
        String[] expResult = {"","","val1","","2","",""};
        //parse the line
        String[] result = instance.parseLine(line);
        //test the results are as expected
        assertArrayEquals(expResult, result);
    }
    

    /**
     * Test of getData method, of class CSVReader.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        //create an instance of the CSVReader
        CSVReader instance = new CSVReader();
        //point to the test file
        File file = new File("/users/kirkharland/"+
                "desktop/code/testarea/data/test_1.csv");
        //create a 2D array of how the results should look
        String[][] expResult = 
            {{"field1 val1", "field2 val1"},
             {"field1 val2", "field2 val2"},
             {"field1 val3", "field2 val3"}};
        try {
            //load the data
            instance.loadData(file);
        } catch (IOException ex) {
            Assert.fail("Unexpected exception thrown");
        }
        //get the results
        String[][] result = instance.getData();
        //test that the results are as expected
        assertArrayEquals(expResult, result);
    }
}
