package diverge_2;

/**
 * A much sliced version of java.util.LinkedList.
 * 
 * @author Pietro Braione
 */
public class Diverge2 {
    public static class Entry {
        Object element;
        Entry next;
        Entry previous;

        Entry(Object element, Entry next, Entry previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    private Entry header = new Entry(null, null, null);
    private int size = 0;

    /**
     * A sliced version of java.util.LinkedList.ListItr constructor. 
     * With unlimited scope symbolic execution diverges as the number 
     * of entries is increased. With limited scope diverges anyways, 
     * but at some points no more entries are added, and divergence
     * occurs as the case is analyzed where entries form a loop.
     */
    void find(int index) {
        Entry next;
        if (index < 0 || index > size)
            throw new RuntimeException();
        if (index < (size >> 1)) {
            next = header.next;
            for (int nextIndex=0; nextIndex<index; nextIndex++)
                next = next.next;
        } else {
            next = header;
            for (int nextIndex=size; nextIndex>index; nextIndex--)
                next = next.previous;
        }
    }
}
