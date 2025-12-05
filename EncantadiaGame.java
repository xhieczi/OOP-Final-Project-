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
    static printingAsciiArts paa = new printingAsciiArts();
    static int turn = 1;
    static RoundCounter counter = new RoundCounter(0, 0, 1);
    private static Character player;
    private static int choice;


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
        String[] skills;
        int[] manaCost;
        int[][] damageRange; // damage min-max for each skill
        int health = 500;    // player's current HP

        Character(String name, String element, String[] skills, int[] manaCost, int[][] damageRange) {
            this.name = name;
            this.element = element;
            this.skills = skills;
            this.manaCost = manaCost;
            this.damageRange = damageRange;
        }

        boolean isAlive() {
            return health > 0;
        }

        public int getRandomDamage(int i) {
            int min = damageRange[i][0];
            int max = damageRange[i][1];
            return min + (int)(Math.random() * (max - min + 1));
        }
    }



    // Battle method with cooldown and randomized damage
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
            case "Amihan" -> JAAB.showBackstoryOG();
            case "Alena" -> MAAB.showBackstoryOG();
            case "Pirena" -> JAPB.showBackstoryOG();
            case "Danaya" -> DAAB.showBackstoryOG();
            case "Mary (Goddess of Tides)" -> MAAB.showBackstoryMain();
            case "Joygen (Goddess of Eternal Blaze)" -> JAPB.showBackstoryMain();
            case "Dirk (God of Living Soil)" -> DAAB.showBackstoryMain();
            case "Jelian (Goddess of Whispers)" -> JAAB.showBackstoryMain();
            default -> System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t [⚠️] No backstory available.");
        }

        // --- Cooldown setup ---
        int[] skillCooldown = new int[player.skills.length];
        skillCooldown[0] = 0; // Skill 1: no cooldown
        skillCooldown[1] = 2; // Skill 2: 2-turn cooldown
        skillCooldown[2] = 3; // Skill 3: 3-turn cooldown

        int[] currentCooldown = new int[player.skills.length]; // turns left for each skill
        Arrays.fill(currentCooldown, 0);


        int turn = 1;
        int round = 1;

        while (player.isAlive() && enemy.isAlive()) {
            Character player1 = null, player2 =null;
            counter.displayRoundScores(player1, player2);
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
                        "  🔥 Damage: " + player.damageRange[i][0] + "-" + player.damageRange[i][1]); // show range
                if (currentCooldown[i] > 0) System.out.print("  ⏳ Cooldown: " + currentCooldown[i] + " turn(s) 🔒");
                else System.out.print("  ✅ Ready!");
                System.out.println();
            }


            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t Cast your skill: ");
            int skillChoice;

            try {
                skillChoice = sc.nextInt();
                sc.nextLine(); // important! consume leftover newline
            } catch (InputMismatchException e) {
                sc.nextLine(); // clear invalid input
                skillChoice = -1; // treat as a miss
            }

            if (skillChoice < 1 || skillChoice > player.skills.length) {
                paa.tabPrinter(21);
                typePrint("\n You missed your attack! [😱]", 15);
            } else if (currentCooldown[skillChoice - 1] > 0) {
                paa.tabPrinter(21);
                typePrint("\n That skill is on cooldown! You missed your attack! [😱]", 15);
            } else {
                int dmg = player.getRandomDamage(skillChoice - 1); // randomized damage
                paa.tabPrinter(21);
                typePrint("\n " + player.name + " used " + player.skills[skillChoice - 1] + "!", 10);
                enemy.health -= dmg;
                if (enemy.health < 0) enemy.health = 0;
                paa.tabPrinter(21);
                typePrint("\n [💥] " + enemy.name + " took " + dmg + " damage! Remaining HP: " + enemy.health, 10);

                currentCooldown[skillChoice - 1] = skillCooldown[skillChoice - 1];
            }

            // Enemy attack
            if (enemy.isAlive()) {
                int enemySkill = rand.nextInt(enemy.skills.length);
                int dmg = enemy.getRandomDamage(enemySkill);
                typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t " + enemy.name + " used " + enemy.skills[enemySkill] + "!", 10);
                player.health -= dmg;
                if (player.health < 0) player.health = 0;
                typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t [🔥] " + player.name + " took " + dmg + " damage! Remaining HP: " + player.health, 10);
            }

            for (int i = 0; i < currentCooldown.length; i++) {
                if (currentCooldown[i] > 0) currentCooldown[i]--;
            }

            turn++;
        }

        // --- Player lost or won handling ---
        if (player.isAlive()) {
            typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🏆] " + player.name + " has defeated " + enemy.name + "!", 15);
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t You reclaimed the Brilyante of " + enemy.element + "! [✨]", 15);
            return true;
        } else {
            typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t [💀] " + player.name + " has fallen... The Brilyante remains with " + enemy.name + ".", 20);

            // --- Ask to play again ---
            boolean validReplay = false;
            while (!validReplay) {
                try {
                    System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Do you wish to venture once more? [ Yes / No ]: ");
                    String replay = sc.nextLine().trim().toLowerCase();

                    if (replay.equals("yes")) {
                        validReplay = true;
                        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🔄] Isa kang magiting na mandirigma... Maghanda sa panibagong panimula! [✨]\n", 10);
                        main(null); // restart the game
                        return false; // exit current battle
                    } else if (replay.equals("no")) {
                        validReplay = true;
                        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🌙] Avisala Eshma! Encantadia awaits the next brave soul...", 15);
                        prt.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Salamat sa paglalaro! [🏰✨]");
                        return false;
                    } else {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Ashtadi! Please input 'Yes' o 'No'.");
                    }

                } catch (Exception e) {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Patawad! Something went wrong. Please try again.");
                }
            }

            return false; // fallback
        }
    }



    static void battlePvP(Character player1, Character player2) {
        Scanner sc = new Scanner(System.in);
        int tabPrintAmount = 13;
        RoundCounter counter = new RoundCounter(0, 0, 1);

        int[] cooldown1 = {0, 2, 3};
        int[] currentCD1 = {0, 0, 0};
        int[] cooldown2 = {0, 2, 3};
        int[] currentCD2 = {0, 0, 0};

        // --- Best-of-3 match loop ---
        while (!counter.isMatchOver() && counter.getRound() <= 3) {

            // Reset health at start of each round
            player1.health = 500;
            player2.health = 500;
            int turn = 1;

            // Display round and current score at the start of the round
            counter.displayRoundScores(player1, player2);

            // --- Round loop: each turn until someone dies ---
            while (player1.isAlive() && player2.isAlive()) {

                // --- ASCII Battle Display ---
                prt.println("\n\n\n");
                paa.tabPrinter(20);
                System.out.println("═══════════════════════════════════════════════════");
                paa.tabPrinter(20);
                System.out.println("          \uD83C\uDF0A ⛰ . \uD83C\uDF0A ⛰   ~~ ⛰   ~    ✦     ");
                paa.tabPrinter(25);
                System.out.println("//TURN\\\\ " + turn + " ⚔️");
                paa.tabPrinter(20);
                System.out.println("          ☁  . ☁  .  . ✦  ~  \uD83D\uDD25   \uD83D\uDD25 \uD83D\uDD25~");
                paa.tabPrinter(20);
                System.out.println("═══════════════════════════════════════════════════\n");

                paa.tabPrinter(tabPrintAmount - 4);
                System.out.printf("%-45s [HP: %3d]     ///[⚔️]\\\\\\     %-45s [HP: %3d]\n",
                        player1.name, player1.health, player2.name, player2.health);
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint("----------------------------------------------------", 10);

                // --- Player 1 Turn ---
                prt.println();
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint(player1.name + "'s turn! Choose a skill:\n", 10);
                for (int i = 0; i < player1.skills.length; i++) {
                    paa.tabPrinter(tabPrintAmount);
                    System.out.print((i + 1) + ". " + player1.skills[i] +
                            " 🔥 Damage: " + player1.damageRange[i][0] + "-" + player1.damageRange[i][1]);
                    if (currentCD1[i] > 0) System.out.println(" ⏳ Cooldown: " + currentCD1[i] + " turn(s) 🔒");
                    else System.out.println(" ✅ Ready!");
                }

                int choice1;
                try {
                    paa.tabPrinter(tabPrintAmount);
                    System.out.print(" Cast your skill: ");
                    choice1 = sc.nextInt() - 1;
                    sc.nextLine();
                } catch (Exception e) {
                    sc.nextLine();
                    choice1 = -1;
                }

                if (choice1 < 0 || choice1 >= player1.skills.length || currentCD1[choice1] > 0) {
                    prt.println();
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(" Missed your attack! [😱]", 15);
                } else {
                    int dmg = player1.getRandomDamage(choice1);
                    prt.println();
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(player1.name + " used " + player1.skills[choice1] + "!", 10);
                    player2.health -= dmg;
                    if (player2.health < 0) player2.health = 0;
                    prt.println();
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(player2.name + " took " + dmg + " damage! Remaining HP: " + player2.health, 10);
                    currentCD1[choice1] = cooldown1[choice1];
                }

                if (!player2.isAlive()) break;

                // --- Player 2 Turn ---
                prt.println();
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint(player2.name + "'s turn! Choose a skill:\n", 10);
                for (int i = 0; i < player2.skills.length; i++) {
                    paa.tabPrinter(tabPrintAmount);
                    System.out.print((i + 1) + ". " + player2.skills[i] +
                            " 🔥 Damage: " + player2.damageRange[i][0] + "-" + player2.damageRange[i][1]);
                    if (currentCD2[i] > 0) System.out.println(" ⏳ Cooldown: " + currentCD2[i] + " turn(s) 🔒");
                    else System.out.println(" ✅ Ready!");
                }

                int choice2;
                try {
                    paa.tabPrinter(tabPrintAmount);
                    System.out.print(" Cast your skill: ");
                    choice2 = sc.nextInt() - 1;
                    sc.nextLine();
                } catch (Exception e) {
                    sc.nextLine();
                    choice2 = -1;
                }

                if (choice2 < 0 || choice2 >= player2.skills.length || currentCD2[choice2] > 0) {
                    prt.println();
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(" Missed your attack! [😱]", 15);
                } else {
                    int dmg = player2.getRandomDamage(choice2);
                    prt.println();
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(player2.name + " used " + player2.skills[choice2] + "!", 10);
                    player1.health -= dmg;
                    if (player1.health < 0) player1.health = 0;
                    prt.println();
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(player1.name + " took " + dmg + " damage! Remaining HP: " + player1.health, 10);
                    currentCD2[choice2] = cooldown2[choice2];
                }

                // --- Decrease cooldowns ---
                for (int i = 0; i < currentCD1.length; i++) if (currentCD1[i] > 0) currentCD1[i]--;
                for (int i = 0; i < currentCD2.length; i++) if (currentCD2[i] > 0) currentCD2[i]--;

                turn++;
            }

            // --- Round result ---
            if (player1.isAlive() && !player2.isAlive()) {
                counter.setPlayer1WinsRound();
                prt.println();
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint("🏆 " + player1.name + " wins ROUND " + (counter.getRound() - 1) + "!", 15);
            } else if (!player1.isAlive() && player2.isAlive()) {
                counter.setPlayer2WinsRound();
                prt.println();
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint("🏆 " + player2.name + " wins ROUND " + (counter.getRound() - 1) + "!", 15);
            }

            // --- Automatically move to next round ---
            if (!counter.isMatchOver() && counter.getRound() <= 3) {
                // No prompts; next round will start automatically
            }
        }

        // --- Match winner & replay prompt (after rounds) ---
        Character matchWinner = counter.getMatchWinner(player1, player2);
        if (matchWinner != null) {
            prt.println();
            paa.tabPrinter(tabPrintAmount);
            EncantadiaGame.typePrint("🎉 " + matchWinner.name + " wins the MATCH! 🎉", 15);
        }

        // --- Ask players if they want to play again ---
        boolean p1Replay = false;
        boolean p2Replay = false;

        prt.println();
        paa.tabPrinter(tabPrintAmount);
        System.out.print(player1.name + ", Do you wish to play again? (Yes/No): ");
        if (sc.nextLine().trim().equalsIgnoreCase("yes")) p1Replay = true;

        prt.println();
        paa.tabPrinter(tabPrintAmount);
        System.out.print(player2.name + ", Do you wish to play again? (Yes/No): ");
        if (sc.nextLine().trim().equalsIgnoreCase("yes")) p2Replay = true;

        if (p1Replay && p2Replay) {
            battlePvP(player1, player2); // restart match
        } else {
            prt.println();
            paa.tabPrinter(tabPrintAmount);
            EncantadiaGame.typePrint("Redirecting to the Welcome Screen...", 15);

            // Redirect to main welcome screen
            main(null); // or call your specific method for the welcome screen
            return;
        }
    }

    static boolean battlePvE(Character player, Character enemy) {
        Scanner sc = new Scanner(System.in);
        int tabPrintAmount = 13;
        RoundCounter counter = new RoundCounter(0, 0, 1);

        // Skill cooldown setup
        int[] skillCooldown = {0, 2, 3};
        int[] currentCDPlayer = {0, 0, 0};
        int[] currentCDEnemy = {0, 0, 0};

        // --- Best-of-3 match loop ---
        while (!counter.isMatchOver()) {

            // Reset Health AND Cooldowns at start of round
            player.health = 500;
            enemy.health = 500;
            Arrays.fill(currentCDPlayer, 0);
            Arrays.fill(currentCDEnemy, 0);
            int turn = 1;

            // Display round info
            counter.displayRoundScores(player, enemy);

            // --- Single Round Loop ---
            while (player.isAlive() && enemy.isAlive()) {

                // --- ASCII Battle Display (Same as PvP) ---
                prt.println("\n\n\n");
                paa.tabPrinter(20);
                System.out.println("═══════════════════════════════════════════════════");
                paa.tabPrinter(20);
                System.out.println("          \uD83C\uDF0A ⛰ . \uD83C\uDF0A ⛰   ~~ ⛰   ~    ✦     ");
                paa.tabPrinter(25);
                System.out.println("//TURN\\\\ " + turn + " ⚔️");
                paa.tabPrinter(20);
                System.out.println("          ☁  . ☁  .  . ✦  ~  \uD83D\uDD25   \uD83D\uDD25 \uD83D\uDD25~");
                paa.tabPrinter(20);
                System.out.println("═══════════════════════════════════════════════════\n");

                paa.tabPrinter(tabPrintAmount - 4);
                System.out.printf("%-45s [HP: %3d]     ///[⚔️]\\\\\\     %-45s [HP: %3d]\n",
                        player.name, player.health, enemy.name, enemy.health);
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint("----------------------------------------------------", 10);

                // --- Player Turn ---
                prt.println();
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint(player.name + "'s turn! Choose a skill:\n", 10);
                for (int i = 0; i < player.skills.length; i++) {
                    paa.tabPrinter(tabPrintAmount);
                    System.out.print((i + 1) + ". " + player.skills[i] +
                            " 🔥 Damage: " + player.damageRange[i][0] + "-" + player.damageRange[i][1]);
                    if (currentCDPlayer[i] > 0) System.out.println(" ⏳ Cooldown: " + currentCDPlayer[i] + " turn(s) 🔒");
                    else System.out.println(" ✅ Ready!");
                }

                int choice;
                try {
                    paa.tabPrinter(tabPrintAmount);
                    System.out.print(" Cast your skill: ");
                    choice = sc.nextInt() - 1;
                    sc.nextLine();
                } catch (Exception e) {
                    sc.nextLine();
                    choice = -1;
                }

                if (choice < 0 || choice >= player.skills.length || currentCDPlayer[choice] > 0) {
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(" You missed your attack! [😱]", 15);
                } else {
                    int dmg = player.getRandomDamage(choice);
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(player.name + " used " + player.skills[choice] + "!", 10);
                    enemy.health -= dmg;
                    if (enemy.health < 0) enemy.health = 0;
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(enemy.name + " took " + dmg + " damage! Remaining HP: " + enemy.health, 10);
                    currentCDPlayer[choice] = skillCooldown[choice];
                }

                if (!enemy.isAlive()) break;

                // --- Enemy Turn (AI) ---
                prt.println();
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint(enemy.name + " is attacking...\n", 10);

                // Simple AI: Picks a random skill. If it picks a cooldown skill, it rerolls once to try and find a valid one.
                int enemyChoice = rand.nextInt(enemy.skills.length);
                if (currentCDEnemy[enemyChoice] > 0) {
                    enemyChoice = rand.nextInt(enemy.skills.length); // Try one more time
                }

                if (currentCDEnemy[enemyChoice] > 0) {
                    // If AI picks a cooldown skill again, it misses (Fairness)
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(" " + enemy.name + " fumbled and missed the attack! [😱]", 15);
                } else {
                    int dmg = enemy.getRandomDamage(enemyChoice);
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(enemy.name + " used " + enemy.skills[enemyChoice] + "!", 10);
                    player.health -= dmg;
                    if (player.health < 0) player.health = 0;
                    paa.tabPrinter(tabPrintAmount);
                    EncantadiaGame.typePrint(player.name + " took " + dmg + " damage! Remaining HP: " + player.health, 10);
                    currentCDEnemy[enemyChoice] = skillCooldown[enemyChoice];
                }

                // --- Decrease cooldowns ---
                for (int i = 0; i < currentCDPlayer.length; i++) if (currentCDPlayer[i] > 0) currentCDPlayer[i]--;
                for (int i = 0; i < currentCDEnemy.length; i++) if (currentCDEnemy[i] > 0) currentCDEnemy[i]--;

                turn++;
            }

            // --- Round result ---
            if (player.isAlive()) {
                counter.setPlayer1WinsRound();
                prt.println();
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint("🏆 " + player.name + " wins ROUND " + (counter.getRound() - 1) + "!", 15);
            } else {
                counter.setPlayer2WinsRound();
                prt.println();
                paa.tabPrinter(tabPrintAmount);
                EncantadiaGame.typePrint("🏆 " + enemy.name + " wins ROUND " + (counter.getRound() - 1) + "!", 15);
            }

            // Pause slightly between rounds
            try { Thread.sleep(2000); } catch (Exception e) {}
        }

        // --- Match winner ---
        Character winner = counter.getMatchWinner(player, enemy);
        if (winner != null) {
            prt.println();
            paa.tabPrinter(tabPrintAmount);
            EncantadiaGame.typePrint("🎉 " + winner.name + " wins the MATCH! 🎉", 15);
        }

        return player.isAlive(); // Returns true if player won the match
    }


    static void battleArcade(Character player, Character[] allEnemies) {
        Scanner sc = new Scanner(System.in);

        // Show player backstory once
        showBackstory(player);

        // Loop through enemies in order
        for (Character enemy : allEnemies) {
            enemy.health = 500; // reset enemy health

            // Show enemy backstory before the round
            System.out.println("\n⚔️ You will face: " + enemy.name + " ⚔️\n");
            showBackstory(enemy);

            boolean playerWon = battlePvE(player, enemy); // reuse PvE battle logic

            // Handle loss with rematch
            while (!playerWon) {
                try {
                    System.out.print("\nYou lost! Rematch with same enemy? (Yes/No): ");
                    String input = sc.nextLine().trim();

                    if (input.equalsIgnoreCase("yes")) {
                        player.health = 500;
                        enemy.health = 500;
                        playerWon = battlePvE(player, enemy);
                    } else if (input.equalsIgnoreCase("no")) {
                        System.out.println("Redirecting to Welcome Screen...");
                        main(null);
                        return;
                    } else {
                        System.out.println("Invalid input! Please enter 'Yes' or 'No'.");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred. Please try again.");
                }
            }

            System.out.println("\n🎉 You defeated " + enemy.name + " in Arcade Mode! 🎉");
        }

        // All enemies defeated
        System.out.println("\n🏆 Congratulations! You defeated all enemies in Arcade Mode! 🏆");
        // --- Show epic ending sequence here ---
        showEpicEnding(); // <-- place it here, before redirecting to Welcome Screen
    }

    public static void showEpicEnding() {
        Scanner sc = new Scanner(System.in); // <-- added Scanner

        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🌟] Haste Ivo Live! You have united all 4 Brilyantes!", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t The truth is finally revealed...", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Suddenly, the Queen appears before you, smiling...", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t But the shock hits hard—she orchestrated her own kidnapping!", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Her real identity is Mitena, a dark force who corrupted the minds of the new Sang’gres!", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t The original guardians fought desperately to protect the Brilyantes from her growing power.", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t As you unite the Brilyantes, a shimmering portal opens in Encantadia...", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t The guardians step through—it is revealed they are from the future!", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t The future you must prevent is a war sparked by the Queen’s betrayal!", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t You are faced with an impossible choice: confront Mitena and risk the future, or allow her rule and maintain a twisted “peace” in the present!", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Every Brilyante pulses with power, and the weight of Encantadia’s fate is on your shoulders...", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t The guardians bow, the elements quake, and only your courage will decide the true destiny of Encantadia. [⚔️✨]", 15);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t And now, the question that will haunt Encantadia forever: Will you become the hero, or the pawn in Mitena’s game?", 15);

        // --- Ask to play again ---
        boolean validReplay = false;
        while (!validReplay) {
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Do you wish to venture once more? [ Yes / No ]: ");
            String replay = sc.next().trim().toLowerCase();

            if (replay.equalsIgnoreCase("yes")) {
                validReplay = true;
                typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🔄] Isa kang magiting na mandirigma... Maghanda sa panibagong panimula! [✨]\n", 10);
                main(null); // restart the game
                return;
            } else if (replay.equalsIgnoreCase("no")) {
                validReplay = true;
                typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🌙] Avisala Eshma! Encantadia awaits the next brave soul...", 15);
                return;
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Ashtadi! Please input 'Yes' o 'No'.");
                sc.nextLine();
            }
        }
    }

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
    }

    // --- Character selection ---
    static Character chooseCharacterFancy(String playerName, Character[] options) {
        prt.print("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 15; i2++) prt.print("=====");

        typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+\t\t  ~ ~ ~ ~ ~ ~ ~ ~$[Lair of the Sang'gres]$~ ~ ~ ~ ~ ~ ~ ~\t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+\t\t                                                         \t\t  +", 8);
        typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+\t\t                                                         \t\t  +", 8);

        for (int i = 0; i < options.length; i++) {
            typePrint(String.format("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [%d] {%-48s}", i + 1, options[i].name) + " ".repeat(10), 8);
        }

        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i2 = 1; i2 <= 15; i2++) prt.print("=====");

        int choice = -1;
        boolean validChoice = false;

        while (!validChoice) {
            typePrintInline(String.format("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   %s, choose your Sang'gre: ", playerName), 3);

            try {
                String line = sc.nextLine().trim();
                choice = Integer.parseInt(line);

                if (choice < 1 || choice > options.length) {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      Please enter a valid number.");
                    continue;
                }

                validChoice = true;
            } catch (NumberFormatException e) {
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      Invalid input! Enter a number.");
            }
        }

        return options[choice - 1];
    }

    // --- display backstory ---
    static void showBackstory(Character character) {
        switch (character.name) {
            case "{Jelian | • • • (Goddess of Whispers)}" -> JAAB.showBackstoryMain();
            case "{Mary | • • • • (Goddess of Tides)}" -> MAAB.showBackstoryMain();
            case "{Joygen | • (Goddess of Eternal Blaze)}" -> JAPB.showBackstoryMain();
            case "{Dirk | • (Guardian of Growth and Harvest)}" -> DAAB.showBackstoryMain();
            case "{Pirena | • • • • • • (Fire)}" -> JAPB.showBackstoryOG();
            case "{Amihan | • • • • • • (Air)}" -> JAAB.showBackstoryOG();
            case "{Alena | • • • • • • (Water)}" -> MAAB.showBackstoryOG();
            case "{Danaya | • • • • • • (Earth)}" -> DAAB.showBackstoryOG();
            default -> System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t [⚠️] No backstory available.");
        }
    }




    //Art
    public static void main(String[] args) {
        // Welcome screen
        //Start of Menu Page Program
        paa.printLoadingScreen(12);

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
        String start = sc.nextLine().trim();

        // If player chooses anything other than 1
        if (!start.equals("1")) {
            prt.println();
            prt.println();
            prt.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t   ");

            for (int i2 = 1; i2 <= 18; i2++) {
                prt.print("======");
            }

            prt.println();
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t   +\t\t\t        ✰            ᚢ                                ☩                           \t\t\t  +", 3);
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t   +\t\t\t The gates are closing. Encantadia shall await another soul brave enough to enter. \t\t\t  +", 3);
            typePrint("\t\t\t\t\t\t\t\t\t\t\t\t\t   +\t\t\t                    ⊕        ⊙                              ✦                      \t\t\t  +", 3);
            prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t   ");
            for (int i2 = 1; i2 <= 18; i2++) {
                prt.print("======");
            }

            // --- Ask to play again ---
            boolean validReplay = false;
            while (!validReplay) {
                System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Do you wish to venture once more? [ Yes / No ]: ");
                String replay = sc.nextLine().trim().toLowerCase();

                if (replay.equals("yes")) {
                    validReplay = true;
                    typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🔄] Isa kang magiting na mandirigma... Maghanda sa panibagong panimula! [✨]\n", 10);
                    for(int i = 1; i <= 30; i++){
                        prt.println();
                    }
                    main(null); // restart the game
                    return; // prevent further execution in current main
                } else if (replay.equals("no")) {
                    validReplay = true;
                    typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [🌙] Avisala Eshma! Encantadia awaits the next brave soul...", 15);
                    prt.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Salamat sa paglalaro! [🏰✨]");
                    return; // exit
                } else {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Ashtadi! Please input 'Yes' o 'No'."); // invalid input
                }
            }
        }

        boolean validInput = false;

        prt.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [Press [1] to Discover \uD83E\uDEB6 or [0] to Skip ⚔\uFE0F and Start the Game]");
        prt.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for (int i1 = 1; i1 <= 18; i1++) {
            prt.print("=====");
        }
        while (!validInput) {
            try {


                prt.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Enter your choice: ");
                String input = sc.nextLine().trim();

                if (input.equals("0")) {
                    validInput = true;
                    System.out.println(" ");
                    prt.println("\t\t\t\t\t\t\t\t                                                       Skipping... Maghanda mandirigma!");
                    break;
                } else if (input.equals("1")) {
                    validInput = true;
                    System.out.println("\n\n");
                    paa.printBackgroundScene();
                    prt.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
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
                    typePrint("", 100000);
                    for(int i = 1; i <= 10; i++){
                        prt.println();
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


// New characters (players)
        Character Jelian = new Character("{Jelian | • • • (Goddess of Whispers)}", "Air",
                new String[]{"Carbon Dioxide", "Bad Breathe", "Utot"},
                new int[]{40, 55, 70},
                new int[][]{{35, 45}, {50, 60}, {65, 75}});  // damage ranges

        Character Mary = new Character("{Mary | • • • • (Goddess of Tides)}", "Water",
                new String[]{"Luha", "Flood Control", "Tsunami"},
                new int[]{35, 50, 65},
                new int[][]{{30, 40}, {45, 55}, {60, 70}});

        Character Joygen = new Character("{Joygen | • (Goddess of Eternal Blaze)}", "Fire",
                new String[]{"In Heat", "Thirstrap", "Wow wow wow naay nasunog"},
                new int[]{45, 60, 80},
                new int[][]{{40, 50}, {55, 65}, {70, 85}});

        Character Dirk = new Character("{Dirk | • (Guardian of Growth and Harvest)}", "Earth",
                new String[]{"Bato Dela Rosa", "Ding ang Bato", "Linog"},
                new int[]{40, 55, 75},
                new int[][]{{35, 45}, {50, 60}, {70, 80}});

// Original Sang’gres (enemies)
        Character Pirena = new Character("{Pirena | • • • • • • (Fire)}", "Fire",
                new String[]{"Flame fury", "Hyperventilation", "Impyerno"},
                new int[]{45, 60, 80},
                new int[][]{{40, 50}, {55, 65}, {70, 85}});

        Character Amihan = new Character("{Amihan | • • • • • • (Air)}", "Air",
                new String[]{"Wind Slash", "Storm Fury", "Whirlwind Strike"},
                new int[]{40, 55, 70},
                new int[][]{{35, 45}, {50, 60}, {65, 75}});

        Character Alena = new Character("{Alena | • • • • • • (Water)}", "Water",
                new String[]{"Water Spear", "Ocean Wave", "Tsunami Blast"},
                new int[]{35, 50, 65},
                new int[][]{{30, 40}, {45, 55}, {60, 70}});

        Character Danaya = new Character("{Danaya | • • • • • • (Earth)}", "Earth",
                new String[]{"Rock Smash", "Earthquake", "Nature’s Wrath"},
                new int[]{40, 55, 75},
                new int[][]{{35, 45}, {50, 60}, {70, 80}});


        Character[] allCharacters = {Jelian, Mary, Joygen, Dirk, Pirena, Amihan, Alena, Danaya};
        Character[] playerCharacters;
        Character[] enemies = null;

        // --- Game mode selection ---
        int gameMode = GameMode.chooseGameMode();

        switch (gameMode) {
            case 1: // PvP
                paa.printPVP();
                playerCharacters = new Character[2];
                playerCharacters[0] = chooseCharacterFancy("Player 1", allCharacters);
                Character[] remaining = Arrays.stream(allCharacters)
                        .filter(c -> c != playerCharacters[0])
                        .toArray(Character[]::new);
                playerCharacters[1] = chooseCharacterFancy("Player 2", remaining);

                // Show backstories
                showBackstory(playerCharacters[0]);
                showBackstory(playerCharacters[1]);

                // Call PvP battle
                battlePvP(playerCharacters[0], playerCharacters[1]);
                break;

            case 2: // PvE - Single Random Enemy
                playerCharacters = new Character[1];
                paa.printPVE();
                // 1. Choose Player
                Character[] availableHeroes = {Jelian, Joygen, Mary, Dirk};
                playerCharacters[0] = chooseCharacterFancy("Player", availableHeroes);
                Character p1 = playerCharacters[0];

                // 2. Determine Random Enemy
                // Create a list of all characters EXCEPT the one the player picked
                List<Character> enemyPool = new ArrayList<>();
                for (Character c : allCharacters) {
                    // Check checking logic to ensure we don't fight ourselves
                    if (!c.name.equals(p1.name)) {
                        enemyPool.add(c);
                    }
                }

                // Pick one random enemy from the pool
                Character randomEnemy = enemyPool.get(rand.nextInt(enemyPool.size()));

                // Reset health before battle starts
                p1.health = 500;
                randomEnemy.health = 500;

                // 3. Intro
                prt.println();
                paa.tabPrinter(13);
                System.out.println(" ⚔️ FATE HAS CHOSEN YOUR OPPONENT: " + randomEnemy.name + " ⚔️\n");
                showBackstory(p1);
                showBackstory(randomEnemy);

                // 4. Start Battle
                boolean playerWon = battlePvE(p1, randomEnemy);

                // 5. Post-Battle Logic
                if (playerWon) {
                    System.out.println("\n🎉 You have defeated " + randomEnemy.name + "! The realm is safe for now. 🎉");
                    showEpicEnding();
                    // Returns to main menu automatically after ending logic
                } else {
                    // Rematch Logic
                    boolean retry = true;
                    while (retry) {
                        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t You were defeated. Try again against " + randomEnemy.name + "? (Yes/No): ");
                        String input = sc.nextLine().trim();
                        if (input.equalsIgnoreCase("yes")) {
                            playerWon = battlePvE(p1, randomEnemy);
                            if (playerWon) {
                                showEpicEnding();
                                retry = false;
                            }
                        } else {
                            paa.lostScreen();
                            typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t Returning to menu...", 10);
                            for(int i = 1; i <= 20; i++){
                                prt.println();
                            }
                            main(null);
                            return;
                        }
                    }
                }
                break;

            case 3: // Arcade
                paa.printARCADE();
                playerCharacters = new Character[1];
                playerCharacters[0] = chooseCharacterFancy("Player", allCharacters);
                enemies = Arrays.stream(allCharacters)
                        .filter(c -> c != playerCharacters[0])
                        .toArray(Character[]::new);

                battleArcade(playerCharacters[0], enemies); // call new Arcade battle
                break;

            default:
                // Default fallback: PvE with Jelian
                playerCharacters = new Character[1];
                playerCharacters[0] = Jelian;
                enemies = new Character[]{Pirena, Amihan, Alena, Danaya};

                for (Character enemy : enemies) {
                    enemy.health = 500;
                    playerWon = battlePvE(playerCharacters[0], enemy);
                    if (!playerWon) return;
                }
                main(null);
        }

        // Brilyante quest loop
        int brilyantesCollected = 0;

        for (Character en : enemies) {
            en.health = 500; // reset enemy health

            boolean won = battle(player, en, choice);
            if (!won) {
                paa.lostScreen();
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

                            for (int i = 0; i < player.damageRange.length; i++) {
                                player.damageRange[i][0] += 10; // increase min damage
                                player.damageRange[i][1] += 10; // increase max damage
                            }
                            player.health = 500;

                            // Power up all enemies slightly
                            for (Character foe : enemies) {
                                for (int j = 0; j < foe.damageRange.length; j++) {
                                    foe.damageRange[j][0] += 5; // increase min damage
                                    foe.damageRange[j][1] += 5; // increase max damage
                                }
                            }

                            typePrint("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [💪] Mahusay Mandirigma! Ika'y maghanda ", 15);

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
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Sheda!... Invalid input!.");
                    }
                }
            }
        }
    }
}
