package iwrap.aground;

import iwrap.util.*;

public class FOne {
	Ship shipOne;//The kind of the ship船舶种类
	Channel channel;//The channel of the case航道信息
	
	public FOne( Ship shipOne, Channel channel) {
		this.shipOne = shipOne;
		this.channel = channel;
	}
	
	
	public Ship getShipOne() {
		return shipOne;
	}
	public void setShipOne(Ship shipOne) {
		this.shipOne = shipOne;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	@Override
	public String toString() {
		return "FOne [shipOne=" + shipOne + ", channel=" + channel + "]";
	}
	
	public float getFOneValue() {
		shipOne.getDist().setMinIntegration(channel.getMinObstacle());
		shipOne.getDist().setMaxIntegration(channel.getMaxObstacle());
		float tempIntegration = shipOne.getDist().getIntegrationbyRectangle();
		//System.out.println(tempIntegration+"****");
		return (float)(CausasionFactor.agroundFactor*shipOne.getQuantityofShip()*tempIntegration);
	}
}
