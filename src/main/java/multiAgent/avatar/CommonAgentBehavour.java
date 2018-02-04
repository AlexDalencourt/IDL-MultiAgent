package multiAgent.avatar;

import java.awt.Graphics;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;

public abstract class CommonAgentBehavour extends Agent {
	
	public CommonAgentBehavour(int posX, int posY, Environnement env) {
		super(posX, posY, env);
	}

	public abstract boolean canGoOn();

	@Override
	public void drawAgent(Graphics g) {
		int pixelPosX = ConstantParams.getBoxSize() * posX;
		int pixelPosY = ConstantParams.getBoxSize() * posY;
		g.fillRect(pixelPosX, pixelPosY, ConstantParams.getBoxSize(), ConstantParams.getBoxSize());
	}
}
