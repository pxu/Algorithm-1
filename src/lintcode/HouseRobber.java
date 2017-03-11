package lintcode;

public class HouseRobber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long re = houseRobber(new int[]{1, 5, 4, 2, 5});
		System.out.println(re);
	}

	public static long houseRobber(int[] A) {
		// write your code here
		int lastT = 0;
		int lastF = 0;
		int curT = 0;
		int curF = 0;
		for (int i = 0; i < A.length; i++) {
			curT = lastF + A[i];
			curF = lastT > lastF ? lastT : lastF;
			lastT = curT;
			lastF = curF;
		}
		return curT > curF ? curT : curF;
	}

	public static long houseRobberR1(int[] A) {
		if (A.length == 0)
			return 0;
		long lastT = 0;
		long lastF = 0;
		long currentT;
		long currentF;
		for (int i = 0; i < A.length; i++) {
			currentT = A[i] + lastF;
			currentF = Math.max(lastT, lastF);
			lastT = currentT;
			lastF = currentF;
		}
		return Math.max(lastT, lastF);
	}
}
