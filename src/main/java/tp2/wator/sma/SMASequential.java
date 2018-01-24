package tp2.wator.sma;

public class SMASequential extends SMA {

	@Override
	public void run() {
		for(int i = 0; i < agentList.size(); i++) {
			agentList.get(i).decide();	
		}
	}

}
