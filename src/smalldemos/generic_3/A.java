package smalldemos.generic_3;

import java.util.HashMap;

class A {
    void m(B<C<D>> b) {
        if (!(b.y.get(0).get(2).z instanceof D)) {
            throw new RuntimeException();
        }
    }
}

class B<Y> {
    HashMap<Integer, HashMap<Integer, Y>> y;
}

class C<Z> {
    Z z;
}

class D { };
