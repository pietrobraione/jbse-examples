package smalldemos.generic_4;

class A<X> {
    class B<Y> {
        class C<Z> {
            X x;
            Y y;
            Z z;
        }
    }
    class F { }
}

class D {
    public void m(A<Integer>.B<Float>.C<Character> c) { 
        if (!((c.x instanceof Integer) && (c.y instanceof Float) && (c.z instanceof Character))) {
            throw new RuntimeException();
        }
    }
}