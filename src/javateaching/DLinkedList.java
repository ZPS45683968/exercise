package javateaching;

import java.util.ArrayList;
class Node {
	private int value;
	private Node nextNode;
	private Node prevNode;

	public Node(int v) {
		value = v;
		nextNode = null;
		prevNode = null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int v) {
		value = v;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node n) {
		nextNode = n;
	}

	public Node getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(Node n) {
		prevNode = n;
	}

}
public class DLinkedList {

	// Holds a reference to the head and tail of the list
	private Node headNode;
	private Node tailNode;

	public DLinkedList() {
		headNode = null;
		tailNode = null;
	}

	public Object getHeadValue() {
		if (headNode == null)
			return null;
		return headNode.getValue();
	}

	public Object getTailValue() {
		if (tailNode == null)
			return null;
		return tailNode.getValue();
	}

	public void addAtHead(int o) {
		Node newNode = new Node(o);
		newNode.setNextNode(headNode);
		if (headNode != null)
			headNode.setPrevNode(newNode);
		headNode = newNode;
		// special case for empty list
		if (tailNode == null)
			tailNode = newNode;
	}

	public void addAtTail(int o) {
		Node newNode = new Node(o);
		// this means that headNode == null too!
		if (tailNode == null) {
			tailNode = newNode;
			headNode = newNode;
		} else {
			newNode.setPrevNode(tailNode);
			tailNode.setNextNode(newNode);
			tailNode = newNode;
		}
	}

	public int deleteAtHead() {
		// list is empty
		if (headNode == null) {
			headNode = null;
			tailNode = null;
			return -1;
		}
		// singleton: must update tailnode too
		if (headNode == tailNode) {
			int res = headNode.getValue();
			headNode = null;
			tailNode = null;
			return res;
		}

		int res = headNode.getValue();
		headNode = headNode.getNextNode();
		headNode.setPrevNode(null);
		return res;
	}

	public int deleteAtTail() {
		// list is empty
		if (tailNode == null) {
			headNode = null;
			tailNode = null;
			return -1;
		}
		// singleton: must update tailnode too
		if (headNode == tailNode) {
			int res = tailNode.getValue();
			headNode = null;
			tailNode = null;
			return res;
		}
		int res = tailNode.getValue();
		tailNode = tailNode.getPrevNode();
		tailNode.setNextNode(null);
		return res;
	}

	public int delete(Node n) {
		if (n == null)
			return -1;
		Node next = n.getNextNode();
		Node prev = n.getPrevNode();
		int val = n.getValue();
		if (prev != null)
			prev.setNextNode(next);
		if (next != null)
			next.setPrevNode(prev);
		// deleting at the end
		if (n == tailNode)
			tailNode = prev;
		// deleteing at beginning
		if (n == headNode)
			headNode = next;
		return val;
	}

	public void insertAfter(Node n, int val) {
		if (n == null) { // this is the headNode
			addAtHead(val);
			return;
		}
		Node next = n.getNextNode();
		Node newNode = new Node(val);
		newNode.setPrevNode(n);
		newNode.setNextNode(next);
		n.setNextNode(newNode);
		if (next == null) { // insert at tail
			tailNode = newNode;
		} else {
			next.setPrevNode(newNode);
		}
	}

	// computes the size of the list
	public int size() {
		if (headNode == null)
			return 0;
		Node n = headNode;
		int size = 0;
		while (n != null) {
			size++;
			n = n.getNextNode();
		}
		return size;
	}

	// Predicate to check if the linked list is sorted
	public boolean isSorted() {
		if (headNode == null || headNode.getNextNode() == null)
			return true;
		Node i = headNode.getNextNode();
		while (i != null) {
			if (i.getValue() < i.getPrevNode().getValue())
				return false;
			i = i.getNextNode();
		}
		return true;
	}

	// toString methods to override printing of object
	public String toString() {
		Node n = headNode;
		StringBuffer buf = new StringBuffer();
		while (n != null) {
			buf.append(n.getValue());
			buf.append(" ");
			n = n.getNextNode();
		}
		return buf.toString();
	}

	/**
	 * Sorted the doubly linked list using the insertion-sort algorithm.
	 * 
	 * This is Question 4
	 * 
	 * Look at how insertionSort in ArraySort.java does insertion sort on arrays.
	 * The task is to perform the same algorithm, but sorting nodes linked together
	 * in a doubly linked list.
	 */
	//双链表插入排序
	public void insertionSort() {
		if (headNode == null || headNode.getNextNode() == null)	//空链表或只有一个节点的链表
			return;
		Node i = headNode.getNextNode();					//i指向第二个节点
		while (i != null) {									//遍历链表
			Node j = i;										//j指向i
			while (j.getPrevNode() != null && j.getValue() < j.getPrevNode().getValue()) {
				int temp = j.getValue();					//交换j和j的前一个节点的值
				j.setValue(j.getPrevNode().getValue());		//j的值变为j的前一个节点的值
				j.getPrevNode().setValue(temp);				//j的前一个节点的值变为j的值
				j = j.getPrevNode();						//j指向j的前一个节点
			}
			i = i.getNextNode();							//i指向i的下一个节点
		}
	}
	public static void main(String[] args) {
		DLinkedList d = new DLinkedList();
		d.addAtHead(4);
		d.addAtHead(1);
		d.addAtHead(7);
		d.addAtHead(10);
		System.out.println("Before sorting: " + d); // this will call the toString method
		d.insertionSort();
		System.out.println("After sorting: " + d);
	}

}
