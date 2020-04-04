import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private String name;                //numele jucatorului
    private Board playerGameBoard;      //board-ul jocului la care e asignat
    private List<Token> tokens;         //lista proprie de tokens extrasi de pe board in timpul jocului

    public Player(String name, Board board) {
        this.name = name;
        this.playerGameBoard = board;
        tokens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getTokensSize() {
        return tokens.size();
    }

    /**
     * Returneaza numarul de pe token
     * @param index indicele token-ului ce trebuie returnat
     * @return numarul reprezentat pe token-ul de la pozitia index
     */
    public int getTokenByIndex(int index) {
        return tokens.get(index).getNumber();
    }

    /***
     * Metoda ce trebuie suprascrisa din interfata Runnable
     * Implementarea ei preia din lista de token-uri din board in mod repetat pana nu mai este nici un token
     */
    @Override
    public void run() {
        Token token = playerGameBoard.getToken();

        while (token != null) {
            tokens.add(token);
            token = playerGameBoard.getToken();
        }
    }
}
