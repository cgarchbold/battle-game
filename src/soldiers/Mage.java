package soldiers;

public class Mage extends Soldier {
	
	public Mage(boolean ev){
		super(50,0,15,ev);
	
	}
	
	public void attackAction(){
			Soldier s = getBoard().getClosestSoldierInDir(getMySquare(),getDirection());
			
			if(s!=null)
				if(this.isEnemy(s))
					s.takeDamage(getAttackPower(), this);	
	}
	
	public void moveAction(){
		super.standardMove();
	}
}
