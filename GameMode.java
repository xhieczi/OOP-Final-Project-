import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMode {
    // We do not close this scanner to avoid closing System.in for the main game
    static Scanner sc = new Scanner(System.in);
    static PrintStream prt = System.out;

    static int chooseGameMode() {
        prt.print("\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        // Make sure typePrint is public static in EncantadiaGame
        EncantadiaGame.typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \t\t\t\t\t   ✦✦✦ Choose Your Quest ✦✦✦\t\t\t\t\t\t  +", 8);
        EncantadiaGame.typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \t\t\t[1] Player vs Player (Manual Battle)\t\t\t\t\t      +", 8);
        EncantadiaGame.typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \t\t\t[2] Player vs Enemy (Normal Mode)\t\t\t\t\t          +", 8);
        EncantadiaGame.typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \t\t\t[3] Arcade Mode (Player vs All)\t\t\t\t\t              +", 8);

        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        int mode = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoose your battle: ");
            try {
                mode = sc.nextInt();
                sc.nextLine(); // Clear buffer

                if (mode >= 1 && mode <= 3) {
                    validInput = true;
                } else {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tInvalid quest! Please choose a mode between 1-3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tInvalid input! Enter a number between 1-3.");
                sc.nextLine(); // Clear invalid input
            }
        }

        String modeName = switch (mode) {
            case 1 -> "Player vs Player";
            case 2 -> "Player vs Enemy";
            case 3 -> "Arcade Mode";
            default -> "Unknown";
        };

        prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");
        EncantadiaGame.typePrint(String.format("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+        \t\t[🎮] You chose %-15s!        \t\t\t+\t\t\t ", modeName), 8);
        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        return mode;
    }
}
