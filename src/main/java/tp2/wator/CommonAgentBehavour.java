package tp2.wator;

import java.awt.Color;
import java.awt.Graphics;

import tp2.ConstantParams;
import tp2.core.Agent;
import tp2.core.Environnement;

public abstract class CommonAgentBehavour extends Agent {
	
	protected long breed;
	
	protected Color agentColor;
	
	protected int newCoord;
	
	public int age = 0;
	
	public CommonAgentBehavour(int posX, int posY, Environnement env, int breed, Color agentColor) {
		super(posX,posY,env);
		this.breed = breed;
		this.agentColor = agentColor;
	}
	
	public abstract boolean canBeEat();
	
	public abstract void changeGenerationAgent();
	
	@Override
	public void drawAgent(Graphics g) {
		int pixelPosX = ConstantParams.getBoxSize() * posX;
		int pixelPosY = ConstantParams.getBoxSize() * posY;
		g.setColor(agentColor);
		g.fillRect(pixelPosX, pixelPosY, ConstantParams.getBoxSize(), ConstantParams.getBoxSize());
	}
	
	@Override
	public int getNewPosX() {
		return posX + (newCoord >> 0x4) - 1;
	}

	@Override
	public int getNewPosY() {
		return posY + (newCoord & 0b1111) - 1;
	}
	
	@Override
	public String toString() {
		return breed + ";" + agentColor + ";" + age + ";" + super.toString();
	}

}
