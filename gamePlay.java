package del1;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


public class gamePlay {

	private int nRows, nColumns; //GRID DIMENSIONS
	private char grid[][]; //EMPTY, PLANT, SUNFLOWER OR ZOMBIE
	private gameEnum gameState; // holds the state of the game
	private static int sunshine; // sunshine to be used as currency to purchase sunflowers, peashooters
	private static final int plantToZombieLength = 3; //Plant must be >= 3 steps away from zombie or else it will eat it.
	private static char plantType;
	private static int nTurns = 0;
	private static ArrayList<Peashooter> peashooters = new ArrayList<Peashooter>();
	private static ArrayList<Sunflower> sunflowers = new ArrayList<Sunflower>();
	private static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	private int plantsEaten = 0; // counter to keep track of the number of plants that are eaten by the zombies
	private int zombiesEaten = 0; // counter to keep track of the number of zombies that are killed

	/**
	 * Constructor for the game play
	 * @param int nRows
	 * @param int nColumns
	 * @param int sunshine
	 * @throws IllegalArgumentException is the <row,column> is out of range or if the location is not adjacent to a filled lower spot
	 */
	public gamePlay(int nRows, int nColumns, int sunshine)
	{
		if (nRows < 0 || nColumns < 0) 
			throw new IllegalArgumentException("Grid must be a positive size");

		this.nRows = nRows;
		this.nColumns = nColumns;
		gamePlay.sunshine = sunshine;
		plantType = 'p';
		this.gameState = gameEnum.PLANT_TIME;
		this.grid = new char[nRows][nColumns];

	}

	/**
	 * Reset the game back to original parameters.
	 */
	public void reset()
	{
		for (int r=0;r<nRows; r++) {
			for (int c=0; c<nColumns; c++) {
				this.grid[r][c] = ' ';
			}
		}

		gamePlay.sunshine = 0;
		this.gameState = gameEnum.PLANT_TIME;
	}

	/**
	 * Code for the plant's turn 
	 * @param int row
	 * @param int column
	 * @param char plantType
	 * @return
	 */
	public gameEnum plantTurn(int row, int column, char plantType)
	{
		gameState = gameEnum.PLANT_TIME;
		Scanner scanner = new Scanner(System.in);

		if(sunshine >= 100) {
			this.grid[row][column] = plantType;
			nTurns++;
			System.out.println(toString());

			if(plantType == 'p')
			{
				sunshine -= 100;
				Peashooter p = new Peashooter(100, row, column);
				peashooters.add(p);


			}
			if(plantType == 's')
			{
				System.out.println(" Do you want to collect the sun? (y/n) ");
				char sun = scanner.next().charAt(0);
				if(sun == 'y'){
					sunshine += 100;
				}
				sunshine -= 50;
				Sunflower s = new Sunflower(200, row, column);
				sunflowers.add(s);
			}


		}
		else {
			System.out.println("You don't have enough sunshine");
		}

		setGameState(gameEnum.PLANT_TIME);
		return this.gameState;
	}
	/**
	 * Code for zombie time. It allows the user to know when the zombies start appearing
	 * @param int numZombies
	 */
	public void zombieTime(int numZombies)
	{

		System.out.println(" ");
		System.out.println("=======================");
		System.out.println("ZOMBIES ARE ATTACKING!");
		System.out.println("Peashooters are shooting!");
		System.out.println("=======================");
		System.out.println(" ");
		Random r = new Random();

		if(zombiesEaten <= 1) {
			for(int i = 0; i < 1; i++)
			{
				int random = r.nextInt(nRows);
				//int random2 = 2;
				grid[random][(nRows - 1)] = 'z';
				//grid[random2][(nRows - 1)] = 'z';
				Zombie z = new Zombie(random, (nRows -1)); //Add a new zombie to the list
				//Zombie z2 = new Zombie(random2, (nRows - 1));
				zombies.add(z);
				//zombies.add(z2);
			}
		}


		System.out.println(toString());
		gameState = plantOrZombie();

	}

