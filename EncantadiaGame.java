import java.util.*;
import java.io.*;

public class EncantadiaGame {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static PrintStream prt = System.out;
    static showJelianAndAmihan JAAB = new showJelianAndAmihan();
    static int turn = 1;

    // Typewriter effect
    static void typePrint(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // Character class
    static class Character {
        String name;
        String element;
        int health = 500;
        String[] skills;
        int[] damage;
        int[] manaCost;

        Character(String name, String element, String[] skills, int[] damage, int[] manaCost) {
            this.name = name;
            this.element = element;
            this.skills = skills;
            this.damage = damage;
            this.manaCost = manaCost;
        }

        boolean isAlive() {
            return health > 0;
        }
    }

    // Battle method
    static boolean battle(Character player, Character enemy, int choice) {
        // here backstory
        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 17; i2++) {
            prt.print("=====");
        }
        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [🌌][✨]You chose " + player.name + " [🌌][✨] +", 1);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [⚔️] You will face " + enemy.name + "!      +", 1);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ The battle for the Brilyante of " + enemy.element + " begins!", 1);

        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 17; i2++) {
            prt.print("=====");
        }

        switch (choice) {
            case 1 -> {
                JAAB.showBackstoryMain();
                break;
            }
            case 2 -> {
                break;
            }
        }

        // added number of turns here - MryyClrr
        while (player.isAlive() && enemy.isAlive()) {

            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t═══════════════════════════════════════════════════");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                      TURN " + turn + " ⚔️");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t═══════════════════════════════════════════════════");

            System.out.println("\n[⚔️] " + player.name + " HP: " + player.health + " | " +
                    enemy.name + " HP: " + enemy.health);

            typePrint("---------------------------------------------------------", 10);

            // Player chooses skill
            System.out.println("Choose a skill:");
            for (int i = 0; i < player.skills.length; i++) {
                // ✅ Display damage cost
                System.out.println((i + 1) + ". " + player.skills[i] +
            "  🔥 Damage: " + player.damage[i]);
            }
            System.out.println();
            System.out.print("Enter your choice: ");
            int skillChoice = sc.nextInt();

            if (skillChoice < 1 || skillChoice > player.skills.length) {
                typePrint("You missed your attack! [😱]", 15);
            } else {
                int dmg = player.damage[skillChoice - 1];
                System.out.println();

                typePrint(player.name + " used " + player.skills[skillChoice - 1] + "!", 10);
                enemy.health -= dmg;
                System.out.println("---------------------------------------------------------");
                if (enemy.health < 0) enemy.health = 0;
                typePrint("[💥] " + enemy.name + " took " + dmg + " damage! Remaining HP: " + enemy.health, 10);
            }

            // Enemy counterattack
            if (enemy.isAlive()) {
                int enemySkill = rand.nextInt(enemy.skills.length);
                int dmg = enemy.damage[enemySkill];
                typePrint(enemy.name + " used " + enemy.skills[enemySkill] + "!", 10);
                player.health -= dmg;
                if (player.health < 0) player.health = 0;
                typePrint("[🔥] " + player.name + " took " + dmg + " damage! Remaining HP: " + player.health, 10);
            }
            turn++;
        }

        if (player.isAlive()) {
            typePrint("\n[🏆] " + player.name + " has defeated " + enemy.name + "!", 15);
            typePrint("You reclaimed the Brilyante of " + enemy.element + "! [✨]", 15);
            return true;
        } else {
            typePrint("\n[💀] " + player.name + " has fallen... The Brilyante remains with " + enemy.name + ".", 20);
            return false;
        }
    }
    //First
    // Typewriter effect (inline, no newline at the end), for proper input
    static void typePrintInline(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // no println here
    }

    public static void main(String[] args) {
        // Welcome screen
        //Start of Menu Page Program
        prt.print("\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        typePrint("  ✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦", 8); // booting purposes
        prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        typePrint("    ☁  ⛰   \uD835\uDD08\uD835\uDD2B\uD835\uDD20\uD835\uDD1E\uD835\uDD2B\uD835\uDD31\uD835\uDD1E\uD835\uDD21\uD835\uDD26\uD835\uDD1E : \uD835\uDD4B\uD835\uDD66\uD835\uDD63\uD835\uDD5F  \uD835\uDD60\uD835\uDD57  \uD835\uDD65\uD835\uDD59\uD835\uDD56  \uD835\uDD3E\uD835\uDD56\uD835\uDD5E\uD835\uDD64  \uD83C\uDF0A \uD83D\uDD25 ", 8);
        prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        typePrint("  ✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦", 8);
        prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i1 = 1; i1 <= 15; i1++) {
            prt.print("=====");
        }

        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+[🌌][✨]Avisala! Maligayang paglalakbay sa mundo ng Encantadia![✨][🌌] +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+       Legends whisper of heroes who shaped the fate of kingdoms...      +" , 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+              Do you dare take the first step into destiny?              +", 8);
        prt.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                                                                         +");
        prt.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+         ---------------------------------------------------------       +");
        prt.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                                                                         +");
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [⚔️] Press 1 to begin your journey.                                     + ", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [❌] Press any other key to turn back and remain in the ordinary world. +", 8);
        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 15; i2++) {
            prt.print("=====");
        }
        typePrintInline("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t[👉] Choose Your Fate: ", 8);
        String start = sc.nextLine();
        //End of Menu Page Program

        if (!start.equals("1")) {
            prt.println();
            prt.println();
            prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            for (int i2 = 1; i2 <= 17; i2++) {
                prt.print("=====");
            }

            prt.println();
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+         ✰            ᚢ                                ☩                          +", 3);
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ The gates are closing. Encantadia shall await another soul brave enough to enter. +", 8);
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                    ⊕        ⊙                              ✦                      +", 3);
            prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            for (int i2 = 1; i2 <= 17; i2++) {
                prt.print("=====");
            }
            return;
        }

        boolean validInput = false;

        while (!validInput) {
            try {
                prt.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t       [Press [1] to Discover \uD83E\uDEB6 or [0] to Skip ⚔\uFE0F and Start the Game]");
                prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                for (int i1 = 1; i1 <= 18; i1++) {
                    prt.print("=====");
                }

                prt.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t       Enter your choice: ");
                String input = sc.nextLine().trim();

                if (input.equals("0")) {
                    validInput = true;
                    System.out.println(" ");
                    prt.println("                                                                                     Skipping... Maghanda mandirigma!");
                    break;
                } else if (input.equals("1")) {
                    validInput = true;
                    prt.println();
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+            ------------------------- ✨ ANG NAKARAAN ✨ -------------------------                 +", 20);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                                                                                                   +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+            Nonong Imaw: Long ago, there was peace in the kingdoms of Encantadia...                +", 20);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+            Under the guidance of the Queen and her four Sang’gres, harmony reigned.               +", 20);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+            But darkness soon came, for the Queen was taken by a mysterious rival...               +", 20);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+            The realms fell into despair, and the balance of power was broken.                     +", 20);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                                                                                                   +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+            To discover the Queen’s fate, the Sang’gres must seek the four great Brilyantes—       +", 20);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+            the powerful gems that give life and strength to Encantadia itself.                    +", 20);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+            Only by uniting these Brilyantes shall the truth be revealed and peace be...                        +", 20);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+            restored once more. [⚔️]                                                               +", 20);
                    prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                    for (int i1 = 1; i1 <= 18; i1++) {
                        prt.print("=====");
                    }
                    break;
                } else {
                    throw new IllegalArgumentException("                                                                   Invalid Input!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(" ");
                System.out.println("                                                                                     Ashtadi! Please enter only [1] or [0].");
            } catch (Exception e) {
                System.out.println(" ");
                System.out.println("                                                                                     Patawad... Please try again.");
                sc.nextLine();
            }
        }

        int gameMode = GameMode.chooseGameMode();

