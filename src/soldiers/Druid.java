package soldiers;

import board.Square;

public class Druid extends Mage {
	private double healChance = 0.10;
	private double bearChance = 0.20;
	public Druid(boolean ev){
		super(ev);
		setArmor(15);
		setAttackPower(10);
	}
	public void attackAction(){
		heal();
		Square q = getMySquare();
		if(Math.random()<bearChance){
			boolean evil= isEvil();
			getBoard().highlightSquare(getMySquare(), true);
			die();
			q.setSoldier(new Bear(evil,((double)getHealth()/getMaxHealth())));
		}
			
			
		
	}
	public void heal(){
		if(Math.random()<healChance)
			setHealth(getHealth()+30);
	}
}
