package FinalProject;
import java.util.*;

public class EncantadiaGame {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

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

    //setting(if what place ang na pili, enemy built in)
    //loop would u like to proceed getting other brilyante
    //if continue playing, low mana cost, increase skill damage
    //enemy skill also increases

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

    public static void main(String[] args) {
        // Welcome screen
        typePrint("🌌✨ Avisala! Maligayang paglalakbay sa mundo ng Encantadia! ✨🌌", 30);
        typePrint("Legends whisper of heroes who shaped the fate of kingdoms...", 30);
        typePrint("Do you dare take the first step into destiny?", 30);
        System.out.println("");
        typePrint("⚔️ Press 1 to begin your journey.", 30);
        typePrint("❌ Press any other key to turn back and remain in the ordinary world.", 30);
        System.out.println("");
        System.out.print("👉 Enter your choice: ");
        String start = sc.nextLine();

        if (!start.equals("1")) {
            typePrint("The gates close... Encantadia shall await another soul brave enough to enter.", 10);
            return;
        }

        // Storyline
        typePrint("\nLong ago, the four Sang’gres ruled Encantadia with their Brilyantes...", 20);
        typePrint("But darkness has risen. To restore peace, you must defeat your rival Sang’gre...", 20);
        typePrint("and reclaim their Brilyante to rule all of Encantadia! ⚔️\n", 20);

        // New characters (players)
        Character jelian = new Character("Jelian (Goddes of Whispers)", "Air",
                new String[]{"Carbon Dioxide", "Bad Breathe", "Utot"},
                new int[]{40, 55, 70}, // this one is for the damage
                new int[]{20, 30, 40}); // kani ang mana cost

        Character mary = new Character("Mary (Goddes of Tides)", "Water",
                new String[]{"Luha", "Flood Control", "Tsunami"},
                new int[]{35, 50, 65}, //same goes dre
                new int[]{15, 25, 35});

        Character joygen = new Character("Joygen (Goddes of Eternal Blaze)", "Fire",
                new String[]{"In Heat", "Hyperventilation", "Impyerno"},
                new int[]{45, 60, 80},
                new int[]{20, 35, 50});

        Character dirk = new Character("Dirk (God of Living Soil)", "Earth",
                new String[]{"Bato Dela Rosa", "Ding ang Bato", "Linog"},
                new int[]{40, 55, 75},
                new int[]{15, 30, 40});

        // Original Sang’gres (enemies)
        Character pirena = new Character("Pirena ()", "Fire",
                new String[]{"In Heat", "Thirstrap", "Wow naay nasunog, wow wow wa wow"},
                new int[]{45, 60, 80},
                new int[]{20, 35, 50});

        Character amihan = new Character("Amihan (Air)", "Air",
                new String[]{"Wind Slash", "Storm Fury", "Whirlwind Strike"},
                new int[]{40, 55, 70},
                new int[]{20, 30, 40});

        Character alena = new Character("Alena (Water)", "Water",
                new String[]{"Water Spear", "Ocean Wave", "Tsunami Blast"},
                new int[]{35, 50, 65},
                new int[]{15, 25, 35});

        Character danaya = new Character("Danaya (Earth)", "Earth",
                new String[]{"Rock Smash", "Earthquake", "Nature’s Wrath"},
                new int[]{40, 55, 75},
                new int[]{15, 30, 40});

        // Choose player
        typePrint("Choose your Sang’gre:", 20);
        typePrint("1. Jelian (Goddess of Whispers)\n2. Mary (Goddess of Tides)\n3. Joygen (Goddess of Eternal Blaze )\n4. Dirk (God of Living Soil)", 10);
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        Character player = null;
        Character enemy = null;

        switch (choice) {
            case 1 -> { player = jelian; enemy = amihan; }
            case 2 -> { player = mary; enemy = alena; }
            case 3 -> { player = joygen; enemy = pirena; }
            case 4 -> { player = dirk; enemy = danaya; }
            default -> {
                typePrint("Invalid choice. Encantadia is lost without a champion...", 10);
                return;
            }
        }

        typePrint("\nYou chose " + player.name + "! Your opponent is " + enemy.name + "!", 10);
        typePrint("The battle for the Brilyante begins!\n", 10);

        // Battle loop
        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\n⚔️ " + player.name + " HP: " + player.health + " | " +
                    enemy.name + " HP: " + enemy.health);

            // Player chooses skill
            System.out.println("\nChoose a skill:");
            for (int i = 0; i < player.skills.length; i++) {
                System.out.println((i + 1) + ". " + player.skills[i] + " (DMG: " + player.damage[i] +
                        ", Mana: " + player.manaCost[i] + ")");
            }
            System.out.print("Enter your choice: ");
            int skillChoice = sc.nextInt();

            if (skillChoice < 1 || skillChoice > player.skills.length) {
                typePrint("You missed your attack! 😱", 20);
            } else {
                int dmg = player.damage[skillChoice - 1];
                typePrint(player.name + " used " + player.skills[skillChoice - 1] + "!", 10);
                enemy.health -= dmg;
                if (enemy.health < 0) enemy.health = 0;
                typePrint("💥 " + enemy.name + " took " + dmg + " damage! Remaining HP: " + enemy.health, 10);
            }

            // Enemy counterattack if alive
            if (enemy.isAlive()) {
                int enemySkill = rand.nextInt(enemy.skills.length);
                int dmg = enemy.damage[enemySkill];
                typePrint(enemy.name + " used " + enemy.skills[enemySkill] + "!", 10);
                player.health -= dmg;
                if (player.health < 0) player.health = 0;
                typePrint("🔥 " + player.name + " took " + dmg + " damage! Remaining HP: " + player.health, 10);
            }
        }

        // Game result
        if (player.isAlive()) {
            typePrint("\n🏆 " + player.name + " has defeated " + enemy.name + "!", 20);
            typePrint("You reclaimed the Brilyante of " + enemy.element + "! ✨", 20);
            typePrint("Encantadia bows before your power!", 20);
        } else {
            typePrint("\n💀 " + player.name + " has fallen... The Brilyante remains with " + enemy.name + ".", 40);
            typePrint("Encantadia is lost in darkness...", 20);
        }
    }
}
