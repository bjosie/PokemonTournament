
/**
 * Venom effect.
 *
 * @author Cash Hilstad
 * @version 1.0
 */
public class Venom extends Effect
{

    public Venom(Pokemon target, int timeLeft)
    {
        super(target, timeLeft);
    }
    
    public void act(){
        super.act();
        if(Math.random() >= 0.9 || timeLeft <= 0){
            System.out.println(target.getName() + "'s venom wore off!");
            timeLeft = 0;
            return;
        }
        int damage = (int) (Math.random() * 12) + 1;
        damage += (int) (Math.random() * 12);
        System.out.println(target.getName() + " took " + damage + " damage to venom!");
        damageHP(damage);
        return;
    }

}
