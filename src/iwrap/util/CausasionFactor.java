package iwrap.util;

public class CausasionFactor {
	public static double headOnCollision = 0.00005;
	public static double overtakeCollision = 0.00011;
	public static double crossCollision = 0.00013;
	public static double agroundFactor = 0.00016;
	//The ship out of control frequency in default
	//����ʧ��Ƶ�ʣ�Ĭ�ϵ�Ϊ�ʹ�0.1/�꣬����Ϊ0.75/��
	public static double frequencyOutofControlDefault = 0.1/(360*24*3600);
	public static double frequencyOutofControl = 0.75/(360*24*3600);
}
