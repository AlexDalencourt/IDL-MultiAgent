package tp2.wator.sma;

import java.util.Collections;

public class SMASequentialRandom extends SMA{
	
	@Override
	public void run() {
		Collections.shuffle(agentList);
		it = agentList.listIterator();
		while(it.hasNext()) {
			it.next().decide();
		}
	}

}
