package multiAgent.avatar;

import java.awt.Color;
import java.awt.Graphics;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;

public class Defender extends CommonAgentBehavour{

	private int life;
	
	public Defender(int posX, int posY, Environnement env) {
		super(posX, posY, env, 0);
		life = ConstantParams.getGridSizeX() + ConstantParams.getGridSizeY();
	}

	@Override
	public boolean canGoOn() {
		return true;
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
	public void specialActionWhenErasedByAvatar(Agent agent) {
		((Avatar) agent).setInivisible(ConstantParams.getAvatarInvisibleTime());
	}
	
	@Override
	public void drawAgent(Graphics g) {
		decide();
		g.setColor(Color.GREEN);
		super.drawAgent(g);
	}
}
