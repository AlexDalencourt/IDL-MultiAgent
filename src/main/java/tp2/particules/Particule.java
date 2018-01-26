package tp2.particules;

import java.awt.Color;
import java.awt.Graphics;

import tp2.ConstantParams;
import tp2.Logger;
import tp2.core.Agent;
import tp2.core.Environnement;

public class Particule extends Agent {

	private int id;
	
	private int pasX;

	private int pasY;
	
	private boolean collision;

	public Particule(int id, int posX, int posY, int pasX, int pasY, Environnement env) {
		super(posX,posY,env);
		this.id = id;
		this.pasX = pasX;
		this.pasY = pasY;
		collision = false;
	}

	@Override
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
		} else {
			env.applyTransition(this);
		}
	}

	@Override
	public void update() {
		this.posX = this.getNewPosX();
		this.posY = this.getNewPosY();
		if(env.getTorus()) {
			this.posX = env.calculateTorus(posX, ConstantParams.getGridSizeX());
			this.posY = env.calculateTorus(posY, ConstantParams.getGridSizeY());
		}
	}
	
	@Override
	public void drawAgent(Graphics g) {
		int pixelPosX = ConstantParams.getBoxSize() * posX;
		int pixelPosY = ConstantParams.getBoxSize() * posY;
		if(collision) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillOval(pixelPosX, pixelPosY, ConstantParams.getBoxSize(), ConstantParams.getBoxSize());
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
	
	@Override
	public String toString() {
		return String.format("Agent id : %s , Position : [%s,%s], Pas : [%s,%s], Collision %s", id, posX, posY, pasX, pasY, collision);
	}
}
