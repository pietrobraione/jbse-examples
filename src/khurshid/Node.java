package khurshid;

import static jbse.meta.Analysis.ass3rt;

/**
 * An example from S. Khurshid, C. S. Pasareanu, and W. Visser, 
 * "Generalized Symbolic Execution for Model Checking and Testing", 
 * in Proceedings of TACAS 2003, fig. 1. 
 * This class is the {@code Node} class of fig. 1. 
 */
public class Node {
	int elem;
	Node next;
	
	//instrumentation variables
	static Node _OLD_NEXT;
	static Node _OLD_NEXT_NEXT;

	/**
	 * Swaps this node's position with the position of its {@code next} node, 
	 * if the next node exists (not null) and has an {@code elem} which is smaller 
	 * than this node's {@code elem}, otherwise does nothing.
	 * 
	 * @return either {@code this.next} or {@code this}, depending on whether
	 *         it made the swap or not. Equivalently, returns the node that
	 *         after the swap has minimal {@code elem}, and therefore must occupy the
	 *         position {@code this} had before the swap. 
	 */
	Node swapNode() {
		Node retVal;
		if (next != null) {
			_OLD_NEXT = next;
			_OLD_NEXT_NEXT = next.next;
			if (elem - next.elem > 0) {
				Node t = next;
				next = t.next;
				t.next = this;
				retVal = t;
			} else {
				retVal = this;
			}
		} else {
			_OLD_NEXT = null;
			_OLD_NEXT_NEXT = null; //meaningless
			retVal = this;
		}
		
		//The final assertions prove that:
		//1- The method does not return null;
		//2- The returned node is either this or the next node,  
		//   and the successor of the returned node is the other 
		//   of the two;
		//3- The returned node and its successor (if exists) 
		//   are ordered;
		//4- The rest of the list is not touched.
		//
		ass3rt(retVal != null);
		ass3rt((retVal == this && retVal.next == _OLD_NEXT) || 
			   (retVal == _OLD_NEXT && retVal.next == this));
		ass3rt(retVal.next == null || retVal.elem <= retVal.next.elem);
		ass3rt(retVal.next == null || retVal.next.next == _OLD_NEXT_NEXT);

		return retVal;
	}
}