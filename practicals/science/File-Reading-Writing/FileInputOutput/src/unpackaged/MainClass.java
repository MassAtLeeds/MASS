
/**
 * Uses a WebReader to get a webpage as a 2D String array and display it at cmd.
 * @author <A href="http://www.geog.leeds.ac.uk/people/a.evans/">Andy Evans</A>
 * @version 1.0 
**/
public class MainClass {


	public MainClass() {
	
		WebReader wr = new WebReader();
		String[][] data = wr.read("https://www.geog.leeds.ac.uk/courses/other/programming/summer-school/hack/materials/2013/test.html");
	
		// Check data. 
		System.out.println("=== Content ===");
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print (data[i][j] + " ");
			}
			System.out.println("");
		}
	
	}


	public static void main (String args[]) {
		new MainClass();
	}



}