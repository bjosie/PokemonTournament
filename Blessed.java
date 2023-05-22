
/**
 * Blessed status effect.
 *
 * @author Cash Hilstad
 * @version 1.0
 */
public class Blessed extends Effect
{
    public Blessed(Pokemon target, int timeLeft)
    {
        super(target, timeLeft);
    }
    
    public void act(){
        super.act();
        if(Math.random() >= 0.8 || timeLeft <= 0){
            System.out.println(target.getName() + "'s blessing ended!");
            timeLeft = 0;
            return;
        }
        target.setSpeed((int)(target.getSpeed() * 1.05));
        target.setAttack((int)(target.getAttack() * 1.05));
        target.setSpecialAttack((int)(target.getSpecialAttack() * 1.05));
        target.setDefense((int)(target.getDefense() * 1.05));
        target.setSpecialDefense((int)(target.getSpecialDefense() * 1.05));
        System.out.println(target.getName() + "'s blessing increased their stats!");
        return;
    }
}
