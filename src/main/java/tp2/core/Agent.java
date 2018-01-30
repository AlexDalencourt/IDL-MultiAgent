package tp2.core;

import java.awt.Graphics;

public abstract class Agent {

	protected Environnement env;

	protected int posX;

	protected int posY;

	public Agent(int posX, int posY, Environnement env) {
		this.posX = posX;
		this.posY = posY;
		this.env = env;
		this.env.putAgent(this);
	}

	public abstract void decide();

	public abstract void update();
	
	public abstract void drawAgent(Graphics g);
	
	public abstract int getNewPosX();
	
	public abstract int getNewPosY();

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public String toString() {
		return posX + ";" + posY;
	}
}
