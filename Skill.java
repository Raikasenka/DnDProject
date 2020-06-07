enum Skill{
	BITE("Bite",1,6,1,"The enemy bites the chosen target for 1d6+1 damage"),
	JAVELIN("Javelin",1,6,2,"The enemy directly thrusts their spear or throw it at a chosen target");

	private String name,description;
	private int diceRolls,diceRolled,baseDamage,bonusDiceRolls,bonusDiceRolled,bonusBaseDamage;
	
	Skill(String name,int diceRolls, int diceRolled,int baseDamage,String description){
		this.name=name;
		this.diceRolls=diceRolls;
		this.diceRolled=diceRolled;
		this.baseDamage=baseDamage;
		this.description=description;
		this.bonusDiceRolls=0;
		this.bonusDiceRolled=0;
		this.bonusBaseDamage=0;
	}
	Skill(String name,int diceRolls, int diceRolled,int baseDamage,int bonusDiceRolls, int bonusDiceRolled,int bonusBaseDamage,String description){
		this.name=name;
		this.diceRolls=diceRolls;
		this.diceRolled=diceRolled;
		this.baseDamage=baseDamage;
		this.bonusDiceRolls=bonusDiceRolls;
		this.bonusDiceRolled=bonusDiceRolled;
		this.bonusBaseDamage=bonusBaseDamage;
		this.description=description;
	}
	
	public String getName(){return name;}
	
	public int getDiceRolls(){return diceRolls;}
	public int getDiceRolled(){return diceRolled;}
	public int getBaseDamage(){return baseDamage;}
	public int getBonusDiceRolls(){return bonusDiceRolls;}
	public int getBonusDiceRolled(){return bonusDiceRolled;}
	public int getBonusBaseDamage(){return bonusBaseDamage;}
	
	public String getDescription(){return description;}
	
	public int attack(){
		int damage=this.baseDamage+this.bonusBaseDamage;
		for(int x=0; x<diceRolls;x++){
			damage+=Static.rollAny(diceRolled);
		}
		for(int x=0; x<bonusDiceRolls;x++){
			damage+=Static.rollAny(bonusDiceRolled);
		}return damage;
	}
}