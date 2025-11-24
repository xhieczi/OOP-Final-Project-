import java.util.Random;

public class Character {
    String name;
    String element;
    String[] skills;
    int[] manaCost;
    int[][] damageRange; // [min, max] for each skill
    int health;

    private Random rand = new Random();

    // Constructor
    Character(String name, String element, String[] skills, int[] manaCost, int[][] damageRange) {
        this.name = name;
        this.element = element;
        this.skills = skills;
        this.manaCost = manaCost;
        this.damageRange = damageRange;
        this.health = 500; // default health
    }

    // Check if character is alive
    boolean isAlive() {
        return health > 0;
    }

    // Get random damage for a skill
    public int getRandomDamage(int skillIndex) {
        int min = damageRange[skillIndex][0];
        int max = damageRange[skillIndex][1];
        return rand.nextInt(max - min + 1) + min;
    }

    // Display skills
    void displaySkills() {
        System.out.println(name + " - Choose a skill:");
        for (int i = 0; i < skills.length; i++) {
            System.out.println((i + 1) + ". " + skills[i] +
                    " (Mana: " + manaCost[i] +
                    ", Damage: " + damageRange[i][0] + "-" + damageRange[i][1] + ")");
        }
    }

    // Attack another character using a skill
    void attack(Character target, int skillIndex) {
        int dmg = getRandomDamage(skillIndex);
        target.health -= dmg;
        if (target.health < 0) target.health = 0;
        System.out.println(name + " used " + skills[skillIndex] + "! Damage: " + dmg);
    }
}
