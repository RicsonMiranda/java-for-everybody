import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessGame {

    // Constants for the min range
    private static final int MIN = 1;

    // MAX cannot be final if it will change → use non-final variable
    private static int maxRange = 100;

    public static void RunGuessGame(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            System.out.println("Choose a difficulty level: ");
            System.out.println("1. Easy (1-50)");
            System.out.println("2. Medium (1-100)");
            System.out.println("3. Hard (1-200)");

            int difficulty;
            try {
                difficulty = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Defaulting to Medium.");
                difficulty = 2;
                input.next(); // clear invalid token
            }

            switch (difficulty) {
                case 1:
                    maxRange = 50;
                    break;
                case 2:
                    maxRange = 100;
                    break;
                case 3:
                    maxRange = 200;
                    break;
                default:
                    System.out.println("Invalid choice. Defaulting to Medium.");
                    maxRange = 100;
            }

            // Main game loop
            while (true) {
                playGame(input);
                if (!playAgain(input)) {
                    return;
                }
            }

        } // scanner closed automatically here
    }

    /**
     * Runs the guessing portion of the game.
     */
    private static void playGame(Scanner input) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(maxRange - MIN + 1) + MIN;

        int numGuesses = 0;
        boolean correct = false;

        System.out.print("Guess a number between " + MIN + " and " + maxRange + ": ");

        while (!correct) {
            try {
                int guess = input.nextInt();
                numGuesses++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number in " + numGuesses + " guesses.");
                    correct = true;
                } else if (guess < randomNumber) {
                    System.out.print("Too low, try again: ");
                } else {
                    System.out.print("Too high, try again: ");
                }

            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number between " + MIN + " and " + maxRange + ": ");
                input.next(); // clear invalid input
            }
        }
    }

    /**
     * Asks the user if they want to play again.
     */
    private static boolean playAgain(Scanner input) {
        while (true) {
            System.out.print("Do you want to play again? (yes/no): ");
            String response = input.next().trim().toLowerCase();

            if (response.equals("yes") || response.equals("y")) {
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid response. Please type 'yes' or 'no'.");
            }
        }
    }
}
