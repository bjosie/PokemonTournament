
/**
 * Dream effect.
 *
 * @author Cash Hilstad
 * @version 1.0
 */
public class Dream extends Effect
{
    public Dream(Pokemon target, int timeLeft)
    {
        super(target, timeLeft);
    }
    
    public void act(){
        super.act();
        if(timeLeft <= 0){
            System.out.println(target.getName() + "'s dream ended!");
            timeLeft = 0;
            target.setSpeed(50);
            target.setAttack(50);
            target.setSpecialAttack(50);
            target.setDefense(50);
            target.setSpecialDefense(50);
            
            return;
        }
        target.setSpeed(target.getSpeed() + 75);
        target.setAttack(target.getAttack() + 75);
        target.setSpecialAttack(target.getSpecialAttack() + 75);
        target.setDefense(target.getDefense() + 75);
        target.setSpecialDefense(target.getSpecialDefense() + 75);
 
        System.out.println(target.getName() + "'s dream made them stronger!");
        return;
    }
}
