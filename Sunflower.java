import javax.swing.Timer;

public class Sunflower extends gamePlay {
    private int counter;
    private boolean collect;
    private static final int money = 25;

    public Sunflower() {
    	counter = 3;
        this.collect = false;
    }
    
    //Generates a sun after the counter is 0
    public void generateSun() {
        if (counter == 0) {
            System.out.println("Collect the Sun");
            this.collect = true;
        }
    }
    
    //returns the money generated by the sunflower
    public int collectSun(){
        this.collect = false;
        return money;
    }
    
    public boolean isCollect() {
        return collect;
    }
    
    public static void main(){
        
    }
}
