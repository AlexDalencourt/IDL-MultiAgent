package multiAgent.particules.sma;

import java.util.ArrayList;
import java.util.List;

import multiAgent.ConstantParams;

public class SMASequentialRandom extends SMA{

	@Override
	public void run() {
		List<Integer> enableAgent = new ArrayList<>();
		for(int i = 0; i < agentList.length; i++) {
			enableAgent.add(i);
		}
		while(enableAgent.size() > 0) {
			int idAgent = enableAgent.remove((ConstantParams.getRandom().nextInt(enableAgent.size())));
			agentList[idAgent].decide();
		}
	}

}
