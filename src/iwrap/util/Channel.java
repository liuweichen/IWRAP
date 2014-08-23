package iwrap.util;

public class Channel {
	float length;//The length of the channel航道长度
	float width;//The width of the channel航道宽度
	float distanceObstacle = 0f;//The width or the distance of the obstacle障碍物者前方距离
	float minObstacle = 0f;//The min of the obstacle障碍物横向宽度最小值
	float maxObstacle = 0f;//The max the distance of the obstacle障碍物横向宽度最大值
	
	public Channel(float length, float width, float distanceObstacle,
			float minObstacle, float maxObstacle) {
		super();
		this.length = length;
		this.width = width;
		this.distanceObstacle = distanceObstacle;
		this.minObstacle = minObstacle;
		this.maxObstacle = maxObstacle;
	}
	public Channel(float length, float width) {
		this.length = length;
		this.width = width;
	}
	public Channel() {}
	
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getDistanceObstacle() {
		return distanceObstacle;
	}
	public void setDistanceObstacle(float distanceObstacle) {
		this.distanceObstacle = distanceObstacle;
	}
	public float getMinObstacle() {
		return minObstacle;
	}
	public void setMinObstacle(float minObstacle) {
		this.minObstacle = minObstacle;
	}
	public float getMaxObstacle() {
		return maxObstacle;
	}
	public void setMaxObstacle(float maxObstacle) {
		this.maxObstacle = maxObstacle;
	}
	
	@Override
	public String toString() {
		return "Channel [length=" + length + ", width=" + width
				+ ", distanceObstacle=" + distanceObstacle + ", minObstacle="
				+ minObstacle + ", maxObstacle=" + maxObstacle + "]";
	}
	
}
