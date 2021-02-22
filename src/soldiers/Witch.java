package soldiers;

import java.util.ArrayList;

import board.Square;

public class Witch extends Mage{
	//chance to move
	private double moveChance = 0.25;
	//array list to hold the skeletons a witch creates
	ArrayList<Soldier> Skellys;
	
	//constructor
	public Witch(boolean ev){
		super(ev);
		//creating arraylist for skeletons
		Skellys = new ArrayList();
	}
	
	//overriding Mage moveAction
	public void moveAction(){
		//movement based off of chance
		//25% chance to move
		if(Math.random()<=moveChance)
			super.standardMove();
		//75% chance to not move
		else
			return;
	}
	//overriding Mage attackAction
	public void attackAction(){
		//getting the square infront
		Square inFront = getBoard().getSquareInDirection(getMySquare(),getDirection());
		//if there is no square(at a wall), leave the function
		if(inFront==null)
			return;
		//if it has no soldier,
		if(inFront.getSoldier()==null){
			//create a new skeleton named fred
			Skeleton fred = new Skeleton(isEvil());
			//add him to the previosly selected square
			getBoard().addSoldierToGame(fred, inFront);
			//add him to the arraylist Skellys
			Skellys.add(fred);
			//-1 the witch's health
			setHealth(getHealth()-1);
		}
		//if there is a soldier do attack action
		else
			super.attackAction();
	}	
	//overriding Soldier die
	public void die(){
		//die like a soldier
		super.die();
		//if maade skeletons, .die() each of them
		if(!Skellys.isEmpty())
			for(Soldier guy:Skellys)
				guy.die();
	}
}
