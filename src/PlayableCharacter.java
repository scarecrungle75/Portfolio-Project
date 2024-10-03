public class PlayableCharacter {
    private String name;
    private int level;
    private int experience;
    private int experienceToLevelUp;
    private String item;
    private int maxHP;
    private int HP;

    /*
     * Creates the character object
     */
    public PlayableCharacter(String name) {
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.experienceToLevelUp = 100; // Starting experience needed to level up
        this.item = "None";
        this.maxHP = 10;
        this.HP = 10;
    }

    /*
     * This function just gives the character some amount of given XP to level
     * up
     */
    public void gainExperience(int amountOfXP) {
        if (amountOfXP < 0) {
            System.out.println("Experience gained cannot be negative.");
            return;
        }

        this.experience += amountOfXP;
        System.out
                .println(this.name + " gained " + amountOfXP + " experience!");

        while (this.experience >= this.experienceToLevelUp) {
            this.levelUp();
        }
    }

    /*
     * This function just applies a simple damage system that would be used for
     * battles and such.
     */
    private void takeDamage(int damage) {
        System.out.println(this.name + " takes " + damage + " damage!");
        if (this.HP > damage) {
            this.HP = this.HP - damage;
        } else if (this.HP <= damage) { // "Game Over" via losing all health
            System.out.println(this.name + " has passed out!");
            this.experience = 0;
            this.HP = this.maxHP;
        }
    }

    private void pickUpItem(String item) {
        this.item = item; // I'll have to add a drop item system later
    }

    private void levelUp() {
        this.experience -= this.experienceToLevelUp;
        this.level++;
        this.maxHP = this.maxHP + 5; // Add 5 max hp and fully heal for every level
        this.HP = this.maxHP;
        this.experienceToLevelUp *= 1.5; // Increase experience needed for next level
        System.out.println(
                this.name + " leveled up! Now at level " + this.level + ".");
    }

    public String name() {
        return this.name;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getLevel() {
        return this.level;
    }

    public String heldItem() {
        return this.item;
    }

    public int getHP() {
        return this.HP;
    }

    public static void main(String[] args) {
        PlayableCharacter character = new PlayableCharacter("Ness");

        System.out.println("Current experience: " + character.getExperience());

        character.gainExperience(50);
        System.out.println("Current experience: " + character.getExperience());

        character.gainExperience(60); // This should cause a level up
        System.out.println("Current experience: " + character.getExperience());
        System.out.println("Current level: " + character.getLevel());

        character.gainExperience(200); // Another level up
        System.out.println("Current experience: " + character.getExperience());
        System.out.println("Current level: " + character.getLevel());

        character.pickUpItem("Baseball Bat");
        System.out.println(character.name() + " is holding a "
                + character.heldItem() + "!");

        character.takeDamage(3);
        System.out.println(character.name() + " has " + character.getHP()
                + " health left!");
        character.takeDamage(6);
        System.out.println(character.name() + " has " + character.getHP()
                + " health left!");
        character.takeDamage(500); // Ness is hit by a moving car

        System.out.println("Current experience: " + character.getExperience());
        System.out.println(character.name() + " has " + character.getHP()
                + " health left!"); // Should be 20 health at lvl 3
    }
}
