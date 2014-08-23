package iwrap.util;

public class CausasionFactor {
	public static double headOnCollision = 0.00005;
	public static double overtakeCollision = 0.00011;
	public static double crossCollision = 0.00013;
	public static double agroundFactor = 0.00016;
	//The ship out of control frequency in default
	//船舶失控频率，默认的为客船0.1/年，其他为0.75/年
	public static double frequencyOutofControlDefault = 0.1/(360*24*3600);
	public static double frequencyOutofControl = 0.75/(360*24*3600);
}
