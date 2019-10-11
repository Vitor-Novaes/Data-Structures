package br.uece.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.uece.splay.SplayTree;
import br.uece.structures.view.TreeSplayViewer;

public class PanelTreeSplay extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private PanelSettings jpSettings;

	private GridBagConstraints gbc;

	private SplayTree<Integer> tree;
	private TreeSplayViewer<Integer> listView;

	public PanelTreeSplay() {
		tree = new SplayTree<>();
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

		listView = new TreeSplayViewer<>(tree.getRoot());

		add(jpSettings, gbc);
		gbc.gridy = 1;
		add(container, gbc);
		jpSettings.getBtNew().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());

				jpSettings.getJtNumber().setText("");
				container.removeAll();
				tree.insert(value);
				listView = new TreeSplayViewer<>(tree.getRoot());

				container.add(listView.getGraphComponent());
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

				listView = new TreeSplayViewer<>(tree.getRoot());

				container.removeAll();

				container.add(listView.getGraphComponent());

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
//					listView = null;
//					tree.search(value);
					tree.splay(value);
					tree.print();
					listView = new TreeSplayViewer<>(tree.getRoot());
					container.removeAll();

					container.add(listView.getGraphComponent());

				}
				container.revalidate();
				container.repaint();
				jpSettings.getJtNumber().setText("");
				jpSettings.getJtNumber().requestFocus();

			}
		});
	}


}
