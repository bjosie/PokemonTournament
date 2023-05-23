import java.util.Scanner;

public class Battler {
    //contains the two pokemon fighting
    protected Pokemon[] fighters = new Pokemon[2];
    static private Scanner input = new Scanner(System.in);
    private EffectManager effects = new EffectManager();
    
    //battle order
    protected int firstToGo;
    protected int secondToGo;
    private boolean firstUltUsed = false;
    private boolean secondUltUsed = false;

    //save if battle can ever run
    private boolean isLegalBattle = true;
    
    private int turn = 1;

    //constructor, also determines which is faster
    public Battler(Pokemon newFighterOne, Pokemon newFighterTwo, int maxPoints){
        if (!Pokemon.validate(newFighterOne, maxPoints)){
            System.out.println(newFighterOne.getName() + " is illegal! Battle ending...");
            isLegalBattle = false;
        }
        if (!Pokemon.validate(newFighterTwo, maxPoints)){
            System.out.println(newFighterTwo.getName() + " is illegal! Battle ending...");
            isLegalBattle = false;
        }
   
        fighters[0] = newFighterOne;
        fighters[1] = newFighterTwo;
        
        determineOrder();


    }
    
    private final void determineOrder(){
        if (fighters[0].getSpeed() > fighters[1].getSpeed()){
            firstToGo = 0;
            secondToGo = 1;
        } else {
            firstToGo = 1;
            secondToGo = 0;
        }    
    }

    //return random number
    private final int roll(int sides){
        return (int) (Math.random() * sides);
    }

    private boolean hit(double chance){
        return (chance >= Math.random());
    }


