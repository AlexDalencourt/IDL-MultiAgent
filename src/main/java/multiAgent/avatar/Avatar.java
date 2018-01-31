package multiAgent.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;

public class Avatar extends CommonAgentBehavour implements KeyListener {

	private int dirX = 0;
	
	private int dirY = 0;
	
	public Avatar(int posX, int posY, Environnement env) {
		super(posX, posY, env);
	}

	@Override
	public void decide() {
		if(dirX == 0 && dirY == 0) {
			return;
		}
		env.applyTransition(this);
	}

	@Override
	public void update() {
		int futurX = posX + dirX;
		int futurY = posY + dirY;
		Agent targetCell = env.getCell(futurX, futurY);
		if(targetCell != null && !((CommonAgentBehavour) targetCell).canGoOn()) {
			dirX = 0;
			dirY = 0;
			return;
		}
		posX  = futurX;
		posY = futurY;
	}

	@Override
	public void drawAgent(Graphics g) {
		int pixelPosX = ConstantParams.getBoxSize() * posX;
		int pixelPosY = ConstantParams.getBoxSize() * posY;
		g.setColor(Color.RED);
		g.fillRect(pixelPosX, pixelPosY, ConstantParams.getBoxSize(), ConstantParams.getBoxSize());
	}

	@Override
	public boolean canGoOn() {
		return false;
	}
	
	@Override
	public int getNewPosX() {
		return posX + dirX;
	}

	@Override
	public int getNewPosY() {
		return posY + dirY;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			dirY = -1;
			dirX = 0;
			break;
		case KeyEvent.VK_DOWN:
			dirY = 1;
			dirX = 0;
			break;
		case KeyEvent.VK_LEFT:
			dirY = 0;
			dirX = -1;
			break;
		case KeyEvent.VK_RIGHT:
			dirY = 0;
			dirX = 1;
			break;
		default:;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public String toString() {
		return "Avatar;" + dirX + ";" + dirY + ";" + super.toString();
	}
}
