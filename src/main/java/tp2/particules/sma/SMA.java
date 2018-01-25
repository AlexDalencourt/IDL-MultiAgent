package tp2.particules.sma;

import tp2.ConstantParams;
import tp2.Logger;
import tp2.core.Agent;
import tp2.core.Environnement;
import tp2.core.SMAInterface;
import tp2.particules.Particule;

public abstract class SMA implements SMAInterface {
//	protected final List<Agent> agentList = new ArrayList<>();
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
//			agentList.add(new Agent(i, posX, posY, pasX, pasY, env));
			agentList[i] = new Particule(i, posX, posY, pasX, pasY, env);
		}
		Logger.log(agentList);
	}
	
	@Override
	public void addAgent(Agent agent) {
		
	}

}
