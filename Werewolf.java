public class Werewolf extends Adventurer {
    int fur, furMax;

    // Constructors
    public Werewolf(String name, int hp) {
        super(name, hp);
        furMax = 10;
        fur = furMax / 2;
    }

    public Werewolf(String name) {
        this(name, 30);
    }

    public Werewolf() {
        this("Lupin");
    }

    // Abstract method implementations
    public String getSpecialName() {
        return "fur";
    }

    public int getSpecial() {
        return fur;
    }

    public void setSpecial(int n) {
        fur = n;
    }

    public int getSpecialMax() {
        return furMax;
    }

    public String attack(Adventurer other) {
        int damage = (int) (Math.random() * 3) + 2;
        other.applyDamage(damage);
        return this + " clawed " + other + " dealing " + damage + " damage.";
    }

    public String specialAttack(Adventurer other) {
        return this + " attempted a special attack";
    }

    // Support: Increase damage of allies by 50% for a turn
    public String support(Adventurer other) {
        return this + " howls" + other + ". Their damage will be increased by 50% for the next turn!";
    }

    // Self-support: Restores 5 fur and 2 HP
    public String support() {
        int hp = 2;
        setHP(getHP() + hp);
        return this + " regenerates, restoring " + restoreSpecial(5) + " " + getSpecialName() + " and " + hp + " HP.";
    }
}
