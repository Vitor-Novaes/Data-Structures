package br.uece.splay;

public class SplayTree<T extends Comparable<T>> {

	private SplayTreeNode<T> root;

	public SplayTreeNode<T> getRoot() {
		return root;
	}

	public SplayTree() {
		root = null;
	}

	private SplayTreeNode<T> search(SplayTreeNode<T> x, T value) {
		if (x == null) {
			return x;
		}

		int cmp = value.compareTo(x.getValue());

		if (cmp < 0) {
			return search(x.getLeft(), value);
		} else if (cmp > 0) {
			return search(x.getRight(), value);
		} else {
			return x;
		}
	}

	public SplayTreeNode<T> search(T value) {
		return search(root, value);
	}

	private SplayTreeNode<T> findMin(SplayTreeNode<T> tree) {

		if (tree == null)
			return null;

		while (tree.getLeft() != null)
			tree = tree.getLeft();

		return tree;

	}

	public void insert(T value) {

		root = insert(value, root);

	}

	private SplayTreeNode<T> insert(T value, SplayTreeNode<T> node) {
		if (node == null) {
			return new SplayTreeNode<T>(value, null, null);
		} else {
			int comp = value.compareTo(node.getValue());

			if (comp == 0) {
				return new SplayTreeNode<T>(value, null, null);
			} else if (comp < 0) {
				node.setLeft(insert(value, node.getLeft()));
			} else if (comp > 0) {
				node.setRight(insert(value, node.getRight()));
			} else {
				;
			}

		}
		return node;

	}

	public void remove(T value) {
		root = remove(root, value);
	}

	private SplayTreeNode<T> remove(SplayTreeNode<T> node, T value) {
		if (node == null) {
			return node;
		} else {
			int comp = value.compareTo(node.getValue());

			if (comp < 0) {
				node.setLeft(remove(node.getLeft(), value));
			} else if (comp > 0) {
				node.setRight(remove(node.getRight(), value));
			} else if (node.getLeft() != null && node.getRight() != null) {
				node.setValue(findMin(node.getRight()).getValue());
				node.setRight(remove(node.getRight(), node.getValue()));
			} else {
				node = node.getLeft() != null ? node.getLeft() : node.getRight();
			}
			return node;
		}

	}

	public void print() {
		if (root != null) {
			print(root, root.getValue(), 0);
		} else {
			System.out.println("Árvore vazia.");
		}
	}

	private void print(SplayTreeNode<T> tree, T value, int direction) {

		if (tree != null) {
			if (direction == 0) {
				System.out.printf("%2d é raiz\n", tree.getValue());
			} else {
				System.out.printf("%2d está a %6s de %2d\n", tree.getValue(), direction == 1 ? "direita" : "esquerda", value);
			}

			print(tree.getLeft(), tree.getValue(), -1);
			print(tree.getRight(), tree.getValue(), 1);
		}
	}

	public void splay(T value) {
		root = splay(root, value);
	}

	private SplayTreeNode<T> splay(SplayTreeNode<T> tree, T value) {

		if (tree == null)
			return null;

		SplayTreeNode<T> N = new SplayTreeNode<T>();
		SplayTreeNode<T> left = N;
		SplayTreeNode<T> right = N;
		SplayTreeNode<T> aux;

		for (;;) {

			int cmp = value.compareTo(tree.getValue());

			if (cmp < 0) {
				if (tree.getLeft() == null)
					break;
				if (value.compareTo(tree.getLeft().getValue()) < 0) {
					aux = tree.getLeft();
					tree.setLeft(aux.getRight());
					aux.setRight(tree);
					tree = aux;
					if (tree.getLeft() == null) {
						break;
					}
				}
				right.setLeft(tree);
				right = tree;
				tree = tree.getLeft();
			} else if (cmp > 0) {

				if (tree.getRight() == null)
					break;
				if (value.compareTo(tree.getRight().getValue()) > 0) {
					aux = tree.getRight();
					tree.setRight(aux.getLeft());
					aux.setLeft(tree);
					tree = aux;
					if (tree.getRight() == null) {
						break;
					}
				}

				left.setRight(tree);
				left = tree;
				tree = tree.getRight();
			} else {
				break;
			}
		}

		left.setRight(tree.getLeft());
		right.setLeft(tree.getRight());
		tree.setLeft(N.getRight());
		tree.setRight(N.getLeft());

		return tree;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean contains(T x, SplayTreeNode<T> t) {
		if (isEmpty())
			return false;
		else if (t == null) {
			return false;
		} else {

			int comp = x.compareTo(t.getValue());
			if (comp == 0) {
				return true;
			} else if (comp < 0) {
				return contains(x, t.getLeft());
			} else if (comp > 0) {
				return contains(x, t.getRight());
			} else {
				return false;
			}
		}
	}
}
