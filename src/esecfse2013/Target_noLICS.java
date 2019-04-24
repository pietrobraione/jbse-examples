package esecfse2013;

import static jbse.meta.Analysis.assume;

import java.util.List;

public class Target_noLICS {
    private final static int MAX_LIST_SIZE = 10;

    int sum(List<Integer> list) {
        int tot = 0; 
        int _niters = 0;
        for (Integer item : list) {
            tot += item.intValue();
            assume(++_niters <= MAX_LIST_SIZE);
        }
        return tot;
    }

    void guide() {
        final List<Integer> l = new LinkedList_noLICS<Integer>();
        l.add(2);
        l.add(3);
        l.add(5);
        sum(l);
    }
}
