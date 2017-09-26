package smalldemos.array_3;

public class ArrayDemo3 {
	public static int a, b;
	
	//8 leaves:
	//1- p and q are both 1
	//2- identical to 1
	//3- p and q are both 0
	//4- p is 0 and q is 1
	//5- identical to 3
	//6- p is 1 and q is 0
	//7- p is in range, q is out of range
	//8- p is out of range
	public void entryPoint(int p, int q) {
		int[] x = new int[2];
		x[p] = 1;
		x[q] = 1;
		a = x[0];
		b = x[1];
	}
}
