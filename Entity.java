import java.util.*;
import java.io.*;
enum Profession{
	BARBARIAN("Barbarian",12),
	BARD("Bard",8),
	CLERIC("Cleric",8),
	DRUID("Druid",8),
	FIGHTER("Fighter",10),
	MONK("Monk",8),
	PALADIN("Paladin",10),
	RANGER("Ranger",10),
	SORCERER("Sorcerer",6),
	WARLOCK("Warlock",8),
	WIZARD("Wizard",8);
	
	private String name;
	private int hitDie;
	Profession(String name,int hitDie){
		this.name=name;
		this.hitDie=hitDie;
	}
	public String getName(){return name;}

	public int getHD(){return hitDie;}

}

public class Entity{
	
	//Private variable declarations

		//						 (												Social stats												)
		private String name,race; private Profession profession;private int age,level,maxHP,currentHP;
		//					(													Core Stats												)
		private int strength,dexterity,intelligence,constitution,wisdom,charisma;
		//					(							Core Modifiers						 )
		private int strMod,dexMod,intMod,conMod,wisMod,chrMod;
		//					(		Damage states HashMap		)
		private HashMap<DamageType,State>damages;
		//					(																			 			HashMap Values																						 )
		private int fireState,coldState,poisonState,acidState,shockState,radiantState,necroticState,forceState,psychicState;
		//(Itemization)
		private Weapon equippedWeapon;
		//Skills
		private ArrayList<Skill> skillList;

	//End of variable declarations

	//Beginning of constructors

		//Regular constructor for user save loads

				Entity(
				String name,
				int age,
				String race,
				int level,
				Profession profession,
				
				int strength,
				int dexterity,
				int constitution,
				int intelligence,
				int wisdom,
				int charisma,
				String skillString){

					/*Social Stats*/
					//************************\\
						this.name=name;
						this.age=age;
						this.race=race;
						this.level=level;
						this.profession=profession;
					//\\**********************//

					/*Core Stats*/
					//****************************\\
						this.strength=strength;
						this.dexterity=dexterity;
						this.intelligence=intelligence;
						this.constitution=constitution;
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
						damages=new HashMap<>();

					maxHP=profession.getHD()+((level-1)*(profession.getHD()/2+1))+(level*conMod);
					currentHP=maxHP;
					
					skillList=new ArrayList<>();
					if (!skillString.isEmpty()) {
						String[] skillNumbers = skillString.split("-");
						Skill[] skills = Skill.values();
						
						for (String skillNumber : skillNumbers) {
							int skill = Integer.parseInt(skillNumber);
							skillList.add(skills[skill]);
						}
					}
				}

		//Instant constructor for "Boss" type monster like Tiamat or such

			Entity(
				String name,
				int age,
				String race,
				int level,
				Profession profession,
				
				int strength,
				int dexterity,
				int constitution,
				int intelligence,
				int wisdom,
				int charisma,

				int fireState,
				int coldState,
				int poisonState,
				int acidState,
				int shockState,
				int radiantState,
				int necroticState,
				int forceState,
				int psychicState,
				String skillString){

					/*Social Stats*/
					//************************\\
						this.name=name;
						this.age=age;
						this.race=race;
						this.level=level;
						this.profession=profession;
					//\\**********************//

					/*Core Stats*/
					//****************************\\
						this.strength=strength;
						this.dexterity=dexterity;
						this.intelligence=intelligence;
						this.constitution=constitution;
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
						damages=new HashMap<>();
						State[] states=State.values();
						damages.put(DamageType.FIRE,states[fireState]);
						damages.put(DamageType.COLD,states[coldState]);
						damages.put(DamageType.POISON,states[poisonState]);
						damages.put(DamageType.ACID,states[acidState]);
						damages.put(DamageType.SHOCK,states[shockState]);
						damages.put(DamageType.RADIANT,states[radiantState]);
						damages.put(DamageType.NECROTIC,states[necroticState]);
						damages.put(DamageType.FORCE,states[forceState]);
						damages.put(DamageType.PSYCHIC,states[psychicState]);

						maxHP=profession.getHD()+((level-1)*(profession.getHD()/2+1))+(level*conMod);
						currentHP=maxHP;
						skillList=new ArrayList<Skill>();
						if (!skillString.isEmpty()) {
							String[] skillNumbers = skillString.split("-");
							Skill[] skills = Skill.values();
							
							for (String skillNumber : skillNumbers) {
								int skill = Integer.parseInt(skillNumber);
								skillList.add(skills[skill]);
							}
						}
			}

	//End of constructor

