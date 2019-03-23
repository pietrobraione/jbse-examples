package smalldemos.array_3;

public class ArrayDemo3 {
    public int a, b;

    public void entryPoint(int p, int q) {
        int[] x = new int[2];
        x[p] = 1;
        x[q] = 2;
        this.a = x[0];
        this.b = x[1];
    }
    //6 leaves:
    //1- p and q are both 1: a == 0, b == 2
    //2- p is 0 and q is 1: a == 1, b == 2
    //3- p and q are both 0: a == 2, b == 0
    //4- p is 1 and q is 0: a == 2, b == 1
    //5- p is in range, q is out of range: raises IndexOutOfBoundException
    //6- p is out of range: raises IndexOutOfBoundException
}
