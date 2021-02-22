package soldiers;

import java.util.ArrayList;

public class Orc extends Warrior {
	public static Soldier commander;
	public Orc(boolean ev){
		super(ev);
		setHealth(100);
		setArmor(0);
		setAttackPower(getAttackPower()*2);
	}
public void moveAction(){
		boolean nearcommand= false;
		ArrayList<Soldier> neighbors = getBoard().getSoldiersAround( getMySquare(), 2);
		findCommander(neighbors);
		if(commander==null)
			super.standardMove();
		else{
			int dir = getBoard().getDirectionFrom(getMySquare(),commander.getMySquare());
			this.setDirection(dir);	
			super.standardMove();
			}
}
public void findCommander(ArrayList<Soldier> allies){
	for(Soldier s:allies)
		if(s.isOrcCommander())
			commander=s;
		
}

}
