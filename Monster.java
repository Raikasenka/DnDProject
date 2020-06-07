import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
import java.io.File;
public class Monster{
	
	//Private variable declarations
			
		private String type; private int hitDie,diceRolled,maxHP,currentHP,pageNumber;
		private int strength,dexterity,intelligence,constitution,wisdom,charisma;
		private int strMod,dexMod,intMod,conMod,wisMod,chrMod;
		private HashMap<DamageType, State> damages;
		private ArrayList<Skill> skillList;

	//End of private variable declarations

	Monster(
		String type,
		int diceRolled,
		int hitDie,

		int strength,
		int dexterity,
		int constitution,
		int intelligence,
		int wisdom,
		int charisma,
		
		int pageNumber){

			/*Social Stats*/
			//************************\\
				this.type=type;
				this.diceRolled=diceRolled;
				this.hitDie=hitDie;
			//\\**********************//

			/*Core Stats*/
			//****************************\\
				this.strength=strength;
				this.dexterity=dexterity;
				this.constitution=constitution;
				this.intelligence=intelligence;
				this.wisdom=wisdom;
				this.charisma=charisma;
			//\\**************************//

			/*CoreModifiers*/
			//********************************************\\
				strMod=(int)Math.floor((strength-10)/2.0);
				dexMod=(int)Math.floor((dexterity-10)/2.0);
				intMod=(int)Math.floor((intelligence-10)/2.0);
				conMod=(int)Math.floor((constitution-10)/2.0);
				wisMod=(int)Math.floor((wisdom-10)/2.0);
				chrMod=(int)Math.floor((charisma-10)/2.0);
			//\\******************************************//
				damages=new HashMap<DamageType,State>();
				this.pageNumber=pageNumber;

			maxHP=(int)(diceRolled*(((hitDie+1)/2.0)+conMod));
			currentHP=maxHP;

			skillList=new ArrayList<Skill>();
		}
		public String getType(){return type;}

		public int getDiceRolled(){return diceRolled;}

		public int getHitDie(){return hitDie;}

		public int getSTR(){return strength;}
		public int getDEX(){return dexterity;}
		public int getCON(){return constitution;}
		public int getINT(){return intelligence;}
		public int getWIS(){return wisdom;}
		public int getCHR(){return charisma;}

		public int getFireState(){return damages.get(DamageType.FIRE).ordinal();}
		public int getColdState(){return damages.get(DamageType.COLD).ordinal();}
		public int getPoisonState(){return damages.get(DamageType.POISON).ordinal();}
		public int getAcidState(){return damages.get(DamageType.ACID).ordinal();}
		public int getShockState(){return damages.get(DamageType.SHOCK).ordinal();}
		public int getRadiantState(){return damages.get(DamageType.RADIANT).ordinal();}
		public int getNecroticState(){return damages.get(DamageType.NECROTIC).ordinal();}
		public int getForceState(){return damages.get(DamageType.FORCE).ordinal();}
		public int getPsychicState(){return damages.get(DamageType.PSYCHIC).ordinal();}

		public int getMaxHP(){return maxHP;}
		public int getCurrentHP(){return currentHP;}
		public void takeDamage(int damage){currentHP-=damage;}
		public void heal(int life){currentHP+=life;}

		public HashMap getMap(){return damages;}
		public ArrayList<Skill> getSkillList(){return skillList;}

		/*Multiple overloads will be required, will ask for a MonsterList of a specific area and will take that as an input
			Monster createMonster(int id, String areaMonsterPoolFile)*/
		public static Monster createMonster(int id){
			try (Stream<String> lines = Files.lines(Paths.get("MonsterList.txt"))){
				String monsterData=lines.skip((id)).findFirst().get();
				Scanner s=new Scanner(monsterData);
				s.useDelimiter(",");
				String type=s.next();
				int diceRolled=s.nextInt();
				int hitDie=s.nextInt();

				int strength=s.nextInt();
				int dexterity=s.nextInt();
				int intelligence=s.nextInt();
				int constitution=s.nextInt();
				int wisdom=s.nextInt();
				int charisma=s.nextInt();

				int pageNumber=s.nextInt();
				Monster monster=new Monster(type,diceRolled,hitDie,
					strength,dexterity,constitution,intelligence,wisdom,charisma,pageNumber);
				//Damage types and states association
				DamageType[] damageTypes=DamageType.values();
				State[] states=State.values();
				for(int i=0;i<damageTypes.length;i++){
					monster.getMap().put(damageTypes[i],states[s.nextInt()]);
				}
				//Skill list assigning
				String skillStr=s.next();
				String[] skillNumbers=skillStr.split("-");
				Skill[] skills=Skill.values();
				for(int i=0;i<skillNumbers.length;i++){
					int skill= Integer.parseInt(skillNumbers[i]);
					monster.getSkillList().add(skills[skill]);
				}
				s=new Scanner("");
				s.close();
				return monster;
			}catch(Exception e){System.out.println(e.getMessage());}
			return null;//If ya down here ya fucked up somewhere
		}
	
		public boolean isAlive(){
		return this.currentHP>0?true:false;
	}
}