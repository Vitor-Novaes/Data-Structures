package br.uece.structures.view;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import br.uece.structures.hashing.halfopen.Node;

public class ListViewer {

	private int NODE_SIZE = 25;
	mxGraph graph;
	mxGraphComponent graphComponent;
	Object parent;

	public Object drawTree(Node node, int value) {
		if (node == null) {
			return null;
		}

		Object vertex;
		if (node.getValue() == -1) {
			System.out.println("Entrou no -1");
			vertex = graph.insertVertex(parent, null, "", 0, 0, NODE_SIZE, NODE_SIZE, "fillColor=#F47373");
		} else if (node.getValue() == value) {
			vertex = graph.insertVertex(parent, null, node.getValue(), 0, 0, NODE_SIZE, NODE_SIZE,
					"shape=ellipse;perimeter=ellipsePerimeter;fillColor=#73F4B3");
		} else {
			vertex = graph.insertVertex(parent, null, node.getValue(), 0, 0, NODE_SIZE, NODE_SIZE,
					"shape=ellipse;perimeter=ellipsePerimeter");
		}
		Object childNextVertex = drawTree(node.getNext(), value);

		// recurse for right child

		if (childNextVertex != null) { // edge
			graph.insertEdge(parent, null, "", vertex, childNextVertex, "");
		}

		return vertex;
	}

	public void update(Node root, int value) {

		graph.getModel().beginUpdate();

		try {

			Object[] cells = graph.getChildCells(parent, true, false);
			graph.removeCells(cells, true);
			drawTree(root, value);

		} finally {
			graph.getModel().endUpdate();
		}
	}

	public ListViewer(Node root) {
		this(root, -2);
	}

	public ListViewer(Node root, int value) {

		// if (graph == null) {
		graph = new mxGraph();
		graph.setCellsEditable(false); // don't work
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
		// }
		this.update(root, value);

		mxIGraphLayout layout = new mxHierarchicalLayout(graph);
		layout.execute(graph.getDefaultParent());

		graphComponent = new mxGraphComponent(graph);

	}

	public mxGraphComponent getGraphComponent() {
		return graphComponent;
	}

}