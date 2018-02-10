package multiAgent.particules;

import java.awt.Color;
import java.awt.Graphics;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;
import multiAgent.particules.main.MainGUI2;

public class Particule2 extends Agent {

	private int id;
	
	private int pasX;

	private int pasY;
	
	private boolean collision;
	
	private int tick;

	public Particule2(int id, int posX, int posY, int pasX, int pasY, Environnement env) {
		super(posX,posY,env);
		this.id = id;
		this.pasX = pasX;
		this.pasY = pasY;
		collision = false;
	}

	@Override
	public void decide() {
		tick++;
		boolean invertPos = false;
		boolean refreshInvert = true;
		if(env.checkOutOfBorders(getNewPosX(), getNewPosY())) {
			pasX *= -1;
			pasY *= -1;
			invertPos = true;
		}
		if(!env.isEmptyCellule(getNewPosX(), getNewPosY())) {
			Particule2 target = (Particule2) env.getCell(getNewPosX(), getNewPosY());
			int savePasX = pasX;
			int savePasY = pasY;
			pasX = target.getPasX();
			pasY = target.getPasY();
			target.setPasX(savePasX);
			target.setPasY(savePasY);
			invertPos = !invertPos;
			refreshInvert = invertPos;
			collision = true;
		}
		if(invertPos) {
			if (env.checkOutOfBorders(getNewPosX(), getNewPosY())) {
				pasX *= -1;
				pasY *= -1;
				refreshInvert = false;
			} else if (!env.isEmptyCellule(getNewPosX(), getNewPosY())) {
				Particule2 target = (Particule2) env.getCell(getNewPosX(), getNewPosY());
				int savePasX = pasX;
				int savePasY = pasY;
				pasX = target.getPasX();
				pasY = target.getPasY();
				target.setPasX(savePasX);
				target.setPasY(savePasY);
				refreshInvert = false;
				collision = true;
			}
		}
		if(ConstantParams.showTrace() && (invertPos || !refreshInvert)) {
			MainGUI2.getOutputStream().println(this.toString());
		}
		if(refreshInvert) {
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
	
	public int getPasX() {
		return this.pasX;
	}
	
	public int getPasY() {
		return this.pasY;
	}
	
	public void setPasX(int pasX) {
		this.pasX = pasX;
	}
	
	public void setPasY(int pasY) {
		this.pasY = pasY;
	}

	public boolean getCollision() {
		return collision;
	}
	
	@Override
	public String toString() {
		return tick + ";" + super.toString();
	}
}
