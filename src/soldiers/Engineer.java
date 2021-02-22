package soldiers;

import board.Square;

public class Engineer extends Warrior{
	private double turretchance = 0.15;
	public Engineer(boolean ev){
		super(ev);
		setAttackPower(getAttackPower()/2);
		setHealth(40);
		setMaxHealth(40);
	}
	public void attackAction(){
		if(isDead())
			return;
		super.standardMove();
		if(Math.random()<turretchance){
			Square front = getBoard().getSquareInDirection( getMySquare() , getDirection() );
		
			if(front==null)
				return;
			
			if(front.isEmpty())
				getBoard().addSoldierToGame(new Turret(isEvil()), front);
		}
	}
	public void moveAction(){
		super.standardMove();
	}
}
