package smalldemos.arraycopy;

public class ArraycopyDemo {
    boolean error;

    public void entryPoint(int[] x) {
        int[] y = new int[x.length]; 
        System.arraycopy(x, 0, y, 0, x.length);
        int a = x[1];
        int b = y[1];
        if (a == b) {
            this.error = false;
        } else {
            this.error = true;
        }
        //three leaves, one with path condition {ROOT}:x fresh && {ROOT}:x.length > 1, and 
        //in the final state {ROOT}:this.error == false, one with path condition {ROOT}:x fresh && 
        //0 <= {ROOT}:x.length <= 1, and in the final state raises ArrayIndexOutOfBoundsException,
        //and one with path condition {ROOT}:x == null, and in the final state raises NullPointerException.
    }
}
