package iwrap.collision;
import iwrap.util.*;

public class Na {
	Ship shipOne, shipTwo;
	EncounterSituation es;
	Channel channel;
	PG pg;
	public Na(Ship shipOne, Ship shipTwo, Channel channel) {		
		this.shipOne = shipOne;
		this.shipTwo = shipTwo;
		this.channel = channel;
	}
	
	public float getNa() {
		es = Ship.getCounterSituation(shipOne, shipTwo);
		switch(es) {
		case headOn: return this.getNaofHeadOnorTakeOver();
		case takeOver: return this.getNaofHeadOnorTakeOver();
		case cross: return this.getNaofCross();
		default: return 0;
		}
	}
	
	public float getNaofHeadOnorTakeOver() {
		es = Ship.getCounterSituation(shipOne, shipTwo);
		pg = new PG(shipOne,shipTwo);
		return channel.getLength() * pg.getPG() * Ship.getRelativeSpeed(shipOne, shipTwo, es)				
				* (shipOne.getQuantityofShip() * shipTwo.getQuantityofShip())
				/(shipOne.getSpeed() * shipTwo.getSpeed());
	}
	
	public float getNaofCross() {
		es = Ship.getCounterSituation(shipOne, shipTwo);
		float angleDifference = Ship.getAngleDifference(shipOne, shipTwo);
		float relativeSpeed = Ship.getRelativeSpeed(shipOne, shipTwo, es);
		double d1 =(shipOne.getLength() * shipTwo.getSpeed() + shipTwo.getLength() * shipOne.getSpeed())
				* Math.sin(angleDifference*2*Math.PI/360)/relativeSpeed;
		double d2 = shipTwo.getWidth() * 
				Math.sqrt(1-Math.pow(Math.sin(angleDifference*2*Math.PI/360)*shipOne.getSpeed()/relativeSpeed, 2));
		double d3 = shipOne.getWidth() * 
				Math.sqrt(1-Math.pow(Math.sin(angleDifference*2*Math.PI/360)*shipTwo.getSpeed()/relativeSpeed, 2));
		float d = (float)(d1 + d2 + d3);
		//System.out.println("D="+relativeSpeed);
		// = 332.5f;
		float temp = (float)(shipOne.getQuantityofShip() * shipTwo.getQuantityofShip()
				/ (shipOne.getSpeed() * shipTwo.getSpeed()) * d * relativeSpeed 
				/Math.sin(angleDifference*2*Math.PI/360));//2*Math.PI/360Ò×´íµã
		return temp;
	}

}
