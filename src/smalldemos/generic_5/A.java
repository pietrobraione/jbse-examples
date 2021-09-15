package smalldemos.generic_5;

import java.util.Optional;

class A {
    public void m(Optional<Integer>[][] c) {
    	if (!(c[0][2].get() instanceof Integer)) {
    		throw new RuntimeException();
    	}
    }
}