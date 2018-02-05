package esecfse2013;

import static jbse.meta.Analysis.assume;

import java.util.List;

public class Target_LICS {
	private final static int MAX_NUM_OF_ITERATIONS = 10;
	
	int sum(List<Integer> list) {
		assume(list.size() <= MAX_NUM_OF_ITERATIONS);
		int tot = 0; 
		for (Integer item : list) {
			tot += item.intValue();
		}
		return tot;
	}
}
