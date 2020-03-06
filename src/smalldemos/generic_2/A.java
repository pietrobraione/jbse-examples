package smalldemos.generic_2;

import java.util.List;

class A<X> {
    class B<Y> {
        F f;
        class C<Z> {
            X x;
            Y y;
            Z z;
        }
    }
    class F { }
}

class D {
    List<? extends Thread> l;
    void m(A<Integer>.B<Float>.C<Character> c) { 
        if (!(c.y instanceof Float)) {
            throw new RuntimeException();
        }
    }
}