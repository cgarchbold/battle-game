package soldiers;

public class Turret extends Mage {
	public Turret(boolean ev){
		super(ev);
		setHealth(75);
		setAttackPower(20);
	}
	public void moveAction(){
		return;
	}
	
	public void attackAction(){
		Soldier enemy= getEnemy();
		if(enemy!=null)
			if(this.isEnemy(enemy))
				enemy.takeDamage(getAttackPower(), this);	
	}
	public Soldier getEnemy(){
		Soldier s = getBoard().getClosestSoldierInDir(getMySquare(),getDirection());
		while(s==null){
			turn();
			s = getBoard().getClosestSoldierInDir(getMySquare(),getDirection());
		}
		return s;
	}
}
