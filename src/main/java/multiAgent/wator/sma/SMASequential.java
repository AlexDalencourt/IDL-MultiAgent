package multiAgent.wator.sma;

public class SMASequential extends SMA {
	
	@Override
	public void run() {
		it = agentList.listIterator();
		while(it.hasNext()) {
			it.next().decide();
		}
	}

}
