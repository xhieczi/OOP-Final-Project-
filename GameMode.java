import java.io.PrintStream;
import java.util.Scanner;

public class GameMode {
    static Scanner sc = new Scanner(System.in);
    static PrintStream prt = System.out;

    static int chooseGameMode() {
        prt.print("\n\n\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        EncantadiaGame.typePrint("\n\t\t\t\t\t\t\t\t\t\t+ \t\t\t\t\t\t✦✦✦ Choose Your Game Mode ✦✦✦\t\t\t\t\t  +", 8);
        EncantadiaGame.typePrint("\t\t\t\t\t\t\t\t\t\t+ \t\t\t[1] Player vs Player (Manual Battle)\t\t\t\t\t      +", 8);
        EncantadiaGame.typePrint("\t\t\t\t\t\t\t\t\t\t+ \t\t\t[2] Player vs Enemy (Normal Mode)\t\t\t\t\t          +", 8);
        EncantadiaGame.typePrint("\t\t\t\t\t\t\t\t\t\t+ \t\t\t[3] Arcade Mode (Player vs All)\t\t\t\t\t              +", 8);

        prt.print("\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        int mode = -1;
        boolean validInput = false;

        while (!validInput) {
            EncantadiaGame.typePrintInline("\n\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice: ", 3);
            try {
                mode = sc.nextInt();
                sc.nextLine(); // clear buffer
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Invalid input! Please enter from (1–3) only.");
                sc.nextLine();
            }
        }

        String modeName = switch (mode) {
            case 1 -> "Player vs Player (Manual Battle)";
            case 2 -> "Player vs Enemy (Normal Mode)";
            case 3 -> "Arcade Mode (Player vs All)";
            default -> "Invalid Mode";
        };

        prt.print("\n\n\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        EncantadiaGame.typePrint("\n\t\t\t\t\t\t\t\t\t\t +        [🎮] You chose " + modeName    + "!        +\t\t\t ", 8);

        prt.print("\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        return mode;
    }
}

