
/**
 * Base class for effects.
 *
 * @author Cash Hilstad
 * @version 1.0
 */
public class Effect
{
    //target of effects
    protected Pokemon target;
    //timer of effects
    protected int timeLeft;
    
    
    //create base effect 
    public Effect(Pokemon target, int timeLeft)
    {
        this.target = target;
        this.timeLeft = timeLeft;
    }
    
    //action for effect. will be extended by subclasses
    public void act(){
        timeLeft -= 1;
    }
    
    //returns true if no time is left
    public boolean noTimeLeft(){
        return timeLeft <= 0;
    }
    
    protected void damageHP(int damageAmount){
        target.setHealth(target.getHealth() - damageAmount);
    }
    
    protected final int roll(int sides){
        return (int) (Math.random() * sides);
    }
    
    protected boolean hit(double chance){
        return (chance >= Math.random());
    }

    
    protected int attack(Pokemon user, Pokemon target, boolean isSpecial, double modifier){
        int attackStat = 1;
        int defenseStat = 1;
        if (isSpecial){
            attackStat = user.getSpecialAttack();
            defenseStat = target.getSpecialDefense();
        } else {
            attackStat = user.getAttack();
            defenseStat = target.getDefense();
        }
        double baseDMG = (double) attackStat / (double) defenseStat / 255.0;
        baseDMG *= (double)(roll(32) + 224);
        if (hit(0.05)){
            System.out.println("A critical hit!");
            return (int) (baseDMG * 25.0 * modifier);
        }
        return (int) (baseDMG * 10.0 * modifier);
    }

}
