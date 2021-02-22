package soldiers;

import java.util.ArrayList;

import board.Square;
import effects.GoblinAnger;

public class Goblin extends Warrior {
	public static int deadgoblins=0;
	private static Soldier enemy;
	public Goblin(boolean ev){
		super(ev);
		setHealth(50);
		setMaxHealth(50);
		setArmor(getArmor()/3);
	}
	
	public void moveAction(){
		if(this.isDead())
			return;
		ArrayList<Soldier> targets= getBoard().getAllEnemies(this);
			if(enemy==null){
				enemy= targets.get((int)(Math.random()*(targets.size())));				
			}
			if(enemy.isDead()){
				enemy=null;
				enemy= targets.get((int)(Math.random()*(targets.size())));
				}
			
			getBoard().printToDisplay(this+" is targeting "+enemy);
			int dir = getBoard().getDirectionFrom(getMySquare(),enemy.getMySquare());
			
			this.setDirection(dir);
			
			super.standardMove();
			Square front = getBoard().getSquareInDirection( getMySquare() , getDirection() );
			
			
			if(!(front==null))
				if(!front.isEmpty()&&!(front.getSoldier()==null)){
					if(!front.getSoldier().isEnemy(this)){
						turn();
						super.standardMove();
					}
			}
		}
	
	public static int getDeadGoblins(){return deadgoblins;}
	
	public void die(){
		ArrayList<Soldier> neighbors = getBoard().getSoldiersAround( getMySquare(), 1 );
		
		for( Soldier otherDude : neighbors )
			if( this.isFriend(otherDude) ){ //don't heal enemies!
				otherDude.addEffect( new GoblinAnger(1,5));
			}
		deadgoblins+=1;
		super.die();
	}
	
	}
