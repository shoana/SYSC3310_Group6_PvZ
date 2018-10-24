import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;


public class gamePlay {
	
	private int nRows, nColumns; //GRID DIMENSIONS
	private char grid[][]; //EMPTY, PLANT, SUNFLOWER OR ZOMBIE
	private gameEnum gameState;
	private static int sunshine; // sunshine to be used as currency to purchase sunflowers, peashooters
	private static final int plantToZombieLength = 3; //Plant must be >= 3 steps away from zombie or else it will eat it.
	private char plantType;
	private ArrayList<Peashooter> peashooters = new ArrayList<Peashooter>();
	private ArrayList<Sunflower> sunflowers = new ArrayList<Sunflower>();
	
	/**
	 * Constructor for the game play
	 * @param nRows
	 * @param nColumns
	 * @param sunshine
	 */
	public gamePlay(int nRows, int nColumns, int sunshine)
	{
		if (nRows < 0 || nColumns < 0) 
            throw new IllegalArgumentException("Grid must be a positive size");
		
		this.nRows = nRows;
		this.nColumns = nColumns;
		gamePlay.sunshine = sunshine;
		this.plantType = 'p';
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
	 * @param row
	 * @param column
	 * @param plantType
	 * @return
	 */
	public gameEnum plantTurn(int row, int column, char plantType)
	{
		gameState = gameEnum.PLANT_TIME;
		
			
			if(sunshine > 100) {
				this.grid[row][column] = plantType;
				System.out.println(toString());
				
				if(plantType == 'p')
				{
					sunshine -= 100;
					Peashooter p = new Peashooter(100);
					peashooters.add(p);
					
					
				}
				if(plantType == 's')
				{
					sunshine -= 200;
					Sunflower s = new Sunflower(200);
					sunflowers.add(s);
				}
				
				
			}
			else {
				System.out.println("You don't have enough sunshine");
			}
			
			
		
		
		this.gameState = plantOrZombie();
		return this.gameState;
	}
	
	/**
	 * Finding if the plant is < 2 away from the zombie
	 * If plant is < 2 away, then the zombie will get killed
	 * @param row
	 * @param column
	 * @return
	 */
	public gameEnum plantOrZombie()
	{
		int count; //finding if the zombie is less than 3 away
		return gameEnum.PLANT_TIME;
	}
	
	 public char getPlantType() {
	   return this.plantType;
	 }
	 
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
	 
	 public void setPlantType(char s)
	 {
		 this.plantType = s;
	 }
	 public gameEnum getGameState() {
	    return this.gameState;
	 }
	 
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
	        gamePlay game = new gamePlay(4,4,300);
	        Scanner scanner = new Scanner(System.in);
	        
	        do { 
	        		
				System.out.println("Sunshine: " + sunshine + "\nPEASHOOTER PRICE: 100 -  SUNFLOWER PRICE: 200" );
				System.out.println("Choose your plant type. You have " + sunshine + " Sunshines");
				char plantType = scanner.next().charAt(0);
				if(plantType == 's' || plantType == 'p')
				{
					game.setPlantType(plantType);
				
				
					System.out.println("Where do you want to put your " + game.charToPlantType(plantType) +"? Enter row column");
					int row = scanner.nextInt();
					int column = scanner.nextInt();
					scanner.nextLine();
	            
					if(row < 4 && row >= 0 && column < 4 && column >= 0) {
	            			game.plantTurn(row, column, plantType);
	            		
					}
					else
					{
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
