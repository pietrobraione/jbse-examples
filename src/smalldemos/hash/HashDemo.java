package smalldemos.hash;

import static jbse.meta.Analysis.any;

import java.util.HashMap;

class ClassSuper { }
class ClassSub extends ClassSuper { }

@SuppressWarnings("unused")
public class HashDemo {
	private int i;
	public void entryPoint() {
		HashMap<String, Integer> m = new HashMap<>();
		m.put("foo", 2);
		if (any()) {
			i = m.get("foo");
		} else {
			i = m.get(new String("foo"));
		}
	}
	
	public static void main(String[] s) {
		new HashDemo().entryPoint();
	}
}
