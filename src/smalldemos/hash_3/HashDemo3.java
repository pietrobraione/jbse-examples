package smalldemos.hash_3;

import static jbse.meta.Analysis.any;

import java.util.HashMap;

@SuppressWarnings("unused")
public class HashDemo3 {
    private int i;
    public void entryPoint(String j, String k) {
        HashMap<String, Integer> m = new HashMap<>();
        m.put(j, 0);
        this.i = m.get(k);
    }
}
