package DungeonGame;

import java.util.Random;
import java.util.Scanner;

public class Dungeon {

    // Scanner and Random initializing
    static Scanner sc = new Scanner(System.in);
    static Random ra = new Random();

    // Entity Objects Initialization
    static Entity player = new Entity("", 100, 100, 10);

    static int inventory[] = {0 , 1, 0};

    // main method
    public static void main(String[] args) throws InterruptedException {

        // Declaring and initializing
        Entity goblin = new Entity("Goblin", 75, 50, 5);
        Entity orc = new Entity("Orc(Guardian)", 150, 100, 10);
        Entity Darkknight = new Entity("Darkknight(Boss)", 300, 175, 15);
        Entity GameSystem = new Entity("System", 0, 0, 0);

        // name prompt
        System.out.print("Please Enter the Player's name : ");
        player.name = sc.next();

        // Game information
        System.out.println("Welcome to the Dungeon Game!");
        System.out.println("The game has 3 levels!\nContinue to know more!\n");
        String StartGame = "yes";

        // Main Game Loop
        while (StartGame.equalsIgnoreCase("yes")) {

            // Game Dialogues Skip Prompt
            System.out.print("Skip Level 1 Dialogues? (y/n) : ");
            char DiaSkip = sc.next().charAt(0);
            // Skips Goblin Dialogue
            if (DiaSkip == 'y') {
                System.out.println("Skiping dialogues...");
                Thread.sleep(500);


            } else if (DiaSkip == 'n'){

                // Dialogues
                Dialogue("Creating scenario...\n", GameSystem);
                Thread.sleep(1000);
                Dialogue("Oh! a wild Goblin appeared in the Jungle.\n", player);
                Thread.sleep(250);
                Dialogue("Objective, Follow the Goblin \n", GameSystem);
                Thread.sleep(250);
                Dialogue("???: Oh Hey there! Hehehehe, would you like some sweets?\n", goblin);
                Thread.sleep(250);
                Dialogue("New Objective, Converse with the Goblin\n", GameSystem);
                Thread.sleep(250);
                Dialogue("Before that, who are you?\n", player);
                Dialogue("???: Oh, sorry I forgot to introduce myself to you, hehehe.\n", goblin);
                Dialogue("So, who are you?\n", player);
                Dialogue("I am a Goblin a wanderer around this area!\n", goblin);
                Dialogue("Uhh, that doesn't really help, but okay.\n", player);
                Dialogue("Anyways, so how much does this sweet cost?\n", player);
                Dialogue("Goblin: Oh, Just 15 rubies, sir!\n", goblin);
                Dialogue("15 RUBIES? That is NOT cheap!\n", player);
                Dialogue("Wha- What di- did you said?\n", goblin);
                Dialogue("Apologies, I am not interested.\n", player);
                Dialogue("Then you shall go through me... ", goblin);
                Thread.sleep(400);
                for (char c : "and my dagger".toCharArray()) {
                    System.out.print(c);
                    Thread.sleep(100);
                }
                System.out.println();
                Thread.sleep(500);
                Dialogue("Oh no...\n", goblin);
            } else {
                System.out.println("Invalid Output.");
                continue;
            }
            Dialogue("New Objective, Defeat the Goblin\n", GameSystem);

            // Level 1
            while (player.currenthealth > 0 && goblin.currenthealth > 0) {
                Playerturn(goblin.currenthealth, goblin.currentenergy, goblin);
                if (goblin.currenthealth > 0) {
                    Enemyturn(goblin);
                }
            }

            // level results
            if (goblin.currenthealth <= 0) {
                Dialogue("You defeated the Goblin", GameSystem);
                Dialogue("You found the following items in it's bag : ", GameSystem);
                Dialogue("\n1.Health Potion(s)[2]\n2.Energy Potion(s)[1]", GameSystem);
                inventory[1] = 2;
                inventory[2] = 1;

            } else {
                System.out.println("You Lost!");
                break;
            }

            // Stats Refreshment
            Dialogue("\nI think i should rest... ", player);
            Dialogue("\nYou Decided to rest before continuing.", GameSystem);
            Thread.sleep(1000);


            Dialogue("\nStarting the next scenario...", GameSystem);
            Thread.sleep(2000);
            Dialogue("\nHmm there seems to be an orc guarding the gate to the main room", GameSystem);

            Dialogue("\nNew Objective, Defeat the Orc", GameSystem);
            // Level 2
            while (player.currenthealth > 0 && orc.currenthealth > 0) {
                Playerturn(orc.currenthealth, orc.currentenergy, orc);
                if (orc.currenthealth > 0) {
                    Enemyturn(orc);
                }
            }

            // level results
            if (orc.currenthealth == 0) {
                Dialogue("\nYou defeated the Orc", GameSystem);
                Dialogue("\nYou took the following items from it : ", GameSystem);
                Dialogue("\n1.StrengthPotion(s)[3]\n2.HealthPotion(s)[3]\nEnergy Potion(s)[2]", GameSystem);
                inventory[0] = 3;
                inventory[1] = 3;
                inventory[2] = 2;

            } else {
                System.out.println("\nYou Lost!");
                break;
            }

            // Level 3
            while (player.currenthealth > 0 && Darkknight.currenthealth > 0) {
                Playerturn(Darkknight.currenthealth, Darkknight.currentenergy, Darkknight);
                if (Darkknight.currenthealth > 0) {
                    Enemyturn(Darkknight);
                }
            }

            // level results
            if (Darkknight.currenthealth == 0) {
                Dialogue("Congratulations You WON!", GameSystem);
                Dialogue("You received the following rewards", GameSystem);
                Dialogue("1.Health Potion(s)(2)\n2.Energy Potion(s)(1)", GameSystem);
            } else {
                System.out.println("You Lost!");
                break;
            }
        }
        Dialogue("Game Over",GameSystem);
        // Closes Scanner
        sc.close();
    }

