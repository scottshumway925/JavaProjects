import java.util.Scanner;

public class DeckUtils {
    public static FlashCardDeck GetChosenDeck(DeckHolder decks, Scanner scan) {
        System.out.println("Please select the deck you would like to use: ");
        decks.DisplayDeckNames();
        int deckChoice = scan.nextInt();
        scan.nextLine();
        return decks.SelectCardDeck(deckChoice);
    }
}
