package guid.hash_1;

import java.util.HashMap;

/**
 * Guidance demo; here we have a {@link HashMap} that 
 * is interpreted by map model in the executions.
 * Moreover, the {@link String#equals(Object)} method is 
 * set as uninterpreted in the symbolic execution.
 * 
 * @author Pietro Braione
 */
public class GuidDemoHash1 {
    public int entryPoint(HashMap<String, String> m) {
    	final String s = m.get("abc");
        if ("def".equals(s)) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public void guidanceStart() {
    	final HashMap<String, String> m = new HashMap<>();
    	m.put("abc", "def");
        entryPoint(m);
    }
    
    public static void main(String[] args) {
    	GuidDemoHash1 gd = new GuidDemoHash1();
    	gd.guidanceStart();
    }
    
    //expected effect of the guided execution: it must return 1
}