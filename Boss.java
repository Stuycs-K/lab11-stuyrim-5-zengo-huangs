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
        int damage = random.nextInt(10) + 5; 
        other.applyDamage(damage);
        return this + " attacks " + other + " and deals " + damage + " damage.";
    }


    public String specialAttack(Adventurer other) {
        if (power >= 10) {
            setSpecial(power - 10); 
            int damage = random.nextInt(20) + 10;
            other.applyDamage(damage);
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


}
