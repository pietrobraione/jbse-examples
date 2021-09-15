package smalldemos.generic_6;

class A {
	<X> B<C<X>> f(X x) {
		return null; //never executed
	}
	
    void m() {
    	final B<C<D>> b = f(new D());
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
