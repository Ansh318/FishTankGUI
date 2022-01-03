//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Fall 2021
//
// Author:   (Ansh Agarwal)
// Email:    (aragarwal@wisc.edu)
// Lecturer: (Mouna Kacem)
//
import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class FishTank 
    {
	
	private static PApplet processing; // PApplet object that represents the graphic. // interface of the JunglePark application
	private static PImage backgroundImage;// PImage object that represents the. // background image
	private static Fish[] fishes; // perfect size array storing the different fish present. // in the fish tank. These fish can be of different species.
	private static Random randGen; // Generator of random numbers


	/**
	 * Defines the initial environment properties of this application
	 * @param processingObj a reference to the graphic display window of this application
	 */
	public static void setup(PApplet processingObj) 
	    
	{
		processing = processingObj; //Set the processing static variable to the one passed as function parameter
		
		backgroundImage = processing.loadImage("images/background.png"); // Load background image from PNG.
		
		fishes = new Fish[]{null, null, null, null, null, null, null, null}; // Create an array to store fish objects

		randGen = new Random(); // Creating random object to vary fish positions  
				
		fishes[0] = new Fish(processing, (float)randGen.nextInt(processing.width), (float)randGen.nextInt(processing.height)); 
		// generates a random x and y position of type float within the width of the display window
		// Creating fish object and storing 
	}  
	
	/**
	 * Draws and updates the application display window.
	 * This callback method called in an infinite loop.
	 */
	public static void draw() 
	{
		processing.image(backgroundImage, processing.width / 2, processing.height / 2);	// Draw the background image at the center of the screen
		// width [resp. height]: System variable of the processing library that stores
		// the width [resp. height] of the display window.
		for (int j = 0; j < fishes.length; j++) 
		{
			if(fishes[j] != null)
			{
				fishes[j].draw(); //Draw fish object on application display window of fishes in the array.
				// i is the index of the created Fish in the fishes array.
			}
		}		

	}
	
	
	public static void main (String[] args) 
	{
		Utility.startApplication(); // Creating the main window for the application and allowing it to constantly update its appearance.
	}

	/**
	* Checks if the mouse is over a specific Fish whose reference is provided
	* as input parameter
	*
	* @param Fish reference to a specific fish
	* @return true if the mouse is over the specific Fish object (i.e. over
	* the image of the Fish), false otherwise
	*/
	public static boolean isMouseOver(Fish Fish) 
	{
		PImage fish = Fish.getImage(); // Reference to the Fish’s image of type PImage
		float x = Fish.getPositionX(); //fish.getPositionX()
		float y = Fish.getPositionY(); //fish.getPositionY()
		int domain = fish.width; // Acessing width of fish object
		int range = fish.height;  //Acessing height of fish object
		
		if ((processing.mouseX <= domain/2 + x) && (processing.mouseX >= x - domain/2)) //Accessing Mouse position and domain of mouse.
		{
			if((processing.mouseY <= range/2 + y) && (processing.mouseY >= y - range/2)) //Acessing mouse position and range of mouse.
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else 
		    {
			return false;
			}
	 }
	
	/**
	* Callback method called each time the user presses the mouse
	*/
	public static void mousePressed() 
	{
		for(int j = 0; j < fishes.length; j++) 
		{
			if(fishes[j] != null && isMouseOver(fishes[j])) //check if the mouse is over one of the Fish objects stored in the fishes array and start dragging it
			    {
				fishes[j].setDragging(true);
				break;
			    }
		}
	}
	
	/**
	* Callback method called each time the mouse is released
	*/
	public static void mouseReleased() 
	{
		for(int j = 0; j < fishes.length; j++) 
		{
			if(fishes[j] != null) 
			{
				fishes[j].setDragging(false); //No Fish must be dragged when the mouseis released. 
			}
		}
	}
	
	/**
	* Callback method called each time the user presses a key
	*/
	public static void keyPressed() 
	{
	    if (processing.key == 'F' || processing.key == 'f') //We’ll allow the user to add new fishes (up to eight fish) into the fish tank by pressing the F-key. 
	    {
	        for (int j = 0; j < fishes.length; j++) 
	        {
	          if (fishes[j] == null) 
	          {
	            fishes[j] = new Fish(processing, (float) randGen.nextInt(processing.width),
	                (float) randGen.nextInt(processing.height)); //replace the first (lowest index) null reference with 
	                                                             //a new Fish object located at a random position of the display window
	            break;
	          }
	        }
	    }

	    if (processing.key == 'R' || processing.key == 'r') //allow the user to remove fishes from the fish tank if key R or r is pressed.
	    {
	        for (int j = 0; j < fishes.length; j++)
	        {
	          if (fishes[j] != null)
	          {
	            if (isMouseOver(fishes[j])) 
	            {
	              fishes[j] = null; //reference will be set to null
	              break;
	            }
	          }
	        }
	    }
    	}
	}