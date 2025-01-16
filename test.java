import java.util.ArrayList;

public class test {

    public static void main(String[] args) {
        ArrayList<Adventurer>  alliedParty = new ArrayList<Adventurer>();
        ArrayList<Adventurer>  enemyParty = new ArrayList<Adventurer>();

        alliedParty.add(new Zombie());
        alliedParty.add(Game.createRandomAdventurer());
        enemyParty.add(Game.createRandomAdventurer());

        Text.hideCursor();
        Game.drawBackground();
        Game.drawScreen(alliedParty, enemyParty);
        Text.go(31,1);

        

    }
}
