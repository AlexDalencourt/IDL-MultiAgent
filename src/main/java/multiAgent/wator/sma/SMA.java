package multiAgent.wator.sma;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;
import multiAgent.core.SMAInterface;
import multiAgent.wator.CommonAgentBehavour;
import multiAgent.wator.Fish;
import multiAgent.wator.Shark;

public abstract class SMA implements SMAInterface {
	
	protected final List<Agent> agentList = new LinkedList<>();
	
	protected final List<Agent> nextGeneration = new ArrayList<>();
	
	protected ListIterator<Agent> it;
	
	private PrintWriter logfile;
	
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
		if(ConstantParams.showTrace()) {
			try {
				logfile = new PrintWriter(new FileWriter("log-wator.txt"));
				logfile.println("Agent type;Starve time;Breed time;Agent color;Age;Positon X; Position Y");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
		}
	}
	
	@Override
	public void addAgent(Agent agent) {
		this.nextGeneration.add(agent);
	}
	
	@Override
	public void removeAgent(Agent agent) {
		it.remove();
	}
	
	public void addNewGeneration() {
		agentList.addAll(nextGeneration);
		nextGeneration.forEach(x -> ((CommonAgentBehavour)x).changeGenerationAgent());
		nextGeneration.clear();
	}
	
	public void log() {
		for(Agent agent : agentList) {
			logfile.println(agent.toString());
		}
	}
}
