//class declaration for our CarFactory.
public class CarFactory {

  //way for the JVM to run our code
	public static void main(String[] args){
	
		//show that the program has started.
		System.out.println("Started.");

		//create a car object from the Car class
		Car car1 = new Car();
		//set the engine size
		car1.engineSize = 2.0;
		//set the colour
		car1.carColour = "black";
		
		//set the default car colour
		Car.defaultCarColour = "red";

		//print out car1 attributes to the screen
		System.out.println("Car 1: colour = " 
			+ car1.carColour 
			+ " engine size = "
			+ car1.engineSize);
		
		//create another car object from Car
		Car car2 = new Car();
		//set the engine size
		car2.engineSize = 1.1;
		//set the colour
		car2.carColour = "green";

		//print out car2 attributes to the screen
		System.out.println("Car 2: colour = "
			+ car2.carColour
			+ " engine size = "
			+ car2.engineSize);
			
		//create yet another car object from Car
		Car car3 = new Car();
		
		//print out car3 attributes to the screen
		System.out.println("Car 3: colour = " 
			+ car3.carColour 
			+ " engine size = "
			+ car3.engineSize);
			
		//print out the default colour and engine 
		//size to the screen
		//car1
		System.out.println("Car 1 defaults: colour = "
			+ car1.defaultCarColour
			+ " engine size = "
			+ car1.defaultEngineSize);
		//car2
		System.out.println("Car 2 defaults: colour = " 
			+ car2.defaultCarColour 
			+ " engine size = "
			+ car2.defaultEngineSize);
		//car3
		System.out.println("Car 3 defaults: colour = " 
			+ car3.defaultCarColour 
			+ " engine size = "
			+ car3.defaultEngineSize);

		//show that the program has finished.
		System.out.println("Finished.");

	}

}
