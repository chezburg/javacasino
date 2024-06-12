//Name: Rand Ali & Evan Chung
//Title: Casino CPT
//Date: June, 11, 2024
//Verified By: Tyler Zhang, Sharon Basovich, Kerry Li
//Last edited: Jun/11/24/12:36
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CasinoCPT {
    // Global variable used to keep money throughout the casino, no matter what game you play you will have the same amount of money
    static int Money = 10000;
    public static void main(String[] args) {
        System.out.println("Welcome to the ");
       System.out.println("   ▄███████▄    ▄████████  ▄██████▄   ▄█    █▄   ▄█  ████████▄     ▄████████ ███▄▄▄▄    ▄████████    ▄████████       ▄████████    ▄████████    ▄████████  ▄█  ███▄▄▄▄    ▄██████▄  \r\n" + //
                      "  ███    ███   ███    ███ ███    ███ ███    ███ ███  ███   ▀███   ███    ███ ███▀▀▀██▄ ███    ███   ███    ███      ███    ███   ███    ███   ███    ███ ███  ███▀▀▀██▄ ███    ███ \r\n" + //
                      "  ███    ███   ███    ███ ███    ███ ███    ███ ███▌ ███    ███   ███    █▀  ███   ███ ███    █▀    ███    █▀       ███    █▀    ███    ███   ███    █▀  ███▌ ███   ███ ███    ███ \r\n" + //
                      "  ███    ███  ▄███▄▄▄▄██▀ ███    ███ ███    ███ ███▌ ███    ███  ▄███▄▄▄     ███   ███ ███         ▄███▄▄▄          ███          ███    ███   ███        ███▌ ███   ███ ███    ███ \r\n" + //
                      "▀█████████▀  ▀▀███▀▀▀▀▀   ███    ███ ███    ███ ███▌ ███    ███ ▀▀███▀▀▀     ███   ███ ███        ▀▀███▀▀▀          ███        ▀███████████ ▀███████████ ███▌ ███   ███ ███    ███ \r\n" + //
                      "  ███        ▀███████████ ███    ███ ███    ███ ███  ███    ███   ███    █▄  ███   ███ ███    █▄    ███    █▄       ███    █▄    ███    ███          ███ ███  ███   ███ ███    ███ \r\n" + //
                      "  ███          ███    ███ ███    ███ ███    ███ ███  ███   ▄███   ███    ███ ███   ███ ███    ███   ███    ███      ███    ███   ███    ███    ▄█    ███ ███  ███   ███ ███    ███ \r\n" + //
                      " ▄████▀        ███    ███  ▀██████▀   ▀██████▀  █▀   ████████▀    ██████████  ▀█   █▀  ████████▀    ██████████      ████████▀    ███    █▀   ▄████████▀  █▀    ▀█   █▀   ▀██████▀  \r\n" + //
                      "               ███    ███                                                                                                                                                          ");
        System.out.println("If you are low on time, stop by our famous Blackjack and Roulette tables! ");
        boolean replay = true;
        Scanner in = new Scanner(System.in);

        while (replay) {
            if (Money <= 0) {
                System.out.println(" ");
                System.out.println("Broke boy! We are kicking you out of the casino, you have $" + Money + " left.");
                replay = false;
                break;
            } else {
                System.out.println("");
                System.out.println("You have $" + Money);
                System.out.println("");
                System.out.println("What do you want to play?");
                System.out.println("1. Bogus Blackjack");
                System.out.println("2. Roulette Royale");
                System.out.println("3. Sovereign Slots");
                System.out.println("4. Heroic Hand");
                System.out.println("5. Wicked Wins");
                System.out.println("6. Kaiju Keno");
                System.out.println("7. Exit");

                int betType = getIntInput(in);
                // Main menu to  redirect user to the game they want to play
                switch (betType) {
                    case 1:
                        new Blackjack().startGame();
                        break;
                    case 2:
                        new RouletteGame().startGame();
                        break;
                    case 3:
                        new SlotsGame().startGame();
                        break;
                    case 4:
                        new beatthedealer().startGame();
                        break;
                    case 5:
                        new losinggame().startGame();
                        break;
                    case 6:
                        new KenoGame().startGame();
                        break;
                    case 7:
                        System.out.println("We didn't want you here anyways, loser!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid, please select a game.");
                }
            }
        }

        System.out.println("Thank you for playing!");
    }

    private static int getIntInput(Scanner in) {
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
    public static class Blackjack {
        // Creating the basic variables for the game
        int chips = Money;
        int multiplier = 3; // The multiplier for Blackjack is usually 3
        int standOn = 17; // I choose to stand on 17 as this was the way I learned to play
        int twentyOne = 21;
        int minBet = 5;
        int balance = Money; // Balance stays throughout the casino
        Scanner in = new Scanner(System.in);
        CardDeck cardDeck; // Deck of cards

        // Method that includes the bulk of the game
        public void startGame() {
            // Shows the welcome message and asks if the player wants to see the rules
            System.out.println("Welcome to");
            try {
                Thread.sleep(1000); // Delay for 1 second so its not a lot of text at once
                System.out.println(".");
                Thread.sleep(1000);
                System.out.println(".");
                Thread.sleep(1000);
                System.out.println(".");
                Thread.sleep(1000);
                System.out.println(" ");
                System.out.println("▀█████████▄   ▄██████▄     ▄██████▄  ███    █▄     ▄████████      ▀█████████▄   ▄█          ▄████████  ▄████████    ▄█   ▄█▄      ▄█    ▄████████  ▄████████    ▄█   ▄█▄ 
  ███    ███ ███    ███   ███    ███ ███    ███   ███    ███        ███    ███ ███         ███    ███ ███    ███   ███ ▄███▀     ███   ███    ███ ███    ███   ███ ▄███▀ 
  ███    ███ ███    ███   ███    █▀  ███    ███   ███    █▀         ███    ███ ███         ███    ███ ███    █▀    ███▐██▀       ███   ███    ███ ███    █▀    ███▐██▀   
 ▄███▄▄▄██▀  ███    ███  ▄███        ███    ███   ███              ▄███▄▄▄██▀  ███         ███    ███ ███         ▄█████▀        ███   ███    ███ ███         ▄█████▀    
▀▀███▀▀▀██▄  ███    ███ ▀▀███ ████▄  ███    ███ ▀███████████      ▀▀███▀▀▀██▄  ███       ▀███████████ ███        ▀▀█████▄        ███ ▀███████████ ███        ▀▀█████▄    
  ███    ██▄ ███    ███   ███    ███ ███    ███          ███        ███    ██▄ ███         ███    ███ ███    █▄    ███▐██▄       ███   ███    ███ ███    █▄    ███▐██▄   
  ███    ███ ███    ███   ███    ███ ███    ███    ▄█    ███        ███    ███ ███▌    ▄   ███    ███ ███    ███   ███ ▀███▄     ███   ███    ███ ███    ███   ███ ▀███▄ 
▄█████████▀   ▀██████▀    ████████▀  ████████▀   ▄████████▀       ▄█████████▀  █████▄▄██   ███    █▀  ████████▀    ███   ▀█▀ █▄ ▄███   ███    █▀  ████████▀    ███   ▀█▀ 
                                                                               ▀                                   ▀         ▀▀▀▀▀▀                            ▀         ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
                System.out.println("Would you like to see the rules? (yes/no)");
                String i = in.nextLine();
                if (i.equalsIgnoreCase("yes")) {
                    // Displaying game rules
                    System.out.println("- The objective is to beat the dealer's hand without going over 21.");
                    Thread.sleep(2000);
                    System.out.println(
                            "- Number cards are worth their face value. Face cards are worth 10. Aces can be worth 1 or 11.");
                    Thread.sleep(2000);
                    System.out.println(
                            "- You are dealt two cards initially. You can choose to hit (take another card) or stand (keep current hand).");
                    Thread.sleep(2000);
                    System.out.println(
                            "- If your hand exceeds 21, you bust and lose the round. If the dealer busts, you win.");
                    Thread.sleep(2000);
                    System.out.println("- Blackjack (an ace and a ten-value card) pays 3/2.");
                    Thread.sleep(2000);
                    System.out.println("- Players can double down (double the bet and receive one more card).");
                    Thread.sleep(2000);
                    System.out.println("- The dealer must stand on 17.");
                    Thread.sleep(3500);
                    System.out.println("You understand? Good. Let's go.");
                } else {
                    System.out.println("Okay smarty pants, we'll skip the rules.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Asking the user to input the number of decks to be used, some casinos go up to 8 decks, I wanted to give it a wide range without it being too much to handle
            System.out.println("Enter the number of decks (1-10):");
            int numDecks = getValidNumDecks();
            // Creating a new card deck with the specified number of decks (52 x the number inputted)
            cardDeck = new CardDeck(numDecks);

            // Starting the game as long as the player has at least 5 dollars, if they don't the game ends.
            while (balance >= minBet) {
                playRound();
                if (balance == 0) {
                    System.out.println("HAHA YOU ARE POOR. Game over!");
                    break;
                }
                System.out.println("Your balance: $" + balance); // Displaying current balance
                System.out.println("Would you like to play another round? (yes/no)");
                Money = chips = balance;
                String response = in.nextLine();
                if (!response.equalsIgnoreCase("yes") && (!response.equalsIgnoreCase("y"))) {
                    break;
                }
            }
        }

        // Method to play one round of the game
        public void playRound() {
            System.out.println("Your balance: $" + balance); // Displaying current balance
            int bet = getValidBet();
            balance -= bet;
            cardDeck.shuffleDeck(); // Shuffling the deck of cards

            List<String> playerCards = new ArrayList<>(); // Creating player's card list
            List<String> dealerCards = new ArrayList<>();

            // Dealing initial cards to player and dealer
            playerCards.add(cardDeck.dealCard());
            playerCards.add(cardDeck.dealCard());
            dealerCards.add(cardDeck.dealCard());
            dealerCards.add(cardDeck.dealCard());

            int playerScore = calculateScore(playerCards); // Calculating player's score
            int dealerScore = calculateScore(dealerCards);

            // Displaying player's initial cards and score
            System.out.println("No more bets, best of luck!");
            System.out.println("Your first card is: " + playerCards.get(0));
            System.out.println("Your second card is: " + playerCards.get(1));
            System.out.println("Your card total is: " + playerScore);
            System.out.println("");
            System.out.println("The dealer's shown card is: " + dealerCards.get(0));

            // Loop for player's turn
            while (playerScore <= twentyOne) {
                if (playerScore == twentyOne) { // Checking for Blackjack
                    System.out.println("Blackjack! You win " + (bet * multiplier) + "!");
                    balance += bet * multiplier; // Adding winnings to balance
                    return;
                } else if (playerScore < twentyOne) { // Asking for player's action
                    System.out.println("Do you want to hit, stand, or double down? (hit/stand/double)");
                    String choice = in.nextLine();
                    if (choice.equalsIgnoreCase("hit")) { // Dealing another card to player
                        String newCard = cardDeck.dealCard();
                        playerCards.add(newCard);
                        playerScore = calculateScore(playerCards);
                        System.out.println("Your new card total is: " + playerScore);
                    } else if (choice.equalsIgnoreCase("double")) { // Doubling down the bet
                        if (balance < bet) {
                            System.out.println("Insufficient balance to double down.");
                            continue;
                        }
                        bet *= 2;
                        String newCard = cardDeck.dealCard();
                        playerCards.add(newCard);
                        playerScore = calculateScore(playerCards);
                        System.out.println("Your new card total is: " + playerScore);
                        break;
                    } else if (choice.equalsIgnoreCase("stand")) { // Ending player's turn
                        break;
                    }
                }
            }

            // Starting the dealers turn and seeing who wins
            if (playerScore <= twentyOne) {
                while (dealerScore < standOn) { // Dealer draws cards until reaching stand threshold
                    dealerCards.add(cardDeck.dealCard());
                    dealerScore = calculateScore(dealerCards);
                }

                // Displaying dealer's cards
                System.out.println("Dealer's cards: " + dealerCards);

                // Determining the winner based on scores
                if (dealerScore > twentyOne || playerScore > dealerScore) {
                    System.out.println("You win " + (bet * 2) + "!");
                    balance += bet * 2; // Adding winnings to balance
                } else if (playerScore == dealerScore) {
                    System.out.println("Push, you get your bet back.");
                    balance += bet; // Returning bet amount to balance
                } else {
                    System.out.println("Dealer wins.");
                }
            } else {
                System.out.println("You bust. Dealer wins.");
            }
        }

        // Method to calculate the score of a given list of cards
        public int calculateScore(List<String> cards) {
            int score = 0;
            int numAces = 0; // Counting the number of Aces so we can later change the value if needed

            // Iterating through each card in the list
            for (String card : cards) {
                int cardValue = cardDeck.cardValue(card, score); // Getting the value of the card
                if (card.startsWith("Ace")) { // Checking if the card is an Ace
                    numAces++;
                    cardValue = 11;
                }
                score += cardValue;
            }

            // Adjusting the score if the player has Aces and their value needs to be
            // changed
            while (score > twentyOne && numAces > 0) {
                score -= 10;
                numAces--;
            }

            return score; // Returning the final score
        }

        // Method to get a valid bet amount from the player
        public int getValidBet() {
            while (true) {
                System.out.println("Place your bet (minimum bet is $" + minBet + "):");
                if (in.hasNextInt()) { // Checking if input is an integer
                    int bet = in.nextInt();
                    if (bet < minBet || bet > balance) {
                        System.out.println("Invalid bet. Enter a value between $" + minBet + " and $" + balance);
                        continue;
                    }
                    in.nextLine();
                    return bet; // Returning the valid bet amount
                } else {
                    System.out.println("Invalid input. Enter a non-decimal number."); // Error message for invalid input
                    in.nextLine();
                }
            }
        }

        // Method to get a valid number of decks from the player
        public int getValidNumDecks() {
            while (true) {
                if (in.hasNextInt()) { // Checking if input is an integer
                    int numDecks = in.nextInt(); // Getting the number of decks
                    if (numDecks < 1 || numDecks > 10) { // Validating the number of decks
                        System.out.println("Invalid number of decks. Enter a value between 1 and 10.");
                        continue;
                    }
                    in.nextLine();
                    return numDecks; // Returning the valid number of decks
                } else {
                    System.out.println("Invalid input. Enter a non-decimal number."); // Error message for invalid input
                    in.nextLine();
                }
            }
        }

        // Class representing a deck of cards
        class CardDeck {
            private List<String> deck; // List to store cards
            private int numDecks; // Number of decks

            // Constructor to create a deck of cards
            public CardDeck(int numDecks) {
                this.numDecks = numDecks; // Assigning the number of decks
                this.deck = new ArrayList<>();
                createDeck(); // Creating the deck of cards
            }

            // Method to create the deck of cards
            private void createDeck() {
                String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
                String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
                // Loop to create cards for each suit and rank
                for (int i = 0; i < numDecks; i++) {
                    for (String suit : suits) {
                        for (String rank : ranks) {
                            deck.add(rank + " of " + suit); // Adding card to the deck
                        }
                    }
                }
            }

            // Method to shuffle the deck of cards
            public void shuffleDeck() {
                Collections.shuffle(deck); // Shuffling the deck
            }

            // Method to deal a card from the deck
            public String dealCard() {
                return deck.remove(0);
            }

            // Method to determine the value of a card
            public int cardValue(String card, int currentScore) {
                String rank = card.split(" ")[0]; // Finding the rank of the card
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
                    case "Jack":
                    case "Queen":
                    case "King":
                        return 10; // Assigning value for 10, Jack, Queen, and King
                    case "Ace":
                        return (currentScore + 11 > 21) ? 1 : 11; // Assigning value for Ace
                    default:
                        throw new IllegalArgumentException("Unknown card rank: " + rank); // Handling unknown rank (will never happen, but we need a default)
                }
            }
        }

    }

    public static class RouletteGame {
        private static int chips = Money;
        private static final int number_win_multiplyer = 35;
        private static final int color_win_multiplyer = 2;
        private static final int oddeven_win_multiplyer_multiplyer = 2;
        private static final String[] COLORS = { "Red", "Black" };
        private static final Random RANDOM = new Random();
        private static final Scanner in = new Scanner(System.in);

        public void startGame() {
            System.out.println("Welcome to");
            System.out.println("   ▄████████  ▄██████▄  ███    █▄   ▄█          ▄████████     ███         ███        ▄████████         ▄████████  ▄██████▄  ▄██   ▄      ▄████████  ▄█          ▄████████ \r\n" + //
                                "  ███    ███ ███    ███ ███    ███ ███         ███    ███ ▀█████████▄ ▀█████████▄   ███    ███        ███    ███ ███    ███ ███   ██▄   ███    ███ ███         ███    ███ \r\n" + //
                                "  ███    ███ ███    ███ ███    ███ ███         ███    █▀     ▀███▀▀██    ▀███▀▀██   ███    █▀         ███    ███ ███    ███ ███▄▄▄███   ███    ███ ███         ███    █▀  \r\n" + //
                                " ▄███▄▄▄▄██▀ ███    ███ ███    ███ ███        ▄███▄▄▄         ███   ▀     ███   ▀  ▄███▄▄▄           ▄███▄▄▄▄██▀ ███    ███ ▀▀▀▀▀▀███   ███    ███ ███        ▄███▄▄▄     \r\n" + //
                                "▀▀███▀▀▀▀▀   ███    ███ ███    ███ ███       ▀▀███▀▀▀         ███         ███     ▀▀███▀▀▀          ▀▀███▀▀▀▀▀   ███    ███ ▄██   ███ ▀███████████ ███       ▀▀███▀▀▀     \r\n" + //
                                "▀███████████ ███    ███ ███    ███ ███         ███    █▄      ███         ███       ███    █▄       ▀███████████ ███    ███ ███   ███   ███    ███ ███         ███    █▄  \r\n" + //
                                "  ███    ███ ███    ███ ███    ███ ███▌    ▄   ███    ███     ███         ███       ███    ███        ███    ███ ███    ███ ███   ███   ███    ███ ███▌    ▄   ███    ███ \r\n" + //
                                "  ███    ███  ▀██████▀  ████████▀  █████▄▄██   ██████████    ▄████▀      ▄████▀     ██████████        ███    ███  ▀██████▀   ▀█████▀    ███    █▀  █████▄▄██   ██████████ \r\n" + //
                                "  ███    ███                       ▀                                                                  ███    ███                                   ▀                      ");
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
                Money = chips;
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
                System.out.println(
                        "Congratulations you won " + (oddeven_win_multiplyer_multiplyer * bet) + " chips.");
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

    public static class SlotsGame {
        private static final int max_bet = 1000000;
        private static final int min_bet = 1;
        private static final int num_slots = 5;
        private static final int max_slots_value = 9;
        private static final int win_multiplyer = 50;
        private static final int secondary_multiplyer = 2;
        private static boolean replay = true;

        public void startGame() {
            int chips = Money;
            Random random = new Random();
            Scanner in = new Scanner(System.in);

            while (replay) {
                if (chips <= 0) {
                    System.out.println("You're poor. You have " + chips + " chips left.");
                    replay = false;
                    break;
                }
                System.out.println("Welcome to ");
                System.out.println("   ▄████████  ▄██████▄   ▄█    █▄     ▄████████    ▄████████    ▄████████  ▄█     ▄██████▄  ███▄▄▄▄           ▄████████  ▄█        ▄██████▄      ███        ▄████████ \r\n" + //
                                        "  ███    ███ ███    ███ ███    ███   ███    ███   ███    ███   ███    ███ ███    ███    ███ ███▀▀▀██▄        ███    ███ ███       ███    ███ ▀█████████▄   ███    ███ \r\n" + //
                                        "  ███    █▀  ███    ███ ███    ███   ███    █▀    ███    ███   ███    █▀  ███▌   ███    █▀  ███   ███        ███    █▀  ███       ███    ███    ▀███▀▀██   ███    █▀  \r\n" + //
                                        "  ███        ███    ███ ███    ███  ▄███▄▄▄      ▄███▄▄▄▄██▀  ▄███▄▄▄     ███▌  ▄███        ███   ███        ███        ███       ███    ███     ███   ▀   ███        \r\n" + //
                                        "▀███████████ ███    ███ ███    ███ ▀▀███▀▀▀     ▀▀███▀▀▀▀▀   ▀▀███▀▀▀     ███▌ ▀▀███ ████▄  ███   ███      ▀███████████ ███       ███    ███     ███     ▀███████████ \r\n" + //
                                        "         ███ ███    ███ ███    ███   ███    █▄  ▀███████████   ███    █▄  ███    ███    ███ ███   ███               ███ ███       ███    ███     ███              ███ \r\n" + //
                                        "   ▄█    ███ ███    ███ ███    ███   ███    ███   ███    ███   ███    ███ ███    ███    ███ ███   ███         ▄█    ███ ███▌    ▄ ███    ███     ███        ▄█    ███ \r\n" + //
                                        " ▄████████▀   ▀██████▀   ▀██████▀    ██████████   ███    ███   ██████████ █▀     ████████▀   ▀█   █▀        ▄████████▀  █████▄▄██  ▀██████▀     ▄████▀    ▄████████▀  \r\n" + //
                                        "                                                  ███    ███                                                            ▀                                             ");
                System.out.println("You have " + chips + " chips. Enter your bet (between " + min_bet + " and " + max_bet + "):");
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
                        Money = chips;

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

    public static class beatthedealer {
        private static int chips = Money;
        private static final int WIN_MULTIPLIER = 2;
        private static final Scanner in = new Scanner(System.in);
        public void startGame() {
            System.out.println("Welcome to");
            System.out.println("You have " + chips + " chips.");
            System.out.println("   ▄█    █▄       ▄████████    ▄████████  ▄██████▄   ▄█   ▄████████         ▄█    █▄       ▄████████ ███▄▄▄▄   ████████▄  \r\n" + //
                                "  ███    ███     ███    ███   ███    ███ ███    ███ ███  ███    ███        ███    ███     ███    ███ ███▀▀▀██▄ ███   ▀███ \r\n" + //
                                "  ███    ███     ███    █▀    ███    ███ ███    ███ ███▌ ███    █▀         ███    ███     ███    ███ ███   ███ ███    ███ \r\n" + //
                                " ▄███▄▄▄▄███▄▄  ▄███▄▄▄      ▄███▄▄▄▄██▀ ███    ███ ███▌ ███              ▄███▄▄▄▄███▄▄   ███    ███ ███   ███ ███    ███ \r\n" + //
                                "▀▀███▀▀▀▀███▀  ▀▀███▀▀▀     ▀▀███▀▀▀▀▀   ███    ███ ███▌ ███             ▀▀███▀▀▀▀███▀  ▀███████████ ███   ███ ███    ███ \r\n" + //
                                "  ███    ███     ███    █▄  ▀███████████ ███    ███ ███  ███    █▄         ███    ███     ███    ███ ███   ███ ███    ███ \r\n" + //
                                "  ███    ███     ███    ███   ███    ███ ███    ███ ███  ███    ███        ███    ███     ███    ███ ███   ███ ███   ▄███ \r\n" + //
                                "  ███    █▀      ██████████   ███    ███  ▀██████▀  █▀   ████████▀         ███    █▀      ███    █▀   ▀█   █▀  ████████▀  \r\n" + //
                                "                              ███    ███                                                                                  ");
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
                    System.out.println("You push");
                }

                System.out.println("You have " + chips + " chips.");
                System.out.println("Play again? (yes/no)");
                String response = in.next();
                replay = !response.equalsIgnoreCase("no") && !response.equalsIgnoreCase("n");
            }

            System.out.println("You leave with " + chips + " chips.");
            Money = chips;
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

    static class Card {
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

    public static class losinggame {
        public void startGame() { // Everyhthing done in the main method as this game is relatively short, only around 65 lines
            int chips = Money;
            // Premise of the game is that you can never win, if you do you get the maximum amount of money able to be stored in an "int"
            int maxChips = 2147483647;
            int betChips = (chips - chips) + 1;
            Scanner in = new Scanner(System.in);

            while (true) {
                int randomNumber = new Random().nextInt(1000) + 1;
                System.out.println("Welcome to");
                        System.out.println(" ▄█     █▄   ▄█   ▄████████    ▄█   ▄█▄    ▄████████ ████████▄        ▄█     █▄   ▄█  ███▄▄▄▄      ▄████████ \r\n" + //
                                                        "███     ███ ███  ███    ███   ███ ▄███▀   ███    ███ ███   ▀███      ███     ███ ███  ███▀▀▀██▄   ███    ███ \r\n" + //
                                                        "███     ███ ███▌ ███    █▀    ███▐██▀     ███    █▀  ███    ███      ███     ███ ███▌ ███   ███   ███    █▀  \r\n" + //
                                                        "███     ███ ███▌ ███         ▄█████▀     ▄███▄▄▄     ███    ███      ███     ███ ███▌ ███   ███   ███        \r\n" + //
                                                        "███     ███ ███▌ ███        ▀▀█████▄    ▀▀███▀▀▀     ███    ███      ███     ███ ███▌ ███   ███ ▀███████████ \r\n" + //
                                                        "███     ███ ███  ███    █▄    ███▐██▄     ███    █▄  ███    ███      ███     ███ ███  ███   ███          ███ \r\n" + //
                                                        "███ ▄█▄ ███ ███  ███    ███   ███ ▀███▄   ███    ███ ███   ▄███      ███ ▄█▄ ███ ███  ███   ███    ▄█    ███ \r\n" + //
                                                        " ▀███▀███▀  █▀   ████████▀    ███   ▀█▀   ██████████ ████████▀        ▀███▀███▀  █▀    ▀█   █▀   ▄████████▀  \r\n" + //
                                                        "                              ▀                                                                              ");
                System.out.println(" If you win, you can earn $2147483647!\\n" + //
                                        "But if you lose, you will be left with $1\\n" + //
                                        "You must correctly guess a number between 1 and 1000.\\n" + //
                                        "Do you want to play? (yes/no)");
                String exit = in.nextLine();
                // You will be left with 1 dollar when you lose, wanted to make sure that you understand what you are getting in to
                if (!exit.equalsIgnoreCase("yes") && (!exit.equalsIgnoreCase("y"))) {
                    System.out.println("Are you sure?");
                    String rusure = in.nextLine();
                    if (!rusure.equalsIgnoreCase("yes") && (!rusure.equalsIgnoreCase("y"))) {
                        System.out.println("Chicken!");
                        break;
                    }
                }
                // While you have more than 1 chip, you are able to guess the number, if you are correct you get the max chips
                while (chips > 1) {
                    System.out.println("You have $" + chips + ". \n\nGuess a number between 1 and 1000:");
                    int playerGuess = in.nextInt();

                    if (playerGuess == randomNumber) {
                        System.out.println(
                                "Congratulations! You guessed the number correctly. You win $" + maxChips + "!");
                        chips = maxChips;
                        break;
                    } else {
                        chips = betChips;
                        System.out.println("Womp womp! you lost! the number was " + randomNumber);
                    }
                }
                // If not you are kicked from the game
                if (chips == 1) {
                    System.out.println("You've run out of money. Game over.");
                    break;
                }
                // Return to casino
                System.out.println("Do you want to play again or go back to the casino? (play/casino)");
                String choice = in.nextLine();
                if (!choice.equalsIgnoreCase("play")) {
                    System.out.println("Exiting to the casino. Good luck!");
                    Money = chips;
                    break;
                }
            }

            in.close();
        }
    }

    public static class KenoGame {
        public void startGame() {
            int number = 0;
            int chips = Money;
            int[] kenoNumbers = new int[20]; // Numbers randomly drawn
            ArrayList<Integer> p = new ArrayList<>();
            Random random = new Random();
            Scanner in = new Scanner(System.in);
            boolean playAgain = true;

            while (playAgain) {
                int count = 0;
                int[] pickedNumbers = new int[10]; // Array to store the numers they picked
                int same = 0; // Variable to store the count of matching numbers
                // Smart idea to make sure that if there are repeat numbers, the list gets regenerated, and to make sure that it is bound from 1 - 80
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

                System.out.println("Welcome to");
                System.out.println("   ▄█   ▄█▄    ▄████████  ▄█       ▄█ ███    █▄          ▄█   ▄█▄    ▄████████ ███▄▄▄▄    ▄██████▄  \r\n" + //
                                        "  ███ ▄███▀   ███    ███ ███      ███ ███    ███        ███ ▄███▀   ███    ███ ███▀▀▀██▄ ███    ███ \r\n" + //
                                        "  ███▐██▀     ███    ███ ███▌     ███ ███    ███        ███▐██▀     ███    █▀  ███   ███ ███    ███ \r\n" + //
                                        " ▄█████▀      ███    ███ ███▌     ███ ███    ███       ▄█████▀     ▄███▄▄▄     ███   ███ ███    ███ \r\n" + //
                                        "▀▀█████▄    ▀███████████ ███▌     ███ ███    ███      ▀▀█████▄    ▀▀███▀▀▀     ███   ███ ███    ███ \r\n" + //
                                        "  ███▐██▄     ███    ███ ███      ███ ███    ███        ███▐██▄     ███    █▄  ███   ███ ███    ███ \r\n" + //
                                        "  ███ ▀███▄   ███    ███ ███      ███ ███    ███        ███ ▀███▄   ███    ███ ███   ███ ███    ███ \r\n" + //
                                        "  ███   ▀█▀   ███    █▀  █▀   █▄ ▄███ ████████▀         ███   ▀█▀   ██████████  ▀█   █▀   ▀██████▀  \r\n" + //
                                        "  ▀                           ▀▀▀▀▀▀                    ▀                                           ");
                System.out.println(" ");
                System.out.println("Your current balance: $" + chips);
                System.out.println(" ");
                System.out.println("    In this game, you will choose 10 numbers from 1 to 80 (inclusive).");
                System.out.println(
                        "    The computer will pick 20 random numbers, and the correct matches determine your payout.");
                System.out.println("    For example, if you bet $10 and get 4 correct, you win $4!");
                System.out.println("");

                // Input and validate the bet
                double bet;
                do {
                    System.out.print("Enter your bet (between 1 and " + chips + "): $");
                    bet = in.nextDouble();
                    if (bet < 1 || bet > chips) {
                        System.out.println("Invalid bet. Please enter a valid amount.");
                    }
                } while (bet < 1 || bet > chips);

                // Deduct the bet from the balance
                chips -= bet;
                // Error catching to make sure that they dont pick the same number, also makes sure it is an int
                while (count < 10) {
                    System.out.print("Enter number " + (count + 1) + ": "); 
                try {
                    number = in.nextInt();
                } catch (InputMismatchException e) {
                    in.next(); // Clear the invalid input
                }
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
                chips += payout; // Add the payout to the balance

                System.out.println("Your payout: $" + payout);
                System.out.println("Your updated balance: $" + chips);

                // Play again or exit
                System.out.print("Play again? (yes/no): ");
                Money = chips;
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
}
