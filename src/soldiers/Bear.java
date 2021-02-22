package soldiers;

import board.Square;


public class Bear extends Warrior{
	private boolean low;
 public Bear(boolean ev, double hppercent){
	 super(ev);
	 setArmor(0);
	 setMaxHealth(200);
	 setHealth((int)(200*hppercent));
	 setAttackPower(getAttackPower()+10);
	 setBlockChance(0);
	 low=false;
	 
 }
 
 public void moveAction(){
	 if(isDead())
		 return;
	 if(getHealth()<=100)
		 low=true;
	 super.standardMove();
	 if(low){
		Square q = getMySquare();
		boolean evil= isEvil();
		getBoard().highlightSquare(getMySquare(), true);
		die();
		q.setSoldier(new Druid(evil));
	 }
		 
 }
 
}
