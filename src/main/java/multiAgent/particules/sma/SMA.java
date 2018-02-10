package multiAgent.particules.sma;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;
import multiAgent.core.SMAInterface;
import multiAgent.particules.Particule;
import multiAgent.particules.Particule2;

public abstract class SMA implements SMAInterface {
	protected final Agent[] agentList = new Agent[ConstantParams.getNumberOfParticules()];
	
	@Override
	public void initAgent(Environnement env) {
		if (ConstantParams.getGridSizeX() * ConstantParams.getGridSizeY() < ConstantParams.getNumberOfParticules()) {
			throw new IllegalArgumentException("Nombre de particules supérieur au nombre de cases du tableau");
		}
		for (int i = 0; i < ConstantParams.getNumberOfParticules(); i++) {
			int posX, posY, pasX = 0, pasY = 0;
			do {
				posX = ConstantParams.getRandom().nextInt(ConstantParams.getGridSizeX());
				posY = ConstantParams.getRandom().nextInt(ConstantParams.getGridSizeY());
				while(pasX == 0 && pasY == 0) {
					pasX = ConstantParams.getRandom().nextInt(3) - 1;
					pasY = ConstantParams.getRandom().nextInt(3) - 1;
				}
			} while (!env.isEmptyCellule(posX, posY));
			agentList[i] = new Particule(i, posX, posY, pasX, pasY, env);
		}
	}
	
	public void initAgent2(Environnement env) {
		if (ConstantParams.getGridSizeX() * ConstantParams.getGridSizeY() < ConstantParams.getNumberOfParticules()) {
			throw new IllegalArgumentException("Nombre de particules supérieur au nombre de cases du tableau");
		}
		for (int i = 0; i < ConstantParams.getNumberOfParticules(); i++) {
			int posX, posY, pasX = 0, pasY = 0;
			do {
				posX = ConstantParams.getRandom().nextInt(ConstantParams.getGridSizeX());
				posY = ConstantParams.getRandom().nextInt(ConstantParams.getGridSizeY());
				while(pasX == 0 && pasY == 0) {
					pasX = ConstantParams.getRandom().nextInt(3) - 1;
					pasY = ConstantParams.getRandom().nextInt(3) - 1;
				}
			} while (!env.isEmptyCellule(posX, posY));
			agentList[i] = new Particule2(i, posX, posY, pasX, pasY, env);
		}
	}
	
	@Override
	public void addAgent(Agent agent) {
		
	}

	@Override
	public void removeAgent(Agent shark) {
		
	}
	
	@Override
	public void log() {
		
	}
}
