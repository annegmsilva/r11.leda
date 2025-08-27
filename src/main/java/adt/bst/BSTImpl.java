package adt.bst;

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
		// recursivamente, verifica o right e o left e soma o maior enre os dois (se for nil, sera -1)
	}

	@Override
	public BSTNode<T> search(T element) {
		return searchAux(root, element);
	}

	private BSTNode<T> searchAux(BSTNode<T> node, T element) {
		BSTNode<T> result = node;
		if (!node.getData().equals(element)) {
			if (node.getData().compareTo(element) > 0) {  //elemento está à esquerda
				result = searchAux((BSTNode<T>) node.getLeft(), element);
			} else {
				result = searchAux((BSTNode<T>) node.getRight(), element);
			}
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

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
