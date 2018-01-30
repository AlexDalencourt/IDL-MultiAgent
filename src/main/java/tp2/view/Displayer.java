package tp2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import tp2.ConstantParams;
import tp2.core.Environnement;

public class Displayer extends JPanel implements Observer {

	private static final long serialVersionUID = -5921995227209226746L;

	private Environnement env;
	
	private int frameSizeX;
	
	private int frameSizeY;

	public Displayer(Environnement env) {
		this.env = env;
		this.env.addObserver(this);
		this.frameSizeX = ConstantParams.getGridSizeX() * ConstantParams.getBoxSize();
		this.frameSizeY = ConstantParams.getGridSizeY() * ConstantParams.getBoxSize();
		this.setPreferredSize(new Dimension(frameSizeX,frameSizeY));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(env.getBackground());
		g.setColor(Color.BLACK);
		if (ConstantParams.showGrid()) {
			for (int i = ConstantParams.getBoxSize(); i < frameSizeX; i += ConstantParams.getBoxSize()) {
				g.drawLine(i, 0, i, frameSizeY);
			}
			for (int i = ConstantParams.getBoxSize(); i < frameSizeY; i += ConstantParams.getBoxSize()) {
				g.drawLine(0, i, frameSizeX, i);
			}
		}
		for (int i = 0; i < env.getEnvironnement().length; i++) {
			for (int j = 0; j < env.getEnvironnement()[i].length; j++) {
				if (env.getEnvironnement()[i][j] != null) {
					env.getEnvironnement()[i][j].drawAgent(g);
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
