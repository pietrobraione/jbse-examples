package smalldemos.hash_1;

import static jbse.meta.Analysis.any;

import java.util.HashMap;

import jbse.base.JAVA_MAP;

@SuppressWarnings("unused")
public class HashDemo1 {
    private int i;
    public void entryPoint() {
        HashMap<String, Integer> m = new HashMap<>();
        m.put("foo", 2);
        if (any()) {
            i = m.get("foo");
        } else {
            i = m.get(new String("foo"));
        }
    }
}