// New characters (players)
        Character Jelian = new Character("Jelian (Goddess of Whispers)", "Air",
                new String[]{"Carbon Dioxide", "Bad Breathe", "Utot"},
                new int[]{40, 55, 70},
                new int[]{20, 30, 40});

        Character Mary = new Character("Mary (Goddess of Tides)", "Water",
                new String[]{"Luha", "Flood Control", "Tsunami"},
                new int[]{35, 50, 65},
                new int[]{15, 25, 35});

        Character Joygen = new Character("Joygen (Goddess of Eternal Blaze)", "Fire",
                new String[]{"In Heat", "Thirstrap", "Wow wow wow naay nasunog"},
                new int[]{45, 60, 80},
                new int[]{20, 35, 50});

        Character Dirk = new Character("Dirk (God of Living Soil)", "Earth",
                new String[]{"Bato Dela Rosa", "Ding ang Bato", "Linog"},
                new int[]{40, 55, 75},
                new int[]{15, 30, 40});

// Original Sang’gres (enemies)
        Character Pirena = new Character("Pirena", "Fire",
                new String[]{"Flame fury", "Hyperventilation", "Impyerno"},
                new int[]{45, 60, 80},
                new int[]{20, 35, 50});

        Character Amihan = new Character("Amihan", "Air",
                new String[]{"Wind Slash", "Storm Fury", "Whirlwind Strike"},
                new int[]{40, 55, 70},
                new int[]{20, 30, 40});

        Character Alena = new Character("Alena", "Water",
                new String[]{"Water Spear", "Ocean Wave", "Tsunami Blast"},
                new int[]{35, 50, 65},
                new int[]{15, 25, 35});

        Character Danaya = new Character("Danaya", "Earth",
                new String[]{"Rock Smash", "Earthquake", "Nature’s Wrath"},
                new int[]{40, 55, 75},
                new int[]{15, 30, 40});

