
/**
 * Glitch effect
 *
 * @author Cash Hilstad
 * @version 1.0
 */
public class Glitch extends Effect
{
    public Glitch(Pokemon target, int timeLeft)
    {
        super(target, timeLeft);
    }
    
    private double factor(){
        return 1.0 - ((Math.random() * 0.2) -0.1);
    }
    
    public void act(){
        super.act();
        if(Math.random() >= 0.8 || timeLeft <= 0){
            System.out.println(target.getName() + "'s glitch ended!");
            timeLeft = 0;
            return;
        }
        target.setSpeed((int)(target.getSpeed() * factor()));
        target.setAttack((int)(target.getAttack() * factor()));
        target.setSpecialAttack((int)(target.getSpecialAttack() * factor()));
        target.setDefense((int)(target.getDefense() * factor()));
        target.setSpecialDefense((int)(target.getSpecialDefense() * factor()));
        System.out.println(target.getName() + "'s glitch altered their stats!");
        return;
    }
}
