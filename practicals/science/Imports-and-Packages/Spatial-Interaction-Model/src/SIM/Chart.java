/*
 * This is the code to accompany the online Java practical
 * Imports and Packages: Using JFree Chart
 * 
 */
package SIM;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author kirkharland
 */
public class Chart {
    
    //default constructor made private
    private Chart(){}
    
    //public constructor with 2 2D double array parameters
    public Chart(double[][] result, double[][] observed){
        //Create a new Frame object
        Frame f1 = new Frame("Model Fit");
	//Set the Frame object size	
	f1.setSize(600,400);
        //Add a window listener to the Frame to close it
	f1.addWindowListener(new WindowAdapter() {
                @Override
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	});
          
        //add in the code to create the chart
        //create a data object to hold the XY data
        DefaultXYDataset data = new DefaultXYDataset();
        //cycle through the origins
        for (int i = 0; i < observed.length; i++) {
            //create a two dimensional array to hold the observed and result
            double dataArray[][] = new double[2][observed[i].length];
            //put the observed data in element 0
            dataArray[0]=observed[i];
            //put the result data in element 1
            dataArray[1]=result[i];
            //add the data series with a unique name
            data.addSeries("Origin "+i, dataArray);
        }
	//Create the chart with the correct title and axis names and data etc.
	JFreeChart chart = ChartFactory.createScatterPlot("Observed vs. Results", 
		"Observed", // x axis label
		"Results", // y axis label
		data, // data
		PlotOrientation.VERTICAL, // orientation
		true, // legend
		true, // tooltips
		false // URLs
	);
	//get the plot area and format it to the desired colours
	XYPlot plot = (XYPlot) chart.getPlot();
	plot.setBackgroundPaint(Color.white);
	plot.setDomainGridlinePaint(Color.darkGray);
	plot.setDomainGridlinesVisible(true);
	plot.setRangeGridlinePaint(Color.black);
	
	// We're going to add some regression stuff here shortly.
        double[] coeffs = Regression.getOLSRegression(createTwoDArray(result, observed));
	LineFunction2D linefunction2d = new LineFunction2D(coeffs[0], coeffs[1]);
	XYDataset series2 = DatasetUtilities.sampleFunction2D(linefunction2d, 10, 50, 5, "Linear Regression Line");
	plot.setDataset(2, series2); 
	XYLineAndShapeRenderer lineDrawer = new XYLineAndShapeRenderer(true, false);
	lineDrawer.setSeriesPaint(0, Color.BLACK);
	plot.setRenderer(2, lineDrawer);
	
        //create a chart panel to render to the screnn
	ChartPanel chartPanel = new ChartPanel(chart);
        //add the chart panel to the frame
	f1.add(chartPanel);
        //set the frame to be visible
	f1.setVisible(true);
    }
    
    
    //private method to create a single two dimensional array from the 
    private double[][] createTwoDArray(double[][] result, double[][] observed){
        //set up an array to hold the new merged data
        //the fist dimension is only 2 elements, one for each of the 
        //arrays to be merged
        double[][] d = new double[2][result.length * result[0].length];
        
        //create an int counter to keep track of the index value for the 
        //new array 
        int index = 0;
        //cycle through both dimensions for the results and observed
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                //add the values into the new array
                //observed in dimension 1
                d[0][index] = observed[i][j];
                //result in dimension 2
                d[1][index] = result[i][j];
                //inrement the index for the new array
                index++;
            }
        }
        //return the new 2D array
        return d;
    }
    
}