    // Player turn method
    static void Playerturn(int enhealth, int enenergy, Entity enemy) throws InterruptedException {

        // Initializing and declaring variables
        int choice = 0;
        int inventorychoice;
        enhealth = enemy.currenthealth;
        enenergy = enemy.currentenergy;

        // Prompt Interface
        System.out.printf("\n\n%s's turn!", player.name);
        System.out.printf("\n%s's Health : %d | Energy : %d\n", player.name , player.currenthealth, player.currentenergy);
        System.out.printf("%s's Health : %d | Energy : %d\n", enemy.name, enhealth, enenergy);

        // Turn Prompt Loop
        while (choice != 1) {

            // inventorychoice reset
            inventorychoice = 0;

            // turn prompt
            System.out.println("1.Attack\n2.Meditate\n3.Open Inventory");
            System.out.print("Choose an option : ");
            choice = sc.nextInt();

            // attack and meditate parameter
            switch (choice){
                case 1 -> {
                    System.out.println("You have chosen to Attack");
                    enemy.currenthealth = Attack(enemy.currenthealth, player.currentenergy, player.weapondamage);
                    Thread.sleep(1000);
                    player.currentenergy -= 10;
                }
                case 2 -> {
                    System.out.println("You have chosen to Meditate!\n");
                    player.currentenergy = Meditate(player.currentenergy, player);
                    Thread.sleep(1000);
                    choice = 1;
                }
                case 3 -> OpenInventory(inventorychoice);
                default -> System.out.println("Invalid Choice!");
            }
        }
        player.StrengthDebuff();
    }

    // Enemy turn method
    static void Enemyturn(Entity enemy) throws InterruptedException {

        // turn indicator
        System.out.printf("\n%s's Turn!\n", enemy.name );
        System.out.printf("%s is Thinking....\n", enemy.name);
        Thread.sleep(2000);

        // Enemy AI
        int medirandom = ra.nextInt(4);
        if (enemy.currentenergy > 0 || medirandom < 3) {
            System.out.println(enemy.name + " has chosen to attack!");
            player.currenthealth = Attack(player.currenthealth, enemy.currentenergy, enemy.weapondamage);
            enemy.currentenergy -= 10;
        }
        else {
            System.out.println("The " + enemy.name + " has chose to meditate!");
            enemy.currentenergy = Meditate(enemy.currentenergy, enemy);
        }
    }

    // Dialogue method
    static void Dialogue(String dialogue, Entity entity) throws InterruptedException {

        // Dialogue Animation
        System.out.print(entity.name + " : ");
        for (char c : dialogue.toCharArray()) {
            System.out.print(c);
            Thread.sleep(100);
        }
    }

    // method to attack
    static int Attack(int health, int energy, int damage) {

        // Attack requirement 
        if (energy > 0) {

            // Critical hit decider
                int critChance = ra.nextInt(6);
                if (critChance == 0) {
                    damage *= 2;
                    System.out.println("Critical Hit!");
                }
            health -= damage;
        } else {
            System.out.println("Out of energy");
        }
        return health;
    }

    // method to meditate
    static int Meditate(int energy, Entity entity) {

        // energy boundaries
        if (energy <= 0) {
            energy = 30;
        } else if (energy > entity.maxenergy) {
            energy = entity.maxenergy;
        } else {
            energy += 25;
            // Energy Refill
                if (energy <= 0) {
                    energy = 30;
                }

            else if (energy > entity.maxenergy) {
                energy = entity.maxenergy;
            }
        }
        return energy;
    }

    // To view inventory
    static void OpenInventory(int inventorychoice) throws InterruptedException {

        // inventory prompt
        while (inventorychoice != 1) {
            System.out.println("   1.Close Inventory\n   2.Use Strength Potion\n   3.Use Health Potion\n   4.Energy Potion");
            System.out.print("Choose an option : ");
            inventorychoice = sc.nextInt();

            // choice prompt
            switch (inventorychoice) {
                case 1 -> {
                    System.out.println("Closing Inventory...");
                    Thread.sleep(1000);
                }
                case 2 -> {
                    player.useStrength(inventory[0]);
                    Thread.sleep(500);
                }
                case 3 -> {
                    player.useHealth(inventory[1]);
                    Thread.sleep(500);
                }
                case 4 -> {
                    player.useEnergy(inventory[2]);
                    Thread.sleep(500);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}