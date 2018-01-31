package multiAgent.view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import multiAgent.ConstantParams;
import multiAgent.core.Environnement;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8456560429229699542L;

	private Environnement env;
	
	private Displayer displayer;
	
	public MainFrame (Environnement env) {
		this.env = env;
		
		init();
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void init() {
		// 15 est la large ou hauteur en fonction de l'axe de l'ascenseur
		int sizeX = ConstantParams.getCanvasSizeX() + 15;
		int sizeY = ConstantParams.getCanvasSizeY() + 15;
		displayer = new Displayer(env);
		JScrollPane scrollPanel = new JScrollPane(displayer);
		setContentPane(scrollPanel);
		this.setSize(new Dimension(sizeX,sizeY));
	}
	
	public void addEventKeyListener (KeyListener listener) {
		this.addKeyListener(listener);
	}
}
