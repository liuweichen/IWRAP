package iwrap.util;

public class Distribution {
	DistributionKind dk;//The kind of the distribution分布函数种类
	float meanValue;//The average of the values平均值
	float variance;//The variance of the values方差
	float min;//The minimum of the values最小值
	float max;//The maximum of the values最大值
	float minIntegration;//The min of the integration积分起点
	float maxIntegration;//The max of the integration积分终点
	
	public Distribution(DistributionKind dk,float meanValue, float variance, float min, float max,
			float minIntegration, float maxIntegration ) {
		this.meanValue = meanValue;
		this.variance = variance;
		this.min = min;
		this.max = max;
		this.minIntegration = minIntegration;
		this.maxIntegration = maxIntegration;
		this.dk = dk;
	}

	public Distribution(float meanValue, float variance, float min, float max,
			 DistributionKind dk) {
		this.meanValue = meanValue;
		this.variance = variance;
		this.min = min;
		this.max = max;
		this.dk = dk;
	}
	
	public Distribution() {}
	
	public float getMeanValue() {
		return meanValue;
	}
	public void setMeanValue(float meanValue) {
		this.meanValue = meanValue;
	}
	public float getVariance() {
		return variance;
	}
	public void setVariance(float variance) {
		this.variance = variance;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}	
	public DistributionKind getDk() {
		return dk;
	}
	public void setDk(DistributionKind dk) {
		this.dk = dk;
	}	
	public float getMinIntegration() {
		return minIntegration;
	}

	public void setMinIntegration(float minIntegration) {
		this.minIntegration = minIntegration;
	}

	public float getMaxIntegration() {
		return maxIntegration;
	}

	public void setMaxIntegration(float maxIntegration) {
		this.maxIntegration = maxIntegration;
	}

	@Override
	public String toString() {
		return "Distribution [dk=" + dk + ", meanValue=" + meanValue
				+ ", variance=" + variance + ", min=" + min + ", max=" + max
				+ ", minIntegration=" + minIntegration + ", maxIntegration="
				+ maxIntegration + "]";
	}

	//get the distribution 得到分布函数值
	public double getDistribution() {
		switch(dk) {
		case uniformDistribution:
			return uniformDistribution();
		case normalDistribution:
			return normalDistribution();
			default: return 0;
		}	
	}
	
	//minimum-max之间的均匀分布
	public float uniformDistribution() {
		return (float)(min + (max - min) * Math.random());
	}
	
	//normal distribution正态分布
	public double normalDistribution() {
		int n = 120;
		float temp = 0;
		for(int i = 0; i < n; i++) {
			temp += Math.random();
		}
		//standard normal distribution标准正态分布
		float standardNormalDistribution =(float) (Math.sqrt(12.0/n)*(temp - n / 2));
		//normal distribution by mean value and variance任意的正态分布 
		//System.out.println((float)(meanValue + Math.sqrt(variance) * standardNormalDistribution));
		return meanValue + Math.sqrt(variance) * standardNormalDistribution;
	}
	
	//get the area for integration
	//积分面积
	public float getIntegrationbyRectangle() {
		double areaIntegration = 0d;
		int n = 10000;
	
		double step = (maxIntegration - minIntegration) / n;//The step of integration步长
		for(int i = 0; i < n;i++) {
			areaIntegration += this.getNoramlDistribution(minIntegration+i*step);
		}
		//System.out.println(areaIntegration*step);
		return (float)(areaIntegration*step);
	}
	
	//get the normal distribution value by x  通过X得到正态分布函数的值
	public double getNoramlDistribution(double x) {
		double firstPart =1.0d/Math.sqrt(Math.PI * variance * 2);
		double secondPart = -(x-meanValue)*(x-meanValue)/(2*variance);
		return firstPart*Math.exp(secondPart);
	}
	
	//switch form String to Enum 将String类型转化为枚举类型
	public static DistributionKind getDistributionKind(String dist) {
		switch(dist) {
		case "UniformDistribution": return DistributionKind.uniformDistribution;
		case "NormalDistribution": return DistributionKind.normalDistribution;
		
		default: return DistributionKind.normalDistribution;
		}
	}	
}
