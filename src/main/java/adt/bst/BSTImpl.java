package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return heightAux(root);
	}

	private int heightAux(BSTNode<T> node) {
		int result = -1;
		if (node != null) {
			result = 1 + Math.max(heightAux((BSTNode<T>) node.getLeft()), heightAux((BSTNode<T>) node.getRight()));
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		return searchAux(root, element);
	}

	private BSTNode<T> searchAux(BSTNode<T> node, T element) {
		BSTNode<T> result = new BSTNode<T>();
		if (!node.getData().equals(element)) {
			if (node.getData().compareTo(element) > 0) {  //elemento está à esquerda
				result = searchAux((BSTNode<T>) node.getLeft(), element);
			} else {
				result = searchAux((BSTNode<T>) node.getRight(), element);
			}
		} else {
			result = node;
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			BSTNode<T> newNode = new BSTNode<>();
			newNode.setData(element);
			root = newNode;
		} else {
			insertAux(root, element);
		}
	}

	private void insertAux(BSTNode<T> node, T element) {
		if (node.getData().compareTo(element) > 0) {  //elemento está à esquerda
			if (node.getLeft() == null) {
				BSTNode<T> newNode = new BSTNode<>();
				newNode.setData(element);
				node.setLeft(newNode);
				newNode.setParent(node);
			} else {
				insertAux((BSTNode<T>) node.getLeft(), element);
			}
		} else {
			if (node.getRight() == null) {
				BSTNode<T> newNode = new BSTNode<>();
				newNode.setData(element);
				node.setRight(newNode);
				newNode.setParent(node);
			} else {
				insertAux((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> result = null;
		if (!isEmpty()) {
			result = maximumAux(root);
		}
		return result;
	}

	private BSTNode<T> maximumAux(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (node.getRight() != null) {
			result = maximumAux((BSTNode<T>) node.getRight());
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> result = null;
		if (!isEmpty()) {
			result = minimumAux(root);
		}
		return result;
	}

	private BSTNode<T> minimumAux(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (node.getLeft() != null) {
			result = minimumAux((BSTNode<T>) node.getLeft());
		}
		return result;
	}

	private boolean contained(BSTNode<T> node, T element) {
		boolean result = false;
		if (!node.getData().equals(element)) {
			if (node.getData().compareTo(element) > 0) {  //elemento está à esquerda
				result = contained((BSTNode<T>) node.getLeft(), element);
			} else {
				result = contained((BSTNode<T>) node.getRight(), element);
			}
		} else {
			result = true;
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;
		if (element != null && contained(root, element)) {
			BSTNode<T> node = search(element);
			if (node.getRight().getData() != null) {
				result = minimumAux((BSTNode<T>) node.getRight());
			} else {
				BSTNode<T> aux = (BSTNode<T>) node.getParent();
				while (aux.getData() != null && element.compareTo(node.getData()) < 0) {
					aux = (BSTNode<T>) aux.getParent();
				}
				result = aux;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		if (element != null && contained(root, element)) {
			BSTNode<T> node = search(element);
			if (node.getLeft().getData() != null) {
				result = maximumAux((BSTNode<T>) node.getLeft());
			} else {
				BSTNode<T> aux = (BSTNode<T>) node.getParent();
				while (aux.getData() != null && element.compareTo(node.getData()) > 0) {
					aux = (BSTNode<T>) aux.getParent();
				}
				result = aux;
			}
		}
		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (node.getData() != null) {
			if (node.isLeaf()){
				node = new BSTNode<T>();
			} else if (!node.equals(root)) { // casos em que só tem um filho
				if (node.getLeft() != null && node.getRight() == null) { // se o filho é o nó à esquerda
					if (node.getLeft().getData() != null) {
						// não terminei
					}
				}
			}
		}
	}

	@Override
	public T[] preOrder() {
		return preOrderAux(root);
	}

	private T[] preOrderAux(BSTNode<T> node) {
		ArrayList<T> result = new ArrayList<>();
		if (node.getData() != null) {
			result.add(node.getData());
			preOrderAux((BSTNode<T>) node.getLeft());
			preOrderAux((BSTNode<T>) node.getRight());
		}
		return (T[]) result.toArray();
	}

	@Override
	public T[] order() {
		return orderAux(root);
	}

	private T[] orderAux(BSTNode<T> node) {
		ArrayList<T> result = new ArrayList<>();
		if (node.getData() != null) {
			orderAux((BSTNode<T>) node.getLeft());
			result.add(node.getData());
			orderAux((BSTNode<T>) node.getRight());
		}
		return (T[]) result.toArray();
	}

	@Override
	public T[] postOrder() {
		return postOrderAux(root);
	}

	private T[] postOrderAux(BSTNode<T> node) {
		ArrayList<T> result = new ArrayList<>();
		if (node.getData() != null) {
			preOrderAux((BSTNode<T>) node.getLeft());
			preOrderAux((BSTNode<T>) node.getRight());
			result.add(node.getData());
		}
		return (T[]) result.toArray();
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