// Choose player (same as before)
        prt.print("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 15; i2++) {
            prt.print("=====");
        }
        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+\t\t  ~ ~ ~ ~ ~ ~ ~ ~$[Lair of the Sang'gres]$~ ~ ~ ~ ~ ~ ~ ~\t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+\t\t                                                         \t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+\t\t                                                         \t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [1] Jelian (Goddess of Whispers)\t\t\t\t\t\t\t\t\t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [2] Mary (Goddess of Tides)\t\t\t\t\t\t\t\t\t\t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [3] Joygen (Goddess of Eternal Blaze)\t\t\t\t\t\t\t\t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [4] Dirk (God of Living Soil)\t\t\t\t\t\t\t\t\t\t\t  +", 8);
        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 15; i2++) {
            prt.print("=====");
        }
        typePrintInline("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoose your Sang'gre: ", 3);
        int choice = sc.nextInt();

// Loading spacing
        typePrint("                         \n", 4);
        typePrint("                         \n", 4);
        typePrint("                         \n", 4);

        Character player = null;
        Character[] enemies = null;

// ✅ Player setup
        switch (choice) {
            case 1 -> { player = Jelian; JAAB.showBackstoryMain(); }
            case 2 -> player = Mary;
            case 3 -> player = Joygen;
            case 4 -> player = Dirk;
            default -> {
                prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                for (int i2 = 1; i2 <= 15; i2++) prt.print("=====");
                prt.println();
                typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ That Sang'gre does not exist! Encantadia is lost without a champion...  +", 10);
                for (int i2 = 1; i2 <= 15; i2++) prt.print("=====");
                return;
            }
        }

// ✅ Choose enemies based on game mode
        if (gameMode == 1) {
            // Player vs Player
            enemies = new Character[]{Mary, Dirk, Joygen};
        } else if (gameMode == 2) {
            // Player vs Enemy
            enemies = new Character[]{Pirena, Amihan, Alena, Danaya};
        } else if (gameMode == 3) {
            // Arcade mode - fight all
            enemies = new Character[]{Mary, Dirk, Joygen, Pirena, Amihan, Alena, Danaya};
        } else {
            // fallback
            enemies = new Character[]{Pirena, Amihan, Alena, Danaya};
        }





        // Brilyante quest loop
        int brilyantesCollected = 0;

        for (Character en : enemies) {
            en.health = 500; // reset enemy health

            boolean won = battle(player, en, choice);
            if (!won) {
                typePrint("Encantadia is lost in darkness... [💀]", 15);
                return;
            }

            brilyantesCollected++;
            typePrint("You now hold " + brilyantesCollected + " Brilyante(s).", 15);

            if (brilyantesCollected < 4) {
                System.out.print("\nDo you wish to continue your quest for the remaining Brilyantes? (Yes/No): ");
                String cont = sc.next().toLowerCase();
                if (!cont.equals("yes")) {
                    typePrint("\n[🌙] You chose to rest. Encantadia awaits your return...", 15);
                    return;
                } else {
                    // Power up player
                    for (int i = 0; i < player.damage.length; i++) {
                        player.damage[i] += 10;
                    }
                    player.health = 500;

                    // Power up all enemies slightly
                    for (Character foe : enemies) {
                        for (int j = 0; j < foe.damage.length; j++) {
                            foe.damage[j] += 5;
                        }
                    }
                    typePrint("\n[💪] Your strength grows as your quest continues!", 15);
                }
            }
        }

        // Victory if all Brilyantes collected
        typePrint("\n[🌟] Congratulations! You have united all 4 Brilyantes!", 15);
        typePrint("The truth is revealed... and peace returns to Encantadia! [✨]", 15);
    }
}
