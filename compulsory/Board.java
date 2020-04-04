import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Board {
    private int numberOfTokens;         //numarul maxim de token-uri din board-ul curent
    private final List<Token> tokens;   //lista de token-uri din board-ul curent

    public Board(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
        tokens = new LinkedList<>();
    }

    /**
     * Implementarea din cerinta a token-urilor (initializarea cu numere de la 1 la numberOfTokens)
     */
    public void fillTokens() {
        Token new_token;

        for (int i = 1; i <= numberOfTokens; i++) {
            new_token = new Token(numberOfTokens);
            new_token.setNumber(i);
            tokens.add(new_token);
        }
    }

    /**
     * Sterge un element din lista de token-uri
     * @return elementul care a fost sters sau null in cazul in care nu mai exista elemente in lista
     */
    public Token getToken() {
        try {
            synchronized (tokens) {
                return ((LinkedList<Token>) tokens).removeFirst();
            }
        }
        catch (NoSuchElementException ex) {
            return null;
        }
    }
}
