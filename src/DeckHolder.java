import java.util.List;
import java.util.ArrayList;

public class DeckHolder {
    private List<FlashCardDeck> _deckHolder = new ArrayList<>();

    public DeckHolder() {

    }

    public int GetDeckCount() {
        return _deckHolder.size();
    }

    public void DisplayDeckNames() {
        for (int i = 0; i < _deckHolder.size(); i++) {
            System.out.print((i + 1) + ". ");
            _deckHolder.get(i).DisplayDeckName();
        }
    }

    public FlashCardDeck SelectCardDeck(int deckChoice) {
        return _deckHolder.get(deckChoice - 1);
    }

    public void AddDeck(FlashCardDeck deck) {
        _deckHolder.add(deck);
    }
}
