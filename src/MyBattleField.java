import java.awt.event.WindowEvent;

import board.GameBoard;
import utilities.*;
import soldiers.*;

/**Disclaimer:  All Images are copyright "BattleHeart" for iPhone.**/
public class MyBattleField extends GameBoard{
	private String[] types = { "Warrior","Archer","Cleric","Mage","Barbarian","Wizard","Pirate","Monk","Witch","Rogue",
			"Goblin","Engineer","Turret","OrcCommander","Orc","Dragon","Druid","Gunslinger"};
	
	public MyBattleField(){
		super(20,20);
		super.setSoldierTypes( types );
	}
	
	
	public void setUpSoldiers(){
		//ROWS = the number of rows
		//COLS = the number of columns
		
		
	}
	
	//@Override
	public void eachFrame() {
		//don't loop through the grid!!  If so, the person will be able to move several times per frame
		//INSTEAD loop through the occupants
		for(Soldier p : this.getAllSoldiers()){
			/**~~~~~~~~~~~~~ WRITE CODE HERE ~~~~~~~~~~~~~~~~**/
			if(!p.isDead()){
				p.setSelected(true);
				p.moveAction();
				p.attackAction();
				p.setSelected(false);
			}
			
			/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**/
		}
	}
	
	public static void main(String[] args) { 
		MyBattleField bf = new MyBattleField();
		new ArmySelectionGUI(bf);
	}


}
