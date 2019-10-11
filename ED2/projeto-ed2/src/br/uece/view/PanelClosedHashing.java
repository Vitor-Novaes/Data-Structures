package br.uece.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.uece.structures.hashing.closed.ClosedHashingList;
import br.uece.structures.hashing.halfopen.Node;
import br.uece.structures.view.ListViewer;

public class PanelClosedHashing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container;
	private PanelSettings jpSettings;
	private ClosedHashingList hashing;

	private ListViewer[] listView;

	public PanelClosedHashing() {
		jpSettings = new PanelSettings();
		hashing = new ClosedHashingList();
		container = new JPanel();
		listView = new ListViewer[ClosedHashingList.SIZE];
		// add(jpSettings);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 0, 10, 0);

		add(jpSettings, c);
		GridBagConstraints c1 = new GridBagConstraints();

		c1.fill = GridBagConstraints.HORIZONTAL;
		// c1.ipady = 60;
		c1.weightx = 0.0;
		c1.gridwidth = 3;
		c1.gridx = 0;
		c1.gridy = 1;
		c1.insets = new Insets(50, 0, 10, 0);
		// add(jpSettings, c);
		add(container, c1);

		// add(myTreeViewer.getGraphComponent(), c);

		// add(myTreeViewer.getGraphComponent());
	

		jpSettings.getBtNew().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());
				jpSettings.getJtNumber().setText("");
				container.removeAll();
				hashing.add(value);
				for (int i = 0; i < hashing.getVetor().length; i++) {
					if (hashing.getVetor()[i] != null) {
						listView[i] = new ListViewer(new Node(hashing.getVetor()[i]));
					} else {
						listView[i] = new ListViewer(new Node(-1));
					}
				}

				for (ListViewer listViewer2 : listView) {
					if (listViewer2 != null) {
						container.add(listViewer2.getGraphComponent());
					}
				}
				container.revalidate();
				container.repaint();
				jpSettings.getJtNumber().requestFocus();
			}
		});

		jpSettings.getBtFind().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());
				jpSettings.getJtNumber().setText("");
//				container.removeAll();
				int posicao = hashing.busca(value);

				if (posicao == -1 || hashing.getVetor()[posicao] != value	) {
					JOptionPane.showMessageDialog(null, "Numero inexistente");
				} else {
					listView[posicao] = new ListViewer(new Node(hashing.getVetor()[posicao]), value);
					container.removeAll();
					for (ListViewer listViewer : listView) {
						if (listViewer != null) {
							container.add(listViewer.getGraphComponent());
						}
					}
				}

				jpSettings.getJtNumber().setText("");
				jpSettings.getJtNumber().requestFocus();
				container.revalidate();
				container.repaint();

			}
		});

		jpSettings.getBtDelete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());
				hashing.remove(value);
				
				for (int i = 0; i < hashing.getVetor().length; i++) {
					if (hashing.getVetor()[i] != null) {
						listView[i] = new ListViewer(new Node(hashing.getVetor()[i]));
					} else {
						listView[i] = new ListViewer(new Node(-1));
					}
				}
				container.removeAll();

				for (ListViewer treeViewer : listView) {
					if (treeViewer != null) {
						container.add(treeViewer.getGraphComponent());
					}
				}

				jpSettings.getJtNumber().setText("");
				container.revalidate();
				container.repaint();
				jpSettings.getJtNumber().requestFocus();

			}
		});
	}

}
