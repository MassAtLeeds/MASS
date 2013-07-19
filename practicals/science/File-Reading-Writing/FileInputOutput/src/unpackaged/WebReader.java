import java.io.*;
import java.util.*;
import java.net.*;

/**
 * WebReader for turning webpages into 2D String arrays.
 * ToDo: give read() a boolean parameter that forces it to cut out tags between angle-brackets (i.e. HTML tags).
 * @author <A href="http://www.geog.leeds.ac.uk/people/a.evans/">Andy Evans</A>
 * @version 1.0 
**/
public class WebReader {


	/**
	* Takes in a URL address as a string, e.g.<BR />
	* read("http://www.bbc.co.uk/");<BR />
	* Returns the page (including HTML tags) as 2D String array.
	* @param URL address as String.
	* @return 2D array of text in page. 
	**/
	public String[][] read(String urlStr) {
	
		String[][] data = null;

		// Make a URL object encapsulating the web address.
		
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException m) {
			m.printStackTrace();
		}
	
		// Open a connection to the webpage.

		URLConnection con = null;
		
		try {		
			con = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		// Print page info.
	
		System.out.println("Date: " + new Date(con.getDate()));
		System.out.println("Content-Type: " + con.getContentType());
		System.out.println("Expires: " + con.getExpiration());
		System.out.println("Last-Modified: " + new Date(con.getLastModified()));
    
		int len = con.getContentLength();
		System.out.println("Content-Length: " + len);
		
		if (len <= 0) return null;
		
		// Connect a stream.
		
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (IOException i) {
			i.printStackTrace();
		}

		// Count the lines. Note that these are lines of HTML, which 
		// isn't necessarily the lines as displayed in a browser.
		
		int lines = -1;
		String textIn = " ";
		String[] file = null;

		try {
			while (textIn != null) {
				textIn = br.readLine();
				lines++;
			}
		} catch (IOException e) {
			e.printStackTrace(); 
		} finally {
			try {
			  br.close();
			} catch (IOException e2) {
			  e2.printStackTrace();
			}
		}

		// Reopen the file at the top and read the 
		// file into a String array of the right size.

		try {		
			con = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
		   br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
		   e.printStackTrace();
		}

		file = new String[lines];

		try {
			for (int i = 0; i < lines; i++) {
				file[i] = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

		// Parse.

		data = new String [lines][];

		for (int i = 0; i < lines; i++) {
			
			StringTokenizer st = new StringTokenizer(file[i]," ,");
			data[i] = new String[st.countTokens()];
			
			int j = 0;
			while (st.hasMoreTokens()) {
				data[i][j] = st.nextToken();
				j++;
			}
		}


		return data;
		
	}
	
	
	
}
