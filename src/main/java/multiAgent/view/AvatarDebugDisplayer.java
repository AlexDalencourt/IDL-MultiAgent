package multiAgent.view;

import java.awt.Color;
import java.awt.Graphics;

import multiAgent.ConstantParams;
import multiAgent.avatar.sma.SMA;
import multiAgent.core.Environnement;

public class AvatarDebugDisplayer extends Displayer{

	private static final long serialVersionUID = -264000027543596747L;

	private Environnement env;

	private boolean disjkstraIsCalculate = false;
	
	public AvatarDebugDisplayer(Environnement env) {
		super(env);
		this.env = env;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(disjkstraIsCalculate) {
			g.setColor(Color.BLACK);
			int[][] dijkstra = ((SMA)env.getSMA()).getDijkstra();
			for(int x = 0; x < dijkstra.length; x++) {
				for(int y = 0; y < dijkstra[x].length; y++) {
					int pixelPosX = ConstantParams.getBoxSize() * x + 2;
					int pixelPosY = ConstantParams.getBoxSize() * y + ConstantParams.getBoxSize() -2;
					g.drawString(String.valueOf(dijkstra[x][y]), pixelPosX, pixelPosY);
				}
			}
		}
	}
	
	public void setDisjkstraIsCalculate(boolean disjkstraIsCalculate) {
		this.disjkstraIsCalculate = disjkstraIsCalculate;
	}
}
