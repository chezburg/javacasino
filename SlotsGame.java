import java.util.Random;
import java.util.Scanner;

public class SlotsGame {
    private static final int money = 1000000000;
    private static final int max_bet = 1000000;
    private static final int min_bet = 1;
    private static final int num_slots = 5;
    private static final int max_slots_value = 9;
    private static final int win_multiplyer = 50;
    private static final int secondary_multiplyer = 2;
    private static boolean replay = true;

    public static void main(String[] args) {
        int chips = money;
        Random random = new Random();
        Scanner in = new Scanner(System.in);

        while (replay) {
            if (chips <= 0) {
                System.out.println("You're poor. You have " + chips + " chips left.");
                replay = false;
                break;
            }

            System.out.println(
                    "You have " + chips + " chips. Enter your bet (between " + min_bet + " and " + max_bet + "):");
            String betInput = in.next();
            if (Integer.parseInt(betInput) <= chips) {

                try {
                    int bet = Integer.parseInt(betInput);

                    if (bet > chips) {
                        bet = chips;
                    }

                    if (bet <= 0) {
                        System.out.println("Invalid bet. Exiting game.");
                        break;
                    }

                    int[] slots = new int[num_slots];
                    for (int i = 0; i < num_slots; i++) {
                        slots[i] = random.nextInt(max_slots_value + 1);
                    }

                    boolean allEqual = true;
                    for (int i = 1; i < num_slots; i++) {
                        if (slots[i] != slots[0]) {
                            allEqual = false;
                            break;
                        }
                    }

                    boolean twoConsecutiveEqual = false;
                    for (int i = 1; i < num_slots; i++) {
                        if (slots[i] == slots[i - 1]) {
                            twoConsecutiveEqual = true;
                            break;
                        }
                    }

                    if (allEqual) {
                        chips += win_multiplyer * bet;
                        System.out.println("Congratulations! You won " + win_multiplyer * bet + " chips!");
                    } else if (twoConsecutiveEqual) {
                        chips += secondary_multiplyer * bet;
                        System.out.println("Congratulations! You won " + secondary_multiplyer * bet + " chips!");
                    } else {
                        chips -= bet;
                        System.out.println("You lost " + bet + " chips.");
                    }

                    for (int i = 0; i < num_slots; i++) {
                        System.out.print(slots[i] + " ");
                    }

                    System.out.println();
                    System.out.println("Your current chips: " + chips);
                    System.out.println("Do you want to replay the game? (yes/no)");
                    String replayInput = in.next();

                    if ((replayInput.equalsIgnoreCase("no")) || replayInput.equalsIgnoreCase("n")) {
                        replay = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Exiting game.");
                    break;
                }
            }
        }

        in.close();
    }
}