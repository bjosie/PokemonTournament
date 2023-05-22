
/**
 * Random Pokemon. Stats and moves are randomized and legal.
 *
 * @author Cash Hilstad
 * @version 1.0
 */
public class RandomMonster extends Pokemon
{
    String[] possibleMoves = {
        "Slap", "Kick", "Laser", "Plasma Shot", 
        "Heal", "Fortify", "Sharpen", "Focus", "Turntables",
        "Switch", "Resist", "Coin Flip", "Death Ray", "Body Slam",
        "Leech", "Intimidate", "Tickle", "Self Destruct", "Adrenaline Rush",
        "Chip", "Rush"};
        
    public RandomMonster(){
        int[] stats = genStats();
        name = "Monmon";
        fainted = false;
        health = stats[0];
        maxHealth = health;
        speed = stats[1];
        attack = stats[2];
        specialAttack = stats[3];
        defense = stats[4];
        specialDefense = stats[5];
        
        int movesSize = possibleMoves.length;
        int move1 = (int)(Math.random() * movesSize);
        int move2 = (int)(Math.random() * movesSize);
        int move3 = (int)(Math.random() * movesSize);
        int move4 = (int)(Math.random() * movesSize);
        
        moves = new String[] {
            possibleMoves[move1],
            possibleMoves[move2],
            possibleMoves[move3],
            possibleMoves[move4],
        };
    }
    
    public void print(){
        super.print();
        System.out.println("Move 0: " + moves[0]);
        System.out.println("Move 1: " + moves[1]);
        System.out.println("Move 2: " + moves[2]);
        System.out.println("Move 3: " + moves[3]);
        System.out.println();
    }
    
    private int[] genStats(){
        int[] stats = new int[6];
        for(int i = 0; i < stats.length; i++){
            stats[i] = 50;
        }
        
        for(int i = 0; i < 300; i++){
            int randomStat = (int)(Math.random() * 6.0);
            stats[randomStat] += 1;
        }
        return stats;
    }
    
    public void setHealth(int newHealth){
        health = newHealth;
    }
    public void setSpeed(int newSpeed){
        speed = newSpeed;
    }    
    public void setAttack(int newAttack){
        attack = newAttack;
    }    
    public void setSpecialAttack(int newSpecialAttack){
        specialAttack = newSpecialAttack;
    }    
    public void setDefense(int newDefense){
        defense = newDefense;
    }    
    public void setSpecialDefense(int newSpecialDefense){
        specialDefense = newSpecialDefense;
    }
    public void setMove(String move, int index){
        moves[index] = move;
    }
    public void setName(String newName){
        name = newName;
    }

    public String getName(){return name;}
    public int getHealth(){return health;}
    public int getMaxHealth(){return maxHealth;}
    public int getSpeed(){return speed;}    
    public int getAttack(){return attack;}    
    public int getSpecialAttack(){return specialAttack;}    
    public int getDefense(){return defense;}    
    public int getSpecialDefense(){return specialDefense;}
    public String getMove(int index){return moves[index];}
}
