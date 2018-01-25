package tp2.core;

import java.util.Observable;

import tp2.ConstantParams;

public class Environnement extends Observable {

	private SMAInterface sma;
	
	private Agent[][] environnement;
	
	private boolean torus;
	
	private int tick;
	
	private int localTick;
	
	public Environnement(int x, int y, boolean torus, SMAInterface sma) {
		this.sma = sma;
		this.environnement = new Agent[x][y];
		this.torus = torus;
		this.tick = 0;
		this.localTick = 0;
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
	
	public void tickUpdate() {
		this.localTick++;
		if(localTick >= ConstantParams.getNumberOfParticules() - 1) {
			this.localTick = 0;
			this.tick++;
			setChanged();
			notifyObservers();
		}
	}
	
	public Agent[][] getEnvironnement() {
		return environnement;
	}
	
	public int getTick() {
		return tick;
	}
	
	public boolean getTorus() {
		return torus;
	}

	public Object getCell(int x, int y) {
		return environnement[x][y];
	}
	
}
