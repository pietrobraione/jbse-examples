package guid.hash_3;

import java.util.HashMap;

/**
 * Guidance demo; here we have a {@link HashMap} that 
 * is interpreted by map model in the executions.
 * Moreover, the {@link Foo#toString()} method is 
 * set as uninterpreted in the symbolic execution.
 * 
 * @author Pietro Braione
 */
public class GuidDemoHash3 {
    public int entryPoint(Foo f, HashMap<Foo, Integer> m) {
    	if (m.containsKey(f)) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
    
    public void guidanceStart() {
    	final HashMap<Foo, Integer> m = new HashMap<>();
    	m.put(new Foo(0), 0);
    	m.put(new Foo(1), 1);
        entryPoint(new Foo(1), m);
    }
    
    public static void main(String[] args) {
    	GuidDemoHash3 gd = new GuidDemoHash3();
    	gd.guidanceStart();
    }
    
    //expected effect of the guided execution: it must return 1
}

class Foo {
	private final int i;

	Foo(int i) {
		this.i = i;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foo other = (Foo) obj;
		return toString().equals(other.toString());
	}

	@Override
	public String toString() {
		return "Foo [i=" + i + "]";
	}
	
}