	//Beginning of public methods
		//Start of getters
			//Common stats
				public String getName(){return name;}
				public int getAge(){return age;}
				public String getRace(){return race;}
				public int getLevel(){return level;}
			//Entity Attributes
				public int getSTR(){return strength;}
				public int getDEX(){return dexterity;}
				public int getCON(){return constitution;}
				public int getINT(){return intelligence;}
				public int getWIS(){return wisdom;}
				public int getCHR(){return charisma;}
			//Entity Attribute Modifiers
				public int getStrMod(){return strMod;}
				public int getDexMod(){return dexMod;}
				public int getConMod(){return conMod;}
				public int getIntMod(){return intMod;}
				public int getWisMod(){return wisMod;}
				public int getChrMod(){return chrMod;}
			//Damage States
				public int getFireState(){return damages.get(DamageType.FIRE).ordinal();}
				public int getColdState(){return damages.get(DamageType.COLD).ordinal();}
				public int getPoisonState(){return damages.get(DamageType.POISON).ordinal();}
				public int getAcidState(){return damages.get(DamageType.ACID).ordinal();}
				public int getShockState(){return damages.get(DamageType.SHOCK).ordinal();}
				public int getRadiantState(){return damages.get(DamageType.RADIANT).ordinal();}
				public int getNecroticState(){return damages.get(DamageType.NECROTIC).ordinal();}
				public int getForceState(){return damages.get(DamageType.FORCE).ordinal();}
				public int getPsychicState(){return damages.get(DamageType.PSYCHIC).ordinal();}
			//Damage map
				public HashMap<DamageType,State> getMap(){return damages;}
			//Weapon (Potential inventory location)
				public Weapon getEquippedWeapon(){return equippedWeapon;}
			//HP related methods
				public int getCurrentHP(){return currentHP;}
			//SkillList getter
				public ArrayList<Skill> getSkillList(){return skillList;}
		//End of getters
		
		//Start of setters
			//Common stats
				public void setName(String name){this.name=name;}
				public void setAge(int age){this.age=age;}
				public void setRace(String race){this.race=race;}
				public void setLevel(int level){this.level=level;}
			//Entity Attributes
				public void setSTR(int value){this.strength=value;}
				public void setDEX(int value){this.dexterity=value;}
				public void setCON(int value){this.constitution=value;}
				public void setINT(int value){this.intelligence=value;}
				public void setWIS(int value){this.wisdom=value;}
				public void setCHR(int value){this.charisma=value;}
			//Damage States
				public void setFireState(int value){this.damages.put(DamageType.FIRE,State.values()[value]);}
				public void setColdState(int value){this.damages.put(DamageType.COLD,State.values()[value]);}
				public void setPoisonState(int value){this.damages.put(DamageType.POISON,State.values()[value]);}
				public void setAcidState(int value){this.damages.put(DamageType.ACID,State.values()[value]);}
				public void setShockState(int value){this.damages.put(DamageType.SHOCK,State.values()[value]);}
				public void setRadiantState(int value){this.damages.put(DamageType.RADIANT,State.values()[value]);}
				public void setNecroticState(int value){this.damages.put(DamageType.NECROTIC,State.values()[value]);}
				public void setForceState(int value){this.damages.put(DamageType.FORCE,State.values()[value]);}
				public void setPsychicState(int value){this.damages.put(DamageType.PSYCHIC,State.values()[value]);}
			//Damage map
				public void setMap(HashMap<DamageType,State> map){this.damages=map;}
			//Weapon (Potential inventory location)
				public void setEquippedWeapon(Weapon weapon){this.equippedWeapon=weapon;}
			//HP Related methods
			public void takeDamage(int damage){currentHP-=damage;}
			public void heal(int life){currentHP=Math.min(maxHP,currentHP+life);}
		//End of setters
		
		//Methods to list out Entity values in various ways

			//Creates a formatted list of stats
			public String viewSimpleInfo(){
				String info="Name: "+name+"| Age: "+age+"| Race: "+race+"|\n";
				info+="Level: "+level+"| Profession: "+profession.getName()+"|\n";
				return info;
			}

			//Creates a more large formatted list of stats
			public String viewOfficialInfo(){
				String officialInfo=viewSimpleInfo()+"\n";
				officialInfo+="STR: "+strength+"\n";
				officialInfo+="DEX: "+dexterity+"\n";
				officialInfo+="INT: "+intelligence+"\n";
				officialInfo+="CON: "+constitution+"\n";
				officialInfo+="WIS: "+wisdom+"\n";
				officialInfo+="CHA: "+charisma+"\n";
				return officialInfo;
			}

			//Creates an in-depth formatted list of stats
			public String viewDetailedInfo(){
				String detailedInfo=viewOfficialInfo()+"\n";
				detailedInfo+="STRMod: "+strMod+"\n";
				detailedInfo+="DEXMod: "+dexMod+"\n";
				detailedInfo+="INTMod: "+intMod+"\n";
				detailedInfo+="CONMod: "+conMod+"\n";
				detailedInfo+="WISMod: "+wisMod+"\n";
				detailedInfo+="CHRMod: "+chrMod+"\n\n";

				detailedInfo+="Max Health: "+maxHP;
				detailedInfo+="| Health/level: "+((profession.getHD()/2)+1+conMod)+"\n";
				return detailedInfo;
			}

