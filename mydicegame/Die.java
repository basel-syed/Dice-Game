import java.util.Random;

/**
 * Class for a dice
 * @author Basel Syed
 * @version 0.1
 * Student Number: 101173816
 */
public class Die {
    /**
     * Attrubute for the class
     */
    private int numberOfSides;

    /**
     * Default constructor incase the user is not using custom values
     */
    public Die(){
        this.numberOfSides = 6;
    }

    /**
     * Constructor for if the user is using custom values
     * @param numberOfSides int representing the number of sides for the dice
     */
    public Die(int numberOfSides){
        this.numberOfSides = numberOfSides;
    }

    /**
     * Method to roll the dice
     * @return int represting the number rolled
     */
    public int roll(){
        Random randomInt = new Random();
        int outcome = randomInt.nextInt(numberOfSides) + 1;
        return outcome;
    }
}
