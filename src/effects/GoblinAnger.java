package effects;

import java.awt.Color;

import soldiers.Goblin;

public class GoblinAnger extends Effect{
		public static final Color h = new Color(255,50,50);
		private int attackPowerGained;
	
		
		public GoblinAnger(double str, int lng){
			super(str,lng,h, true);//can stack
		}

		@Override
		public void startEffect() {
			if(getTarget()==null || getTarget().isDead())
				return;
			//nothing to do here
			getTarget().getBoard().printToDisplay(getTarget()+" is ANGERED!");
			attackPowerGained = (int)Math.round(getTarget().getAttackPower()*getStrength()*Goblin.getDeadGoblins());
			getTarget().setAttackPower( getTarget().getAttackPower()+attackPowerGained);
		}

		
		@Override
		public void endEffect() {
			getTarget().setAttackPower( getTarget().getAttackPower()-attackPowerGained);		
		}
}

