package smalldemos.generic_6;

import java.util.Optional;

class A {
	int foo; //this forces the "this" parameter to f to be not simple, so the invocations of f are not base-level
	
	<X> B<C<X>> f(Optional<X> x) {
		return null; //never executed
	}
	
    void m1() {
        if (!(f(Optional.of(new D())).y.z instanceof D)) {
            throw new RuntimeException();
        }
    }
	
    void m2() {
    	Optional<?> x = Optional.empty();
        if (!(f(x).y.z instanceof D)) { 
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
