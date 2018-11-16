# SYSC3310_Group6_PvZ -- Milestone 2

Authors:
- Sarah Lamonica
- Shoana Sharma 
- Fatima Hashi 
- Mounica Pillarisetty

Plants vs. Zombies

Milestone 1 feedback was given at 11:40pm on Thursday, Nov 16th 2018. Therefore, we could not implement all required changes from the feedback file for this milestone, however we will work on adding them to the next iteration (due Nov. 25th)

Milestone 2: 
- This milestone is a visual game rather than a text-base game
- GUI-based version (with the View and the Controller) of the bare-bones version of the game 
- Utilized the Event Model design pattern to ensure the game followed the previous iteration of the game (loop-based)
- Unit tests for the Model (gamePlay class)
- Game includes sunflowers, one other type of plant (peashooters), one type of zombie at Level 1
- UML models (class & sequence diagrams)

=================================================================================================
 Requirements:
 - Peashooter
 - Sunflowers
 - Zombies
 - Sunshine(Money)
 - Type of zombie the user wants to fight (NOT IMPLEMENTED IN MILESTONE 2)
 - Zombies Life Bar (NOT IMPLEMENTED IN MILESTONE 2)
 - Lawn Mower (lives) (NOT IMPLEMENTED IN MILESTONE 2)

=================================================================================================
LEVEL ONE 
- grid = JButton board[column][row]
- bar increses as zombies decreases (NOT IMPLEMENTED IN MILESTONE 2)
- sunshine counter -- used like money
- game state: zombie state, plant state
- plant state: sunflower and peashooter
- planting sunflowers allow users to gain more sunshine
- lawnMower = number of rows (NOT IMPLEMENTED IN MILESTONE 2)
- enum: different states of the game

=================================================================================================
Classes:
- Plants
   - sunflowers
   - peashooter
- Zombie
- gamePlay (model)
- View 
- Controller
- App -- the main of the game, that should be run to play the game
- gameEnum -- the states of the game

=================================================================================================
Change Log:
- The user will be given a 1000 sunshines to start the game rather than 300. It will be 
=================================================================================================
User-visible Changes:
- In this milestone the game has become a visul rather than a textual base
- JFrame is used as the display and GridLayout using JButton is used for choosing and placing plant
- At the bottom of the frame there is a status update of the game, tell you how many zombies and plants have been killed
- There is a live counter used for money (sunshines) that changes after every move
- There are pop-ups to collect suns, to notify if a game is finshed (either the plants or the zombies win)

=================================================================================================
Known Errors:
- Since the the GUI is event diven, the updation of the amount of sunshine lags by one frame
    - will be fixed for the next milestone by adding event and action listener
- The movement of zombies is one frame behind as well, which sometimes causes the status of the game to not match the actaul game
- 
-
=================================================================================================
Roadmap Ahead:
- Milestone 3: Complete implementation of the game which includes multiple types of zombies, plants, etc. 
To complete this, we will be utilizing inheritance to group plants and zombies together. They will each have their own behaviour but will act similar on the grid and in the game.
We will also include the types of zombies that the user wants to fight and 
- Milestone 4: Save/Load features. 