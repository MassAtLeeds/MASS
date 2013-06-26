//class declaration for our CarFactory.
public class CarFactory {

  //way for the JVM to run our code
	public static void main(String[] args){
	
		//show that the program has started.
		System.out.println("Started.");
		
		//create car1
		Car car1 = new Car();
		car1.engineSize = 2.0;
		car1.carColour = "black";

		//declare, initialise and populate array
		Car[] cars = {car1, new Car(), new Car()};
		
		//adjust second cars element
		cars[1].engineSize = 1.1;
		cars[1].carColour = "green";
		
		//print out the contents of our array
		//set up the for-loop to cycle until it gets 
		//to the arrays length
		for(int i=0;i<cars.length;i++){
			//print out the Car object at element 'i'
			System.out.println("Car " + (i+1) + " from array: "
				+ "colour = " 
				+ cars[i].carColour 
				+ " engine size = "
				+ cars[i].engineSize);
		}

		//show that the program has finished.
		System.out.println("Finished.");

	}

}
