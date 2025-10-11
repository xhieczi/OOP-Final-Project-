import java.io.PrintStream;
import java.util.Scanner;

public class GameMode {
    static Scanner sc = new Scanner(System.in);
    static PrintStream prt = System.out;

    static int chooseGameMode() {
        prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");


        EncantadiaGame.typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ ✦✦✦ Choose Your Game Mode ✦✦✦ +", 8);
        EncantadiaGame.typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [1] Player vs Player (Manual Battle)  +", 8);
        EncantadiaGame.typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [2] Player vs Enemy (Normal Mode)     +", 8);
        EncantadiaGame.typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [3] Arcade Mode (Player vs All)       +", 8);

        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        EncantadiaGame.typePrintInline("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice: ", 3);

        int mode = sc.nextInt();
        sc.nextLine(); // clear buffer

        String modeName = switch (mode) {
            case 1 -> "Player vs Player (Manual Battle)";
            case 2 -> "Player vs Enemy (Normal Mode)";
            case 3 -> "Arcade Mode (Player vs All)";
            default -> "Invalid Mode";
        };

        prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        EncantadiaGame.typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [🎮] You chose " + modeName + "! +", 8);

        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i = 1; i <= 15; i++) prt.print("=====");

        return mode;
    }
}

