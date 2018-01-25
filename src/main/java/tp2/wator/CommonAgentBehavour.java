package tp2.wator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import tp2.ConstantParams;
import tp2.core.Agent;
import tp2.core.Environnement;

public abstract class CommonAgentBehavour extends Agent {
	
	protected int breed;
	
	protected Color agentColor;
	
	protected final List<Integer> availableMovement = new ArrayList<>();
	
	public CommonAgentBehavour(int id, int posX, int posY, Environnement env, int breed, Color agentColor) {
		super(id,posX,posY,env);
		this.breed = breed;
		this.agentColor = agentColor;
	}

	@Override
	public void drawAgent(Graphics g) {
		int pixelPosX = ConstantParams.getBoxSize() * posX;
		int pixelPosY = ConstantParams.getBoxSize() * posY;
		g.setColor(agentColor);
		g.fillRect(pixelPosX, pixelPosY, ConstantParams.getBoxSize(), ConstantParams.getBoxSize());
	}
}