			public String statChart(){
				String chart="";
				chart+="\t"+name+"'s stat chart\n";
				chart+="STR|"+starString(strength)+"\n";
				chart+="DEX|"+starString(dexterity)+"\n";
				chart+="CON|"+starString(constitution)+"\n";
				chart+="INT|"+starString(intelligence)+"\n";
				chart+="WIS|"+starString(wisdom)+"\n";
				chart+="CHR|"+starString(charisma)+"\n";
				chart+="   0----5----10---15---20---25---30\n";
				return chart;
			}
			private String starString(int value){
				return "*".repeat(Math.max(0, value));
			}

		//End of stat listing methods

		public void save(){
			try{
				Scanner s=new Scanner(System.in);
				System.out.print("Which file do you wish to save in?(1-10)>");
				int saveFile=s.nextInt();
				if(saveFile<0||saveFile>10){
					System.out.println("Illegal file selection.\nTerminating save process...");
					s=new Scanner("");
					s.close();
					return;
				}
				s=new Scanner("");
				s.close();
				File file=new File("UserSaves/player"+saveFile+".dat");
				String data=entityToString();
				file.getParentFile().mkdirs();
				file.createNewFile();
				FileWriter fw=new FileWriter(file);
				fw.write(data);
				fw.close();
			}catch(Exception e){System.out.println(e.getMessage());}
		}

		public static Entity load(){
			try{
				Scanner s=new Scanner(System.in);
				System.out.print("Which file do you wish to load?(1-10)>");
				int loadFile=s.nextInt();
				if(loadFile<0||loadFile>10){
					System.out.println("Illegal file selection.\nTerminating load process...");
					s=new Scanner("");
					s.close();
					return null;
				}
				File file=new File("UserSaves/player"+loadFile+".dat");
				s=new Scanner(file);
				s.useDelimiter(",");

				String name=s.next();
				int age=s.nextInt();
				String race=s.next();
				int level=s.nextInt();
				String prof=s.next();
				Profession profession=null;
				switch(prof){
					case "Barbarian":
					profession=Profession.BARBARIAN;break;
					case "Bard":
					profession=Profession.BARD;break;
					case "Cleric":
					profession=Profession.CLERIC;break;
					case "Druid":
					profession=Profession.DRUID;break;
					case "Fighter":
					profession=Profession.FIGHTER;break;
					case "Monk":
					profession=Profession.MONK;break;
					case "Paladin":
					profession=Profession.PALADIN;break;
					case "Ranger":
					profession=Profession.RANGER;break;
					case "Sorcerer":
					profession=Profession.SORCERER;break;
					case "Warlock":
					profession=Profession.WARLOCK;break;
					case "Wizard":
					profession=Profession.WIZARD;break;
					default:
						System.out.println("There was an error in file formatting to acquire the Profession\nTerminating...");
						s=new Scanner("");
						s.close();
						System.exit(-1);
				}

				int strength=s.nextInt();
				int dexterity=s.nextInt();
				int intelligence=s.nextInt();
				int constitution=s.nextInt();
				int wisdom=s.nextInt();
				int charisma=s.nextInt();
				
				DamageType[] damageTypes=DamageType.values();
				State[] states=State.values();
				int[] types=new int[damageTypes.length];
				for (int i=0;i<types.length;i++) {
					types[i]=s.nextInt();
				}
				String skillString=s.next();
				Entity entity=new Entity(name,age,race,level,profession,
					strength,dexterity,constitution,intelligence,wisdom,charisma,skillString);
				for(int i=0;i<damageTypes.length;i++){
					entity.getMap().put(damageTypes[i],states[types[i]]);
				}
				s=new Scanner("");
				s.close();
				return entity;
			}catch(Exception e){System.out.println(e.getMessage());}
			//If you somehow reach here, it means you've fucked with the file creation.
			//The files should save and load with the only user input being the file#
			return null;
		}
		
		public String entityToString(){
			StringBuilder data= new StringBuilder();
				data.append(name).append(",");
				data.append(age).append(",");
				data.append(race).append(",");
				data.append(level).append(",");
				data.append(profession.getName()).append(",");
				data.append(strength).append(",");
				data.append(dexterity).append(",");
				data.append(constitution).append(",");
				data.append(intelligence).append(",");
				data.append(wisdom).append(",");
				data.append(charisma).append(",");
				DamageType[] damageTypes=DamageType.values();
				for (DamageType damageType : damageTypes) {
					data.append(damages.get(damageType).ordinal()).append(",");
				}
				if(!skillList.isEmpty()){
					for (Skill skill : skillList) {
						data.append(skill.ordinal()).append("-");
					}
				}else{
					data.append("\"\",");
				}
				return data.substring(0,data.length()-1);
		}

		public int attack(){return this.equippedWeapon.attack();}
		
		public boolean isAlive(){return this.currentHP>0;}
	//End of public methods

}