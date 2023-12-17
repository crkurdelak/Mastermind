import java.util.*;

/**
 * Mastermind game
 * @author ckurdelak20@georgefox.edu
 */
public class Mastermind {

    /**
     * The main loop of the Mastermind game.
     * Generates a random 4-letter string using letters A-E, has the user guess the string, and
     * output how many correct letters and how many correct positions until the user guessees
     * correctly.
     * @param args empty
     */
    public static void main(String[] args) {
        boolean playAgain = true;
        boolean guessedCorrectly = false;
        int guesses = 0;
        while (playAgain) {
            System.out.println("Welcome to Mastermind");
            // generate string
            String correctAnswer = generateString();
            // print string
            System.out.println("(" + correctAnswer + ")");

            // get user guess
            String userGuess = getUserGuess();
            guesses ++;

            // for testing purposes
            System.out.println(userGuess);

            while (!guessedCorrectly) {
                // check for correct letters and positions
                guessedCorrectly = checkAnswer(correctAnswer, userGuess);
                if (! guessedCorrectly) {
                    userGuess = getUserGuess();
                    guesses ++;
                }
            }
            System.out.println("Great Job, It took you only " + guesses + " guesses!");

            // get user play again input
            playAgain = getPlayAgain();
            guesses = 0;
        }
        System.out.println("Thanks for Playing");
    }


    /**
     * Gets a 4-character string from the user containing only letters A-E.
     * @return A 4-character string containing only characters A-E
     */
    public static String getUserGuess() {
        String userGuess = "";
        boolean validGuess = false;
        Scanner sc = new Scanner(System.in);
        while (!validGuess) {
            // prompt user
            System.out.print("Guess a 4-letter string using only A-E: ");
            userGuess = sc.next().toUpperCase();
            // if length < 4, prompt again
            if (userGuess.length() == 4) {
                // check if the characters are valid (A-E)
                int numInvalid = 0;
                for (int i = 0; i < userGuess.length(); i++) {
                    char currentLetter = userGuess.charAt(i);
                    if (currentLetter != 'A'
                        && currentLetter != 'B'
                        && currentLetter != 'C'
                        && currentLetter != 'D') {
                        numInvalid ++;
                    }
                }
                if (numInvalid == 0) {
                    validGuess = true;
                }
            }
        }
        return userGuess;
    }


    /**
     * Prompts the user if they want to play again and returns their answer.
     * @return true or false
     */
    public static boolean getPlayAgain() {
        boolean playAgain = false;
        String YN = "";
        boolean validYN = false;
        Scanner sc = new Scanner(System.in);

        while (!validYN) {
            System.out.print("Do you want to play again(Y/N)? ");
            YN = sc.next().toUpperCase();
            if (YN.equals("Y")) {
                validYN = true;
                playAgain = true;
            }
            else if (YN.equals("N")) {
                validYN = true;
                // playAgain remains false
            }
        }
        return playAgain;
    }


    /**
     * Generates a random 4-character string using letters A-E.
     * @return a random 4-character string using letters A-E
     */
    public static String generateString() {
        int leftLimit = 65;
        int rightLimit = 68;
        int stringLength = 4;
        Random rand = new Random();

        return rand.ints(leftLimit, rightLimit + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


    /**
     * Checks the correctness of the user's guess, prints the number of correct letters and
     * the number of correct positions, and returns true if the guess is correct and false if it
     * was incorrect.
     * @param correctAnswer the correct answer
     * @param userGuess the user's guess
     * @return true if the guess is correct; else false
     */
    public static boolean checkAnswer(String correctAnswer, String userGuess) {
        boolean isCorrect = false;
        int numCorrectLetters = 0;
        int numCorrectPositions = 0;

        // iterate over strings and compare characters
        // iterate over guess
        for (int i = 0; i < userGuess.length(); i++) {
            char guessLetter = userGuess.charAt(i);

            // iterate over correct answer
            for (int j = 0; j < correctAnswer.length(); j++) {
                char correctLetter = correctAnswer.charAt(j);

                // check if guess letter is any of the letters of the correct answer
                if (guessLetter == correctLetter) {
                    numCorrectLetters ++;
                    // TODO fix bug where letters are being counted multiple times

                    // check if the letter is also in the correct position
                    if (i == j) {
                        numCorrectPositions ++;
                    }
                }
            }
        }

        System.out.println("Correct Letters: " + numCorrectLetters);
        System.out.println("Correct Positions: " + numCorrectPositions);

        if (numCorrectPositions == 4) {
            isCorrect = true;
        }

        return isCorrect;
    }

}
