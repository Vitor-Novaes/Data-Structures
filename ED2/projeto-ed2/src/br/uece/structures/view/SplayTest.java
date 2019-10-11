package br.uece.structures.view;

import br.uece.splay.SplayTree;

public class SplayTest {
	public static void main(String[] args) {
		SplayTree<Integer> tree = new SplayTree<>();
		
		int[] s = {27, 51, 18, 16, 28, 2, 52,  15,  8, 12, 21, 9, 53, 12, 16, 21, 19, 63, 7, 24, 97};
		for (int i = 0; i < s.length; i++) {
			tree.insert(s[i]);
		}
		tree.print();
		System.out.println("------------\n\n");
		int[] b ={15, 12, 18, 15, 52, 16, 12, 18};
		for (int i = 0; i < b.length; i++) {
			tree.splay(b[i]);
		}
		
		tree.print();
	}

}
