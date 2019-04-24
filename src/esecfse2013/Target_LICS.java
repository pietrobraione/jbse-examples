package esecfse2013;

import static jbse.meta.Analysis.assume;

import java.util.List;

public class Target_LICS {
    private final static int MAX_LIST_SIZE = 10;

    int sum(List<Integer> list) {
        assume(list.size() <= MAX_LIST_SIZE);
        int tot = 0; 
        for (Integer item : list) {
            tot += item.intValue();
        }
        return tot;
    }

    void guide() {
        final List<Integer> l = new LinkedList_LICS<Integer>();
        l.add(2);
        l.add(3);
        l.add(5);
        sum(l);
    }
}
