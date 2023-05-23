
/**
 * Manager of effects
 *
 * @author Cash Hilstad
 * @version 1.0
 */
import java.util.*;
public class EffectManager
{
    private ArrayList<Effect> effectList = new ArrayList<Effect>();
    
    public EffectManager()
    {
  
    }
    
    //run each effect and remove effects whose timer ran out
    public void run(){
        for(Effect e : effectList){
            e.act();
        }
        for(int i = effectList.size() - 1; i >= 0; i--){
            if(effectList.get(i).noTimeLeft()){
                effectList.remove(i);
            }
        }
    }
    
    //add effects
    public void addEffect(Effect newEffect){
        effectList.add(newEffect);
    }
    
    //if no effects, return false
    public boolean areNoEffects(){
        if (effectList.size() == 0){
            return true;
        }
        return false;
    }

   
}
