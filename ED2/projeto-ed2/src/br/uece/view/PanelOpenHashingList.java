package br.uece.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.uece.structures.hashing.halfopen.Node;
import br.uece.structures.hashing.open.OpenHashingList;
import br.uece.structures.view.ListViewer;

public class PanelOpenHashingList extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container;
	private PanelSettings jpSettings;
	private NumberFormat nf;
	private OpenHashingList hashing;

	private ListViewer[] listView;

	public PanelOpenHashingList() {
		nf = new DecimalFormat("#,####0.0000", new DecimalFormatSymbols(new Locale("pt", "BR")));
		jpSettings = new PanelSettings();
		hashing = new OpenHashingList();
		container = new JPanel();
		listView = new ListViewer[OpenHashingList.SIZE];
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
		OpenHashingList op = new OpenHashingList();
		int[] s = { 0, 1, 85, 6, 36, 46, 89, 112, 44 };
		for (int i = 0; i < s.length; i++) {
			op.add(s[i]);
		}

		for (int i = 0; i < op.getVetor().length; i++) {
			if (op.getVetor()[i] != null) {
				// listView[i] = new ListViewer2(op.getVetor()[i]);
			} else {
				// listView[i] = new ListViewer2(new Node(-1));
			}
		}
		for (ListViewer listViewer2 : listView) {
			if (listViewer2 != null) {
				container.add(listViewer2.getGraphComponent());
			}
		}
		jpSettings.getBtNew().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());
				jpSettings.getJtNumber().setText("");
				container.removeAll();
				hashing.add(value);
				for (int i = 0; i < hashing.getVetor().length; i++) {
					if (hashing.getVetor()[i] != null) {
						listView[i] = new ListViewer(hashing.getVetor()[i]);
					} else {
						System.out.println("manout -1");
						listView[i] = new ListViewer(new Node(-1));
					}
				}

				for (ListViewer listViewer2 : listView) {
					if (listViewer2 != null) {
						container.add(listViewer2.getGraphComponent());
					}
				}
				jpSettings.getJtLoadFactor().setText(nf.format(hashing.fatorCarga()));
				container.revalidate();
				container.repaint();

			}
		});

		jpSettings.getBtFind().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());
				jpSettings.getJtNumber().setText("");
				container.removeAll();
				int posicao = hashing.busca(value);

				if (posicao == -1) {
					JOptionPane.showMessageDialog(null, "Numero inexistente");
				} else {
					listView[posicao] = new ListViewer(hashing.getVetor()[posicao], value);
					container.removeAll();
					for (ListViewer listViewer : listView) {
						if (listViewer != null) {
							container.add(listViewer.getGraphComponent());
						}
					}
				}

				jpSettings.getJtNumber().setText("");
				jpSettings.getJtLoadFactor().setText(nf.format(hashing.fatorCarga()));
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
						listView[i] = new ListViewer(hashing.getVetor()[i]);
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
				jpSettings.getJtLoadFactor().setText(nf.format(hashing.fatorCarga()));
				container.revalidate();
				container.repaint();

			}
		});
	}
}
