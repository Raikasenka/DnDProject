public class Weapon{
	private String name,damageType;
	private int diceRolls,diceRolled,baseDamage,durability;
	
	//Start of static damage types
		public static final String BLUDGEONING="Bludgeoning";
		public static final String PIERCING="Piercing";
		public static final String SLASHING="Slashing";
	//End of static damage types
	
	public Weapon(String nameIn, int diceRolls, int diceRolled,int baseDamage, String damageType,int durability){
		this.name=nameIn;
		this.diceRolls=diceRolls;
		this.diceRolled=diceRolled;
		this.baseDamage=baseDamage;
		this.damageType=damageType;
		this.durability=durability;
	}
	public Weapon(String nameIn, int diceRolls, int diceRolled,int baseDamage, String damageType){
		this.name=nameIn;
		this.diceRolls=diceRolls;
		this.diceRolled=diceRolled;
		this.baseDamage=baseDamage;
		this.damageType=damageType;
	}

	//This will go unused as long as there is no getter for opponent details
	public double calculateDamageRatio(String opponentArmorType){
		double ratio=1;
		if(this.damageType.equals("Bludgeoning")){
			switch(opponentArmorType){
				case "Skin":
					ratio=2/3;break;
				case "Light Armor":
					ratio=3/2;break;
				case "Heavy Armor":
					ratio=1;break;
			}
		}

		else if(this.damageType.equals("Piercing")){
			switch(opponentArmorType){
				case "Skin":
					ratio=1;break;
				case "Light Armor":
					ratio=2/3;break;
				case "Heavy Armor":
					ratio=3/2;break;
			}
		}

		else if(this.damageType.equals("Slashing")){
			switch(opponentArmorType){
				case "Skin":
					ratio=3/2;break;
				case "Light Armor":
					ratio=1;break;
				case "Heavy Armor":
					ratio=2/3;break;
			}
		}
		else{
			ratio=0;
		}return ratio;
	}

	public int attack(){
		int damage=this.baseDamage;
		for(int x=0; x<diceRolls;x++){
			damage+=Static.rollAny(diceRolled);
		}return damage;
	}
}