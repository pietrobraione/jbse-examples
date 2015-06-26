package testgen;

public class Testgen {
	public static class Node {
		Node next;
		int info;
	}
	Node anotherNode;

	public Node getNode(Node aNode, int anInt) {
		if (anInt < 0) {
			final Node first_1 = aNode;
			final Node first_2 = anotherNode;
			if (first_1.info == first_2.info) {
				return null;
			}

			final Node second = first_1.next;
			final Node secondOther = first_2.next;
			if (second.info == secondOther.info) {
				return null;
			}
			return second.next;

		} else {
			if (anInt > 10) {
				return null;
			} else {
				return new Node();
			}
		}
	}
}
