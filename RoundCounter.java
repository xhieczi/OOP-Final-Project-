public class RoundCounter {
    private int round;
    private int player1Wins;
    private int player2Wins;

    public printingAsciiArts pr = new printingAsciiArts();

    public RoundCounter() {
        this.round = 1;
        this.player1Wins = 0;
        this.player2Wins = 0;
    }

    public int getRound() {
        return round;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }

    public void player1WinsRound() {
        player1Wins++;
        round++;
    }

    public void player2WinsRound() {
        player2Wins++;
        round++;
    }

    public void reset() {
        round = 1;
        player1Wins = 0;
        player2Wins = 0;
    }

    public void displayRoundScores(EncantadiaGame.Character player1, EncantadiaGame.Character player2) {
        System.out.println();
        pr.tabPrinter(20);
        EncantadiaGame.typePrint("========================================================",8);
        pr.tabPrinter(20);
        System.out.println("        ROUND " + round);
        pr.tabPrinter(4);
        System.out.println("Score: " + player1.name + " [" + player1Wins + "] - " + player2.name + " [" + player2Wins + "]");
        pr.tabPrinter(20);
        EncantadiaGame.typePrint("=========================================================",8);
    }

    public boolean isMatchOver() {
        return player1Wins >= 2 || player2Wins >= 2;
    }

    public EncantadiaGame.Character getMatchWinner(EncantadiaGame.Character player1, EncantadiaGame.Character player2) {
        if (player1Wins >= 2) return player1;
        else if (player2Wins >= 2) return player2;
        else return null;
    }
}
