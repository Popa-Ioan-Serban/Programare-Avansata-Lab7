public class Main {
    /**
     * Se creeaza un board nou cu 500 de token-uri numerotate de la 1 la 500
     * Se apeleaza o instanta a jocului cu board-ul creeat anterior si 3 playeri cu numele "player1", "player2" si "player3"
     * iar la final se afiseaza rezultatul
     * @param args
     */
    public static void main(String[] args) {
        Board board = new Board(500);
        board.fillTokens();

        Game game = new Game(board, "player1", "player2", "player3");
        game.startGame();
        game.showWinner();
    }
}
