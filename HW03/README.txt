COMP 504 HW02: Inheritance-based Ballworld
September 20, 2017
Kylan Race (ker7)
Chen Zeng (cz39)


# Environments
* This project was deigned in Eclipse Oxygen version 4.7.0.
* The main tools utilized were Window Builder, UML Lab, and Subclipse (for source control)

# Homework Description
* The BallWorld application from Homework 2 now has increased functionality! 

# How to Run application (assuming the project is already pulled down from svn)
1. After starting the application (Ctrl+F11) there are a couple of things that can be done:
	1.1. Populating the Droplists
		1.1.1. To populate the Droplists with various ball (strategy) types, simply type the name of the strategy (e.g. "Straight") into the textField and press "Add to Lists"
			- The possible strategy types are as follows:
				1. "Straight" (A simple bouncing ball)
				2. "Disappear" (A ball that will disappear and re-appear repeatedly)
				3. "ColorChanging" (A ball that will change color repeatedly)
				4. "Curve" (A ball that will curve a random about while maintaining "forward" motion)
				5. "Spin" (A ball that will spin in a vortex-like pattern using sinusoidal waves as a base)
				6. "Breathing" (A ball that will increase and decree its radius repeatedly)
				7. "Wander" (A ball that will wander aimlessly inside the canvas)
	1.2. Generating General Balls
		1.2.1. To generate a ball of a certain type, simply select the strategy from the first (top) Droplist and press "Make Selected Ball"
			note: If the "Make Selected Ball" button is pressed before selecting a ball in the Droplist, nothing will occur
	1.3. Combining Ball Strategies
		1.3.1. If the user wishes to do so, they can also combine the two separate ball strategies to create a hybrid strategy.
			- This is done by (after step 1.1.1. is completed) selecting which two strategies to combine by selecting them in the two Droplists
			- Then, after two strategies are selected, press the "Combine!" button
				note: If the combine button is pressed with either one or both of the Droplist fields not being populated (as they were in step 1.1.1.) the the application will create a simple bouncing ball with a placeholder as the name.
			- The hybrid strategy can then be selected in the first (top) Droplist, and then created by pressing "Make Selected Ball"
	1.4. Making & Controlling Switcher Balls
		1.4.1. The User also has the ability to create balls that can switch their strategy
			- This is done by first pressing the "Make Switcher" button. This will create a basic bouncing ball
			- Then, whenever the "Switch!" button is pressed, the Switcher ball will switch to whatever is currently in the first (top) Droplist
				note: If the "Switch!" button is pressed before a Switcher ball has been made with "Make Switcher" or before a ball strategy has been selected in the Droplist, the program will return an error with detailed stack information (NULL Pointer Exception)
	1.5. Clearing Balls
		1.5.1 Finally, pressing the "ClearAll" button will completely wipe out all balls currently on the canvas.