package guid.uif_1;

/**
 * Guidance demo; here we have two method that are
 * set as uninterpreted in the symbolic execution
 * (the method {@link GuidDemoUIF1#foo(String)} and 
 * the method {@link Math#sin(double)}).
 * 
 * @author Pietro Braione
 */
public class GuidDemoUIF1 {
    public int entryPoint(double x) {
    	final double y = foo(x);
        if (Math.sin(y) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    
    private static double foo(double x) {
    	return 10.0 * x; 
    }
    
    public void guidanceStart() {
    	final double x = 1.0d;
        entryPoint(x);
    }
    
    public static void main(String[] args) {
    	GuidDemoUIF1 gd = new GuidDemoUIF1();
    	gd.guidanceStart();
    }
    
    //expected effect of the guided execution: it must return 0
}