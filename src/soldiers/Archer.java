package soldiers;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import board.Square;


public class Archer extends Soldier{
	//boolean that dictates the way archer moves
	private boolean wantToMove=false;
		
	public Archer( boolean ev ){
		super(75, 5, 10, ev);
		wantToMove=true;
	}

		
	@Override
	public void attackAction() {
		//if dead, dont move
		if(isDead())
			return;
		//if the archer currently wants to move, then use supers move twice
		if(wantToMove==true){
			super.standardMove();
			super.standardMove();
		}
		Soldier target = getBoard().getClosestSoldierInDir(this.getMySquare(), this.getDirection());//findClosestTarget( getDirection() );
		if(target==null)//no one there
			wantToMove=true;// then he wants to move
		else if( this.isFriend( target ) ) //friendly target
			wantToMove = true;//then he wants to move
		else if( getMySquare().distance( target.getMySquare()) < 5 ){ //must be an enemy...make sure he is in range
			target.takeDamage( getAttackPower(), this );//attack him
			wantToMove=false;//I want to stand here and shoot forever			
		}
		else //target was too far away
			wantToMove = true;//then he wants to move
				
	}
	//override soldiers takeDamage
	public void takeDamage( int dmg, Soldier dudeWhoHitMe ){
		super.takeDamage(dmg, dudeWhoHitMe);//take damage like usual
		this.turn();//turn away from the enemy, Calling MY run function, not soldiers
		wantToMove = true;//then he wants to move
	}
	//override soldiers turn
	public void turn(){
		//do soldiers turn twice
		super.turn();
		super.turn();
	}
	@Override
	public void moveAction() {
		if(isDead())
			return;
		
		
				
	}
	
	
	
	
}
