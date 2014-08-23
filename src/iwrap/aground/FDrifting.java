package iwrap.aground;

import iwrap.util.CausasionFactor;
import iwrap.util.Channel;
import iwrap.util.Ship;
import iwrap.util.ShipKind;

public class FDrifting {
	Ship shipOne;//The kind of the ship船舶种类
	Channel channel;//The channel of the case航道信息
	
	public FDrifting(Ship shipOne, Channel channel) {
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
		return "FDrifting [shipOne=" + shipOne + ", channel=" + channel + "]";
	}
	//Get the probability of the ship in the danger 得到船舶在危险搁浅区域的概率
	public double getIntegrationValue() {
		shipOne.getDist().setMinIntegration(channel.getMinObstacle());
		shipOne.getDist().setMaxIntegration(channel.getMaxObstacle());
		double tempIntegration = shipOne.getDist().getIntegrationbyRectangle();
		return tempIntegration;
	}
	//Get the probability of ship out of control 船舶失控的概率
	public float getProbabilityOutofControl() {
		float tempIndex;
		if(shipOne.getType()==ShipKind.passengerShip) {
			tempIndex = -(float)CausasionFactor.frequencyOutofControlDefault
					*channel.getLength()/shipOne.getSpeed();
		} else {
			tempIndex = -(float)CausasionFactor.frequencyOutofControl
					*channel.getLength()/shipOne.getSpeed();
		}
		return (float)(1.0f - Math.exp(tempIndex));
	}
	//Get the integral of ship in no repair by speed drift from 1 to 3 求漂移速度的积分
	public float getProbabilityNoRepair() {
		float speedDriftMin = 1.0f;
		float speedDriftMax = 3.0f;
		int n = 1000;
		float step = (speedDriftMax - speedDriftMin)/n;
		//System.out.println(step+"step");
		float tempIntegral = 0.0f;
		for(int i = 0; i < n; i++) {
			tempIntegral += this.functionInNoRepair(speedDriftMin+i*step);
		}
		return tempIntegral*step;
	}
	//The function of ship in no repair 船舶没有修好的概率函数
	public double functionInNoRepair(double speedDrift) {
		double timeGround = channel.getDistanceObstacle()/speedDrift;
		return Math.exp(-1.05*Math.pow(timeGround, 0.9)) * 0.5;
	}
	//Get the integration by rectangle method, drift speed from 1 to 3
	public double getDriftSpeedIntegration() {
		int n = 10000;
		double tempIntegration = 0d;
		double step = 2.0d/n;
		for(int i = 0;i < n; i++) {
			tempIntegration =+this.functionInNoRepair(1.0f + step * i);
		}
		//System.out.println(tempIntegration+"tempIntegration");
		return tempIntegration * step;
	}
	//Return the Frequency of Drifting result
	public float getFDriftingValue() {
		double tempShipInDanger = this.getIntegrationValue();
		double tempOutOfControl = this.getProbabilityOutofControl();
		double tempDriftSpeedIntegration = this.getDriftSpeedIntegration();
		//System.out.println(tempDriftSpeedIntegration+"*tempDriftSpeedIntegration**");
		return (float)(tempShipInDanger*tempOutOfControl*channel.getLength()*tempDriftSpeedIntegration);
	}
	
}
