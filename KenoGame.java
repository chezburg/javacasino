import java.util.*;

public class KenoGame {
    public static void main(String[] args) {
        int[] kenoNumbers = new int[20];
        ArrayList<Integer> p = new ArrayList<>();
        Random random = new Random();
        Scanner in = new Scanner(System.in);

        double balance = 100.0; // Initial balance
        boolean playAgain = true;

        while (playAgain) {
            int count = 0;
            int[] pickedNumbers = new int[10];
            int same = 0; // Variable to store the count of matching numbers

            for (int i = 0; i < 20; i++) {
                while (true) {
                    kenoNumbers[i] = random.nextInt(80) + 1;
                    if (p.contains(kenoNumbers[i])) {
                        continue;
                    } else {
                        p.add(kenoNumbers[i]);
                        break;
                    }
                }
            }

            System.out.println("Welcome to Keno!");
            System.out.println(" ");
            System.out.println("Your current balance: $" + balance);
            System.out.println(" ");
            System.out.println("    In this game, you will choose 10 numbers from 1 to 80 (inclusive).");
            System.out.println("    The computer will pick 20 random numbers, and the correct matches determine your payout.");
            System.out.println("    For example, if you bet $10 and get 4 correct, you win $4!");
            System.out.println("");

            // Input and validate the bet
            double bet;
            do {
                System.out.print("Enter your bet (between 1 and " + balance + "): $");
                bet = in.nextDouble();
                if (bet < 1 || bet > balance) {
                    System.out.println("Invalid bet. Please enter a valid amount.");
                }
            } while (bet < 1 || bet > balance);

            // Deduct the bet from the balance
            balance -= bet;

            while (count < 10) {
                System.out.print("Enter number " + (count + 1) + ": ");
                int number = in.nextInt();
                if (number >= 1 && number <= 80) {
                    boolean isDuplicate = false;
                    for (int i = 0; i < count; i++) {
                        if (pickedNumbers[i] == number) {
                            isDuplicate = true;
                            break;
                        }
                    }
                    if (!isDuplicate) {
                        pickedNumbers[count] = number;
                        count++;
                    } else {
                        System.out.println("Number already picked. Choose a different one.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 80.");
                }
            }

            System.out.println("You picked the following numbers:");
            for (int i = 0; i < 10; i++) {
                System.out.print(pickedNumbers[i]);
                if (i < 9) {
                    System.out.print(", "); // Add a comma and space after each number (except the last one)
                }
                if (p.contains(pickedNumbers[i])) {
                    same++; // Increment the count of matching numbers
                }
            }
            System.out.println(""); // Print a newline after the list

            System.out.println("The computer picked:");
            System.out.println("");
            for (int f = 0; f < kenoNumbers.length; f++) {
                System.out.print(kenoNumbers[f]);
                if (f < kenoNumbers.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
            System.out.println("Number of matching numbers: " + same);

            // Calculate the payout based on matching numbers
            double payout = bet * same;
            balance += payout; // Add the payout to the balance

            System.out.println("Your payout: $" + payout);
            System.out.println("Your updated balance: $" + balance);

            // Play again or exit
            System.out.print("Play again? (yes/no): ");
            String playChoice = in.next().toLowerCase();
            if (playChoice.equals("no")) {
                playAgain = false;
                System.out.println("Thank you for playing! Exiting to the casino...");
            } else {
                // Reset for the next round
                p.clear();
                same = 0;
            }
        }
    }
}
