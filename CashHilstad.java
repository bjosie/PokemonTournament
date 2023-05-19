//test class 
class CashHilstad extends Pokemon {

	public CashHilstad(){
		name = "Yertle";
		fainted = false;
		health = 200;
		maxHealth = health;
		speed = 50;
		attack = 50;
		specialAttack = 50;
		defense = 125;
		specialDefense = 125;
		moves = new String[] {"Rush", "Leech", "Switch", "Fortify"};
	}

	public CashHilstad(String name, boolean fainted, int health,
			int speed, int attack, int specialAttack, int defense, 
			int specialDefense, String[] moves)
	{
		this.name = name;
		this.fainted = fainted;
		this.health = health;
		this.maxHealth = health;
		this.speed = speed;
		this.attack = attack;
		this.specialAttack = specialAttack;
		this.defense = defense;
		this.specialDefense = specialDefense;
		this.moves = moves;
	}

	public void print(){
		super.print();
	}

	public void print(boolean add){
		super.print();
		if (add) {
			System.out.println("Move 0: " + moves[0]);
			System.out.println("Move 1: " + moves[1]);
			System.out.println("Move 2: " + moves[2]);
			System.out.println("Move 3: " + moves[3]);
		}
	}

	public void print(String customMessage){
		super.print();
		System.out.println(customMessage);
	}

	
	public void setHealth(int newHealth){
		health = newHealth;
	}
	public void setMaxHealth(int newMaxHealth){
		health = newMaxHealth;
	}
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}	
	public void setAttack(int newAttack){
		attack = newAttack;
	}	
	public void setSpecialAttack(int newSpecialAttack){
		specialAttack = newSpecialAttack;
	}	
	public void setDefense(int newDefense){
		defense = newDefense;
	}	
	public void setSpecialDefense(int newSpecialDefense){
		specialDefense = newSpecialDefense;
	}
	public void setMove(String move, int index){
		moves[index] = move;
	}
	public void setName(String newName){
		name = newName;
	}

	public String getName(){return name;}
	public int getHealth(){return health;}
	public int getMaxHealth(){return maxHealth;}
	public int getSpeed(){return speed;}	
	public int getAttack(){return attack;}	
	public int getSpecialAttack(){return specialAttack;}	
	public int getDefense(){return defense;}	
	public int getSpecialDefense(){return specialDefense;}
	public String getMove(int index){return moves[index];}
}