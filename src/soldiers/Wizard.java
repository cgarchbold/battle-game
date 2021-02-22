package soldiers;
import java.util.ArrayList;

import board.Square;

public class Wizard extends Mage {
	//chance used for changing to toads, and teleportation
	private double chance = 0.75;
	
	//constructor
	public Wizard(boolean ev){
		super(ev);
	}
	
	//overriding Mage moveAction
	public void moveAction(){
		//75% chance to move like a Soldier
		if(Math.random()<=chance){
			super.standardMove();
		}
		//25% chance to 
		else{
			//get all empty squares on the board
			ArrayList<Square> list=getBoard().getAllEmptySquares(); 
			//step into a random number from that list
			super.stepInto(list.get((int)(Math.random()*list.size())));
		}
	}
	
	//overriding Mage attackAction
	public void attackAction(){
		//75% chance to attack like a Mage
		if(Math.random()<=chance){
			super.attackAction();
		}
		//25% chance to...
		else{
			//get all enemies on the board and store them in list
			ArrayList<Soldier> list=getBoard().getAllEnemies(this);
			//get a random integer, between 0, and the list.size() store it in temp
			int temp=(int)(Math.random()*list.size());
			// get the square of the temp window enemy in the list
			Square where = list.get(temp).getMySquare();
			//kill the randomly selected enemy
			list.get(temp).die();
			//put a toad, opposite of your(good/evil), in the previosly selected enemy's square
			getBoard().addSoldierToGame(new Toad(!isEvil()), where);
		}
		
	}
}
