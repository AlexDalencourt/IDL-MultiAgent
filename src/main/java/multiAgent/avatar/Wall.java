package multiAgent.avatar;

import java.awt.Color;
import java.awt.Graphics;

import multiAgent.ConstantParams;
import multiAgent.core.Environnement;

public class Wall extends CommonAgentBehavour {

	public Wall(int posX, int posY, Environnement env) {
		super(posX, posY, env, 0);
	}

	@Override
	public void decide() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void drawAgent(Graphics g) {
		int pixelPosX = ConstantParams.getBoxSize() * posX;
		int pixelPosY = ConstantParams.getBoxSize() * posY;
		g.setColor(Color.GRAY);
		g.fillRect(pixelPosX, pixelPosY, ConstantParams.getBoxSize(), ConstantParams.getBoxSize());
	}

	@Override
	public boolean canGoOn() {
		return false;
	}

	@Override
	public int getNewPosX() {
		return posX;
	}

	@Override
	public int getNewPosY() {
		return posY;
	}

}
