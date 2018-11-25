/**
 * Normal zombie class
 * @author sarahlamonica
 *
 */
public class NormalZombie extends Zombie {

	public NormalZombie(int positionX, int positionY, boolean isEaten, int damagePoints, char zombieType, boolean isWalnut) {
		super(positionX, positionY, isEaten, damagePoints, zombieType, isWalnut);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Move function
	 */
	public void move()
	{
		super.setPositionY(super.getPositionY() - 1);
	}

	/**
	 * Gets the type of zombie
	 */
	public char getType()
	{
		return super.getType();
	}
}
