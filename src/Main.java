import java.util.Scanner;

// 75 minutes day 1
// 45 minutes day 2
// 160 minutes day 3
// 30 minutes day 4

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        FlashCardDeck chosenDeck;
        DeckHolder decks = new DeckHolder();

        int userChoice = -1;
        while (userChoice != 7) {
            System.out.println("Welcome to the Flash card program!");
            System.out.println("Please enter what you would like to do:\n");
            System.out.println("1. Create a new card deck");
            System.out.println("2. View an existing card deck");
            System.out.println("3. Study flash card deck");
            System.out.println("4. Edit existing deck");
            System.out.println("5. Save card decks to a file");
            System.out.println("6. Load card decks from a file");
            System.out.println("7. Quit program");
            do {
                System.out.print("\nPlease enter your choice (1-7): ");
                userChoice = scan.nextInt();
                scan.nextLine();
            } while (userChoice < 1 || userChoice > 7);

            switch (userChoice) {
                case 1:
                    FlashCardDeck deck = new FlashCardDeck();
                    deck.InitializeDeck(scan);
                    decks.AddDeck(deck);
                    System.out.println("Your new deck has been added to your deck list");
                    break;
                case 2:
                    chosenDeck = DeckUtils.GetChosenDeck(decks, scan);
                    chosenDeck.DisplayDeck();
                    break;
                case 3:
                    chosenDeck = DeckUtils.GetChosenDeck(decks, scan);
                    chosenDeck.StudyDeck(scan);
                    break;
                case 4:
                    chosenDeck = DeckUtils.GetChosenDeck(decks, scan);
                    chosenDeck.EditDeck(scan);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    break;
            }
        }
        scan.close();
        System.out.println("Thank you for using the flash card service!");
    }
}