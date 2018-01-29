package tp2.wator.sma;

import tp2.ConstantParams;
import tp2.core.Agent;

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
