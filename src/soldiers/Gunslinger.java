package soldiers;

public class Gunslinger extends Mage{
	private double unloadChance= 0.10;
	private int clipAmount= 12;
	private int maxClip=12;
	public Gunslinger(boolean ev){
		super(ev);
		setAttackPower(10);
		setMaxHealth(80);
		setHealth(125);
		setArmor(0);
	}
	public void attackAction(){
		if(clipAmount==0){
			clipAmount=maxClip;
			getBoard().printToDisplay(this+"is reloading! ");
			return;
		}
		else if(clipAmount==1){
			clipAmount--;
			super.attackAction();
			return;
		}
		else if(Math.random()<=unloadChance){
			clipAmount=0;
			getBoard().printToDisplay(this+"is unloading!");
			for(int i=0; i<clipAmount;i++)
				super.attackAction();
		}
		else{
			clipAmount-=2;
			super.attackAction();
			super.attackAction();
		}
	}

}
