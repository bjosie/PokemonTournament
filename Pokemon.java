public class Pokemon {
    protected String name;
    protected boolean fainted;
    private int MAX_POINTS = 600;
    

    
    //basic stats
    protected int health;
    protected int maxHealth;
    protected int speed;
    protected int attack;
    protected int specialAttack;
    protected int defense;
    protected int specialDefense;

    //store list of moves in a String array
    protected String[] moves;

    //list of all valid moves
    private static final String[] validMoves = {
        "debug", "Slap", "Kick", "Laser", "Plasma Shot", 
        "Heal", "Fortify", "Sharpen", "Focus", "Turntables",
        "Switch", "Resist", "Coin Flip", "Death Ray", "Body Slam",
        "Leech", "Intimidate", "Tickle", "Self Destruct", "Adrenaline Rush",
        "Chip", "Rush"
    };

    //basic constructor
    public Pokemon(){
        defaultStats();
    }

    //use in constructors so that you don't need to retype defaults
    private void defaultStats(){
        name = "default";
        fainted = false;
        health = 1;
        maxHealth = health;
        speed = 1;
        attack = 1;
        specialAttack = 1;
        defense = 1;
        specialDefense = 1;
        moves = new String[4];
    }

    //basic master setters - for testing
    public final void setHealthM(int newHealth){
        health = newHealth;
    }
    public final void setSpeedM(int newSpeed){
        speed = newSpeed;
    }    
    public final void setAttackM(int newAttack){
        attack = newAttack;
    }    
    public final void setSpecialAttackM(int newSpecialAttack){
        specialAttack = newSpecialAttack;
    }    
    public final void setDefenseM(int newDefense){
        defense = newDefense;
    }    
    public final void setSpecialDefenseM(int newSpecialDefense){
        specialDefense = newSpecialDefense;
    }
    public final void setMoveM(String move, int index){
        moves[index] = move;
    }

    //basic getters
    public final String getNameM(){return name;}
    public final int getHealthM(){return health;}
    public final int getMaxHealthM(){return maxHealth;}
    public final int getSpeedM(){return speed;}    
    public final int getAttackM(){return attack;}    
    public final int getSpecialAttackM(){return specialAttack;}    
    public final int getDefenseM(){return defense;}    
    public final int getSpecialDefenseM(){return specialDefense;}
    public final String getMoveM(int index){return moves[index];}



    //overwrite these
  public String getName(){return "";}
    public int getHealth(){return 0;}
    public int getMaxHealth(){return maxHealth;}
    public int getSpeed(){return 0;}    
    public int getAttack(){return 0;}    
    public int getSpecialAttack(){return 0;}    
    public int getDefense(){return 0;}    
    public int getSpecialDefense(){return 0;}
    public String getMove(int index){return "";}
    public void setName(String replaceThis){}
    public void setHealth(int replaceThis){}
    public void setMaxHealth(int replaceThis){}
    public void setSpeed(int replaceThis){}    
    public void setAttack(int replaceThis){}    
    public void setSpecialAttack(int replaceThis){}    
    public void setDefense(int replaceThis){}    
    public void setSpecialDefense(int replaceThis){}
    public void setMove(String move, int index){}






    

    //check to see if fainted
    //if it has, return true
    public final boolean checkHealth(){
        if (health <= 0){
            fainted = true;
            System.out.println(name + " has fainted!");
            return true;
        }
        return false;
    }

    //add "write your own print!"
    public void print(){
        System.out.println("Name: " + name);
        System.out.println("Health: " + Math.round(((double)health/(double)maxHealth*100.0)) + "%");
        System.out.println("");
    }
    
    public void mPrint(){
        System.out.println("Name: " + name);
        System.out.println("Health: " + Math.round(((double)health/(double)maxHealth*100.0)) + "%");
        System.out.println("");
    }

    public void movePrint(){
        System.out.println("Move 0: " + getMove(0));
        System.out.println("Move 1: " + getMove(1));
        System.out.println("Move 2: " + getMove(2));
        System.out.println("Move 3: " + getMove(3));
        System.out.println("");
    }
    
    //return random number
    private final int roll(int sides){
        return (int) (Math.random() * sides);
    }

    //see if all moves in a given Pokemon are valid
    public static final boolean validateMoves(Pokemon target){
        for(int i = 0; i < 4; i++){
            boolean found = false;
            for(String move : validMoves){
                if(move.equals(target.getMove(i))){
                    found = true;
                }
            }
            if (!found){
                return false;
            }
        }
        return true;
    }

    public static boolean between50and200(int number){
        if ((number >= 50) && (number <= 200)){
            return true;
        }
        return false;
    }

    //see if all stats in a given Pokemon are valid
    public static final boolean validateStats(Pokemon target, int maxPoints){
        int pointSum = (
            target.getMaxHealthM() + target.getSpeedM() + target.getAttackM() +
            target.getSpecialAttackM() + target.getDefenseM() + target.getSpecialDefenseM()
            
        );

        int givenPointSum = (
            target.getMaxHealth() + target.getSpeed() + target.getAttack() +
            target.getSpecialAttack() + target.getDefense() + target.getSpecialDefense()
            
        );

        if (pointSum != givenPointSum){
            return false;
        }
        
        if (pointSum > maxPoints){
            return false;
        }

        if (!between50and200(target.getMaxHealthM())){
            return false;
        }

        if (!between50and200(target.getSpeedM())){
            return false;
        }

        if (!between50and200(target.getAttackM())){
            return false;
        }

        if (!between50and200(target.getSpecialAttackM())){
            return false;
        }

        if (!between50and200(target.getDefenseM())){
            return false;
        }
        
        if (!between50and200(target.getSpecialDefenseM())){
            return false;
        }
        
        return true;
    }

    //validate that a pokemon's setters work as intended
    public static final boolean validateSetters(Pokemon target){
        
        //randomly generated each time to prevent tricking system
        int keyCheck = (int) (Math.random() * 256);

        //one statcheck block
        int savedStat = target.getHealthM();
        target.setHealth(keyCheck);
        if(target.getHealthM() != keyCheck){
            target.setHealthM(savedStat);
            return false;
        }
        target.setHealthM(savedStat);

        savedStat = target.getSpeedM();
        target.setSpeed(keyCheck);
        if(target.getSpeedM() != keyCheck){
            target.setSpeedM(savedStat);
            return false;
        }
        target.setSpeedM(savedStat);

        savedStat = target.getAttackM();
        target.setAttack(keyCheck);
        if(target.getAttackM() != keyCheck){
            target.setAttackM(savedStat);
            return false;
        }
        target.setAttackM(savedStat);

        savedStat = target.getDefenseM();
        target.setDefense(keyCheck);
        if(target.getDefenseM() != keyCheck){
            target.setDefenseM(savedStat);
            return false;
        }
        target.setDefenseM(savedStat);
        
        savedStat = target.getSpecialAttackM();
        target.setSpecialAttack(keyCheck);
        if(target.getSpecialAttackM() != keyCheck){
            target.setSpecialAttackM(savedStat);
            return false;
        }
        target.setSpecialAttackM(savedStat);

        savedStat = target.getSpecialDefenseM();
        target.setSpecialDefense(keyCheck);
        if(target.getSpecialDefenseM() != keyCheck){
            target.setSpecialDefenseM(savedStat);
            return false;
        }
        target.setSpecialDefenseM(savedStat);
        
        return true;
    }

    //validate a pokemon in its entirety
    public static final boolean validate(Pokemon target, int maxPoints){
        int checkCount = (int) ((Math.random() * 10) + (Math.random() * 20) + 5.0);
        for(int i = 0; i < checkCount; i++){
            if(!(
                Pokemon.validateMoves(target) 
                && Pokemon.validateStats(target, maxPoints) 
                && Pokemon.validateSetters(target)
            )){
                    return false;
            }
        }
        return true;
    }
    
}