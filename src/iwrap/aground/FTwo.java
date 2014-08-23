package iwrap.aground;

import iwrap.util.*;

public class FTwo {
	Ship shipOne;//The kind of the ship船舶种类
	Channel channel;//The bending channel of the case 航道信息
	
	public FTwo(Ship shipOne, Channel channel) {
		this.shipOne = shipOne;
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "FTwo [shipOne=" + shipOne + ", channel=" + channel + "]";
	}
	
	public float getFTwoValue() {
		shipOne.getDist().setMinIntegration(channel.getMinObstacle());
		shipOne.getDist().setMaxIntegration(channel.getMaxObstacle());
		float tempIntegration = shipOne.getDist().getIntegrationbyRectangle();
		double tempForgetCheckPosition = 
			Sailor.getProbabilityForgetCheckPosition(channel.getDistanceObstacle());
		return (float)(CausasionFactor.agroundFactor*shipOne.getQuantityofShip()*tempIntegration*tempForgetCheckPosition);
	}
}
