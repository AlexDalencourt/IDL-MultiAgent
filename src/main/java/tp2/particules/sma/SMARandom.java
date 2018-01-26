package tp2.particules.sma;

import tp2.ConstantParams;

public class SMARandom extends SMA {
	
	@Override
	public void run() {
		for(int i = 0; i < agentList.length; i++) {
			agentList[ConstantParams.getRandom().nextInt(agentList.length)].decide();
		}
	}

}
