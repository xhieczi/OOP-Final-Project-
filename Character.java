import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Character {
    String name;
    String backstory;
    String[] skills;
    int[] manaCost;
    int[] damage;
    int hp = 500;


    Character(String name, String backstory, String[] skills, int[] manaCost, int[] damage) {
        this.name = name;
        this.backstory = backstory;
        this.skills = skills;
        this.manaCost = manaCost;
        this.damage = damage;
    }

    void displaySkills() {
        System.out.println("Choose a skill:");
        for (int i = 0; i < skills.length; i++) {
            System.out.println((i + 1) + ". " + skills[i] +
                    " (Mana: " + manaCost[i] +
                    ", Damage: " + damage[i] + ")");
        }
    }

    void attack(Character target, int skillIndex) {
        int dmg = damage[skillIndex];
        target.hp -= dmg;
        if (target.hp < 0) target.hp = 0;
        System.out.println(name + " used " + skills[skillIndex] +
                "! Damage: " + dmg);
    }

}

