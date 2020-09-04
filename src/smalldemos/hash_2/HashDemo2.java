package smalldemos.hash_2;

import static jbse.meta.Analysis.any;

import java.util.HashMap;

import jbse.base.JAVA_MAP;

@SuppressWarnings("unused")
public class HashDemo2 {
    private int i;
    public void entryPoint(HashMap<String, Integer> m) {
        i = m.get("foo");
    }
    
    public void test() {
        HashMap<String, Integer> m = new HashMap<>();
        m.put("foo", 7);
        entryPoint(m);
    }
}