	/**
	 * Finding if the plant is < 2 away from the zombie
	 * If plant is < 2 away, then the zombie will get killed
	 * @param int row
	 * @param int column
	 * @return
	 */
	public gameEnum plantOrZombie()
	{

		//Keeping track of how many zombies and plants have died for game play


		//EDGE CASES:
		//If there are no peashooters left and zombies are still left, zombies win
		if((peashooters.size() == 0 && sunflowers.size() == 0 && zombies.size() > 0))
		{

			return gameEnum.ZOMBIES_WIN;
		}

		//If there are no zombies left, peashooters automatically win
		if(zombiesEaten > 1)
		{
			//System.out.println("sadjasd");
			System.out.println(toString());
			return gameEnum.PLANTS_WIN;
		}


		//Iterator<Zombie> it2 = zombies.iterator();

		for(Iterator<Peashooter> it = peashooters.iterator(); it.hasNext();)
		{
			Peashooter p = it.next();


			for(Iterator<Zombie> it2 = zombies.iterator(); it2.hasNext();)
			{
				Zombie b = it2.next();


				if(b.getPositionY() == 0)
				{
					return gameEnum.ZOMBIES_WIN;
				}

				if((p.getPositionX() == b.getPositionX()))
				{
					if(((b.getPositionY() - p.getPositionY())) < 3)
					{
						//System.out.println("REACHED");
						it.remove();
						grid[p.getPositionX()][p.getPositionY()] = ' ';
						b.setPositionY(b.getPositionY() - 1);
						grid[b.getPositionX()][b.getPositionY()] = 'z';
						plantsEaten++;
					}
					else
					{
						it2.remove();
						grid[b.getPositionX()][b.getPositionY()] = ' ';
						zombiesEaten++;
					}

				}
			}
		}

		Iterator<Sunflower> it3 = sunflowers.iterator();
		Iterator<Zombie> it4 = zombies.iterator();

		while(it3.hasNext()) {
			Sunflower p = it3.next();

			while(it4.hasNext())
			{

				Zombie b = it4.next();

				if(b.getPositionY() == 0)
				{
					return gameEnum.ZOMBIES_WIN;
				}

				if((p.getPositionX() ==  b.getPositionX() ))
				{
					if(((b.getPositionY() - p.getPositionY())) < 3)
					{
						it3.remove();
						grid[p.getPositionX()][p.getPositionY()] = ' ';
						b.setPositionY(b.getPositionY() - 1);
						grid[b.getPositionX()][b.getPositionY()] = 'z';
						plantsEaten++;
					}
					else
					{
						it4.remove();
						grid[b.getPositionX()][b.getPositionY()] = ' ';
						zombiesEaten++;
					}
				}

			}
		}


		if(zombiesEaten > 1)
		{
			System.out.println(toString());
			return gameEnum.PLANTS_WIN;
		}
		//EDGE CASES:
		//If there are no peashooters left and zombies are still left, zombies win
		if((peashooters.size() == 0 && sunflowers.size() == 0 && zombies.size() > 0))
		{

			return gameEnum.ZOMBIES_WIN;
		}


		System.out.println(plantsEaten + " Plants have been eaten!");
		System.out.println(zombiesEaten + " Zombies have been destroyed!");



		//No winner, keep playing and zombies move up
		return gameEnum.PLANT_TIME;

	}

	/**
	 * A getter to get the type of plant that is being placed into the grid
	 * @return char
	 */
	public static char getPlantType() {
		return plantType;
	}
	
	/**
	 * a setter for the plant type
	 * @param char s
	 */
	public void setPlantType(char s) {
		this.plantType = s;
	}

	/**
	 * it changes the plant type from a char to a string 
	 * @param cahr s
	 * @return String
	 */
	public String charToPlantType(char s)
	{
		String plant = "";
		if(s == 's')
		{
			plant += "Sunflower";
		}

		if(s == 'p')
		{
			plant += "Peashooter";
		}

		return plant;
	}

	/**
	 * a getter that returns the state of the game
	 * @return 
	 */
	public gameEnum getGameState() {
		return this.gameState;
	}

	/**
	 * a setter that sets the state of the game
	 * @return 
	 */
	public void setGameState(gameEnum g) {
		this.gameState = g;
	}
	
	/**
	 * a toSring method ...............................
	 * @return String
	 */
	public String toString() {
	
		String s = "";
		for (int r=0;r < nRows; r++ ) {
			for (int c=0; c < nColumns; c++) {
				s += grid[r][c] + " | ";
			}
			s += "\n";
		}
		return s;
	}

	public static void main(String args[]) {
		gamePlay game = new gamePlay(6,6,200);
		Scanner scanner = new Scanner(System.in);

		do { 
			System.out.println("Sunshine: " + sunshine + "\nPEASHOOTER PRICE: 100\nSUNFLOWER PRICE: 200" );
			System.out.println("Choose your plant type. You have " + sunshine + " Sunshines");
			char plantType = scanner.next().charAt(0);
			if(plantType == 's' || plantType == 'p'){
				
				game.setPlantType(plantType);
				System.out.println("Where do you want to put your " + game.charToPlantType(plantType) +"? Enter row column");
				int row = scanner.nextInt();
				int column = scanner.nextInt();
				scanner.nextLine();
				if(row < 6 && row >= 0 && column < 6 && column >= 0) {
					game.plantTurn(row, column, plantType);
					if(nTurns - 2 == 0) {
						game.zombieTime(2);
						nTurns = 0;
					}
				}
				else {
					System.out.println("Invalid input. please choose another location");
				}
			}

			else {
				System.out.println("Invalid input. Please choose 'p' or 's'");
			}

		} while (game.getGameState() == gameEnum.PLANT_TIME);
		System.out.println( game.getGameState());

	}

}
