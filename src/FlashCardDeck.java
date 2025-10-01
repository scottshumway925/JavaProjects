import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class FlashCardDeck {
    private List<FlashCard> _cardDeck = new ArrayList<>();
    private String _deckName;

    public FlashCardDeck() {

    }

    public FlashCardDeck(String name)
    {
        _deckName = name;
    }

    public String GetDeckName() {
        return _deckName;
    }

    public List<FlashCard> GetDeckContents() {
        return _cardDeck;
    }


    public void InitializeDeck(Scanner scan) {
        System.out.print("Please enter the name of this flash card deck: ");
        _deckName = scan.nextLine();

        System.out.println("Here you will be creating all the cards in this current deck.");

        String loopController = "y";

        while (loopController.equals("y")) {
            String cardFront;
            String cardBack;

            System.out.print("Please enter the front side of the flash card: ");
            cardFront = scan.nextLine();

            System.out.print("Please enter the back side of the flash card: ");
            cardBack = scan.nextLine();
            System.out.println();

            FlashCard newCard = new FlashCard(cardFront, cardBack);
            _cardDeck.add(newCard);

            do {
                System.out.print("Would you like to create another card? (y/n) ");
                loopController = scan.nextLine();

                if (!Objects.equals(loopController, "y") && !Objects.equals(loopController, "n")) {
                    System.out.println("That was not valid input, please enter 'y' or 'n' ");
                }
            } while (!Objects.equals(loopController, "n") && !Objects.equals(loopController, "y"));

            System.out.println();
        }
    }

    public void CreateNewCard(Scanner scan) {
        String cardFront;
        String cardBack;

        System.out.print("Please enter the front side of the flash card: ");
        cardFront = scan.nextLine();
        System.out.println();

        System.out.print("Please enter the back side of the flash card: ");
        cardBack = scan.nextLine();
        System.out.println();

        FlashCard newCard = new FlashCard(cardFront, cardBack);
        _cardDeck.add(newCard);

        System.out.println();
    }

    public void DisplayDeck() {
        System.out.println(_deckName + ":");
        for (int i = 0; i < _cardDeck.size(); i++) {
            System.out.println((i + 1) + ".");
            _cardDeck.get(i).DisplayCardInfo();
            System.out.println();
        }
    }

    public void DisplayDeckName() {
        System.out.println(_deckName);
    }

    public void ChangeDeckName(Scanner scan) {
        System.out.println("Your current deck name is: " + _deckName);
        System.out.print("Please enter the new deck name: ");
        _deckName = scan.nextLine();
        System.out.println("The deck has been successfully renamed to: " + _deckName);
    }

    public void StudyDeck(Scanner scan) {
        System.out.println("Here you will be shown your cards at random from your selected deck.");
        System.out.println("Press 'f' to flip your deck from the front to the back side.");

        Random random = new Random();
        List<Boolean> isStudied = new ArrayList<>();
        for (int i = 0; i < _cardDeck.size(); i++) {
            isStudied.add(false);
        }

        for (int i = 0; i < _cardDeck.size(); i++) {
            boolean iterated = false;
            while (!iterated) {
                int randomInt = random.nextInt(0, _cardDeck.size());
                if (!isStudied.get(randomInt)) {
                    isStudied.set(randomInt, true);
                    iterated = true;

                    boolean isFlipped = false;
                    int cardOrientationController = 0;
                    while (cardOrientationController != 1) {
                        if (cardOrientationController == 0 && !isFlipped) {
                            System.out.println("\nHere is the front of your card: ");
                            _cardDeck.get(randomInt).DisplayCardFront();
                            System.out.println("\nEnter '0' to view the backside or '1' to move to the next card: ");
                            cardOrientationController = scan.nextInt();
                            scan.nextLine();
                            isFlipped = true;
                        } else if (cardOrientationController == 0) {
                            System.out.println("\nHere is the back of your card: ");
                            _cardDeck.get(randomInt).DisplayCardBack();
                            System.out.println("\nEnter '0' to view the frontside or '1' to move to the next card: ");
                            cardOrientationController = scan.nextInt();
                            scan.nextLine();
                            isFlipped = false;
                        }
                    }
                }
            }
        }
        System.out.println("You've Successfully studied your whole " + _deckName + " deck!");
        System.out.println("Press enter to continue...");
        scan.nextLine();
    }

    private FlashCard SelectCard(Scanner scan) {
        try {
            System.out.println("Here are the cards that you can edit: ");
            DisplayDeck();
            System.out.println("Select the card you would like to edit: ");
            int chosenCard = scan.nextInt();
            scan.nextLine();
            return _cardDeck.get(chosenCard - 1);
        } catch (Exception e)
        {
            System.out.println("Couldn't select your card: " + e.getMessage());
            return null;
        }
    }

    public void EditDeck(Scanner scan) {
        int userChoice;

        System.out.println("Here you will be editing a previously made deck. Here are your editing options: ");
        System.out.println("1. Add a new card to the deck");
        System.out.println("2. Remove a card from the deck");
        System.out.println("3. Change a card's text");
        System.out.println("4. Exit Editor");
        System.out.print("Please enter: ");
        userChoice = scan.nextInt();
        scan.nextLine();

        switch (userChoice) {
            case 1:
                CreateNewCard(scan);
                break;
            case 2:
                System.out.println("Select the card would you like to remove: ");
                DisplayDeck();
                int removeChoice = scan.nextInt();
                scan.nextLine();
                _cardDeck.remove(removeChoice - 1);
                System.out.println("Your card has been removed...");
                System.out.println("Returning to menu...");
                break;
            case 3:
                FlashCard editCard = SelectCard(scan);
                System.out.println("What would you like to do with the card? ");
                System.out.println("1. Edit the front of the card");
                System.out.println("2. Edit the back of the card");
                System.out.println("3. Edit both sides of the card");
                System.out.println("4. Back to menu");
                System.out.print("> ");
                int editChoice = scan.nextInt();
                scan.nextLine();
                if (editChoice == 1) {
                    String newFront;

                    System.out.print("Enter the new card front: ");
                    newFront = scan.nextLine();
                    editCard.SetCardFront(newFront);

                    System.out.print("Your card has been changed!\n");
                } else if (editChoice == 2) {
                    String newBack;

                    System.out.print("Enter the new card back: ");
                    newBack = scan.nextLine();
                    editCard.SetCardBack(newBack);

                    System.out.print("Your card has been changed!\n");
                } else if (editChoice == 3) {
                    String newFront;
                    String newBack;

                    System.out.print("Enter the new card front: ");
                    newFront = scan.nextLine();
                    editCard.SetCardFront(newFront);

                    System.out.print("Enter the new card back: ");
                    newBack = scan.nextLine();
                    editCard.SetCardBack(newBack);

                    System.out.print("Your card has been changed!\n");
                } else {
                    System.out.println("Returning to menu without changing card...\n");
                    return;
                }
                break;
            case 4:
                break;
        }
    }

    public void LoadCard(String front, String back)
    {
        FlashCard newCard = new FlashCard(front, back);
        _cardDeck.add(newCard);
    }

}