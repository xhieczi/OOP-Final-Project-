import java.io.PrintStream;

public class RoundCounter {
    private int round;
    private int player1Wins;
    private int player2Wins;

    // Assuming printingAsciiArts is in the same package
    public printingAsciiArts pr = new printingAsciiArts();

    public RoundCounter() {
        this.round = 1;
        this.player1Wins = 0;
        this.player2Wins = 0;
    }

    public int getRound() {
        return round;
    }

    public void player1WinsRound() {
        player1Wins++;
        round++;
    }

    public void player2WinsRound() {
        player2Wins++;
        round++;
    }

    public boolean isMatchOver() {
        // Best of 3 means the first to reach 2 wins
        return player1Wins >= 2 || player2Wins >= 2;
    }

    // Displays Round AND Current Score
    public void displayRoundScores(EncantadiaGame.Character player1, EncantadiaGame.Character player2) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        pr.tabPrinter(19);
        EncantadiaGame.typePrint("   ========================================================", 8);
        pr.tabPrinter(23);
        // Added Score Display here:
        System.out.println("           ROUND " + round + "  |  SCORE: [" + player1Wins + " - " + player2Wins + "]");
        pr.tabPrinter(19);
        EncantadiaGame.typePrint("   =========================================================", 8);
    }

    public EncantadiaGame.Character getMatchWinner(EncantadiaGame.Character player1, EncantadiaGame.Character player2) {
        if (player1Wins >= 2) return player1;
        else if (player2Wins >= 2) return player2;
        else return null;
    }
}
