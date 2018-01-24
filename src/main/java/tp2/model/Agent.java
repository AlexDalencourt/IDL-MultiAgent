package tp2.model;

import tp2.Logger;

public class Agent {
	private int id;

	private Environnement env;

	private int posX;

	private int posY;

	private int pasX;

	private int pasY;
	
	private boolean collision;

	public Agent(int id, int posX, int posY, int pasX, int pasY, Environnement env) {
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.pasX = pasX;
		this.pasY = pasY;
		this.env = env;
		this.env.putAgent(this);
		collision = false;
	}

	public int getId() {
		return id;
	}

	public void decide() {
		boolean invertScale = false;
		if(env.checkOutOfBorders(getNewPosX(), getNewPosY())) {
			Logger.log(String.format("Agent %s out of board on position: [%s,%s] calculate with pas : [%s,%s]", id, getNewPosX(), getNewPosY(), pasX, pasY));
			invertScale = true;
		} else if (!env.isEmptyCellule(getNewPosX(), getNewPosY())) {
			Logger.log(String.format("Agent %s collision detect or out of board on position : [%s,%s] calculate with pas : [%s,%s]", id, getNewPosX(), getNewPosY(), pasX, pasY));
			invertScale = true;
		}
		if(invertScale) {
			pasX *= -1;
			pasY *= -1;
			collision = true;
			env.tickUpdate();
		} else {
			env.applyTransition(this);
		}
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

	public int getNewPosX() {
		return this.posX + this.pasX;
	}
	
	public int getNewPosY() {
		return this.posY + this.pasY;
	}

	public boolean getCollision() {
		return collision;
	}
	
	public void update() {
		this.posX = this.getNewPosX();
		this.posY = this.getNewPosY();
		if(env.getTorus()) {
			if(this.posX >= ConstantParams.getGridSizeX()) {
				this.posX = 0;
			}else if(this.posX < 0) {
				this.posX = ConstantParams.getGridSizeX() - 1;
			}
			if(this.posY >= ConstantParams.getGridSizeY()) {
				this.posY = 0;
			}else if (this.posY < 0) {
				this.posY = ConstantParams.getGridSizeY() - 1;
			}
		}
	}

	@Override
	public String toString() {
		return String.format("Agent id : %s , Position : [%s,%s], Pas : [%s,%s], Collision %s", id, posX, posY, pasX, pasY, collision);
	}
}
