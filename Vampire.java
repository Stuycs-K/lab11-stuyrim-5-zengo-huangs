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

    public String getSpecialString(){
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
        int damage = ((int) (4 * Math.random() + 3));
        other.applyDamage(damage);
        return this.getName() + "drained " + other.getName() + "'s blood and dealt " + damage + "damage"; 
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
        return this.getName() + "attempted a special attack " ;
    }










}