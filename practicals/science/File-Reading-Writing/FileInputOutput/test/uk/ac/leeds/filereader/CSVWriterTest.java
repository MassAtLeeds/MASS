/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.filereader;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kirkharland
 */
public class CSVWriterTest {
    
    //replace the file path with a suitable location on your computer.
    //windows users don't forget to insert your drive letter at the beginning
    private String filePath = "/Users/kirkharland/Desktop/Code/TestArea/test_1.csv";
    private File f = new File(filePath);
    
    public CSVWriterTest() {
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
     * Test of saveData method, of class CSVWriter.
     */
    @Test
    public void testSaveData() throws Exception {
        if (f.exists()){f.delete();}
        System.out.println("saveData");
        String[][] data = {{"1","2","3"},{"4","5","6"}};
        CSVWriter instance = new CSVWriter();
        instance.saveData(filePath, data);
        Assert.assertTrue(f.exists());
        if (f.exists()){f.delete();}
    }
    
    /**
     * Test of saveData method, of class CSVWriter.
     */
    @Test
    public void testSaveDataCheckData() throws Exception {
        if (f.exists()){f.delete();}
        System.out.println("saveData");
        String[][] data = {{"1","2","3"},{"4","5","6"}};
        CSVWriter instance = new CSVWriter();
        instance.saveData(filePath, data);
        CSVReader reader = new CSVReader();
        reader.loadData(f);
        Assert.assertArrayEquals(data, reader.getData());
        if (f.exists()){f.delete();}
    }
    
    /**
     * Test of saveData method, of class CSVWriter.
     */
    @Test
    public void testSaveDataFileExists() throws Exception {
        if (f.exists()){f.delete();}
        System.out.println("saveData File Exists");
        String[][] data = {{"1","2","3"},{"4","5","6"}};
        CSVWriter instance = new CSVWriter();
        instance.saveData(filePath, data);
        Assert.assertTrue(f.exists());
        try{
            instance.saveData(filePath, data);
        }catch(IOException ex){
            Assert.assertTrue(true);
            return;
        }finally{
            if (f.exists()){f.delete();}
        }
        Assert.fail("Error not thrown");
    }

    /**
     * Test of constructLine method, of class CSVWriter.
     */
    @Test
    public void testConstructLine() {
        System.out.println("constructLine");
        String[] data = {"1","2","3","4","5","6","7","8"};
        CSVWriter instance = new CSVWriter();
        instance.setDelimiter(CSV.Delimiter.PIPE);
        String expResult = "1|2|3|4|5|6|7|8";
        String result = instance.constructLine(data);
        assertEquals(expResult, result);
    }
}