package tp2.particules.sma;

import tp2.ConstantParams;

public class SMARandom extends SMA {
	
	@Override
	public void run() {
		agentList[ConstantParams.getRandom().nextInt(agentList.length)].decide();
	}

}
