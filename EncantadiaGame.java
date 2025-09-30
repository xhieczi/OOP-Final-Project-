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
    static boolean battle(Character player, Character enemy) {
        //here backstory
        typePrint("\n🌌✨You chose " + player.name + ": Backstory dre guys 🌌✨", 15);
        typePrint("\n⚔️ You face " + enemy.name + "!", 15);
        typePrint("The battle for the Brilyante of " + enemy.element + " begins!\n", 15);

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\n⚔️ " + player.name + " HP: " + player.health + " | " +
                    enemy.name + " HP: " + enemy.health);


            typePrint("---------------------------------------------------------", 10);
            // Player chooses skill
            System.out.println("Choose a skill:");
            for (int i = 0; i < player.skills.length; i++) {
                // ✅ Removed mana and damage display
                System.out.println((i + 1) + ". " + player.skills[i]);
            }
            System.out.println();
            System.out.print("Enter your choice: ");
            int skillChoice = sc.nextInt();

            if (skillChoice < 1 || skillChoice > player.skills.length) {
                typePrint("You missed your attack! 😱", 15);
            } else {
                int dmg = player.damage[skillChoice - 1];
                System.out.println();

                typePrint(player.name + " used " + player.skills[skillChoice - 1] + "!", 10);
                enemy.health -= dmg;
                System.out.println("---------------------------------------------------------");
                if (enemy.health < 0) enemy.health = 0;
                typePrint("💥 " + enemy.name + " took " + dmg + " damage! Remaining HP: " + enemy.health, 10);
            }

            // Enemy counterattack
            if (enemy.isAlive()) {
                int enemySkill = rand.nextInt(enemy.skills.length);
                int dmg = enemy.damage[enemySkill];
                typePrint(enemy.name + " used " + enemy.skills[enemySkill] + "!", 10);
                player.health -= dmg;
                if (player.health < 0) player.health = 0;
                typePrint("🔥 " + player.name + " took " + dmg + " damage! Remaining HP: " + player.health, 10);
            }
        }

        if (player.isAlive()) {
            typePrint("\n🏆 " + player.name + " has defeated " + enemy.name + "!", 15);
            typePrint("You reclaimed the Brilyante of " + enemy.element + "! ✨", 15);
            return true;
        } else {
            typePrint("\n💀 " + player.name + " has fallen... The Brilyante remains with " + enemy.name + ".", 20);
            return false;
        }
    }

    public static void main(String[] args) {
        // Welcome screen
        typePrint("\n\n🌌✨ Avisala! Maligayang paglalakbay sa mundo ng Encantadia! ✨🌌", 15);
        typePrint("Legends whisper of heroes who shaped the fate of kingdoms...", 15);
        typePrint("Do you dare take the first step into destiny?", 15);
        System.out.println();
        typePrint("⚔️ Press 1 to begin your journey.", 15);
        typePrint("❌ Press any other key to turn back and remain in the ordinary world.", 15);
        System.out.println();
        System.out.print("👉 Enter your choice: ");
        String start = sc.nextLine();

        if (!start.equals("1")) {
            typePrint("The gates close... Encantadia shall await another soul brave enough to enter.", 10);
            return;
        }
        //butangan turn until ma end

        //every turn mo regen 20-30
        // Storyline
        System.out.println("---------------------------------------------------------------------------");
        typePrint("Nonong Imaw: Long ago, there was peace in the kingdoms of Encantadia... ", 15);
        typePrint("Under the guidance of the Queen and her four Sang’gres, harmony reigned. ", 15);
        typePrint("But darkness soon came, for the Queen was taken by a mysterious rival... ", 15);
        typePrint("The realms fell into despair, and the balance of power was broken. ", 15);
        typePrint("\nTo discover the Queen’s fate, the Sang’gres must seek the four great Brilyantes— ", 15);
        typePrint("the powerful gems that give life and strength to Encantadia itself. ", 15);
        typePrint("Only by uniting these Brilyantes shall the truth be revealed... ", 15);
        typePrint("and peace be restored once more. ⚔️\n", 15);

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
                new int[]{40, 55, 70}, // damage
                new int[]{20, 30, 40}); // mana cost

        Character Alena = new Character("Alena", "Water",
                new String[]{"Water Spear", "Ocean Wave", "Tsunami Blast"},
                new int[]{35, 50, 65},
                new int[]{15, 25, 35});

        Character Danaya = new Character("Danaya", "Earth",
                new String[]{"Rock Smash", "Earthquake", "Nature’s Wrath"},
                new int[]{40, 55, 75},
                new int[]{15, 30, 40});

        // Choose player
        typePrint("\nChoose your Sang’gre:", 20);
        typePrint("1. Jelian (Goddess of Whispers)\n2. Mary (Goddess of Tides)\n3. Joygen (Goddess of Eternal Blaze)\n4. Dirk (God of Living Soil)", 10);
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        Character player = null;
        Character[] enemies = null;

        switch (choice) {
            case 1 -> { player = Jelian; enemies = new Character[]{Amihan, Alena, Pirena, Danaya}; }
            case 2 -> { player = Mary; enemies = new Character[]{Alena, Pirena, Danaya, Amihan}; }
            case 3 -> { player = Joygen; enemies = new Character[]{Pirena, Amihan, Alena, Danaya}; }
            case 4 -> { player = Dirk; enemies = new Character[]{Danaya, Pirena, Amihan, Alena}; }
            default -> {
                typePrint("Invalid choice. Encantadia is lost without a champion...", 10);
                return;
            }
        }

        // Brilyante quest loop
        int brilyantesCollected = 0;

        for (Character en : enemies) {
            en.health = 500; // reset enemy health

            boolean won = battle(player, en);
            if (!won) {
                typePrint("Encantadia is lost in darkness... 💀", 15);
                return;
            }

            brilyantesCollected++;
            typePrint("You now hold " + brilyantesCollected + " Brilyante(s).", 15);

            if (brilyantesCollected < 4) {
                System.out.print("\nDo you wish to continue your quest for the remaining Brilyantes? (Yes/No): ");
                String cont = sc.next().toLowerCase();
                if (!cont.equals("yes")) {
                    typePrint("\n🌙 You chose to rest. Encantadia awaits your return...", 15);
                    return;
                } else {
                    // Power up player
                    for (int i = 0; i < player.damage.length; i++) {
                        player.damage[i] += 10;
                    }
                    player.health = 500;

                    // Power up all enemies slightly
                    for (Character foe : enemies) {
                        for (int i = 0; i < foe.damage.length; i++) {
                            foe.damage[i] += 5;
                        }
                    }
                    typePrint("\n💪 Your strength grows as your quest continues!", 15);
                }
            }
        }

        // Victory if all Brilyantes collected
        typePrint("\n🌟 Congratulations! You have united all 4 Brilyantes!", 15);
        typePrint("The truth is revealed... and peace returns to Encantadia! ✨", 15);
    }
}
