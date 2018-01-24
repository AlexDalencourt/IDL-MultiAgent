package tp2.sma;

import tp2.model.ConstantParams;

public class SMARandom extends SMA {
	
	@Override
	public void run() {
		agentList[ConstantParams.getRandom().nextInt(agentList.length)].decide();
	}

}
