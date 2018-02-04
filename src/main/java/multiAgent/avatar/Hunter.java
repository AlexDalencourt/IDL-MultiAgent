package multiAgent.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import multiAgent.ConstantParams;
import multiAgent.avatar.sma.SMA;
import multiAgent.core.Environnement;

public class Hunter extends CommonAgentBehavour {

	public Hunter(int posX, int posY, Environnement env) {
		super(posX, posY, env);
	}

	@Override
	public boolean canGoOn() {
		return true;
	}

	private int nextX, nextY;
	
	@Override
	public void decide() {
		int[][] dijkstra = ((SMA) env.getSMA()).getDijkstra();
		int currMin = dijkstra[posX][posY];
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 1; j++) {
				if(dijkstra[posX + i][posY + j] != -1 
						&& dijkstra[posX + i][posY + j] < currMin) {
					nextX = posX + i;
					nextY = posY + j;
					currMin = dijkstra[nextX][nextY];
				}
			}
		}
		while (nextX == posX && nextY == posY && dijkstra[nextX][nextY] == -1) {
			nextX += ConstantParams.getRandom().nextInt(3) - 1;
			nextY += ConstantParams.getRandom().nextInt(3) - 1;
		}
		env.applyTransition(this);
	}

	@Override
	public void update() {
		posX = nextX;
		posY = nextY;
	}

	@Override
	public int getNewPosX() {
		return nextX;
	}

	@Override
	public int getNewPosY() {
		return nextY;
	}

	@Override
	public void drawAgent(Graphics g) {
		g.setColor(Color.BLUE);
		super.drawAgent(g);
	}
}
