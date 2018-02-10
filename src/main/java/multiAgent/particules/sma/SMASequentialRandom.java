package multiAgent.particules.sma;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;

public class SMASequentialRandom extends SMA{

	@Override
	public void run() {
		List<Agent> agents = Arrays.asList(agentList);
		Collections.shuffle(agents, ConstantParams.getRandom());
		for(Agent agent : agents) {
			agent.decide();
		}
	}

}
