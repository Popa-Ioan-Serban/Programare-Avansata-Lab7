import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {
    private Board board;                    //board-ul asignat jocului
    private List<Player> players;           //lista cu playeri ce sunt implicati in joc in acest moment
    private List<Thread> playerThreads;     //lista cu thread-uri ce reprezinta fiecare player

    /**
     * Constructorul care primeste un board si o lista de nume si initializeaza si creaza thread-urile de care avem nevoie
     * mai tarziu in executia jocului
     * @param board board-ul de pe care vor fi extrase token-urile de catre jucatori
     * @param playersNameList lista cu numele jucatorilor
     */
    public Game(Board board, String ... playersNameList) {
        this.board = board;
        players = new ArrayList<>(playersNameList.length);
        playerThreads =  new ArrayList<>(playersNameList.length);

        for (String playerName: playersNameList) {
            Player newPlayer = new Player(playerName, this.board);
            players.add(newPlayer);
            playerThreads.add(new Thread(newPlayer));
        }
    }

    /**
     * Metoda (principala) de start a jocului care porneste thread-urile si astepta la final sa se termine toate pentru a
     * se putea face calculele ce vor oferi castigatorul jocului
     */
    public void startGame() {
        for (Thread playerThread: playerThreads) {
            playerThread.start();
        }

        for (Thread playerThread: playerThreads) {
            try {
                playerThread.join();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metoda ce calculeaza cea mai lunga progresie aritmetica a unui jucator memeorand intr-o matrice dp diferenta dintre
     * fiecare dintre token-urile extrase de el
     * @param player player-ul ce va fi verificat
     * @return maxim-ul de diferente egale ce reprezinta numarul de elemente din cea mai lunga progresie aritmetica
     */
    private int longestArithmeticProgressionLength(Player player) {
        int len = player.getTokensSize();
        int max = 1;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = 2;
                int diff = player.getTokenByIndex(j) - player.getTokenByIndex(i);
                for (int k = i - 1; k >= 0; k--) {
                    if (player.getTokenByIndex(i) - player.getTokenByIndex(k) == diff) {
                        dp[i][j] = Math.max(dp[i][j], dp[k][i] + 1);
                        break;
                    }
                }
                if (max < dp[i][j])
                    max = dp[i][j];
            }
        }
        return max;
    }

    /**
     * Castigatorul este calculat prin preluarea jucatorului cu lungimea progresiei aritmetice maxima din lista jucatorilor
     * disponibili ce au participat la joc
     */
    public void showWinner() {
        Player winnerPlayer = null;
        int maxAP = -1, newAP;

        for (Runnable player: players) {
            newAP = longestArithmeticProgressionLength((Player) player);
            if (maxAP < newAP) {
                winnerPlayer = (Player) player;
                maxAP = newAP;
            }
        }

        try {
            System.out.println("Winner is " + winnerPlayer.getName() + " with an arithmetic progression of length " + maxAP);
        }
        catch (NullPointerException ex) {
            System.out.println("An unexpected error occurs!");
        }
    }
}
