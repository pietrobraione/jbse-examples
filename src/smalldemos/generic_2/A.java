package smalldemos.generic_2;

class A {
    void m(B<C<D>> b) {
        if (!(b.y[0][2].z instanceof D)) {
            throw new RuntimeException();
        }
    }
}

class B<Y> {
    Y[][] y;
}

class C<Z> {
    Z z;
}

class D { };
