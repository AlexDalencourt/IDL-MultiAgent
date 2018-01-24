package tp2.wator.sma;

import tp2.ConstantParams;

public class SMARandom extends SMA {
	
	@Override
	public void run() {
		for(int i = 0; i < agentList.size(); i++) {
			agentList.get(ConstantParams.getRandom().nextInt(agentList.size())).decide();
		}
	}

}
