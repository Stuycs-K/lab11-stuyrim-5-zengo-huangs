public class Werewolf extends Adventurer {
    int rage, rageMax;

    // Constructors
    public Werewolf(String name, int hp) {
        super(name, hp);
        rageMax = 10;
        rage = rageMax / 2;
    }

    public Werewolf(String name) {
        this(name, 30);
    }

    public Werewolf() {
        this("Lupin");
    }

    // Abstract method implementations
    public String getSpecialName() {
        return "rage";
    }

    public int getSpecial() {
        return rage;
    }

    public void setSpecial(int n) {
        rage = n;
    }

    public int getSpecialMax() {
        return rageMax;
    }

    public String attack(Adventurer other) {
        double damage = (int) (Math.random() * 3) + 2;
        damage = damage * this.getDamageMultiplier();
        other.applyDamage((int)(damage * this.getDamageMultiplier()));
        return this + " clawed " + other + " dealing " + damage + " damage.";
    }

    public String specialAttack(Adventurer other) {
        if (rage >= 3) {
            rage -= 3;
            other.setDamageMultiplier(other.getDamageMultiplier() * .25);
            return this.getName() + " howls ferociously, intimidating " + other.getName() + " and reducing their damage by 75% for the next turn";
        } 
        else {
            return this.getName() + " tried to howl, but didn't have enough rage";
        }
    }

    // Support: Increase damage of allies by 50% for a turn
    public String support(Adventurer other) {
        other.setDamageMultiplier(other.getDamageMultiplier() * 1.5);
        return this + " howls at" + other + ". Their damage will be increased by 50% for the next turn!";
    }

    // Self-support: Restores 5 rage and 2 HP
    public String support() {
        int hp = 2;
        setHP(getHP() + hp);
        return this + " regenerates, restoring " + restoreSpecial(5) + " " + getSpecialName() + " and " + hp + " HP.";
    }
}
