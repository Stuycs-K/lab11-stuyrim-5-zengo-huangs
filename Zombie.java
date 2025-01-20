public class Zombie extends Adventurer{
    private int brain;
    private int brainMax;

    public Zombie(String name, int hp){
        super(name, hp);
        this.brainMax = 8;
        this.brain = brainMax / 2;
    }

    public Zombie(String name){
        this(name, 25);
    }
    
    public Zombie(){
        this("Zomboss");
    }

    public String getSpecialName(){
        return "brain";
    }

    public int getSpecial(){
        return this.brain;
    }

    public int getSpecialMax(){
        return this.brainMax;
    }

    public void setSpecial(int brainAmount){
        this.brain = brainAmount;
    }

    public String attack(Adventurer other){
        int damage = (int) (6 * Math.random()) + 2;
        other.applyDamage(damage);

        return this.getName() + "bit " + other.getName() + " and inflicted " + damage + " damage";
    }

    public String support(Adventurer other){
        other.restoreSpecial(1);
        return this.getName() + " attempted to restore " + other.getName() + "'s " + other.getSpecialName() + " by one";
    }

    public String support(){
        this.restoreSpecial(1);
        return this.getName() + " attempted to restore their brains by one";
    }

    public String specialAttack(Adventurer other){
        if (this.brain >= 3) {
            this.brain -= 3;
            int specialReduction = other.getSpecial() / 2;
            other.setSpecial(other.getSpecial() - specialReduction);
            return this.getName() + " unleashed a Bonespear at " + other.getName() + ", reducing their " + other.getSpecialName() + " by 50%";
        } 
        else {
            return this.getName() + " tried to use Bonespear, but didn't have enough brains";
        }
    }
}