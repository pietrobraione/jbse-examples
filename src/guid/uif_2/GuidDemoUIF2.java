package guid.uif_2;

/**
 * Guidance demo; here we have two method that are
 * set as uninterpreted in the symbolic execution
 * (the method {@link GuidDemoUIF2#foo(String)} and 
 * the method {@link String#equals(Object)}).
 * 
 * @author Pietro Braione
 */
public class GuidDemoUIF2 {
    public int entryPoint(String s) {
    	final String s2 = foo(s);
        if (s2.equals("abc")) {
            return 1;
        } else {
            return 0;
        }
    }
    
    private static String foo(String s) {
    	if (s.length() >= 3 && s.charAt(2) == '%') {
    		return null;
    	}
    	return s;
    }
    
    public void guidanceStart() {
    	final String s = "ab%1";
        entryPoint(s);
    }
    
    public static void main(String[] args) {
    	GuidDemoUIF2 gd = new GuidDemoUIF2();
    	gd.guidanceStart();
    }
    
    //expected effect of the guided execution: it must throw NullPointerException
}