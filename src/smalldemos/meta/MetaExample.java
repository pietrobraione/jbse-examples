package smalldemos.meta;

import static jbse.meta.Analysis.any;
import static jbse.meta.Analysis.assume;

public class MetaExample {
    int f(int x) {
        assume(x > 0);
        if (x == 0) {
            throw new RuntimeException(); //infeasible
        }
        boolean b = any();
        if (b) {
            return 0;
        }
        return 1;
    }
    //two leaves, both with {ROOT}:x > 0, one returning 0
    //and one returning 1, plus one trace violating an
    //assumption.
}
