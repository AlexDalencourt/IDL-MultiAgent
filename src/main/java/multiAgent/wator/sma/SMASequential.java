package multiAgent.wator.sma;

public class SMASequential extends SMA {
	
	@Override
	public void run() {
		tick++;
		it = agentList.listIterator();
		while(it.hasNext()) {
			it.next().decide();
		}
	}

}
