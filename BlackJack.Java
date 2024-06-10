// Importing libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    // Creating the basic variables for the game
    int startingMoney = 100; // Initial money that is synced throughout the whole casino
    int multiplier = 3 / 2; // The multiplier for Blackjack is usually 3/2
    int standOn = 17; // I choose to stand on 17 as this was the way I learned to play
    int twentyOne = 21;
    int minBet = 5;
    int balance = startingMoney; // Balance stays throughout the casino
    Scanner in = new Scanner(System.in); 
    CardDeck cardDeck; // Deck of cards

    // Main method to start the game
    public static void Blackjack(String[] args) {
        new Main().startGame(); // Main method just used to start the game, I felt it easier to give each functiopn its own method to debug easier
    }

    // Method that includes the bulk of the game
    public void startGame() {
        // Shows the welcome message and asks if the player wants to see the rules
        System.out.println("Welcome to Blackjack!");
        System.out.println("Would you like to see the rules? (yes/no)");
        String i = in.nextLine();
        if (i.equalsIgnoreCase("yes")) {
            // Displaying game rules
            System.out.println("   - The objective is to beat the dealer's hand without going over 21.\n\n" +
                    "   - Number cards are worth their face value. Face cards are worth 10. Aces can be worth 1 or 11.\n\n" +
                    "   - You are dealt two cards initially. You can choose to hit (take another card) or stand (keep current hand).\n\n" +
                    "   - If your hand exceeds 21, you bust and lose the round. If the dealer busts, you win.\n\n" +
                    "   - Blackjack (an ace and a ten-value card) pays 3/2.\n\n" +
                    "   - Players can double down (double the bet and receive one more card).\n\n" +
                    "   - The dealer must stand on 17.");
            System.out.println("You understand? Good. Let's go.");

        } else {
            System.out.println("Okay smarty pants, we'll skip the rules.");
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
            String response = in.nextLine();
            if (!response.equalsIgnoreCase("yes") && (!response.equalsIgnoreCase("y"))) {
                break;
            }
        }
    }

    // Method to play one round of the game
    public void playRound() {
        System.out.flush(); // I wanted to keep the console clean, so looked up a way to clean the console, 
        System.out.println("Your balance: $" + balance); // Displaying current balance
        int bet = getValidBet(); // Getting a valid bet from the user
        balance -= bet; // Deducting the bet amount from the balance
        cardDeck.shuffleDeck(); // Shuffling the deck of cards

        List<String> playerCards = new ArrayList<>(); // Initializing player's card list
        List<String> dealerCards = new ArrayList<>(); // Initializing dealer's card list

        // Dealing initial cards to player and dealer
        playerCards.add(cardDeck.dealCard());
        playerCards.add(cardDeck.dealCard());
        dealerCards.add(cardDeck.dealCard());
        dealerCards.add(cardDeck.dealCard());

        int playerScore = calculateScore(playerCards); // Calculating player's score
        int dealerScore = calculateScore(dealerCards); // Calculating dealer's score

        // Displaying player's initial cards and score
        System.out.println("Thanks for the money, here we go!");
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

        // Resolving dealer's turn and determining the winner
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
    int score = 0; // Initializing the score
    int numAces = 0; // Counting the number of Aces

    // Iterating through each card in the list
    for (String card : cards) {
        int cardValue = cardDeck.cardValue(card, score); // Getting the value of the card
        if (card.startsWith("Ace")) { // Checking if the card is an Ace
            numAces++; // Incrementing the Ace count
            cardValue = 11; // Assigning value 11 initially
        }
        score += cardValue; // Adding the card value to the score
    }

    // Adjusting the score if the player has Aces and their value needs to be changed
    while (score > twentyOne && numAces > 0) {
        score -= 10; // Changing Ace value from 11 to 1
        numAces--; // Decrementing the Ace count
    }

    return score; // Returning the final score
}

// Method to get a valid bet amount from the player
public int getValidBet() {
    while (true) {
        System.out.println("Place your bet (minimum bet is $" + minBet + "):");
        if (in.hasNextInt()) { // Checking if input is an integer
            int bet = in.nextInt(); // Getting the bet amount
            if (bet < minBet || bet > balance) { // Validating the bet amount
                System.out.println("Invalid bet. Enter a value between $" + minBet + " and $" + balance);
                continue;
            }
            in.nextLine(); // Consuming newline character
            return bet; // Returning the valid bet amount
        } else {
            System.out.println("Invalid input. Enter a non-decimal number."); // Error message for invalid input
            in.nextLine(); // Consuming the invalid input
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
            in.nextLine(); // Consuming newline character
            return numDecks; // Returning the valid number of decks
        } else {
            System.out.println("Invalid input. Enter a non-decimal number."); // Error message for invalid input
            in.nextLine(); // Consuming the invalid input
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
        this.deck = new ArrayList<>(); // Initializing the deck
        createDeck(); // Creating the deck of cards
    }

    // Method to create the deck of cards
    private void createDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"}; // Array of suits
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}; // Array of ranks
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
        return deck.remove(0); // Removing and returning the top card from the deck
    }

    // Method to determine the value of a card
    public int cardValue(String card, int currentScore) {
        String rank = card.split(" ")[0]; // Extracting the rank of the card
        switch (rank) {
            case "2": return 2; // Assigning value for 2
            case "3": return 3; // Assigning value for 3
            case "4": return 4; // Assigning value for 4
            case "5": return 5; // Assigning value for 5
            case "6": return 6; // Assigning value for 6
            case "7": return 7; // Assigning value for 7
            case "8": return 8; // Assigning value for 8
            case "9": return 9; // Assigning value for 9
            case "10": case "Jack": case "Queen": case "King": return 10; // Assigning value for 10, Jack, Queen, and King
            case "Ace": return (currentScore + 11 > 21) ? 1 : 11; // Assigning value for Ace
            default: throw new IllegalArgumentException("Unknown card rank: " + rank); // Handling unknown rank
        }
    }
}
}
