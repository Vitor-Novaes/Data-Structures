package br.uece.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelSettings extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jlNumber;
	private JLabel jlLoadFactor; // Fator de carga

	private JTextField jtNumber;
	private JTextField jtLoadFactor;

	private JButton btFind;
	private JButton btNew;
	private JButton btDelete;
	private JButton btInfo;

	private GridBagConstraints gbConstraints;

	public PanelSettings() {
		jlNumber = new JLabel("Numero");
		jlLoadFactor = new JLabel("Fator de Carga");

		jtNumber = new JTextField();
		jtLoadFactor = new JTextField();
		jtLoadFactor.setEditable(false);
		jtLoadFactor.setBackground(Color.WHITE);

		btFind = new JButton("Pesquisar");
		btNew = new JButton("Novo");
		btDelete = new JButton("Delete");
		btInfo = new JButton("Informações");

		setLayout(new GridBagLayout());

		jlNumber.setLabelFor(jtNumber);
		gbConstraints = new GridBagConstraints();

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.weightx = 0.5;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		gbConstraints.insets = new Insets(0, 5, 0, 5);
		add(jlNumber, gbConstraints);
		gbConstraints.gridx = 1;
		add(jlLoadFactor, gbConstraints);
		gbConstraints.gridx = 2;
		add(btNew, gbConstraints);
		gbConstraints.gridx = 3;
		add(btFind, gbConstraints);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 1;
		gbConstraints.weightx = 1;
		add(jtNumber, gbConstraints);
		gbConstraints.gridx = 1;
		add(jtLoadFactor, gbConstraints);
		gbConstraints.gridx = 2;
		add(btDelete, gbConstraints);
		gbConstraints.gridx = 3;
		add(btInfo, gbConstraints);

		btInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"ED2 - Equipe : Vitor Novaes\n e Eduardo Patrick\n");

			}
		});
	}

	public JTextField getJtNumber() {
		return jtNumber;
	}

	public void setJtNumber(JTextField jtNumber) {
		this.jtNumber = jtNumber;
	}

	public JTextField getJtLoadFactor() {
		return jtLoadFactor;
	}

	public void setJtLoadFactor(JTextField jtLoadFactor) {
		this.jtLoadFactor = jtLoadFactor;
	}

	public JButton getBtFind() {
		return btFind;
	}

	public void setBtFind(JButton btFind) {
		this.btFind = btFind;
	}

	public JButton getBtNew() {
		return btNew;
	}

	public void setBtNew(JButton btNew) {
		this.btNew = btNew;
	}

	public JButton getBtDelete() {
		return btDelete;
	}

	public void setBtDelete(JButton btDelete) {
		this.btDelete = btDelete;
	}

	public void setJlLoadFactor(JLabel jlLoadFactor) {
		this.jlLoadFactor = jlLoadFactor;
	}

}
