package iwrap.util;

public class Sailor {
	//public static double forgetCheckPosition = 0.00015;//Not true 不正确的值
	public static double distanceCheckPosition = 1000.0d;//The distance to check ship position检测船位的距离

	public static double getProbabilityForgetCheckPosition(float distanceToObstacle) {
		double tempTimesForgetCheckPosition = 
				Math.exp(-distanceToObstacle/distanceCheckPosition);
		//System.out.println(tempTimesForgetCheckPosition+"****");
		return  tempTimesForgetCheckPosition;
	}
}
