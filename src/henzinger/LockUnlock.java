package henzinger;

import static jbse.meta.Analysis.any;
import static jbse.meta.Analysis.assume;

/**
 * A locking example as from T. A. Henzinger, R. Jhala, and
 * R. Majumdar, "Lazy Abstraction", in Proceedings of POPL 2002.
 * Here, rather than using assertions, we detect errors
 * by observing the state of two variables.
 */
public class LockUnlock {
    boolean LOCK;
    boolean _ERROR_LOCK;
    boolean _ERROR_UNLOCK;

    /**
     * Seizes the lock represented by the instance variable
     * {@code LOCK}. Calling this function when the lock is 
     * occupied causes {@code _ERROR_LOCK}.
     */ 
    void lock() {
        if (!LOCK) {
            LOCK = true;
        } else {
            _ERROR_LOCK = true;
        }
    } // lock()

    /**
     * Releases the lock represented by the instance variable
     * {@code LOCK}. Calling this function when the lock is not 
     * occupied causes {@code _ERROR_UNLOCK}.
     */ 
    void unlock() {
        if (LOCK) {
            LOCK = false;
        } else {
            _ERROR_UNLOCK = true;
        }
    } // unlock()

    /**
     * The core of locking example, i.e., a protocol of lock-unlock actions
     * as in fig. 1 of the paper.
     * We preserved the fact that in the paper the variable new (here 
     * renamed {@code new_val} to avoid clashing with the keyword) is not 
     * initialized by declaring it as a parameter.
     */
    void example(int new_val) {
        int got_lock;
        int old;

        assume(!LOCK); //comment out this line to have errors on the output

        if (any()) {
            do {
                got_lock = 0;
                if (any()) {
                    lock();
                    got_lock++;
                }
                if (got_lock != 0) {
                    unlock();
                }
            } while (any());
        } 

        do {
            lock();
            old = new_val;
            if (any()) {
                unlock();
                new_val++;
            }
        } while (new_val != old);
        unlock();
    }
}
