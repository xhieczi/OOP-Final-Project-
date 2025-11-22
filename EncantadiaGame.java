import java.util.*;
import java.io.*;


public class EncantadiaGame {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static PrintStream prt = System.out;
    static showJelianAndAmihan JAAB = new showJelianAndAmihan();
    static showDirkandDanaya DAAB = new showDirkandDanaya();
    static showJoygenAndPerina JAPB = new showJoygenAndPerina();
    static showMaryAndAlena MAAB = new showMaryAndAlena();
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

    // Battle method with cooldown
    static boolean battle(Character player, Character enemy, int choice) {
        // Header
        prt.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 17; i2++) prt.print("=====");

        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t              [🌌][✨]You chose " + player.name + " [🌌][✨]             ", 1);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                   [⚔️] You will face " + enemy.name + "!                     ", 1);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                 The battle for the Brilyante of " + enemy.element + " begins!                     ", 1);

        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 17; i2++) prt.print("=====");
        prt.println("\n");

        // Show enemy backstory
        switch (enemy.name) {
            case "Amihan" -> JAAB.showBackstoryMain();
            case "Alena" -> MAAB.showBackstoryMain();
            case "Pirena" -> JAPB.showBackstoryOG();
            case "Danaya" -> DAAB.showBackstoryMain();
            case "Mary (Goddess of Tides)" -> MAAB.showBackstoryMain();
            case "Joygen (Goddess of Eternal Blaze)" -> JAPB.showBackstoryMain();
            case "Dirk (God of Living Soil)" -> DAAB.showBackstoryMain();
            case "Jelian (Goddess of Whispers)" -> JAAB.showBackstoryMain();
            default -> System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t [⚠️] No backstory available.");
        }

        // --- Cooldown setup ---
        int[] skillCooldown = new int[player.skills.length]; // cooldown duration for each skill
        int[] currentCooldown = new int[player.skills.length]; // turns left for each skill
        Arrays.fill(currentCooldown, 0); // all skills ready at start

        // Battle loop
        while (player.isAlive() && enemy.isAlive()) {

            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t              ═══════════════════════════════════════════════════");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                                    TURN " + turn + " ⚔️");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t              ═══════════════════════════════════════════════════");

            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t [⚔️] " + player.name + " HP: " + player.health + " | " +
                    enemy.name + " HP: " + enemy.health);

            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t ---------------------------------------------------------------", 10);

            // Player chooses skill
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t Choose a skill:");
            for (int i = 0; i < player.skills.length; i++) {
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + (i + 1) + ". " + player.skills[i] +
                        "  🔥 Damage: " + player.damage[i]);
                if (currentCooldown[i] > 0) System.out.print("  ⏳ Cooldown: " + currentCooldown[i] + " turn(s)");
                System.out.println();
            }

            System.out.println();
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t Cast your skill: ");

            int skillChoice;
            try {
                skillChoice = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                sc.nextLine(); // clear invalid input
                skillChoice = -1; // treat as a miss
            }

            // Validate skill choice
            if (skillChoice < 1 || skillChoice > player.skills.length) {
                typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t You missed your attack! [😱]", 15);
            } else if (currentCooldown[skillChoice - 1] > 0) {
                typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t That skill is on cooldown! Please choose another skill.", 15);
            } else {
                int dmg = player.damage[skillChoice - 1];
                typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + player.name + " used " + player.skills[skillChoice - 1] + "!", 10);
                enemy.health -= dmg;
                if (enemy.health < 0) enemy.health = 0;
                typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t [💥] " + enemy.name + " took " + dmg + " damage! Remaining HP: " + enemy.health, 10);

                // Set cooldown for used skill
                currentCooldown[skillChoice - 1] = skillCooldown[skillChoice - 1] = 2; // example: 2-turn cooldown
            }

           ✅ // Enemy attack (manual mode)
