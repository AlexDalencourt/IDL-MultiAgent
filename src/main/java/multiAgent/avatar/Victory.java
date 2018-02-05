package multiAgent.avatar;

import java.awt.Color;
import java.awt.Graphics;

import multiAgent.ConstantParams;
import multiAgent.avatar.sma.SMA;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;

public class Victory extends CommonAgentBehavour{

	private int life;
	
	public Victory(int posX, int posY, Environnement env) {
		super(posX, posY, env, 0);
		life = ConstantParams.getGridSizeX() + ConstantParams.getCanvasSizeY();
	}
	
	@Override
	public boolean canGoOn() {
		return true;
	}

	@Override
	public void specialActionWhenErasedByAvatar(Agent agent) {
		((SMA)env.getSMA()).win();
	}

	@Override
	public void decide() {
		if(--life == 0) {
			env.getEnvironnement()[posX][posY] = null;
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public int getNewPosX() {
		return posX;
	}

	@Override
	public int getNewPosY() {
		return posY;
	}

	@Override
	public void drawAgent(Graphics g) {
		decide();
		g.setColor(Color.YELLOW);
		super.drawAgent(g);
	}
}
