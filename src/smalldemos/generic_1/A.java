package smalldemos.generic_1;

class A {
    void m(B<C<D>> b) {
        if (!(b.y.z instanceof D)) {
            throw new RuntimeException();
        }
    }
}

class B<Y> {
    Y y;
}

class C<Z> {
    Z z;
}

class D { };
