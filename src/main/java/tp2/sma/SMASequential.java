package tp2.sma;

public class SMASequential extends SMA {

	private int cursor = 0; 
	
	@Override
	public void run() {
		agentList[cursor].decide();
		if(++cursor >= agentList.length) {
			cursor = 0;
		};
	}
//	public void run() {
//		Agent agent = agentList.remove(0);
//		agent.decide();
//		agentList.add(agent);
//	}

}
