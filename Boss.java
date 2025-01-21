import java.util.ArrayList;
import java.util.Random;

public class Boss extends Adventurer {
    private int power, powerMax; 
    private Random random = new Random();

    public Boss(String name, int hp) {
        super(name, hp);
        this.powerMax = 25;
        this.power = powerMax / 2; 
    }

    public Boss(String name) {
        this(name, 50);
    }

    public String getSpecialName() {
        return "power"; 
    }

    public int getSpecial() {
        return power;
    }

    public void setSpecial(int n) {
        power = n;
    }

    public int getSpecialMax() {
        return powerMax;
    }


    public String attack(Adventurer other) {
        double damage = (random.nextInt(10) + 5);
        damage = damage * this.getDamageMultiplier(); 
        other.applyDamage((int)(damage));
        return this + " attacks " + other + " and deals " + damage + " damage.";
    }


    public String specialAttack(Adventurer other) {
        if (power >= 10) {
            setSpecial(power - 10); 
            double damage = random.nextInt(20) + 10;
            damage = (random.nextInt(20) + 10);
            other.applyDamage((int)(damage * this.getDamageMultiplier()));
            return this + " uses their power to strike " + other + " for " + damage + " damage!";
        } else {
            return "Not enough power to use special attack. Instead, " + attack(other);
        }
    }

    public String support(Adventurer other) {
        int healing = 10;
        other.setHP(other.getHP() + healing);
        return this + " supports " + other + " and heals them for " + healing + " HP.";
    }

    public String support() {
        int healing = 5;
        setHP(getHP() + healing);
        return this + " supports themself and heals for " + healing + " HP.";
    }

    public String attack(ArrayList<Adventurer> others, int targetIndex) {
        if (targetIndex < 0 || targetIndex >= others.size()) {
            return "Invalid target!";
        }
        Adventurer target = others.get(targetIndex);
        return attack(target);
    }

    public String specialAttack(ArrayList<Adventurer> others, int targetIndex) {
        if (targetIndex < 0 || targetIndex >= others.size()) {
            return "Invalid target!";
        }
        Adventurer target = others.get(targetIndex);
        return specialAttack(target);
    }

    public String support(ArrayList<Adventurer> others, int targetIndex) {
        if (targetIndex < 0 || targetIndex >= others.size()) {
            return "Invalid target!";
        }
        Adventurer target = others.get(targetIndex);
        return support(target);
    }

    public String attack(ArrayList<Adventurer> others) {
        int targetIndex = random.nextInt(others.size());
        return attack(others.get(targetIndex));
    }

    public String specialAttack(ArrayList<Adventurer> others) {
        int targetIndex = random.nextInt(others.size());
        return specialAttack(others.get(targetIndex));
    }

    public String support(ArrayList<Adventurer> others) {
        int targetIndex = random.nextInt(others.size());
        return support(others.get(targetIndex));
    }

    public String toString() {
        return this.getName();
    }
}
