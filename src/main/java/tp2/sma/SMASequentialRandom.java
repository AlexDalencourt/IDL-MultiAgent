package tp2.sma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tp2.model.ConstantParams;

public class SMASequentialRandom extends SMA{
	
	private static final Integer[] ALLINDEX;
	
	static {
		ALLINDEX = new Integer[ConstantParams.getNumberOfParticles()];
		for(int i = 0; i < ALLINDEX.length; i++) {
			ALLINDEX[i] = i;
		}
	}
	
	private final List<Integer> enableAgent = new ArrayList<>();

	@Override
	public void run() {
		if(enableAgent.size() == 0) {
			enableAgent.addAll(Arrays.asList(ALLINDEX));
		}
		int idAgent = enableAgent.remove((ConstantParams.getRandom().nextInt(enableAgent.size())));
		agentList[idAgent].decide();
	}
//	public void run() {
//		if(agentList.size() == 0) {
//			agentList.addAll(saveStack);
//			saveStack.clear();
//		}
//		Agent agent = agentList.remove(ConstantParams.getRandom().nextInt(agentList.size()));
//		saveStack.add(agent);
//		agent.decide();
//	}

}
