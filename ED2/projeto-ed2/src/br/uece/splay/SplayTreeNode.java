package br.uece.splay;

public class SplayTreeNode<T extends Comparable<T>>{
	
	private T value;				
	private SplayTreeNode<T> left;		
	private SplayTreeNode<T> right;		
	

	public SplayTreeNode(){
		this.left = null;
		this.right = null;
	}
	
	public SplayTreeNode(T value, SplayTreeNode<T> left, SplayTreeNode<T> right){
		
		this.value = value;
		this.left = left;
		this.right = right;
		
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public SplayTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(SplayTreeNode<T> left) {
		this.left = left;
	}

	public SplayTreeNode<T> getRight() {
		return right;
	}

	public void setRight(SplayTreeNode<T> right) {
		this.right = right;
	}
	
	
	
}