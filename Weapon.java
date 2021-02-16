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
		this.durability=Integer.MAX_VALUE;
	}

	//This will go unused as long as there is no getter for opponent details
	public double calculateDamageRatio(String opponentArmorType){
		double ratio=1;
		switch (this.damageType){
			case "Bludgeoning":
				switch (opponentArmorType){
					case "Skin":
						ratio=2/3d;break;
					case "Light Armor":
						ratio=3/2d;break;
					case "Heavy Armor":
						ratio=1;break;
				}break;
				
			case "Piercing":
				switch (opponentArmorType){
					case "Skin":
						ratio=1;break;
					case "Light Armor":
						ratio=2/3d;break;
					case "Heavy Armor":
						ratio=3/2d;break;
				}break;
				
			case "Slashing":
				switch (opponentArmorType){
					case "Skin":
						ratio=3/2d;break;
					case "Light Armor":
						ratio=1;break;
					case "Heavy Armor":
						ratio=2/3d;break;
				}break;
			default:
				ratio=0;break;
		}return ratio;
	}

	public int attack(){
		int damage=this.baseDamage;
		for(int x=0; x<diceRolls;x++){
			damage+=Static.rollAny(diceRolled);
		}return damage;
	}
}