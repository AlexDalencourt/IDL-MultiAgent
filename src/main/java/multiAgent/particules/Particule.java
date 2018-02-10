package multiAgent.particules;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;
import multiAgent.particules.main.MainGUI;

public class Particule extends Agent {

	private int id;
	
	private int pasX;

	private int pasY;
	
	private boolean collision;

	private int tick;
	
	public Particule(int id, int posX, int posY, int pasX, int pasY, Environnement env) {
		super(posX,posY,env);
		this.id = id;
		this.pasX = pasX;
		this.pasY = pasY;
		collision = false;
	}

	@Override
	public void decide() {
		tick++;
		if(env.checkOutOfBorders(getNewPosX(), getNewPosY()) || !env.isEmptyCellule(getNewPosX(), getNewPosY())) {
			pasX *= -1;
			pasY *= -1;
			collision = true;
			if(ConstantParams.showTrace()) {
				MainGUI.getOutputStream().println(this.toString());
			}
			if(env.checkOutOfBorders(getNewPosX(), getNewPosY()) || !env.isEmptyCellule(getNewPosX(), getNewPosY())) {
				pasX *= -1;
				pasY *= -1;
			} else {
				env.applyTransition(this);
			}
		}else {
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
		return tick + ";" + super.toString();
	}
}
