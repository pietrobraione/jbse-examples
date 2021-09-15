package guid.hash_2;

import java.util.HashMap;

/**
 * Guidance demo; here we have a {@link HashMap} that 
 * is interpreted by map model in the executions.
 * Moreover, the {@link String#equals(Object)} method is 
 * set as uninterpreted in the symbolic execution.
 * 
 * @author Pietro Braione
 */
public class GuidDemoHash2 {
    public int entryPoint(boolean b, HashMap<Num, String> m) {
    	final Num n = new Num();
    	if (b) {
    		n.inc();
    	}
    	if (m.get(n).equals("abc")) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
    
    public void guidanceStart() {
    	final HashMap<Num, String> m = new HashMap<>();
    	final Num n = new Num();
    	n.inc();
    	m.put(n, "abc");
        entryPoint(true, m);
    }
    
    public static void main(String[] args) {
    	GuidDemoHash2 gd = new GuidDemoHash2();
    	gd.guidanceStart();
    }
    
    //expected effect of the guided execution: it must return 1
}

class Num {
	private int i = 0;
	
	void inc() { 
		++i;
	}
	
	int get() {
		return i;
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
		Num other = (Num) obj;
		if (i != other.i)
			return false;
		return true;
	}
}
