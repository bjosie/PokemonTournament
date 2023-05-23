
/**
 * Delayed attack effect.
 *
 * @author Cash Hilstad
 * @version 1.0
 */
public class DelayedAttack extends Effect
{
    private double hitChance;
    private Pokemon user;
    private double power;
    private String flavorText;
    private boolean special;
    
    public DelayedAttack(int timeLeft, String flavorText, Pokemon target, Pokemon user, boolean special, double hitChance, double power)
    {
        super(target, timeLeft);
        this.flavorText = flavorText;
        this.user = user;
        this.special = special;
        this.hitChance = hitChance;
        this.power = power;
    }
    
    public void act(){
        super.act();
        if(timeLeft <= 0){
            System.out.println(flavorText);
            timeLeft = 0;
 
            int damage = attack(user, target, special, power);
            if(hit(hitChance)){
                System.out.println("...and it hit!");
                target.setHealth(target.getHealth() - damage);
                System.out.println("It did " + damage + " damage!");
            } else {
                System.out.println("...but it missed!");
            }
            
            
        }

 
        return;
    }
}
