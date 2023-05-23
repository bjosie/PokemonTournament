
/**
 * Wall effect
 *
 * @author Cash Hilstad
 * @version 1.0
 */
public class Wall extends Effect
{
    public Wall(Pokemon target, int timeLeft)
    {
        super(target, timeLeft);
        target.setDefense(target.getDefense() + 500);
        target.setSpecialDefense(target.getSpecialDefense() + 500);
    }
    
    public void act(){
        super.act();
        if(timeLeft <= 0){
            System.out.println(target.getName() + "'s defenses returned to normal!");
            timeLeft = 0;
 
            target.setDefense(target.getDefense() - 500);
            target.setSpecialDefense(target.getSpecialDefense() - 500);
            
            return;
        }

 
        return;
    }
}
