package br.uece.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.uece.avl.NodeAVL;
import br.uece.structures.hashing.open.OpenHashingAvl;
import br.uece.structures.view.TreeViewer;

public class PanelOpenHashingAVL extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container;
	private PanelSettings jpSettings;

	private GridBagConstraints gbc;

	private NumberFormat nf;

	private OpenHashingAvl hashing;
	private TreeViewer[] listView;

	public PanelOpenHashingAVL() {
		hashing = new OpenHashingAvl();
		container = new JPanel();

		jpSettings = new PanelSettings();
		jpSettings.getJtLoadFactor().setText("Fator de balanceamento");

//		container.setBackground(Color.YELLOW);
		setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.7;
		gbc.weighty = 0.5;

		listView = new TreeViewer[hashing.SIZE];
		
		nf = new DecimalFormat("#,####0.0000", new DecimalFormatSymbols(new Locale("pt", "BR")));

		add(jpSettings, gbc);
		gbc.gridy = 1;
		add(container, gbc);
		jpSettings.getBtNew().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());

				jpSettings.getJtNumber().setText("");
				container.removeAll();
				hashing.add(value);
				for (int i = 0; i < hashing.SIZE; i++) {
					if (hashing.getVetor()[i] != null) {
						listView[i] = new TreeViewer(hashing.getVetor()[i].getRoot());
						hashing.getVetor()[i].print();
					} else {
						listView[i] = new TreeViewer(new NodeAVL(-1));
					}
				}
				
				for (TreeViewer treeViewer : listView) {
					if (treeViewer != null) {
						container.add(treeViewer.getGraphComponent());
					}
				}
				jpSettings.getJtLoadFactor().setText(nf.format(hashing.fatorBalanceamento()));
				container.revalidate();
				container.repaint();
				jpSettings.setJlLoadFactor(new JLabel("Fator Balanceamento"));
				jpSettings.getJtNumber().requestFocus();

			}
		});

		jpSettings.getBtDelete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int value = Integer.parseInt(jpSettings.getJtNumber().getText());
				hashing.remove(value);
				for (int i = 0; i < hashing.SIZE; i++) {
					if (hashing.getVetor()[i] != null) {
						listView[i] = new TreeViewer(hashing.getVetor()[i].getRoot());
						hashing.getVetor()[i].print();
					} else {
						listView[i] = new TreeViewer(new NodeAVL(-1));
					}
				}
				container.removeAll();

				for (TreeViewer treeViewer : listView) {
					if (treeViewer != null) {
						container.add(treeViewer.getGraphComponent());
					}
				}

				jpSettings.getJtNumber().setText("");
				jpSettings.getJtLoadFactor().setText(nf.format(hashing.fatorBalanceamento()));
				container.revalidate();
				container.repaint();
				jpSettings.setJlLoadFactor(new JLabel("Fator Balanceamento"));
				jpSettings.getJtNumber().requestFocus();

			}
		});
		jpSettings.getBtFind().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jpSettings.getJtNumber().getText());
				int posicao = hashing.busca(value);
				if (hashing.getVetor()[posicao] == null) {
					JOptionPane.showMessageDialog(null, "Numero inexistente");
				} else {
					listView[posicao] = new TreeViewer(hashing.getVetor()[posicao].getRoot(), value);
					container.removeAll();

					for (TreeViewer treeViewer : listView) {
						if (treeViewer != null) {
							container.add(treeViewer.getGraphComponent());
						}
					}
				}
				jpSettings.getJtNumber().setText("");
				jpSettings.getJtLoadFactor().setText(nf.format(hashing.fatorBalanceamento()));
				container.revalidate();
				container.repaint();
				jpSettings.setJlLoadFactor(new JLabel("Fator Balanceamento"));
				jpSettings.getJtNumber().requestFocus();

			}
		});
	}

}
