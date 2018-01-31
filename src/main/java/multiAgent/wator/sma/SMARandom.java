package multiAgent.wator.sma;

import multiAgent.ConstantParams;
import multiAgent.core.Agent;

public class SMARandom extends SMA {
	
	@Override
	public void run() {
		for(int i = 0; i < agentList.size(); i++) {
			agentList.get(ConstantParams.getRandom().nextInt(agentList.size())).decide();
		}
	}

	@Override
	public void removeAgent(Agent agent) {
		agentList.remove(agent);
	}
}
