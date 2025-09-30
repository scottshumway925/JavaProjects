public class FlashCard {
    private String _cardFront;
    private String _cardBack;

    public FlashCard() {
        _cardFront = null;
        _cardBack = null;
    }

    public FlashCard(String front, String back) {
        _cardFront = front;
        _cardBack = back;
    }

    public void SetCardFront(String front) {
        _cardFront = front;
    }

    public void SetCardBack(String back) {
        _cardBack = back;
    }

    public void SetFrontAndBack(String front, String back) {
        _cardFront = front;
        _cardBack = back;
    }

    public void DisplayCardFront() {
        System.out.println(_cardFront);
    }

    public void DisplayCardBack() {
        System.out.println(_cardBack);
    }

    public void DisplayCardInfo() {
        System.out.println("\n--------------------------------------------------------------------------");
        System.out.println("-- " + _cardFront + " --");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(_cardBack);
        System.out.println("--------------------------------------------------------------------------");
    }
}