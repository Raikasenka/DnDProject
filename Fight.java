import java.util.Scanner;
import java.util.InputMismatchException;
public class Fight{
	public Fight(Entity player, Monster enemy){
		System.out.println("\n"+player.getName()+" has encountered: "+enemy.getType()+"\n");
		int roundCount=1;
		while(player.isAlive()&&enemy.isAlive()){
			printRound(roundCount++);
			displayHP(player, enemy);
			Static.newLine();
			
			printOptionScreen();
			switch(optionChoice()){
				case 1: playerAttack(player, enemy);break;
				case 2: notAvailableMessage();break;
				case 3: notAvailableMessage();break;
				case 4: notAvailableMessage();break;
				default:
			}
			if(!enemy.isAlive()){break;}
			//Need to add a way for enemies to attack, probably the addition of skills
			Skill enemyAttack=enemy.getSkillList().get(Static.rollAny(enemy.getSkillList().size())-1);
			int damage=enemyAttack.attack();
			enemyAttackMessage(enemy,damage);
			player.takeDamage(damage);
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
		System.out.print("What do you wish to do?\n1. "+Constants.ATTACK+"\n2. "+Constants.SKILL+"\n3. "+Constants.ITEM+"\n4. "+Constants.FLEE+"\nSelection>");
	}
	public void notAvailableMessage(){
		System.out.println(Constants.NOTAVAILABLE);
	}
	public int optionChoice(){
		Scanner s=new Scanner(System.in);
		while(true){
			String moveChoice=s.next();
			try{
				int x=Integer.parseInt(moveChoice);
				/* FIXME: 2020-06-06 Options only include attacking for the moment thus x should be allowed to be any number in the list later on
					The resulting if will be of the format: if(x<1||x>n) where n is the size of the list. Throw exception
				 */
				if(!(x==1)){
					throw new IndexOutOfBoundsException();
				}
				return x;
			}catch(NumberFormatException e){
				System.out.println("Please input a number");
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
}