if (enemy.isAlive()) {
    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t Enemy's turn!");
    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t Choose a skill for " + enemy.name + ":");

    for (int i = 0; i < enemy.skills.length; i++) {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + (i + 1) + ". " + enemy.skills[i] +
                "  💥 Damage: " + enemy.damage[i]);
    }

    int enemySkill = -1;
    boolean validEnemyChoice = false;

    while (!validEnemyChoice) {
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t Enemy casts: ");
        try {
            enemySkill = sc.nextInt() - 1;
            if (enemySkill >= 0 && enemySkill < enemy.skills.length) {
                validEnemyChoice = true;
            } else {
                System.out.println("\t\t\t\t\t\t Invalid choice! Choose 1-" + enemy.skills.length + ".");
            }
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("\t\t\t\t\t\t Invalid input! Enter a number.");
        }
    }

    int dmg = enemy.damage[enemySkill];
    typePrint("\t\t\t\t\t\t " + enemy.name + " used " + enemy.skills[enemySkill] + "!", 10);

    player.health -= dmg;
    if (player.health < 0) player.health = 0;

    typePrint("\t\t\t\t\t\t [🔥] " + player.name + " took " + dmg + " damage! Remaining HP: " + player.health, 10);
}

            // Reduce all cooldowns by 1 at the end of turn
            for (int i = 0; i < currentCooldown.length; i++) {
                if (currentCooldown[i] > 0) currentCooldown[i]--;
            }

            turn++;
        }

        if (player.isAlive()) {
            typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🏆] " + player.name + " has defeated " + enemy.name + "!", 15);
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t You reclaimed the Brilyante of " + enemy.element + "! [✨]", 15);
            return true;
        } else {
            typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t [💀] " + player.name + " has fallen... The Brilyante remains with " + enemy.name + ".", 20);
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
        prt.print("\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        typePrint("  ✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦", 8); // booting purposes
        prt.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        typePrint("       ☁  ⛰   \uD835\uDD08\uD835\uDD2B\uD835\uDD20\uD835\uDD1E\uD835\uDD2B\uD835\uDD31\uD835\uDD1E\uD835\uDD21\uD835\uDD26\uD835\uDD1E : \uD835\uDD4B\uD835\uDD66\uD835\uDD63\uD835\uDD5F  \uD835\uDD60\uD835\uDD57  \uD835\uDD65\uD835\uDD59\uD835\uDD56  \uD835\uDD3E\uD835\uDD56\uD835\uDD5E\uD835\uDD64  \uD83C\uDF0A \uD83D\uDD25 ", 8);
        prt.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        typePrint("  ✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦", 8);
        prt.print("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i1 = 1; i1 <= 15; i1++) {
            prt.print("======");
        }

        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \t\t[🌌][✨]Avisala! Maligayang paglalakbay sa mundo ng Encantadia![✨][🌌]\t\t +\t\t\t\t\t\t", 5);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \t\tLegends whisper of heroes who shaped the fate of kingdoms...             \t\t +\t\t\t\t\t\t\t\t", 5);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \t\tDo you dare take the first step into destiny?                            \t\t +\t\t\t\t\t\t\t\t", 5);
        prt.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                                                                                 \t\t +\t\t\t\t\t\t\t\t");
        prt.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \t\t------------------------------------------------------------------------ \t\t +\t\t\t\t\t\t\t\t");
        prt.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                                                                                 \t\t +\t\t\t\t\t\t\t\t");
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [⚔️] Press 1 to begin your journey.                                            \t\t +\t\t\t\t\t\t\t\t ", 5);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [❌] Press any other key to turn back and remain in the ordinary world.        \t\t +\t\t\t\t\t\t\t\t", 5);
        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 15; i2++) {
            prt.print("======");
        }
        typePrintInline("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  [👉] Choose Your Fate: ", 8);
        String start = sc.nextLine();

        if (!start.equals("1")) {
            prt.println();
            prt.println();
            prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            for (int i2 = 1; i2 <= 17; i2++) {
                prt.print("======");
            }

            prt.println();
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+        ✰            ᚢ                                ☩                           +", 3);
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ The gates are closing. Encantadia shall await another soul brave enough to enter. +", 3);
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                    ⊕        ⊙                              ✦                      +", 3);
            prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            for (int i2 = 1; i2 <= 17; i2++) {
                prt.print("======");
            }
            return;
        }

        boolean validInput = false;

        while (!validInput) {
            try {
                prt.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [Press [1] to Discover \uD83E\uDEB6 or [0] to Skip ⚔\uFE0F and Start the Game]");
                prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                for (int i1 = 1; i1 <= 18; i1++) {
                    prt.print("=====");
                }

                prt.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Enter your choice: ");
                String input = sc.nextLine().trim();

                if (input.equals("0")) {
                    validInput = true;
                    System.out.println(" ");
                    prt.println("\t\t\t\t\t\t\t\t                                                       Skipping... Maghanda mandirigma!");
                    break;
                } else if (input.equals("1")) {
                    validInput = true;
                    prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                    for (int i1 = 1; i1 <= 18; i1++) {
                        prt.print("=====");
                    }
                    typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+         ------------------------- ✨ ANG NAKARAAN ✨ -------------------------        +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                                                                                        +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+         Nonong Imaw: Long ago, there was peace in the kingdoms of Encantadia...        +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+       Under the guidance of the Queen and her four Sang’gres, harmony reigned.         +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+         But darkness soon came, for the Queen was taken by a mysterious rival...       +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+           The realms fell into despair, and the balance of power was broken.           +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                                                                                        +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+      To discover the Queen’s fate, the Sang’gres must seek the four great Brilyantes   +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+           the powerful gems that give life and strength to Encantadia itself.          +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+       Only by uniting these Brilyantes shall the truth be revealed and peace be...     +", 10);
                    typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+                                 restored once more. [⚔️]                               +", 10);
                    prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                    for (int i1 = 1; i1 <= 18; i1++) {
                        prt.print("=====");
                    }
                    break;
                } else {
                    throw new IllegalArgumentException("\t\t\t\t\t\t\t                                               Invalid Input!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(" ");
                System.out.println("\t\t\t\t\t\t\t                                                       Ashtadi! Please enter only [1] or [0].");
            } catch (Exception e) {
                System.out.println(" ");
                System.out.println("\t\t\t\t\t\t\t                                                       Patawad... Please try again.");
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
        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+\t\t  ~ ~ ~ ~ ~ ~ ~ ~$[Lair of the Sang'gres]$~ ~ ~ ~ ~ ~ ~ ~\t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+\t\t                                                         \t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+\t\t                                                         \t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [1] Jelian (Goddess of Whispers)\t\t\t\t\t\t\t\t\t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [2] Mary (Goddess of Tides)\t\t\t\t\t\t\t\t\t\t      +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [3] Joygen (Goddess of Eternal Blaze) \t\t\t\t\t\t\t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ [4] Dirk (God of Living Soil)\t\t\t\t\t\t\t\t\t\t      +", 8);
        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 15; i2++) {
            prt.print("=====");
        }
        int choice = -1;
        boolean validChoice = false;

        while (!validChoice) {
            typePrintInline("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   Choose your Sang'gre: ", 3);
            try {
                // read the entire line and trim it
                if (!sc.hasNextLine()) {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      No input available. Exiting.");
                    break;
                }
                String line = sc.nextLine().trim();

                // try parse integer
                choice = Integer.parseInt(line);

                // validate range (1..4)
                if (choice < 1 || choice > 4) {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      Please enter a number between 1 and 4.");
                    continue;
                }

                validChoice = true; // valid numeric input in range, exit loop

            } catch (NumberFormatException e) {
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      Invalid input! Please enter a number between 1 and 4.");
            }
        }

        prt.println("\n\n");
        Character player = null;
        Character[] enemies = null;

// ✅ Player setup
        switch (choice) {
            case 1 -> {
                player = Jelian;
                JAAB.showBackstoryMain();
            }
            case 2 -> {
                player = Mary;
                MAAB.showBackstoryMain();
            }

            case 3 -> {
                player = Joygen;
                JAPB.showBackstoryMain();
            }
            case 4 -> {
                player = Dirk;
                DAAB.showBackstoryMain();
            }
            default -> {
                prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                for (int i2 = 1; i2 <= 15; i2++) prt.print("=====");
                prt.println();
                typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ That Sang'gre does not exist! Encantadia is lost without a champion...  +", 10);
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
                typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Encantadia is lost in darkness... [💀]", 15);
                return;
            }

            brilyantesCollected++;
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t You now hold " + brilyantesCollected + " Brilyante(s).", 15);

            if (brilyantesCollected < 4) {
                boolean isValid = false;

                while (!isValid) {
                    try {
                        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Do you wish to continue your quest for the remaining Brilyantes? (Yes/No): ");
                        String cont = sc.next().toLowerCase();

                        if (cont.equals("yes")) {
                            isValid = true;

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

                            typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [💪] Your strength grows as your quest continues!", 15);

                        } else if (cont.equals("no")) {
                            isValid = true;
                            typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🌙] Avisala Eshma! Encantadia awaits your return...", 15);
                            return;
                        } else {
                            // Invalid text input, not yes/no
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Invalid response! Please type only 'Yes' or 'No'.");
                        }

                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Patawad... Invalid input!.");
                    }
                }
            }
        }
        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🌟] Congratulations! You have united all 4 Brilyantes!", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t The truth is revealed... and peace returns to Encantadia! [✨]", 15);

    }
}
