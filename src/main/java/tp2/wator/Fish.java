package tp2.wator;

import java.awt.Color;
import java.awt.Graphics;

import tp2.ConstantParams;
import tp2.core.Agent;
import tp2.core.Environnement;

public class Fish extends Agent{

	public Fish(int id, int posX, int posY, Environnement env) {
		super(id, posX, posY, env);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getNewPosX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNewPosY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void drawAgent(Graphics g) {
		int pixelPosX = ConstantParams.getBoxSize() * posX;
		int pixelPosY = ConstantParams.getBoxSize() * posY;
		g.setColor(Color.GREEN);
		g.fillOval(pixelPosX, pixelPosY, ConstantParams.getBoxSize(), ConstantParams.getBoxSize());
	}

}
