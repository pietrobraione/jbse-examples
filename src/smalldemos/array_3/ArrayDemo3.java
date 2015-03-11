package smalldemos.array_3;

public class ArrayDemo3 {
	public static int a, b;
	
	public void entryPoint(int p, int q) {
		int[] x = new int[2];
		x[p] = 1;
		x[q] = 1;
		a = x[0];
		b = x[1];
	}
}
