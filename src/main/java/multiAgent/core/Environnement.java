package multiAgent.core;

import java.awt.Color;
import java.util.Observable;

import multiAgent.ConstantParams;

public class Environnement extends Observable {

	private SMAInterface sma;
	
	private Agent[][] environnement;
	
	private boolean torus;
	
	private Color background;
	
	public Environnement(int x, int y, boolean torus, SMAInterface sma) {
		this.sma = sma;
		this.environnement = new Agent[x][y];
		this.torus = torus;
		background = Color.WHITE;
	}
	
	public Environnement(int x, int y, boolean torus, SMAInterface sma, Color background) {
		this(x,y,torus,sma);
		this.background = background;
	}

	public void putAgent(Agent agent) {
		environnement[agent.getPosX()][agent.getPosY()] = agent;
	}

	public boolean isEmptyCellule(int x, int y) {
		if (torus) {
			x = calculateTorus(x, ConstantParams.getGridSizeX());
			y = calculateTorus(y, ConstantParams.getGridSizeY());
		}
		return null == environnement[x][y];
	}

	public int calculateTorus(int pos, int size) {
		return (pos + size) % size;
	}

	public boolean checkOutOfBorders(int x, int y) {
		return !torus && (x < 0 || y < 0 || x >= ConstantParams.getGridSizeX() || y >= ConstantParams.getGridSizeY());
	}
	
	public void applyTransition(Agent agent) {
		environnement[agent.getPosX()][agent.getPosY()] = null;
		agent.update();
		environnement[agent.getPosX()][agent.getPosY()] = agent;
	}
	
	public void addNewAgent(Agent agent) {
		sma.addAgent(agent);
		this.putAgent(agent);
	}
	
	public void updateDisplay() {
		setChanged();
		notifyObservers();
	}
	
	public Agent[][] getEnvironnement() {
		return environnement;
	}
	
	public boolean getTorus() {
		return torus;
	}

	public Agent getCell(int x, int y) {
		return environnement[calculateTorus(x, environnement.length)][calculateTorus(y, environnement[0].length)];
	}

	public void deleteAgent(Agent agent) {
		sma.removeAgent(agent);
	}

	public Color getBackground() {
		return background;
	}
	
	public SMAInterface getSMA() {
		return this.sma;
	}
	
	public void clean() {
		this.environnement = new Agent[environnement.length][environnement[0].length];
	}
}
