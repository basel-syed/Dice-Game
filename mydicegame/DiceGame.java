import java.util.Scanner;
/**
 * Class for mydicegame.DiceGame
 * @author Basel Syed
 * @version 0.1
 * Student Number: 101173816
 *
 */
public class DiceGame {
    /**
     * Attributes for the class
     */
    private int numberOfDice;
    private int numberOfSides;
    private int userBalance;
    private int computerBalance;
    private Die dice;

    /**
     * Default constructor for mydicegame.DiceGame
     */
    public DiceGame(){
        this.numberOfDice = 2;
        this.numberOfSides = 6;
        this.userBalance = 100;
        this.computerBalance = 100;
        this.dice = new Die();
    }

    /**
     * Constructor for mydicegame.DiceGame, for if the user wants to use custom values
     * for the number of dice anmd the number of sides of the dice.
     * @param numberOfDice integer representing the number of dice
     * @param numberOfSides integer representing the number of sides of the die
     */
    public DiceGame(int numberOfDice, int numberOfSides){
        this.numberOfDice = numberOfDice;
        this.numberOfSides = numberOfSides;
        this.userBalance = 100;
        this.computerBalance = 100;
        this.dice = new Die(numberOfSides);
    }

    /**
     * Method to play the dice game.
     */
    public void play(){
        System.out.println("Welcome to the Game of Dice!\nStarting a game with "+ this.numberOfDice + " "
                +this.numberOfSides + "-sided dice.");

        Scanner in = new Scanner(System.in);
        int wager;
        int[] userRolls = new int[numberOfDice];
        int[] computerRolls = new int[numberOfDice];
        int userRolllSum;
        int compRollSum;
        do{
            userRolllSum = 0;
            compRollSum = 0;
            if(userBalance == 0){
                System.out.println("Oh no, you're out  of credit!\nGood Bye!");
                return;
            } else if (computerBalance == 0){
                System.out.println("You won the game!\nGood Bye!");
                return;
            }
            System.out.println("------------------------------\nYou have $"+ this.userBalance);
            System.out.println("The computer has $ " + this.computerBalance);
            System.out.print("Place your bet (-1 to quit playing) >>> ");
            wager = in.nextInt();
            if(wager != -1){
                if(wager > userBalance || wager < 0){
                    System.out.println("You have to bet a positive value, but less than your remaining money!");
                }
                else{
                    for(int i = 0; i < numberOfDice; i++){
                        userRolls[i] = this.dice.roll();
                        computerRolls[i] = this.dice.roll();
                        userRolllSum += userRolls[i];
                        compRollSum += computerRolls[i];
                    }
                    System.out.print(numberOfDice + " Dice :: You rolled >{");
                    for(int i : userRolls){
                        System.out.print(" " + i + " ");
                    }
                    System.out.println("}");
                    System.out.print(numberOfDice + " Dice :: Comp rolled >{");
                    for(int j : computerRolls){
                        System.out.print(" " + j + " ");
                    }
                    System.out.println("}");
                    if(userRolllSum > compRollSum){
                        System.out.println("You win this round!");
                        if(wager > computerBalance){
                            userBalance += computerBalance;
                            computerBalance = 0;
                        } else{
                            userBalance += wager;
                            computerBalance -= wager;
                        }
                    }else if(compRollSum > userRolllSum){
                        System.out.println("The computer wins this round!");
                        userBalance -= wager;
                        computerBalance += wager;
                    } else {
                        System.out.println("This round was a tie!");
                    }

                }
            }
        }while(wager != -1);
        System.out.println("Goodbye!");
    }

    /**
     * The main method of the program.
     * @param args an array of Strings representing custom values the user might use for the dice
     */
    public static void main(String[] args) {
        DiceGame newGame;
        try {
            if (args.length != 2 && args.length != 0){
                throw new Exception("Invalid number of arguments: "+ args.length +". Enter 2 arguments to use custom "
                        +"values for the number of dice and number of sides for the dice.");
            }

            newGame = new DiceGame();
            if(args.length == 2){
                
                if (Integer.valueOf(args[0]) < 1){
                    throw new Exception("Invalid argument: " + args[0] + ". Number of dice must be greater than 0.");
                }
                if (Integer.valueOf(args[1]) < 2){
                    throw new Exception("Invalid argument: " + args[1] + ". Number of sides can not be less than 2.");
                }
                newGame = new DiceGame(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
            }

            newGame.play();
        }
        catch(Exception e){
            System.out.println("Please only include valid ints in the argument");
        }


    }
}
