import java.lang.reflect.WildcardType;
import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    Text.clear();
    for (int i = 0; i < WIDTH; i++) {
      Text.go(1, i);
      System.out.print(Text.colorize("—",Text.RED, Text.BRIGHT));

      Text.go(30, i +1);
      System.out.print(Text.colorize("—", Text.RED, Text.BRIGHT));

      Text.go(7, i);
      System.out.print(Text.colorize("—", Text.CYAN, Text.BRIGHT));

      Text.go(15, i);
      System.out.print(Text.colorize("—", Text.CYAN, Text.BRIGHT));
    }

    for (int i = 0; i < HEIGHT; i++) {
      Text.go(i + 1, 0);
      System.out.print(Text.colorize("|", Text.RED, Text.BRIGHT));

      Text.go(i + 1, 80);
      System.out.print(Text.colorize("|",Text.RED, Text.BRIGHT));

    }

    for(int i = 16; i < 30; i++){
      Text.go(i, 40);
      System.out.print(Text.colorize("|", Text.CYAN, Text.BRIGHT));
    }

    for(int i = 2; i < 40; i++){
      Text.go(17, i);
      System.out.println(Text.colorize("—" , Text.GREEN, Text.BRIGHT));
    }

  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    Text.go(startRow, startCol);
    System.out.println(s);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    String[] lines = text.split("(?<=\\G.{" + width + "})");


    for(int i = 0; i < height; i++){
      Text.go(row+i, col);
      for(int j =0; j < width; j++){
        System.out.print(" ");
      }
    }
    Text.go(row,col);
    if(text.length() <= width){
      drawText(text, row,col);
    }
    else{
      for(int i = 0; i < lines.length; i++){
        if(i == height){
          break;
        }
        Text.go(row+i,col);
        System.out.print(lines[i]);
      }
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }


    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){

      int num = (int)(Math.random()* 4);

      if(num == 0){
        return new Vampire();
      }
      if(num == 1){
        return new Zombie();
      }
        return new Werewolf();

    }

    public static Adventurer createRandomAdventurer(String name){

      int num = (int)(Math.random()* 4);

      if(num == 0){
        return new Vampire(name);
      }
      if(num == 1){
        return new Zombie(name);
      }
        return new Werewolf(name);

    }

    public static Adventurer createRandomAdventurer(String name, int hp){

      int num = (int)(Math.random()* 4);

      if(num == 0){
        return new Vampire(name, hp);
      }
      if(num == 1){
        return new Zombie(name,hp );
      }
        return new Werewolf(name, hp);

    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){

      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      //YOUR CODE HERE

      for(int i = 0; i < party.size() ; i++){
        TextBox(startRow,  2 + (19*i), 19, 2, party.get(i).toString());
        String coloredHP = "HP: " + colorByPercent(party.get(i).getHP(), party.get(i).getmaxHP());
        TextBox(startRow + 1,  2 + (19*i), 19, 2, coloredHP);
        Text.reset();        
        TextBox(startRow + 2 ,  2 + (19*i), 19, 2, party.get(i).getSpecialName() + ": " + party.get(i).getSpecial());
        TextBox(startRow + 3,  2 + (19*i), 19, 2, " ");

      }

      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    if (hp * 1.0 / maxHP < 0.25) {
      return Text.colorize(output, Text.RED, Text.BRIGHT);
    }

    // under 75% : yellow

    else if (hp * 1.0 / maxHP < 0.75) {
      return Text.colorize(output, Text.YELLOW, Text.BRIGHT);
    }

    // otherwise : white
    else {
      return output;
    }
  }

  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies, String previousAction, int whichPlayer, boolean partyTurn){

    drawBackground();

    //draw player party
    drawParty(party, 2);

    //draw enemy party
    drawParty(enemies, 10);

    TextBox( 16, 41, 39, 3, previousAction);


    if(partyTurn){
      TextBox(16, 2, 35,1, "Enter command for "+party.get(whichPlayer) + ":");  
    }
    else{
      TextBox(16, 2, 35,1, "Enter anything for enemy action");  

    }
    TextBox(18, 2, 35, 1, "a or attack to: Attack");
    TextBox(19, 2, 35,  1, "su or support to: Support");
    TextBox(20, 2, 36, 1, "sp or special to: use Special Attack");
    TextBox(21, 2, 35, 1, "q or quit to: Leave the game");
    TextBox(22, 2, 38, 1, "Enter number after to select target");



  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(16,34);
      //show cursor
      Text.showCursor();

      String input = in.nextLine();

      //clear the text that was written
      Text.clear();
      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    int num = (int)(Math.random() * 3.0);
    for(int i =0; i < num; i++){
      if(num == 0){
        enemies.add(new Boss("Big Guy"));
      }
      else{
        enemies.add(createRandomAdventurer());
      }
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    party.add(createRandomAdventurer());
    party.add(createRandomAdventurer());
    party.add(createRandomAdventurer("john", 40));
  
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    String previous = "";
    boolean allEnemiesDefeated;
    boolean allPartyDefeated;
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies, previous, whichPlayer, partyTurn); //initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/support/special/quit";

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      
      //Read user input
      input = userInput(in);

      allEnemiesDefeated = true;
        for (int i = 0; i < enemies.size(); i++) {
          if (enemies.get(i).getHP() > 0) {
            allEnemiesDefeated = false;
            break;
          }
        }
        if (allEnemiesDefeated) {
          previous = "You win! All enemies are defeated.";
          quit();
          drawScreen(party,enemies,previous, whichPlayer, partyTurn);
          return;
        }
        allPartyDefeated = true;
        for (int i = 0; i < party.size(); i++) {
          if (party.get(i).getHP() > 0) {
            allPartyDefeated = false;
            break;
          }
        }
        if (allPartyDefeated) {
          previous = "You lose! All party members are defeated.";
          quit();
          drawScreen(party,enemies,previous, whichPlayer, partyTurn);
          return;
        } 


      //display event based on last turn's input
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.startsWith("attack") || input.startsWith("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          try {
            int index = Integer.parseInt(input.split(" ")[1]);
            if (index >= 0 && index < enemies.size()) {
              previous = party.get(whichPlayer).attack(enemies.get(index));
            } 
            else {
              previous = "Invalid target index for attack.";
              whichPlayer--;
            }
          } 
          catch (ArrayIndexOutOfBoundsException e) {
            previous = "Invalid input format for attack.";
            whichPlayer--;
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("special") || input.startsWith("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          try {
            int index = Integer.parseInt(input.split(" ")[1]);
            if (index >= 0 && index < enemies.size()) {
              previous = party.get(whichPlayer).specialAttack(enemies.get(index));
            } 
            else {
              previous = "Invalid target index for special attack .";
              whichPlayer--;
            }
          } 
          catch (ArrayIndexOutOfBoundsException e) {
            previous = "Invalid input format for special attack.";
            whichPlayer--;
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          try {
            int index = Integer.parseInt(input.split(" ")[1]);
            if (index >= 0 && index < party.size()) {
              previous = party.get(whichPlayer).support(party.get(index));
            } 
            else {
              previous = "Invalid target index for support.";
              whichPlayer--;
            }
          } 
          catch (ArrayIndexOutOfBoundsException e) {
            previous = "Invalid input format for support.";
            whichPlayer--;
          }
        }
        else {
          previous = "Invalid command. Try attack/special/support.";
          whichPlayer--;
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

        //You should decide when you want to re-ask for user input
        //If no errors:
        
        whichPlayer++;
        try{
          if(party.get(whichPlayer).getHP() <= 0){
            whichPlayer++;
          }
        }catch(IndexOutOfBoundsException e){}



        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          // previous  = "Enter command for "+party.get(whichPlayer)+": attack/special/support/quit";


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:

          partyTurn = false;
          whichOpponent = 0;
          whichPlayer = 0;
          for(int i = 0; i < party.size(); i++){
            party.get(i).setDamageMultiplier(1.0);
          }
        }
        //done with one party member


      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        Adventurer enemy = enemies.get(whichOpponent);
        int adventurerToBeAttacked = (int)(Math.random() * party.size());
        while(party.get(adventurerToBeAttacked).getHP() < 0){
          adventurerToBeAttacked = (int)(Math.random() * party.size());
        }
        Adventurer target = party.get(adventurerToBeAttacked);
        if (Math.random() < 0.5) {
          previous = enemy.attack(target);
        }
        else {
          previous = enemy.specialAttack(target);
        }
        whichOpponent++;
        try{
          if(party.get(whichOpponent).getHP() <= 0){
            whichOpponent++;
          }
        }catch(IndexOutOfBoundsException e){}
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/



      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        whichOpponent = 0;
        for(int i = 0; i < enemies.size(); i++){
          enemies.get(i).setDamageMultiplier(1.0);
        }
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
      }


      //display the updated screen after input has been processed.
      drawScreen(party, enemies, previous, whichPlayer, partyTurn);


      // TextBox(24,2,78,10,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );


    }//end of main game loop
    // TextBox(24,2,78,10,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );
    TextBox(16, 41, 39, 10, "You have quit the game.");

    //After quit reset things:
    quit();
  }
}
