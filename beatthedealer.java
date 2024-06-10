import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class beatthedealer {
    private static int MONEY = 10000;
    private static int chips = MONEY;
    private static final int WIN_MULTIPLIER = 2;
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Beat the Dealer!");
        System.out.println("You have " + chips + " chips.");

        boolean replay = true;
        while (replay) {
            System.out.println("You have " + chips + " chips.");

            int bet = getBet();

            List<Card> deck = createDeck();
            Collections.shuffle(deck, new Random());

            Card dealerCard = deck.remove(0);
            Card playerCard = deck.remove(0);

            System.out.println("The dealer draws a card...");
            System.out.println("Dealer's card: " + dealerCard);

            System.out.println("You draw a card...");
            System.out.println("Your card: " + playerCard);

            if (playerCard.getRankValue() > dealerCard.getRankValue()) {
                System.out.println("You win");
                chips += bet * (WIN_MULTIPLIER - 1);
            } else if (playerCard.getRankValue() < dealerCard.getRankValue()) {
                System.out.println("Dealer wins");
                chips -= bet;
            } else {
                System.out.println("It's a tie");
                System.out.println("You lose nothing and gain nothing");
            }

            System.out.println("You have " + chips + " chips.");
            System.out.println("Play again? (yes/no)");
            String response = in.next();
            replay = !response.equalsIgnoreCase("no") && !response.equalsIgnoreCase("n");
        }

        System.out.println("You leave with " + chips + " chips.");
        MONEY = chips;
    }

    private static int getBet() {
        int bet = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Place your bet:");
                bet = in.nextInt();
                valid = true;
                if (bet > 0 && bet <= chips) {
                    break;
                } else {
                    System.out.println("Invalid bet amount. You must bet between 1 and " + chips + " chips.");
                    in.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number between 1 and " + chips + " chips.");
                in.next();
            }
        }
        return bet;
    }

    private static List<Card> createDeck() {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
        List<Card> deck = new ArrayList<>();

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }

        return deck;
    }
}

class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getRankValue() {
        switch (rank) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "Jack":
                return 11;
            case "Queen":
                return 12;
            case "King":
                return 13;
            case "Ace":
                return 14;
            default:
                return 0;
        }
    }

    public String toString() {
        return rank + " of " + suit;
    }
}
