package tp2.core;

import java.awt.Graphics;

public abstract class Agent {
	protected int id;

	protected Environnement env;

	protected int posX;

	protected int posY;

	public Agent(int id, int posX, int posY, Environnement env) {
		this.id = id;
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
	
	public int getId() {
		return id;
	}

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
		return String.format("Agent id : %s , Position : [%s,%s]", id, posX, posY);
	}
}
