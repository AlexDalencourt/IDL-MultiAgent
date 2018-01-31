package multiAgent.particules.sma;

public class SMASequential extends SMA {

	@Override
	public void run() {
		for(int i = 0; i < agentList.length; i++) {
			agentList[i].decide();
		}
	}

}
