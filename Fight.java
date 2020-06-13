import java.util.ArrayList;
import java.util.Scanner;
public class Fight{
	public Fight(Entity player, Monster enemy){
		System.out.println("\n"+player.getName()+" has encountered: "+enemy.getType()+"\n");
		int roundCount=1;
		while(player.isAlive()&&enemy.isAlive()){
			printRound(roundCount++);
			playerTurn(player,enemy);
			if(!enemy.isAlive()){break;}
			enemyAttack(player, enemy);
			Static.newLine();
		}
		if(player.isAlive()){
			victoryMessage(player, enemy);
		}else{
			defeatMessage(player, enemy);
		}
	}
	public void printRound(int round){
		System.out.println("Round "+round);
	}
	public void displayHP(Entity player, Monster enemy){
		System.out.println(player.getName()+" has "+player.getCurrentHP()+"HP\t"+enemy.getType()+" has "+enemy.getCurrentHP()+"HP");
	}
	
	public void playerAttackMessage(Entity player,int damage){
		System.out.println(player.getName()+"'s attack has dealt "+damage+" damage!");
	}
	public void enemyAttackMessage(Monster enemy,int damage){
		System.out.println(enemy.getType()+"'s attack has dealt "+damage+" damage!");
	}
	
	public void victoryMessage(Entity player, Monster enemy){
		System.out.println(player.getName()+" has defeated the "+enemy.getType());
	}
	public void defeatMessage(Entity player, Monster enemy){
		System.out.println(player.getName()+" has been defeated by the "+enemy.getType());
	}
	
	public void printOptionScreen(){
		System.out.print(Constants.OPTIONQUERY
			+"\n1. "+Constants.ATTACKPROMPT
			+"\n2. "+Constants.SKILLPROMPT
			+"\n3. "+Constants.ITEMPROMPT
			+"\n4. "+Constants.FLEEPROMPT
			+"\n"+Constants.SELECTIONPROMPT);
	}
	public void notAvailableMessage(){System.out.println(Constants.NOTAVAILABLE);}
	
	public int optionChoice(){
		Scanner s=new Scanner(System.in);
		while(true){
			try{
				int x=Integer.parseInt(s.next());
				/* FIXME: 2020-06-06 Options only include attacking for the moment thus x should be allowed to be any number in the list later on
				    The resulting if will be of the format: if(x<1||x>n) where n is the size of the list. Throw exception*/
				if(!(x==1||x==2)){
					throw new IndexOutOfBoundsException();
				}
				return x;
			}catch(NumberFormatException e){
				System.out.print(Constants.NOTANUMBERMSG);
			}catch(IndexOutOfBoundsException e){
				System.out.println("Please input a valid number (1-4)");
			}
		}
	}
	public void playerAttack(Entity player,Monster enemy){
		int damage=player.attack();
		playerAttackMessage(player,damage);
		enemy.takeDamage(damage);
	}
	public void enemyAttack(Entity player,Monster enemy){
		Skill enemyAttack=enemy.getSkillList().get(Static.rollAny(enemy.getSkillList().size())-1);
		int damage=enemyAttack.attack();
		enemyAttackMessage(enemy,damage);
		player.takeDamage(damage);
	}
	
	public void playerSkill(Entity player,Monster enemy) throws SkillExitException{
		ArrayList<Skill> skills=player.getSkillList();
		System.out.println(Constants.LEAVESKILLPROMPT);
		for(int i=0;i<skills.size();i++){
			System.out.println((i+1)+". "+skills.get(i).getName());
		}
		System.out.print(Constants.SELECTIONPROMPT);
		Scanner s=new Scanner(System.in);
		int choice;
		while(true){
			try{
				String str=s.next();
				if(str.trim().equalsIgnoreCase("Leave")){throw new SkillExitException();}
				int x=Integer.parseInt(str);
				if(x<1||x>skills.size()){
					throw new IndexOutOfBoundsException();
				}
				choice=--x;break;
			}catch(NumberFormatException e){
				System.out.print(Constants.NOTANUMBERMSG);
			}catch(IndexOutOfBoundsException e){
				System.out.print("Please input a valid number (1-"+skills.size()+")>");
			}
		}
		int damage=skills.get(choice).attack();
		playerAttackMessage(player,damage);
		enemy.takeDamage(damage);
	}
	public void playerTurn(Entity player,Monster enemy){
		while(true){
			displayHP(player, enemy);
			printOptionScreen();
			try{
				switch(optionChoice()){
					case 1:playerAttack(player, enemy);return;
					case 2:playerSkill(player, enemy);return;
					case 3:
					case 4:
						notAvailableMessage();return;
				}
			}catch(SkillExitException e){
				System.out.println("Left skill selection\n");
			}
		}
	}
}