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
		calculateDijkstra();
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
		calculateDijkstra();
	}
	
	public void calculateDijkstra() {
		dijkstra = new int [ConstantParams.getGridSizeX()][ConstantParams.getGridSizeY()];
		for(int i = 0; i < dijkstra.length; i++) {
			for(int j = 0 ; j < dijkstra[i].length; j++) {
				dijkstra[i][j] = -1;
			}
		}
		long currentPosition = calculateCompressPosition(posX, posY);
		int currentValue = 0;
		int calculatePosX, calculatePosY;
		int futureX, futureY;
		dijkstra[posX][posY] = 0;
		stack.add(currentPosition);
		while(!stack.isEmpty()) {
			currentPosition = stack.remove(0);
			calculatePosX = uncompressPositionX(currentPosition);
			calculatePosY = uncompressPositionY(currentPosition);
			currentValue = dijkstra[calculatePosX][calculatePosY];
			for(int[] move : enableMovement) {
				futureX = calculatePosX + move[0];
				futureY = calculatePosY + move[1];
				if(//env.checkOutOfBorders(calculatePosX + i, calculatePosY) &&
						(env.getCell(futureX,futureY) == null || ((CommonAgentBehavour)env.getCell(futureX, futureY)).canGoOn()) 
						&& dijkstra[futureX][futureY] == -1){
					dijkstra[futureX][futureY] = currentValue + 1;
					stack.add(calculateCompressPosition(futureX, futureY));
				}
			}
		}
	}
	
	private long calculateCompressPosition(long paramX, long paramY) {
		return (paramX << hexaShift) + paramY;
	}

	private int uncompressPositionX(long compression) {
		return (int) (compression >> hexaShift);
	}
	
	private int uncompressPositionY(long compression) {
		return (int)(compression & binaryMask);
	}
	
	@Override
	public void drawAgent(Graphics g) {
		g.setColor(Color.RED);
		super.drawAgent(g);
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

	public int[][] getDijkstra() {
		return this.dijkstra;
	}
}
