public class Vampire extends Adventurer{
    private int blood;
    private int bloodMax;

    public Vampire(String name, int hp) {
        super(name,hp);
        this.blood = 6;
        this.bloodMax = this.blood / 2;
    }
    public Vampire(String name){
        super(name, 20);
        this.blood = 6;
        this.bloodMax = this.blood /2 ;
    }
    public Vampire(){
        this("Vlad");
    }

    public String getSpecialName(){
        return "blood";
    }
    public int getSpecial(){
        return this.blood;
    }
    public int getSpecialMax(){
        return this.bloodMax;
    }

    public void setSpecial(int bloodAmount){
        this.bloodMax = bloodAmount;
    }
    public String attack(Adventurer other){
        double damage = ((int) (4 * Math.random() + 3));
        damage = damage * this.getDamageMultiplier();
        other.applyDamage((int)(damage * this.getDamageMultiplier()));
        return this.getName() + " drained " + other.getName() + "'s blood and dealt " + damage + " damage"; 
    }

    public String support(Adventurer other){
        int healthGain = (int)(other.getmaxHP() * 1.25) - (int)(other.getmaxHP());
        other.setmaxHP((other.getmaxHP() + healthGain));
        other.setHP(other.getHP() + healthGain);

        return this.getName() + "gave " + other.getName() + " a blood transfusion, healing them and increasing their max hp by " + healthGain;

    }

    public String support(){
        int healthGain = (int)(this.getmaxHP() * 1.25) - (int)(this.getmaxHP());
        this.setmaxHP((this.getmaxHP() + healthGain));
        this.setHP(this.getHP() + healthGain);

        return this.getName() + "gave themselves a blood transfusion, healing them and increasing their max hp by " + healthGain;
    }

    public String specialAttack(Adventurer other){
        double damage = 5;
        damage = damage * this.getDamageMultiplier();
        
        if (other.getmaxHP() > damage) {
            other.setmaxHP((int) (other.getmaxHP() - damage)); 
            if (other.getHP() > other.getmaxHP()) {
                other.setHP(other.getmaxHP());
            }
            return this.getName() + " bit " + other.getName() + ", reducing their max HP by " + damage + "!";
        } 
        else {
            return this.getName() + " tried to bite " + other.getName() + ", but their max HP is too low";
        }
    }










}