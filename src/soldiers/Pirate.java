package soldiers;

import board.Square;

public class Pirate extends Soldier {
	//chance to take scurvy damage
	private double scurvyChance = 0.8;
	//chance to attack twice
	private double doubleATTKChance = 0.75;
	
	//constructor
	public Pirate(boolean ev){
		//health 115, armor 20, attack 30
		super(115,20,30,ev);
	}
	
	//override Soldier moveAction
	public void moveAction(){
		//move like regular
		super.standardMove();
		//80% chance to get hurt from scurvy
		if(Math.random()<=scurvyChance){
			//highlight square when taking damage
			getBoard().highlightSquare(getMySquare(), false);
			//take damage if you have enough health
			if(getHealth()>=11)
				setHealth(getHealth()-10);
			//if you don't have enough health
			else{
				//store my square
				Square mysquare = getMySquare();
				//die from scurvy
				this.die();
				//put a new skeleton on my side when i die
				mysquare.setSoldier(new Skeleton(isEvil()));
				
			}
			
				
		}
	}
	public void attackAction(){
		if(isDead())
			return;
		//find the square in front of me
				Square front = getBoard().getSquareInDirection( getMySquare() , getDirection() );
				//there's no square in front of me (i'm at the edge of board)
				if(front==null)
					return;

				front.highlight(false);//make it red, when i take damage
				
				Soldier target = front.getSoldier();
				//if there is someone there AND he's on the other team
				if( target!=null && this.isEnemy(target) ){
					//have enemy take damage
					target.takeDamage( this.getAttackPower(), this );
					//75% have enemy take damage twice
					if(Math.random()<=doubleATTKChance && !target.isDead())
						target.takeDamage( this.getAttackPower(), this );
					//if tyhe target dies steal his life
					if(target.isDead()){
						//get 20 plus health
						setHealth(getHealth()+20);
						//put a skeleton in his spot on my team
						front.setSoldier(new Skeleton(isEvil()));
					}
						
				}
	}
}
