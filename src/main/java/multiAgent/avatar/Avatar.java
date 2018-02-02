package multiAgent.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;

public class Avatar extends CommonAgentBehavour implements KeyListener {

	private int dirX = 0;
	
	private int dirY = 0;
	
	private int[][] dijkstra;
	
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

	private List<Long> stack = new ArrayList<>();

	private static final int binaryMask = 0b11111111111111111111;
	
	private static final int hexaShift = 0x34;
	
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
		recalculDijkstra();
	}

	private void recalculDijkstra() {
		dijkstra = new int [ConstantParams.getGridSizeX()][ConstantParams.getGridSizeY()];
		long currPosition = (((long)posX) << hexaShift) + ((long)posY);
		int currentValue = 0;
		int calculatePosX;
		int calculatePosY;
		dijkstra[posX][posY] = -1;
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				calculatePosX = posX + i;
				calculatePosY = posY + i;
				if(//env.checkOutOfBorders(calculatePosX, calculatePosY) && 
						((CommonAgentBehavour)env.getCell(calculatePosX, calculatePosY)).canGoOn()) {
					stack.add((long) ((calculatePosX << hexaShift) + calculatePosY));
					dijkstra[calculatePosX][calculatePosY] = 1;
				}
			}
		}
		while(!stack.isEmpty()) {
			currPosition = stack.remove(0);
			calculatePosX = (int)(currPosition >> hexaShift);
			calculatePosY = (int)(currPosition & binaryMask);
			currentValue = dijkstra[calculatePosX][calculatePosY];
			for(int i = -1; i < 2; i++) {
				for(int j = -1; j < 2; j++) {
					if(//env.checkOutOfBorders(calculatePosX + i, calculatePosY) &&
							((CommonAgentBehavour)env.getCell(calculatePosX + i, calculatePosY + j)).canGoOn() &&
							dijkstra[calculatePosX + i][calculatePosY + j] > currentValue + 1){
						dijkstra[calculatePosX + i][calculatePosY + j] = currentValue + 1;
					}
					
					//Stack new pos
				}
			}
		}
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
