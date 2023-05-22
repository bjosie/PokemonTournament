
/**
 * Decay effect.
 *
 * @author Cash Hilstad
 * @version 1.0
 */
public class Decay extends Effect
{

    public Decay(Pokemon target, int timeLeft)
    {
        super(target, timeLeft);
    }
    
    public void act(){
        super.act();
        if(Math.random() >= 0.95 || timeLeft <= 0){
            System.out.println(target.getName() + "'s decaying stopped!");
            timeLeft = 0;
            return;
        }
        int damage = (int) ((double)target.getHealth() * 0.05);
        System.out.println(target.getName() + " took " + damage + " damage from decay!");
        damageHP(damage);
        return;
    }

}
