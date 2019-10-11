package br.uece.structures.view;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import br.uece.avl.NodeAVL;

public class TreeViewer {

	private int NODE_SIZE = 25;
	mxGraph graph;
	mxGraphComponent graphComponent;
	Object parent;


	public Object drawTree(NodeAVL root, int value) {
		if (root == null) {
			return null;
		}

		Object rootVertex;
		if (root.getValue() == -1){
			rootVertex = graph.insertVertex(parent, null, "", 0, 0, NODE_SIZE, NODE_SIZE, "fillColor=#F47373");
		}else	if (root.getValue() == value){
			rootVertex = graph.insertVertex(parent, null, root.getValue(), 0, 0, NODE_SIZE, NODE_SIZE,
					"shape=ellipse;perimeter=ellipsePerimeter;fillColor=#73F4B3");
		}else{
			rootVertex = graph.insertVertex(parent, null, root.getValue(), 0, 0, NODE_SIZE, NODE_SIZE,
					"shape=ellipse;perimeter=ellipsePerimeter");
			}
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
	public void update(NodeAVL root, int value) {

		graph.getModel().beginUpdate();

		try {

			Object[] cells = graph.getChildCells(parent, true, false);
			graph.removeCells(cells, true);
			drawTree(root, value);

		} finally {
			graph.getModel().endUpdate();
		}
	}

	public TreeViewer(NodeAVL root) {
		this(root, -2);
	}

	public TreeViewer(NodeAVL root, int value) {
//		if (graph == null) {
			graph = new mxGraph();
			graph.setCellsEditable(false); 
			graph.setAllowDanglingEdges(false);
			graph.setAllowLoops(false);
			graph.setCellsDeletable(false);
			graph.setCellsCloneable(false);
			graph.setCellsDisconnectable(false);
			graph.setDropEnabled(false);
			graph.setSplitEnabled(false);
			graph.setCellsBendable(false);
			graph.setCellsEditable(false);
			graph.setCellsResizable(false);

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