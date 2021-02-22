package soldiers;

import java.util.ArrayList;

import effects.Protection;

public class OrcCommander extends Orc{
	private double rallychance= 0.25;
	
	public OrcCommander(boolean ev){
		super(ev);
		setArmor(20);
	}
	
	public void moveAction(){
		if(Math.random()<0.50)
			super.standardMove();
	}
	public void attackAction(){
		super.attackAction();
		if(Math.random()<=rallychance){

			ArrayList<Soldier> neighbors = getBoard().getSoldiersAround( getMySquare(), 1 );
			
			for( Soldier otherDude : neighbors ){
				if( this.isFriend(otherDude) ) //don't heal enemies!
					otherDude.addEffect( new Protection(1.00,5));
			}
		}
	}
	public boolean isOrcCommander(){
		return true;
	}
}
