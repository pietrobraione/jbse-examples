package smalldemos.array_2;

import static jbse.meta.Analysis.any;

/** 
 * Demo code for array analysis, focused on symbolic arrays and expansion
 * upon access.
 */ 
public class ArrayDemo2 {
    boolean any;

    public class Foo { }

    //outcomes of read operations
    int i;
    Foo o1;

    void aM(int[] a) {
        i = a[2];
        //this should yield three leaves, one where {ROOT}:a0
        //is null and NullPointerException is raised, one
        //where {ROOT}:a0.length is between 0 and 2 and 
        //ArrayIndexOutOfBoundsException is raised, and one 
        //where {ROOT}:a0.length is > 2 and i is set to {ROOT}:a0[2]
    }

    void bM(Foo[] a) {
        o1 = a[5];
        //this should yield four leaves, one where {ROOT}:a1
        //is null and NullPointerException is raised, 
        //one where {ROOT}:a1.length is between 0 and 5 and 
        //ArrayIndexOutOfBoundsException is raised, 
        //one where {ROOT}:a1.length is > 5 and o1 is
        //set to a[5] fresh, and one where {ROOT}:a1.length 
        //is > 5, and both o1 and a[5] are null
    }

    void cM(Foo[] a) {
        o1 = a[-1];
        //this should yield two leaves, one where {ROOT}:a1
        //is null and NullPointerException is raised,
        //and one where {ROOT}:a1 is expanded to a fresh object,
        //{ROOT}:a1.length is assumed >= 0, and 
        //ArrayIndexOutOfBoundsException is raised
    }

    void dM(Foo[] a) {
        if (a.length < 0) {
            throw new RuntimeException(); //unreachable!
        }
        //this should yield two leaves, one where {ROOT}:a1
        //is null, and NullPointerException is raised,
        //and one where {ROOT}:a1 is expanded to a fresh object,
        //and {ROOT}:a1.length is assumed >= 0
    }

    int x, y, z, u, vx, vy, vz, vu;
    Foo fx, fy, fz, fu;

    void eM(int[] a) {
        a[x] = vx;
        a[y] = vy;
        a[z] = vz;
        vu = a[u];
        //9 leaves (TODO check)
    }

    void fM(Foo[] a) {
        a[x] = fx;
        fu = a[u];
        //13 leaves (TODO check)
    }

    //total: 33 leaves
    public void entryPoint(int[] a0, Foo[] a1) {
        if (any()) {
            aM(a0);
        } else if (any()) {
            bM(a1);
        } else if (any()) {
            cM(a1);
        } else if (any()) {
            dM(a1);
        } else if (any()) {
            eM(a0);
        } else {
            fM(a1);
        }
    }
}
