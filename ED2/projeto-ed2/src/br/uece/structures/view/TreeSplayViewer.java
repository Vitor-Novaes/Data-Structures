package br.uece.structures.view;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import br.uece.splay.SplayTreeNode;

public class TreeSplayViewer<T extends Comparable<T>> {


	private int NODE_SIZE = 25;
	mxGraph graph;
	mxGraphComponent graphComponent;
	Object parent;


	public Object drawTree(SplayTreeNode<T> root, T value) {
		if (root == null) {
			return null;
		}

		Object rootVertex;
//		int rootValue = value.compareTo(root.getValue());
//		if (rootValue == 0){
//			rootVertex = graph.insertVertex(parent, null, root.getValue(), 0, 0, NODE_SIZE, NODE_SIZE,
//					"shape=ellipse;perimeter=ellipsePerimeter;fillColor=#73F4B3");
//		}else{
			rootVertex = graph.insertVertex(parent, null, root.getValue(), 0, 0, NODE_SIZE, NODE_SIZE,
					"shape=ellipse;perimeter=ellipsePerimeter");
//			}
		Object leftChildVertex = drawTree(root.getLeft(), value);


		if (leftChildVertex != null) { // edge
			graph.insertEdge(parent, null, "", rootVertex, leftChildVertex, "");
		}


		Object rightChildVertex = drawTree(root.getRight(), value);

		if (rightChildVertex != null) {// edge
			graph.insertEdge(parent, null, "", rootVertex, rightChildVertex, "");
		}

		return rootVertex;

	}
	public void update(SplayTreeNode<T> root, T value) {

		graph.getModel().beginUpdate();

		try {

			Object[] cells = graph.getChildCells(parent, true, false);
			graph.removeCells(cells, true);
			drawTree(root, value);

		} finally {
			graph.getModel().endUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	public TreeSplayViewer(SplayTreeNode<T> root) {
		
		this(root, (T)new Integer(-2));
	}

	public TreeSplayViewer(SplayTreeNode<T> root, T value) {
//		if (graph == null) {
			graph = new mxGraph();
//		

			// graph.setCellStyle(arg0)
			parent = graph.getDefaultParent();
//		}		
		this.update(root, value);

		mxIGraphLayout layout = new mxHierarchicalLayout(graph);
		layout.execute(graph.getDefaultParent());

		graphComponent = new mxGraphComponent(graph);

	}

	public mxGraphComponent getGraphComponent() {
		return graphComponent;
	}

}
