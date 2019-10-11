package br.uece.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.uece.avl.TreeAVL;
import br.uece.structures.view.TreeViewer;

public class PanelTreeAVL extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container;
	private PanelSettings jpSettings;

	private GridBagConstraints gbc;

	private TreeAVL tree;
	private TreeViewer treeView;

	public PanelTreeAVL() {
		tree = new TreeAVL();
		container = new JPanel();

		jpSettings = new PanelSettings();
	
		// container.setBackground(Color.YELLOW);
		setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.7;
		gbc.weighty = 0.5;

		treeView = new TreeViewer(tree.getRoot());

		add(jpSettings, gbc);
		gbc.gridy = 1;
		add(container, gbc);
		jpSettings.getBtNew().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());

				jpSettings.getJtNumber().setText("");
				container.removeAll();
				tree.add(value);
				treeView = new TreeViewer(tree.getRoot());

				container.add(treeView.getGraphComponent());
				container.revalidate();
				container.repaint();
				jpSettings.getJtNumber().requestFocus();
			}
		});

		jpSettings.getBtDelete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int value = Integer.parseInt(jpSettings.getJtNumber().getText());
				tree.remove(value);

				treeView = new TreeViewer(tree.getRoot());

				container.removeAll();

				container.add(treeView.getGraphComponent());

				jpSettings.getJtNumber().setText("");
				container.revalidate();
				container.repaint();
				jpSettings.getJtNumber().requestFocus();

			}
		});
		
		jpSettings.getBtFind().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());

				if (tree == null) {
					JOptionPane.showMessageDialog(null, "Numero inexistente");
				} else {
					treeView = new TreeViewer(tree.getRoot(), value);
					container.removeAll();

					container.add(treeView.getGraphComponent());

				}
				container.revalidate();
				container.repaint();
				jpSettings.getJtNumber().requestFocus();

			}
		});
	}

}
