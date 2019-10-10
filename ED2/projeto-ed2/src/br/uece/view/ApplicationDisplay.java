package br.uece.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ApplicationDisplay extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem miOpenHashingList;
	private JMenuItem miOpenHashingAVL;
	private JMenuItem miClosedHashing;
	private JMenuItem miHalfOpenHashingAVL;
	private JMenuItem miHalfOpenHashingVector;
	private JMenuItem miTreeAvl;
	private JMenuItem miTreeSplay;
	
//	private PanelSettings jpSettings;
	private JPanel panelOpenHashingList;
	private JPanel panelOpenHashingAVL;
	private JPanel panelClosedHashing;
	private JPanel panelHalfOpenHashingAVL;
	private JPanel panelHalfOpenHashingVector;
	private JPanel panelTreeAVl;
	private JPanel panelTreeSplay;
	
	
	public ApplicationDisplay() {
		initComponents();
		
		menu.add(miOpenHashingList);
		menu.add(miOpenHashingAVL);
		menu.addSeparator();
		menu.add(miClosedHashing);
		menu.add(miHalfOpenHashingAVL);
		menu.add(miHalfOpenHashingVector);
		menu.addSeparator();
		menu.add(miTreeAvl);
		menu.add(miTreeSplay);
		menuBar.add(menu);
		setJMenuBar(menuBar);


		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(500, 500));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}
	
	public void initComponents() {
		panelOpenHashingAVL = new PanelOpenHashingAVL();
		panelOpenHashingList = new PanelOpenHashingList();
		panelClosedHashing = new PanelClosedHashing();
		panelHalfOpenHashingAVL = new PanelHalfOpenHashingAVL();
		panelHalfOpenHashingVector = new PanelHalfOpenHashingVector();
		panelTreeAVl = new PanelTreeAVL();
		panelTreeSplay = new PanelTreeSplay();
//		
		menuBar = new JMenuBar();
		menuBar.setAlignmentX(LEFT_ALIGNMENT);
		menuBar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		menu = new JMenu("Hashings");
		miOpenHashingList = new JMenuItem("Hashing aberta com lista");
		miOpenHashingAVL = new JMenuItem("Hashing aberta com AVL");
		miClosedHashing = new JMenuItem("Hashing fechada");
		miHalfOpenHashingAVL = new JMenuItem("Hashing meio aberta com AVL");
		miOpenHashingList.getAccessibleContext().setAccessibleDescription(
		        "Implementação de Hashing usando Lista Encadeada.");
		miHalfOpenHashingVector = new JMenuItem("Hashing meio aberta com vetor");
		miTreeAvl = new JMenuItem("Arvore AVL");
		miTreeSplay = new JMenuItem("Arvore SPLAY");
		miOpenHashingAVL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
		
				getContentPane().add(panelOpenHashingAVL);
				revalidate();				
			}
		});
		miOpenHashingList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				panelOpenHashingList.setMaximumSize(panelOpenHashingList.getPreferredSize());
//				setContentPane(panelOpenHashingList, BorderLayout.CENTER);
				getContentPane().removeAll();
				getContentPane().add(panelOpenHashingList, BorderLayout.NORTH);
				revalidate();
			}
		});
		miClosedHashing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setContentPane(panelClosedHashing);
				revalidate();
			}
		});
		miHalfOpenHashingAVL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setContentPane(panelHalfOpenHashingAVL);
				
				revalidate();
			}
		});
		miHalfOpenHashingVector.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setContentPane(panelHalfOpenHashingVector);
				revalidate();	
			}
		});
		miTreeAvl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setContentPane(panelTreeAVl);
				revalidate();
				
			}
		});
		
		miTreeSplay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setContentPane(panelTreeSplay);
				revalidate();
			}
		});
	}
	
	public static void main(String[] args) {
		new ApplicationDisplay();
	}

}
