import java.util.Random;
import java.util.Scanner;

public class LosingGame {
    public static void main(String[] args) {
        int startingMoney = 100;
        int maxMoney = 2147483647;
        int betMoney = (startingMoney - startingMoney)+ 1;
        Scanner in = new Scanner(System.in);

        while (true) {
            int randomNumber = new Random().nextInt(1000) + 1;
            System.out.println("Welcome to the 'winning' game!\nIf you win, you can earn $2147483647!\nBut if you lose, you will be left with $1\nYou must correctly guess a number between 1 and 1000.\nDo you want to play? (yes/no)");
            String exit = in.nextLine();
            if (!exit.equalsIgnoreCase("yes") && (!exit.equalsIgnoreCase("y"))) {
                System.out.println("Are you sure?");
                string rusure = in.nextLine();
                if (!rusure.equalsIgnoreCase("yes") && (!rusure.equalsIgnoreCase("y"))){
                System.out.println("Chicken!");
                break;
            }
            }

            while (startingMoney > 1) {
                System.out.println("You have $" + startingMoney + ". \n\nGuess a number between 1 and 1000:");
                int playerGuess = in.nextInt();

                if (playerGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number correctly. You win $" + maxMoney + "!");
                    startingMoney = maxMoney;
                    break;
                } else {
                    startingMoney = betMoney;
                    System.out.println("Womp womp! you lost! the number was "+randomNumber);
                }
            }

            if (startingMoney == 1) {
                System.out.println("You've run out of money. Game over.");
                System. exit(0);
            }

            System.out.println("Do you want to play again or go back to the casino? (play/casino)");
            String choice = in.nextLine();
            if (!choice.equalsIgnoreCase("play")) {
                System.out.println("Exiting to the casino. Good luck!");
                break;
            }
        }

        in.close();
    }
}
