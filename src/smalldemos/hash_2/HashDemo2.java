package smalldemos.hash_2;

import static jbse.meta.Analysis.any;

import java.util.HashMap;

@SuppressWarnings("unused")
public class HashDemo2 {
    private int i;
    public void entryPoint(HashMap<String, Integer> m) {
        m.remove("foo", 2);
        i = m.get("foo");
    }
    
    public void test() {
        HashMap<String, Integer> m = new HashMap<>();
        m.put("foo", 7);
        entryPoint(m);
    }
}
