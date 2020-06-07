package Armor;
public class Armor_ChestPiece{
	private String name,armorType;
	private int damageThreshold;
	
	public Armor_ChestPiece(
		String name,
		String armorType,
		int damageThreshold){
		this.name=name;
		this.armorType=armorType;
		this.damageThreshold=damageThreshold;
	}
}