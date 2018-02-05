package testgen;

public class Testgen {
	public static class Node {
		Node next;
		int info;
		
		public void setNext(Node next) { this.next = next; }
		public Node getNext() { return this.next; }
		public void setInfo(int info) { this.info = info; }
		public int getInfo() { return this.info; }
	}
	Node anotherNode;
	public void setAnotherNode(Node anotherNode) { this.anotherNode = anotherNode; }
	public Node getAnotherNode() { return this.anotherNode; }

	public Node getNode(Node aNode, int anInt) {
		if (anInt < 0) {
			final Node first_1 = aNode;
			final Node first_2 = anotherNode;
			if (first_1.info == first_2.info) {
				return null;
			}

			final Node second_1 = first_1.next;
			final Node second_2 = first_2.next;
			if (second_1.info == second_2.info) {
				return null;
			}
			return second_1.next;

		} else {
			if (anInt > 10) {
				return null;
			} else {
				return new Node();
			}
		}
	}
	
	public void test() {
		final Testgen tg = new Testgen();
		final Testgen.Node node1 = new Node();
		node1.setInfo(1);
		final Testgen.Node node2 = new Node();
		node2.setInfo(2);
		final Testgen.Node node3 = new Node();
		node3.setInfo(3);
		node1.setNext(node3);
		node2.setNext(node3);
		tg.setAnotherNode(node2);
		tg.getNode(node1, -1);
	}
}