    private int attack(Pokemon user, Pokemon target, boolean isSpecial, double modifier){
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
    
    //take in a move, and perform whatever said move does
    private void moveDefinition(String move, Pokemon user, Pokemon target, boolean isFirst){
        int damage = 0;
        boolean ultUsed = false;
        if (isFirst){ultUsed = firstUltUsed;}
        else {ultUsed = secondUltUsed;}
        
        switch(move){
            case "debug": 
                System.out.println("It does nothing!");
                break;
                
            case "Slap":
                if (hit(0.9)){
                    damage = attack(user, target, false, 0.9);
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("It did " + damage + " damage!");
                } else {
                    System.out.println("But it missed!");
                }            
                break;

            case "Leech":
                if (hit(0.75)){
                    damage = attack(user, target, false, 0.75);
                    target.setHealth(target.getHealth() - damage);
                    user.setHealth(user.getHealth() + damage);
                    System.out.println("They stole " + damage + " health!");
                } else {
                    System.out.println("But it missed!");
                }            
                break;
                
            case "Laser":
                if (hit(0.9)){
                    damage = attack(user, target, true, 0.9);
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("It did " + damage + " damage!");
                } else {
                    System.out.println("But it missed!");
                }
                break;
                
            case "Kick":
                if (hit(0.75)){
                    damage = attack(user, target, false, 1.25);
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("It did " + damage + " damage!");
                } else {
                    System.out.println("But it missed!");
                }        
                break;

            case "Rush":
                System.out.println("They started attacking rapidly!");
                while (hit(0.6)){
                    damage = roll(6) + 1;
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("They hit... it did " + damage + " damage!");
                }
                System.out.println("They missed!");
                break;
                
            case "Chip":

                damage = (int) ((double)target.getHealth() * 0.1);
                target.setHealth(target.getHealth() - damage);
                System.out.println("It did " + damage + " damage!");

                break;
                
            case "Plasma Shot":
                if (hit(0.75)){
                    damage = attack(user, target, true, 1.25);
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("It did " + damage + " damage!");
                } else {
                    System.out.println("But it missed!");
                }        
                break;
                
            case "Turntables":
                System.out.println(user.getName() + " used their ultimate move!");
                if (!ultUsed){
                    int atk = target.getAttack();
                    int def = target.getDefense();
                    int spatk = target.getSpecialAttack();
                    int spdef = target.getSpecialDefense();
                    target.setAttack(def);
                    target.setDefense(atk);
                    target.setSpecialAttack(spdef);
                    target.setSpecialDefense(spatk);
                    System.out.println(target.getName() + "'s stats were flipped!");
                    ultUsed = true;
                } else {
                    System.out.println("But they've already used it!");
                }
                break;
                
            case "Switch":
                if (hit(0.8)){
                    int atk = user.getAttack();
                    int def = user.getDefense();
                    int spatk = user.getSpecialAttack();
                    int spdef = user.getSpecialDefense();
                    user.setAttack(def);
                    user.setDefense(atk);
                    user.setSpecialAttack(spdef);
                    user.setSpecialDefense(spatk);
                    System.out.println(user.getName() + " flipped their stats!");
                } else {
                    System.out.println("But they failed!");
                }
                break;

            case "Coin Flip":
                System.out.println(user.getName() + " used their ultimate move!");
                if (!ultUsed){
                    if (hit(0.5)){
                        System.out.println("They flipped heads!");
                        damage = attack(user, target, true, 3.0);
                        target.setHealth(target.getHealth() - damage);
                        System.out.println("It did " + damage + " damage to the opponent!");
                    } else {
                        System.out.println("Uh oh... they flipped tails!");
                        damage = attack(user, user, true, 1.0);
                        user.setHealth(user.getHealth() - damage);
                        System.out.println("It did " + damage + " damage to themselves!");
                    }
                    ultUsed = true;
                } else {
                    System.out.println("But they've already used it!");
                }
                break;

            case "Death Ray":
                System.out.println(user.getName() + " used their ultimate move!");
                if (!ultUsed){
                    if (hit(0.99)){
                        damage = attack(user, target, true, 2.0);
                        target.setHealth(target.getHealth() - damage);
                        System.out.println("It did " + damage + " damage to the opponent!");
                    } else {
                        System.out.println("It missed!");
                    }
                    ultUsed = true;
                } else {
                    System.out.println("But they've already used it!");
                }
                break;

            case "Body Slam":
                System.out.println(user.getName() + " used their ultimate move!");
                if (!ultUsed){
                    if (hit(0.99)){
                        damage = attack(user, target, false, 2.0);
                        target.setHealth(target.getHealth() - damage);
                        System.out.println("It did " + damage + " damage to the opponent!");
                    } else {
                        System.out.println("It missed!");
                    }
                    ultUsed = true;
                } else {
                    System.out.println("But they've already used it!");
                }
                break;

            case "Self Destruct":
                System.out.println(user.getName() + " used their ultimate move!");
                
                if (!ultUsed){
                    System.out.println("They exploded!");
                    if (hit(0.75)){
                        damage = attack(user, target, true, 5.0);
                        target.setHealth(target.getHealth() - damage);
                        System.out.println("It did " + damage + " damage to the opponent!");
                    } else {
                        System.out.println(target.getName() + " evaded the explosion!");
                    }
                    user.setHealth(1);
                    ultUsed = true;
                } else {
                    System.out.println("But they've already used it!");
                }
                break;
                
            case "Fortify":
                int oldDEF = user.getDefense();
                double newDEF = (double) oldDEF * 1.25;
                user.setDefense((int) newDEF);
                System.out.println("They raised their defense!");
                break;

            case "Resist":
                int oldSPDEF = user.getSpecialDefense();
                double newSPDEF = (double) oldSPDEF * 1.25;
                user.setSpecialDefense((int) newSPDEF);
                System.out.println("They raised their special defense!");
                break;

            case "Sharpen":
                int oldATK = user.getAttack();
                double newATK = (double) oldATK * 1.25;
                user.setAttack((int) newATK);
                System.out.println("They raised their attack!");
                break;

            case "Focus":
                int oldSPATK = user.getSpecialAttack();
                double newSPATK = (double) oldSPATK * 1.25;
                user.setSpecialAttack((int) newSPATK);
                System.out.println("They raised their special attack!");
                break;

            case "Intimidate":
                target.setAttack((int)((double) target.getAttack() / 1.25));
                target.setSpecialAttack((int)((double) target.getSpecialAttack() / 1.25));
                System.out.println("They lowered the opponent's attack!");
                break;

            case "Tickle":
                target.setDefense((int)((double) target.getDefense() / 1.25));
                target.setSpecialDefense((int)((double) target.getSpecialDefense() / 1.25));
                System.out.println("They lowered the opponent's defense!");
                break;
                

            case "Heal":
                int oldHP = user.getHealth();
                int addon = roll(12) + roll(12);
                user.setHealth(oldHP + addon);
                System.out.println("They healed " + addon + " health!");
                break;
                
            case "Stretch":
                int oldSpeed = user.getSpeed();
                double newSpeed = (double) oldSpeed * 1.25;
                user.setSpeed((int) newSpeed);
                System.out.println("They raised their speed!");
                break;
                
            case "Quick Attack":
                user.setSpeed(user.getSpeed() + 25);
                System.out.println(user.getName() + " became faster!");
                if (hit(0.85)){
                    damage = attack(user, target, false, 0.8);
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("It did " + damage + " damage!");
                } else {
                    System.out.println("But it missed!");
                }
                break;
                
            case "Lightning Flash":
                user.setSpeed(user.getSpeed() + 25);
                System.out.println(user.getName() + " became faster!");
                if (hit(0.85)){
                    damage = attack(user, target, true, 0.8);
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("It did " + damage + " damage!");
                } else {
                    System.out.println("But it missed!");
                }
                break;
                
            
                
            case "Venomous Bite":
                if (hit(0.75)){
                    damage = attack(user, target, false, 0.8);
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("It did " + damage + " damage!");
                    if(hit(0.5)){
                        effects.addEffect(new Venom(target, roll(6) + roll(6)));
                        System.out.println("Venom enters the target's wounds!");
                    }
                } else {
                    System.out.println("But it missed!");
                }        
                break;  
                
            case "Venomous Splash":
                if (hit(0.95)){
                    effects.addEffect(new Venom(target, roll(6) + roll(3)));
                    System.out.println("Venom sprays onto the target!");  
                } else {
                    System.out.println("But it missed!");
                }        
                break;  
                
            case "Withering Touch":
                if (hit(0.75)){
                    damage = attack(user, target, true, 0.8);
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("It did " + damage + " damage!");
                    if(hit(0.5)){
                        effects.addEffect(new Decay(target, roll(6) + roll(6)));
                        System.out.println(target.getName() + " begins to decay!");
                    }
                } else {
                    System.out.println("But it missed!");
                }        
                break;  
                
            case "Necromancy":
                if (hit(0.95)){
                    effects.addEffect(new Decay(target, roll(6) + roll(3)));
                    System.out.println(target.getName() + " begins to decay!");  
                } else {
                    System.out.println("But it missed!");
                }        
                break;  
                
            case "Bless":
                if (hit(0.75)){
                    effects.addEffect(new Blessed(user, roll(6) + roll(3)));
                    System.out.println(user.getName() + " is blessed!");  
                } else {
                    System.out.println("But it missed!");
                }        
                break;  
                
            case "Hack":
                if (hit(0.75)){
                    effects.addEffect(new Glitch(target, roll(6) + roll(3)));
                    System.out.println(target.getName() + " started to glitch!");  
                } else {
                    System.out.println("But it missed!");
                }        
                break;  
                
            case "Faulty Code":
                effects.addEffect(new Glitch(user, roll(6) + roll(3)));
                System.out.println(user.getName() + " started to glitch!");  
                break;  
                
            case "Blessed Blade":
                if (hit(0.6)){
                    damage = attack(user, target, false, 1.5);
                    target.setHealth(target.getHealth() - damage);
                    System.out.println("It did " + damage + " damage!");

                } else {
                    System.out.println("But it missed!");
                    effects.addEffect(new Blessed(target, roll(6) + roll(6)));
                    System.out.println("Uh oh... " + target.getName() + " was blessed!");
                }        
                break; 
                
            case "Delayed Kick":
                System.out.println("They ready to kick...");
                effects.addEffect(new DelayedAttack(2, user.getName() + " goes for a kick!", target, user, false, 0.9, 1.5));
                break;
                
            case "Delayed Blast":
                System.out.println("They ready a powerful blast...");
                effects.addEffect(new DelayedAttack(2, user.getName() + " launches a blast of energy!", target, user, true, 0.9, 1.5));
                break;
                
            case "Thunder Cross Split Attack":
                System.out.println(user.getName() + " used their ultimate move!");
                
                if (!ultUsed){
                    System.out.println("They jump up into the air, readying their ultimate move!");               
                    effects.addEffect(new DelayedAttack(3, user.getName() + " goes for their ultimate move!", target, user, false, 0.95, 3.0));
                } else {
                    System.out.println("But they've already used it!");
                }
                break;
                
             case "Charge Up":
                System.out.println(user.getName() + " used their ultimate move!");
                
                if (!ultUsed){
                    System.out.println("They create a ball of energy, readying their ultimate move!");               
                    effects.addEffect(new DelayedAttack(3, user.getName() + " goes for their ultimate move!", target, user, true, 0.95, 3.0));
                } else {
                    System.out.println("But they've already used it!");
                }
                break;
            
            case "Dream":
                System.out.println(user.getName() + " used their ultimate move!");
                
                if (!ultUsed){
                    System.out.println("They began to dream... their stats are increasing every turn!");
                    effects.addEffect(new Dream(user, 5));
                } else {
                    System.out.println("But they've already used it!");
                }
                break;
                
            case "Living Wall":
                System.out.println(user.getName() + " used their ultimate move!");
                
                if (!ultUsed){
                    System.out.println("They turned to stone!");
                    effects.addEffect(new Wall(user, 2));
                } else {
                    System.out.println("But they've already used it!");
                }
                break;

            case "Adrenaline Rush":
                System.out.println(user.getName() + " used their ultimate move!");
                
                if (!ultUsed){
                    System.out.println("They lost half of their health... and doubled all their other stats!");
                    user.setHealth(user.getHealth()/2);

                    user.setAttack(user.getAttack()*2);
                    user.setSpecialAttack(user.getSpecialAttack()*2);
                    user.setDefense(user.getDefense()*2);
                    user.setSpecialDefense(user.getSpecialDefense()*2);
                    
                    ultUsed = true;
                } else {
                    System.out.println("But they've already used it!");
                }
                break;
                
        }

        if (isFirst) {firstUltUsed = ultUsed;} else {secondUltUsed = ultUsed;}
        System.out.println("");
    }
    
    public void printTurn(){
        System.out.println("------------------");
        System.out.println("Turn " + turn);
        System.out.println("------------------");
        turn++;
    }

    public boolean turn(){
        printTurn();
        boolean anyHaveFainted = false;
        
        System.out.println(fighters[firstToGo].getName() + ", what move will you use?");
        System.out.println("");
        fighters[firstToGo].movePrint();
        int move = input.nextInt();
        System.out.println(fighters[firstToGo].getName() + " used " + fighters[firstToGo].getMove(move) + "!");
        moveDefinition(fighters[firstToGo].getMove(move), fighters[firstToGo], fighters[secondToGo], true);
        System.out.println("------------------");
        fighters[secondToGo].mPrint();
        System.out.println("------------------");
        if (fighters[firstToGo].checkHealth() || fighters[secondToGo].checkHealth()){
            anyHaveFainted = true;
        }

        if (!anyHaveFainted){
            System.out.println(fighters[secondToGo].getName() + ", what move will you use?");
            System.out.println("");
            fighters[secondToGo].movePrint();
            move = input.nextInt();
            System.out.println(fighters[secondToGo].getName() + " used " + fighters[secondToGo].getMove(move) + "!");
            moveDefinition(fighters[secondToGo].getMove(move), fighters[secondToGo], fighters[firstToGo], false);
            System.out.println("------------------");
            fighters[firstToGo].mPrint();
            System.out.println("------------------");
            if (fighters[firstToGo].checkHealth() || fighters[secondToGo].checkHealth()){
                anyHaveFainted = true;
            }
        }
        
        System.out.println("");
        if(!effects.areNoEffects() && !anyHaveFainted){
            System.out.println("Applying effects...");
            effects.run();
            System.out.println();
        }
        determineOrder();
        return anyHaveFainted;
    }

    public void fight(){
        if (!isLegalBattle) {
            System.out.println("Battle over! One or more of the Pokemon were unable to be run.");
            return;
        }
        boolean battleOver = false;
        while(!battleOver){
            battleOver = turn();
        }

        
        
    }
}