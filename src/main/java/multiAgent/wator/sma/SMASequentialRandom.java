package multiAgent.wator.sma;

import java.util.Collections;

import multiAgent.ConstantParams;

public class SMASequentialRandom extends SMA{
	
	@Override
	public void run() {
		tick++;
		Collections.shuffle(agentList, ConstantParams.getRandom());
		it = agentList.listIterator();
		while(it.hasNext()) {
			it.next().decide();
		}
	}

}
