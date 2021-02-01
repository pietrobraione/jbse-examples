package smalldemos.hash_5;

import java.util.LinkedHashMap;

public class HashDemo5 {
    public void entryPoint() {
    	final LinkedHashMap<String, Integer> m = new LinkedHashMap<>(1<<30, 0.75f, true);
    	m.put("a", 1);
    	m.put("b", 2);
    	m.put("c", 3);
    	m.put("c", 0);
    	m.put("b", 10);
    	m.put("a", 20);
    	System.out.println(m.toString());
    }
    
    public static void main(String[] args) {
    	final HashDemo5 d = new HashDemo5();
    	d.entryPoint();
    }
}
