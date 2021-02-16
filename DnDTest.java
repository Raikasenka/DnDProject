public class DnDTest{
	public static void main(String[] args) {
		Entity user=new Entity("Luke",19,"Human",5,Profession.BARBARIAN,7,8,12,11,10,5,0,0,0,1,0,2,0,0,0,"0-1");
		user.setEquippedWeapon(new Weapon("Shortsword",2,6,1,Weapon.SLASHING));
		@SuppressWarnings({"ConstantConditions","unused"})
		Fight fight=new Fight(user, Monster.createMonster(1));
	}
}