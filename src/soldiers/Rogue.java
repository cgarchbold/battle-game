package soldiers;

import java.util.*;

import board.Square;

public class Rogue extends Warrior{
	//chance to teleport behind someone & chance to counter attack
	private double Chance= 0.5;
	
	//constructor
	public Rogue(boolean ev){
		super(ev);
		//health the max health of a warrior
		setMaxHealth(getMaxHealth()/2);
		//half the starting health of a warrior
		setHealth(getHealth()/2);
		//half the armor of a warrior
		setArmor(getArmor()/2);
		//half the block chance of a warrior
		setBlockChance(getBlockChance()/2);
	}
	
	
	//overriding warriors moveAction
	public void moveAction(){
		//50% chance to move like a soldier
		if(Math.random()<=Chance)
			super.standardMove();
		//50% chance to teleport
		else{
			//get all enemies on the board & store them in an ArrayList called targets
			ArrayList<Soldier> targets= getBoard().getAllEnemies(this);
			//pick a random enemy from targets
			Soldier target= targets.get((int)(Math.random()*(targets.size())));
			//find the square of the enemy selected from targets directly behind the direction the enemy is facing
			Square behind =getBoard().getSquareInDirection( target.getMySquare() , target.getDirection()+180);
			//if that square is not null, and is empty
			if(behind!=null&&behind.isEmpty()){
				//step into the square
				this.stepInto(behind);
				//find the required direction to face target
				int dir = getBoard().getDirectionFrom(getMySquare(),target.getMySquare());
				//set direction to face target
				this.setDirection(dir);
				}
			}
		}
	
	//override Warriors takeDamage
	public void takeDamage( int dmg, Soldier dudeWhoHitMe ){
		//take damage like normal
		super.takeDamage(dmg, dudeWhoHitMe);
		// if by this point the rogue is not dead
		if(!isDead())
			//50% chance to counter attack
			if(Math.random()<=Chance){
				//set attack to half
				setAttackPower(getAttackPower()/2);
				//counter attack
				super.attackAction();
				//return attack power stat to normal
				setAttackPower(getAttackPower()*2);
		}
	}
}
