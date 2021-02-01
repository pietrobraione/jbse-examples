package smalldemos.hash_4;

import static jbse.meta.Analysis.assume;

import java.util.HashMap;
import java.util.Map;

public class HashDemo4 {
    public int entryPoint(HashMap<String, Integer> m) {
    	assume(m.size() <= 3);
        int i = 0;
    	for (Map.Entry<String, Integer> e : m.entrySet()) {
    		i += e.getValue();
    	}
    	return i;
    }    
}
