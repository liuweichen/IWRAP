package iwrap.collision;
import iwrap.util.*;
public class PG {
	Ship shipOne, shipTwo;
	public PG(Ship shipOne, Ship shipTwo) {
		this.shipOne = shipOne;
		this.shipTwo = shipTwo;
	}
	
	public Ship getShipOne() {
		return shipOne;
	}
	public void setShipOne(Ship shipOne) {
		this.shipOne = shipOne;
	}
	public Ship getShipTwo() {
		return shipTwo;
	}
	public void setShipTwo(Ship shipTwo) {
		this.shipTwo = shipTwo;
	}

	public  float getPG() {		
		int dangerCount = 0;
		EncounterSituation es;
		
		es = Ship.getCounterSituation(shipOne, shipTwo);
		if(es == EncounterSituation.cross) return 0;
		//get PG value得到PG值,i最小为100000不然不稳定
		int times = 10000;
		for(int i = 0; i < times; i++) {
			if(Math.abs(shipOne.getDist().getDistribution() - shipTwo.getDist().getDistribution())
					<= (shipOne.getWidth()+shipTwo.getWidth())/2)
			dangerCount++;
		}
		//System.out.println(dangerCount);
		return dangerCount*1.0f/(times*1.0f);
	//	return 0.00237f;
	}
}
