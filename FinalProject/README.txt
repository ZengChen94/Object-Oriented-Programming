Please refer to the google doc for FinalProject MileStone2: (Rice Email Account Only)
[Doc]-- https://docs.google.com/a/rice.edu/document/d/1_-dPsCI_IXG7o6IkcD_7Y9q_lkWS48VCNo1kzGYNlEg/edit?usp=sharing
[Slides]-- https://docs.google.com/a/rice.edu/presentation/d/1dyPn4lk0BIy2wMA9jzdojiVKmroYAgYsHHSwoyjSQcY/edit?usp=sharing

***Milestone 2:
[client and server are modified from HW08]
[game is inspired from popular Real-Time Strategy Game]

Question1: Fully detailed and accurate UML diagram and operational description from Milestone 1 with any changes and additions as were done since then.  (5 pts)
	Please refer to ./uml_png/.*.umlcd.png or ./src/.*.umlcd
	The changes and additions from Milestion 1 is that it adds the implementation of Game and Server.

Question2: Full Javadocs, this time including descriptions for all methods with input parameters and all fields.  (5 pts)
	Please refer to: http://comp504_final_cz39_zx18.surge.sh/

Question3: [User¡¯s Manual] How to start the program, both the program itself as well as any individual games or processes. (3 pts)
	Step1: Running Controller in Client and Server, with run Client and start Client first, run Server then
		Each player run their client, which is similar to HW08 and the chatroom is implemented using the API of this time. 
		At the same time, server should run ServerController in the directory ./cz39_zx18/server/mvc.
		If you want to demo the Client and Server all at your own PC, please run Client and start the Client first, and then run the Server.
	Step2: Join Lobby
		In the client, users connect to IP of server and request for the Lobby, which is a chatroom created by the server.
	Step3: Choose Team
		As all players are connected the server and have joined in the lobby, we can click the button on the GUI of server to send team choosing GUI in the chatroom Lobby.
		In the team choosing GUI, the users can either choose teams have been selected or create a new team and then join in it (finally we don't implement choose team).
	Step4: Send Game
		Having confirmed that all players have selected team, the server can now send game to each user and the game can now start. Enjoy it! 
	Step5: Enjoy Game:
		The rules are included in the following online slides prepared for demo: https://docs.google.com/a/rice.edu/presentation/d/1dyPn4lk0BIy2wMA9jzdojiVKmroYAgYsHHSwoyjSQcY/edit?usp=sharing 

Question4: [User¡¯s Manual] How to connect to other users (3 pts)
	The users are all connected to server by binding the server¡¯s IP address. It¡¯s designed that they are all added to a Lobby, which can also be treated as a chatroom. And in this Lobby room, all players can communicate.
	After the user choosing corresponding team, they are added to the chatroom of this team. And in this chatroom, teammates can chat. 
	To conclude, all teammates are connected in the chatroom of corresponding team and all players are connected in the Lobby.

Question5: [User¡¯s Manual] How to use every feature of the program.  (6 pts)
	The game is based on round and in each round, the user who operate first will take action first. If the time goes to 1min and there are still users who haven¡¯t operated, the operations of this round will be auto-calculated by user and won¡¯t wait for that users.
	In each round, user can choose move to North, South, West, and East with 4 units. User can also choose not move but to attack some position. If the target attacked is very close to some user, then that player is out. And the attack effect both enemies and teammates. 
	The player can click on the annotation of other users on the map to get their location and then they can decide to attack which location. Maybe this process may need their prediction of where of players may go to.
	The user can talk in team within the team selection GUI.
	The user can talk in world within the Lobby.
	The information block can show which operation other users take in the last round and user can refer to that for decision in this round.
	More details can be found in the following online slides prepared for demo day: https://docs.google.com/a/rice.edu/presentation/d/1dyPn4lk0BIy2wMA9jzdojiVKmroYAgYsHHSwoyjSQcY/edit?usp=sharing 

Question6: [User¡¯s Manual] How to end and exit the program.  (3 pts)
	The player can also click on exit on the right-top of the GUI. Then this player will be treated as dead because it won't reply to server in many rounds.
	
Question7: System Architecture (5 pts)
	Our final project is involved with Inheritance-based, Command-Driven, Message-passing, Visitors Design Pattern, etc. This system is designed in accordance with the principles emphasized in the course.

At last, I want to appreciate to all of the instructors, TAs, classmates, and those who help us during the study in COMP504 and the debug process in the final project. Without you all, we can never do it. 

--------------------------------------This is a dividing line--------------------------------------

Please refer to the google doc for FinalProject MileStone1: (Rice Email Account Only)
https://docs.google.com/a/rice.edu/document/d/14Pip3sP6VDrHNpfTw3uCk9JOagqQ2Aj9gIvH2KckwCs/edit?usp=sharing

***MILESTONE 1:

Question 1: 
	Prose description of how your game will work, including typical game play with winning and losing. (35 pts)
Name of Proposed Game: Hit or Move
	Game Play: It¡¯s a round-based game. Initially, every member in every team are located in some designed GIS-location and can be viewed by all the players. Also, all of the players know a target they are required to arrive.  In every round, every alive can take one action between two operations, hit or move. Hit, which means you can choose a GIS-location to attack, and if there is army there, it will dead. Move, which means you can step in one of the North, East, West, and South with designed distance, for example 1 km. After the operation of every alive players in the round, the calculation work will decide the condition of every players in the beginning of next round. Someone may die, and someone may move to another position. In the end, the team whose team member firstly arrive at the target will win.
Definition of 
	Win and Lose:
		Win: The team who arrive at the destination first.
		Lose: The team whose members all die.

Question 2: UML diagram of the interfaces and implementing classes. (5 pts)
	Please refer to ./uml_png/FinalProject.umlcd.png or ./FinalProject.umlcd 

Question 3: A prose description of how your design will implement the required common API as well as any features specific to your implementation. The above UML diagram may be imbedded in this document if desired (recommended!). (20 pts) 
The implementation is very similar to HW8 (that's why we directly use some API of GroupC) and it can work as the following: 
	User starts MainApp by input the user name. Then it will be connected to the corresponding game server. And the game server will return part of the game component to the player, which lead the player to select teams.
User creates / joins a team (chatroom) in the game lobby. Case1: The player may create a new team. Case2: The player may input a remote user IP address to get team list And then user choose a team from the team list to join in.
User starts game
	The user who starts the game will send a datapacket to the game server and if all players are ready, the game server will react.
Remote game server uses a command to handle this message. The controller of Game MVC will be instantiated in the process and Game MVC will have an adapter to the user who starts the game. It means that players may get the component of game GUI.
There will be adapters between team and Remote game MVC. This will help players and teams to control the game.
User plays game
	The user will send datapacket to the server and then server will broadcast another datapacket back to each player.
The user can send MessageDatapacket in the team. This is as same as the last homework assignment.

Question 4: Javadocs for the classes and interfaces in the UML diagram. A minimum detail level of class/interface and method descriptions is required. Descriptions of fields and input parameters is recommended but not required. The Javadocs should be in the form of HTML web pages so they can be easily read, not just the comments in the code. (15 pts)
	Please refer to: http://comp504_final_cz39_zx18.surge.sh/ 

Question 5: The submitted code should have a minimum functionality of being able to connect to other users and display a map plus any agreed upon functionality as detailed in the Piazza discussions, if any. Since the Final Project API will not be finalized by the Milestone 1 due date, functionality should be achieved using the team's Final Project Design Group's API. (15 pts)
	Please refer to: https://svn.rice.edu/r/comp504/turnin/F17/cz39/FinalProject/ 
	Running cz39_zx18.mvc.controller.MainController, users can communicate between clients, just like the operations in HW08.
	Clicking Open Map in MainController, users can use the map in the game GUI which will be the component sent by game server in the later work.
