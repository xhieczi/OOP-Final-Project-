public class RoundCounter {
    private int round;

    public printingAsciiArts pr = new printingAsciiArts();
;    public RoundCounter() {
        this.round = 1;
    }

    public int getRound() {
        return round;
    }

    public void nextRound() {
        round++;
    }

    public void reset() {
        round = 1;
    }

    public void displayRound() {
        System.out.println();
        pr.tabPrinter(24);
        EncantadiaGame.typePrint("==============================",8);
        pr.tabPrinter(24);
        System.out.println("        ROUND " + round);
        pr.tabPrinter(24);
        EncantadiaGame.typePrint("==============================",8);
    }
}
