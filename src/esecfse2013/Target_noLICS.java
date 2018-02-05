package esecfse2013;

import static jbse.meta.Analysis.assume;

import java.util.List;

public class Target_noLICS {
	private final static int MAX_NUM_OF_ITERATIONS = 10;
	
	int sum(List<Integer> list) {
		int tot = 0; 
		int _niters = 0;
		for (Integer item : list) {
			tot += item.intValue();
			assume(++_niters <= MAX_NUM_OF_ITERATIONS);
		}
		return tot;
	}
	
	void g() {
		//List<Integer> l = new LinkedList_LICS<Integer>();
		List<Integer> l = new LinkedList_noLICS<Integer>();
		l.add(2);
		l.add(3);
		l.add(5);
		sum(l);
	}
}
