package co.aurasphere.algo.datastructure;

import java.util.Objects;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {

	private BinarySearchTreeNode<T> head;

	private int nodeCount = 0;

//	Implement:
//		 insert // insert value into tree
//		 get_node_count // get count of values stored
//		 print_values // prints the values in the tree, from min to max
//		 delete_tree
//		 is_in_tree // returns true if given value exists in the tree
//		 get_height // returns the height in nodes (single node's height is 1)
//		 get_min // returns the minimum value stored in the tree
//		 get_max // returns the maximum value stored in the tree
//		 is_binary_search_tree
//		 delete_value
//		 get_successor // returns next-highest value in tree after given value, -1 if none

	public void insert(T value) {
		if (head == null) {
			head = new BinarySearchTreeNode<T>(value);
			nodeCount++;
			return;
		}
		BinarySearchTreeNode<T> currentNode = head;
		while (true) {
			int compare = currentNode.getValue().compareTo(value);
			if (compare == 0) {
				throw new IllegalArgumentException("Duplicate value: " + value);
			}
			if (compare == -1) {
				if (currentNode.right == null) {
					currentNode.right = new BinarySearchTreeNode<T>(value);
					nodeCount++;
					return;
				} else {
					currentNode = currentNode.right;
				}
			}
			if (compare == 1) {
				if (currentNode.left == null) {
					currentNode.left = new BinarySearchTreeNode<T>(value);
					nodeCount++;
					return;
				} else {
					currentNode = currentNode.left;
				}
			}
		}
	}

	public int getNodeCount() {
		return this.nodeCount;
	}

	public void printValues() {
		// DFS
		Stack<BinarySearchTreeNode<T>> stack = new Stack<BinarySearchTreeNode<T>>();
		BinarySearchTreeNode<T> nextNode = head;
		while (nextNode != null || !stack.isEmpty()) {
			while (nextNode != null) {
				stack.push(nextNode);
				nextNode = nextNode.left;
			}

			nextNode = stack.pop();
			System.out.print(nextNode.getValue() + " ");
			nextNode = nextNode.right;
		}
	}

	public void deleteTree() {
		head = null;
	}

	public boolean isInTree(T value) {
		BinarySearchTreeNode<T> nextNode = head;
		while (nextNode != null) {
			int compare = nextNode.getValue().compareTo(value);
			if (compare == 0) {
				return true;
			}
			if (compare == -1) {
				nextNode = nextNode.right;
			}
			if (compare == 1) {
				nextNode = nextNode.left;
			}
		}
		return false;
	}

	public int getHeight() {
		return head.getHeight();
	}

	private T getMin(BinarySearchTreeNode<T> node) {
		if (node == null) {
			return null;
		}
		BinarySearchTreeNode<T> nextNode = node;
		while (nextNode.left != null) {
			nextNode = nextNode.left;
		}
		return nextNode.getValue();
	}

	public T getMin() {
		return getMin(head);
	}

	public T getMax() {
		return getMax(head);
	}

	private T getMax(BinarySearchTreeNode<T> node) {
		if (node == null) {
			return null;
		}
		BinarySearchTreeNode<T> nextNode = node;
		while (nextNode.right != null) {
			nextNode = nextNode.right;
		}
		return nextNode.getValue();
	}

	public T getSuccessor(T value) {
		return getSuccessor(head, value);
	}

	private T getSuccessor(BinarySearchTreeNode<T> node, T value) {
		if (node == null) {
			return null;
		}

		BinarySearchTreeNode<T> currentNode = node;
		while (currentNode != null) {
			int compare = currentNode.getValue().compareTo(value);
			if (compare == 0) {
				return getMin(currentNode.right);
			}
			if (compare == -1) {
				if (currentNode.right == null || currentNode.right.getValue().compareTo(value) != 1) {
					return currentNode.getValue();
				} else {
					currentNode = currentNode.right;
				}
			}
			if (compare == 1) {
				if (currentNode.left == null || currentNode.left.getValue().compareTo(value) != 1) {
					return currentNode.getValue();
				} else {
					currentNode = currentNode.left;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return head.toString();
	}

	public boolean isBinarySearchTree() {
		return isBinarySearchTree(head);
	}

	private boolean isBinarySearchTree(BinarySearchTreeNode<T> node) {
		if (node == null)
			return true;
		return (node.left == null || node.left.getValue().compareTo(node.getValue()) == -1)
				&& (node.right == null || node.right.getValue().compareTo(node.getValue()) == 1)
				&& isBinarySearchTree(node.left) && isBinarySearchTree(node.right);
	}

	public void delete(T value) {
		BinarySearchTreeNode<T> nextNode = head;
		BinarySearchTreeNode<T> toDelete = null;
		BinarySearchTreeNode<T> previous = null;
		while (nextNode != null) {
			int compare = nextNode.getValue().compareTo(value);
			if (compare == 0) {
				toDelete = nextNode;
				break;
			}
			if (compare == -1) {
				previous = nextNode;
				nextNode = nextNode.right;
			}
			if (compare == 1) {
				previous = nextNode;
				nextNode = nextNode.left;
			}
		}

		// Node not found
		if (toDelete == null) {
			return;
		}

		// Checks wether the node to remove is left or right regarding to the parent
		boolean isRoot = previous == null;
		boolean isLeftChild = previous.left != null && previous.left.getValue().compareTo(toDelete.getValue()) == 0;

		// If node is a leaf remove it from parent.
		if (toDelete.left == null && toDelete.right == null) {
			if (isRoot) {
				head = null;
			} else if (isLeftChild) {
				previous.left = null;
			} else {
				previous.right = null;
			}
			return;
		}
		
		// If node has only one child move the child up
		if (toDelete.left == null || toDelete.right == null) {
			if (isRoot) {
				head = toDelete.right == null ? toDelete.left : toDelete.right;
			} else if (isLeftChild) {
				previous.left = toDelete.right == null ? toDelete.left : toDelete.right;
			} else {
				previous.right = toDelete.right == null ? toDelete.left : toDelete.right;
			}
			return;
		}
	}

	private class BinarySearchTreeNode<T extends Comparable<T>> {

		private T value;

		private BinarySearchTreeNode<T> left;

		private BinarySearchTreeNode<T> right;

		public BinarySearchTreeNode(T value) {
			this.value = value;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public BinarySearchTreeNode<T> getLeft() {
			return left;
		}

		public void setLeft(BinarySearchTreeNode<T> left) {
			this.left = left;
		}

		public BinarySearchTreeNode<T> getRight() {
			return right;
		}

		public void setRight(BinarySearchTreeNode<T> right) {
			this.right = right;
		}

		public int getHeight() {
			int leftHeight = left == null ? 0 : left.getHeight();
			int rightHeight = right == null ? 0 : right.getHeight();
			return Math.max(leftHeight, rightHeight) + 1;
		}

		@Override
		public String toString() {
			return Objects.toString(left) + " " + value + " " + Objects.toString(right);
		}

	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(12);
		bst.insert(3);
		bst.insert(54);
		bst.insert(7);
		bst.insert(1);
		bst.insert(25);
		bst.insert(45);
		bst.insert(17);
		bst.insert(2);

		bst.printValues();

		System.out.println();

		System.out.println(bst);
		System.out.println(bst.getNodeCount());

		System.out.println(bst.isInTree(41));
		System.out.println(bst.isInTree(54));
		System.out.println(bst.getHeight());
		System.out.println(bst.getMin());
		System.out.println(bst.getMax());

		System.out.println(bst.getSuccessor(1));
		System.out.println(bst.isBinarySearchTree());
	}

}
