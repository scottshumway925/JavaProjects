import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.*;


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
                    List<FlashCard> cardsToSave;
                    try (FileWriter writer = new FileWriter("decks.txt", false))
                    {
                        for (int i = 0; i < decks.GetDeckCount(); i++) {
                            chosenDeck = decks.SelectCardDeck(i + 1);
                            writer.write(chosenDeck.GetDeckName());
                            cardsToSave = chosenDeck.GetDeckContents();
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
                    break;
                case 6:
                    try (BufferedReader reader = new BufferedReader(new FileReader("decks.txt"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split("\\|");
                            String deckName = parts[0];
                            FlashCardDeck newDeck = new FlashCardDeck(deckName);
                            decks.AddDeck(newDeck);

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
                    break;
                default:
                    break;
            }
        }
        scan.close();
        System.out.println("Thank you for using the flash card service!");
    }
}