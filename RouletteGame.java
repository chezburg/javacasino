import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RouletteGame {
    private static final int money = 10000;
    private static int chips = money;
    private static final int number_win_multiplyer = 35;
    private static final int color_win_multiplyer = 2;
    private static final int oddeven_win_multiplyer_multiplyer = 2;
    private static final String[] COLORS = { "Red", "Black" };
    private static final Random RANDOM = new Random();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Roulette");
        boolean replay = true;

        while (replay) {
            if (chips <= 0) {
                System.out.println("You're poor. You have " + chips + " chips left.");
                replay = false;
                break;
            } else {
                System.out.println("You have " + chips + " chips.");
                System.out.println("");
                System.out.println("Place your bet:");
                System.out.println("1. Bet on a number (0-36)");
                System.out.println("2. Bet on a color (Red or Black)");
                System.out.println("3. Bet on odd or even");
                System.out.println("4. Quit");
                System.out.println("5. Display chips");

                int betType = getIntInput();

                switch (betType) {
                    case 1:
                        betOnNumber();
                        break;
                    case 2:
                        betOnColor();
                        break;
                    case 3:
                        betOnOddEven();
                        break;
                    case 4:
                        quit();
                        break;
                    case 5:
                        displaychips();
                        break;

                    default:
                        System.out.println("Invalid bet type.");
                }
            }

            System.out.println("Do you want to play again? (yes/no)");
            String response = in.next();
            replay = !response.equalsIgnoreCase("no") && !response.equalsIgnoreCase("n");
        }

        System.out.println("Thank you for playing!");
    }

    private static void displaychips() {
        System.out.println("You have " + chips + " chips");
    }

    private static void quit() {
        System.out.println("Thank you for playing!");
        System.exit(0);
    }

    private static int getIntInput() {
        // Get integer input with error handling
        int input = -1;
        boolean valid = false;
        while (!valid) {
            try {
                input = in.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                in.next(); // Clear the invalid input
            }
        }
        return input;
    }

    private static int spinRoulette() {
        // Simulate spinning the roulette and return the winning number
        try {
            // Generate a random number between 0 and 36
            int number = RANDOM.nextInt(37);

            // Simulate the spinning effect with delays
            System.out.println("The roulette spins...");
            Thread.sleep(1000);
            System.out.println(".");
            Thread.sleep(1000);
            System.out.println(".");
            Thread.sleep(1000);
            System.out.println(".");
            Thread.sleep(1000);

            // Print the final result
            System.out.println("And lands on " + number + " (" + getColor(number) + ")");
            return number;
        } catch (InterruptedException e) {
            System.out.println("The coundown somehow broke");
            return -1; // Return an error value or handle it as needed
        }
    }

    private static String getColor(int number) {
        // Determine the color of the given roulette number
        if (number == 0) {
            return "Green";
        } else if (number % 2 == 0) {
            return COLORS[1]; // Black
        } else {
            return COLORS[0]; // Red
        }
    }

    private static void betOnNumber() {
        // Handle betting on a specific number
        System.out.println("Enter the number you want to bet on (0-36):");
        int betNumber = getIntInput();

        // Ensure the bet number is within the valid range
        while (betNumber < 0 || betNumber > 36) {
            System.out.println("Invalid number. Enter a number between 0 and 36:");
            betNumber = getIntInput();
        }

        // Prompt for the bet amount
        System.out.println("Enter your bet amount: ");
        System.out.println("You have " + chips + " chips");
        int bet = in.nextInt();

        while (bet <= 0 || bet > chips) {
            if (bet <= 0) {
                System.out.println("Bet amount must be a positive integer. Enter your bet amount: ");

            } else {
                System.out.println("You can't afford that. Enter your actual bet amount: ");

            }
            bet = in.nextInt();
        }

        int winningNumber = spinRoulette();

        if (betNumber == winningNumber) {
            chips += number_win_multiplyer * bet;
            System.out.println("Congratulations you won " + bet + " chips.");
            System.out.println("You have " + chips + " chips");
        } else {
            chips -= bet;
            System.out.println("L, You lost");
            System.out.println("You have " + chips + " chips");
        }
    }

    private static void betOnColor() {
        // Handle betting on a specific color
        System.out.println("Enter the color you want to bet on (Red/Black): ");
        String betColor = in.next();

        // Ensure the bet color is valid
        while (!betColor.equalsIgnoreCase("Red") && !betColor.equalsIgnoreCase("Black")) {
            System.out.println("Invalid color. Please enter Red or Black: ");
            betColor = in.next();
        }

        // Prompt for the bet amount
        System.out.println("Enter your bet amount: ");
        System.out.println("You have " + chips + " chips");
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter a positive integer: ");
            in.next(); // Discard invalid input
        }
        int bet = in.nextInt();

        while (bet <= 0 || bet > chips) {
            if (bet <= 0) {
                System.out.println("Bet amount must be a positive integer. Enter your bet amount: ");
            } else {
                System.out.println("You can't afford that. Enter your actual bet amount: ");
            }
            bet = in.nextInt();
        }

        int winningNumber = spinRoulette();
        String winningColor = getColor(winningNumber);

        if (betColor.equalsIgnoreCase(winningColor)) {
            chips += color_win_multiplyer * bet;
            System.out.println("Congratulations you won " + color_win_multiplyer * bet + " chips.");
            System.out.println("You have " + chips + " chips");
        } else {
            chips -= bet;
            System.out.println("L, You lost");
            System.out.println("You have " + chips + " chips");
        }
    }

    private static void betOnOddEven() {
        // Handle betting on odd or even numbers
        System.out.println("Do you want to bet on odd or even? (odd/even): ");
        String betType = in.next();

        // Ensure the bet is valid
        while (!betType.equalsIgnoreCase("odd") && !betType.equalsIgnoreCase("even")) {
            System.out.println("Invalid choice. Enter odd or even: ");
            betType = in.next();
        }

        // Prompt for the bet amount
        System.out.println("Enter your bet amount: ");
        System.out.println("You have " + chips + " chips");
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter a positive integer: ");
            in.next(); // Discard invalid input
        }
        int bet = in.nextInt();

        while (bet <= 0 || bet > chips) {
            if (bet <= 0) {
                System.out.println("Bet amount must be a positive integer. Enter your bet amount: ");
            } else {
                System.out.println("You can't afford that. Enter your actual bet amount: ");
            }
            bet = in.nextInt();
        }

        int winningNumber = spinRoulette();
        boolean isEven = winningNumber % 2 == 0;

        if ((betType.equalsIgnoreCase("even") && isEven) || (betType.equalsIgnoreCase("odd") && !isEven)) {
            System.out.println("Congratulations you won " + (oddeven_win_multiplyer_multiplyer * bet) + " chips.");
            chips += oddeven_win_multiplyer_multiplyer * bet;
        } else {
            chips -= bet;
            if (chips < 0)
                chips = 0;
            {
                System.out.println("L, You lost :( The winning number was " + winningNumber + " which is "
                        + (isEven ? "even" : "odd"));
                System.out.println("You now have " + chips + " chips.");
            }

        }
    }
}
