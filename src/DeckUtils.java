import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

public class DeckUtils {
    public static FlashCardDeck GetChosenDeck(DeckHolder decks, Scanner scan) {
        System.out.println("Please select the deck you would like to use: ");
        decks.DisplayDeckNames();
        int deckChoice = scan.nextInt();
        scan.nextLine();
        return decks.SelectCardDeck(deckChoice);
    }

    public static DeckHolder LoadDeck() {
        DeckHolder loadedDecks = new DeckHolder();
        try (BufferedReader reader = new BufferedReader(new FileReader("decks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String deckName = parts[0];
                FlashCardDeck newDeck = new FlashCardDeck(deckName);
                loadedDecks.AddDeck(newDeck);

                for (int i = 1; i < parts.length; i += 2)
                {
                    String cardFront = parts[i];
                    String cardBack = parts[i + 1];
                    newDeck.LoadCard(cardFront, cardBack);
                }
            }
        } catch (Exception e) {
            System.out.println("There was an error loading your decks: " + e.getMessage());
        }
        return loadedDecks;
    }

    public static void SaveCurrentDecks(DeckHolder decks) {
        List<FlashCard> cardsToSave;
        FlashCardDeck deckToSave;
        try (FileWriter writer = new FileWriter("decks.txt", false))
        {
            for (int i = 0; i < decks.GetDeckCount(); i++) {
                deckToSave = decks.SelectCardDeck(i + 1);
                writer.write(deckToSave.GetDeckName());
                cardsToSave = deckToSave.GetDeckContents();
                for (int cardI = 0; cardI < cardsToSave.size(); cardI++)
                {
                    writer.write("|" + cardsToSave.get(cardI).GetCardFront());
                    writer.write("|" + cardsToSave.get(cardI).GetCardBack());
                }
                writer.write("\n");
            }
        } catch (Exception e) {
            System.out.println("There was an error saving your decks: " + e.getMessage());
        }
    }
}
