package multiAgent.avatar;

import java.awt.Graphics;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;

public abstract class CommonAgentBehavour extends Agent {
	
	protected static final int[][] enableMovement = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
	
	private int tick = 0;
	
	private int speed;
	
	public CommonAgentBehavour(int posX, int posY, Environnement env, int speed) {
		super(posX, posY, env);
		this.speed = speed;
	}

	protected boolean canDecide() {
		return ++tick % speed == 0;
	}

	protected void decreaseSpeed() {
		if(speed > 1) {
			speed--;
		}
	}

	protected void increaseSpeed() {
		speed++;
	}
	
	public abstract boolean canGoOn();

	@Override
	public void drawAgent(Graphics g) {
		int pixelPosX = ConstantParams.getBoxSize() * posX;
		int pixelPosY = ConstantParams.getBoxSize() * posY;
		g.fillRect(pixelPosX, pixelPosY, ConstantParams.getBoxSize(), ConstantParams.getBoxSize());
	}
}
