package soldiers;

import java.util.ArrayList;

import board.GameBoard;
import board.Square;

public class Monk extends Soldier{
	//chacne to dodge(25%)
	private double dodgeChance = 0.25;
	
	//constructor
	public Monk(boolean ev){
		//100 health, 5 armor, 4 attack
		super(100,5,4,ev);
	}
	
	//override Soldier moveAction
	public void moveAction(){
		//move like normal, but twice
		super.standardMove();
		super.standardMove();
	}
	
	//override Soldier attackAction
	public void attackAction(){
		//get all enemies around my square in a 1 square radius
		ArrayList<Soldier> neighbors = getBoard().getSoldiersAround( getMySquare(), 1 );
		
		//for each enemy, have them take damage, if they are not dead already
		for( Soldier otherDude : neighbors )
			if( this.isEnemy(otherDude) ){
				if(!otherDude.isDead()){
					//highlight their square as they take damage
					otherDude.getMySquare().highlight(false);
					//enemy take damage = to my attack power
					otherDude.takeDamage( this.getAttackPower(), this );
				}
			}
		}
	
	//override Soldier takeDamage
	public void takeDamage( int dmg, Soldier dudeWhoHitMe ){
		//chance to take damage like normal 75%
		if(Math.random()>=dodgeChance)
			super.takeDamage(dmg,dudeWhoHitMe);
		//chance to dodge attack 25%
		else{
			//get all the empty squares around my square in a 1 square radius and store in list
			ArrayList<Square> list= getBoard().getEmptySquaresAround(getMySquare(),1);
			//if list is empty take damage like normal and leave function
			if(list.isEmpty()){
				super.takeDamage(dmg,dudeWhoHitMe);
				return;
			}
			//step into the first window of list
			stepInto(list.get(1));
			//get direction to enemy
			int dir = getBoard().getDirectionFrom(getMySquare(),dudeWhoHitMe.getMySquare());
			//set my direction to face enemy
			setDirection(dir);
			}
		}
				
	}

