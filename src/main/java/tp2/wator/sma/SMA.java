package tp2.wator.sma;

import java.util.LinkedList;
import java.util.List;

import tp2.ConstantParams;
import tp2.Logger;
import tp2.core.Agent;
import tp2.core.Environnement;
import tp2.core.SMAInterface;
import tp2.wator.Fish;
import tp2.wator.Shark;

public abstract class SMA implements SMAInterface {
	protected final List<Agent> agentList = new LinkedList<>();
	
	public void initAgent(Environnement env) {
		if (ConstantParams.getGridSizeX() * ConstantParams.getGridSizeY() < ConstantParams.getNumberInitialOfFishes()+ConstantParams.getNumberInitialOfSharks()) {
			throw new IllegalArgumentException("Nombre de particules supérieur au nombre de cases du tableau");
		}
		for(int i = 0; i < ConstantParams.getNumberInitialOfSharks(); i++) {
			int x,y;
			do {
				x = ConstantParams.getRandom().nextInt(ConstantParams.getGridSizeX());
				y = ConstantParams.getRandom().nextInt(ConstantParams.getGridSizeY());
			}while(!env.isEmptyCellule(x, y));
			agentList.add(new Shark(x, y, env));
		}
		for(int i = 0; i < ConstantParams.getNumberInitialOfFishes(); i++) {
			int x,y;
			do {
				x = ConstantParams.getRandom().nextInt(ConstantParams.getGridSizeX());
				y = ConstantParams.getRandom().nextInt(ConstantParams.getGridSizeY());
			}while(!env.isEmptyCellule(x, y));
			agentList.add(new Fish(x, y, env));
		}
		Logger.log(agentList);
	}

	@Override
	public void addAgent(Agent agent) {
		this.agentList.add(agent);
	}
}
