package soldiers;

import java.awt.Color;

public class Barbarian extends Warrior{
	private boolean angry;
	
	//constructor
	public Barbarian(boolean ev){
		super(ev);
		//setting armor to half
		setArmor(getArmor()/2);
		//set block chance to false
		setBlockChance(getBlockChance()/2);
		//a barbarian is born not angry
		angry=false;
	}
	//overriding Warriors move
	public void moveAction(){
		//will move once...
		super.standardMove();
		//If angry will move again
		if(angry)
			super.standardMove();
	}
	//overriding Warriors move
	public void takeDamage(int dmg, Soldier dudeWhoHitMe){
		//when a warrior takes damage that puts him below half health
		if(getHealth()<(getMaxHealth()/2)){
			//he becomes angry
			angry=true;
			//his HealthBar becomes orange
			setHealthBarColor(Color.ORANGE);
			//doubles his AttackPower (MaxAttackPower= AttackPower*2)
			setAttackPower(getMaxAttackPower());
			//incoming damage is reduced by half
			dmg=dmg/2;
		}
		//barbarian takes damage like a warrior
		super.takeDamage(dmg, dudeWhoHitMe);
		//if not dead, he faces the direction of the guy he took damage from
		if(!isDead()){
			setDirection(getBoard().getDirectionFrom(getMySquare(), dudeWhoHitMe.getMySquare()));
		}
		
	}
	//overriding Warriors attack
	public void attackAction(){
		// turn to face square diagonal of him
		turn();
		//attack that square
		super.attackAction();
		//turn to face square in front of him
		turn(-45);
		//attack that square
		super.attackAction();
		//turn to face the other square diagonal of him
		turn(-45);
		//attack that square
		super.attackAction();
		
		//turn to face the starting direction
			turn();
	}
	
}
