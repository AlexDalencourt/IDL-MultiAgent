package multiAgent.avatar;

import multiAgent.core.Agent;
import multiAgent.core.Environnement;

public abstract class CommonAgentBehavour extends Agent {
	
	public CommonAgentBehavour(int posX, int posY, Environnement env) {
		super(posX, posY, env);
	}

	public abstract boolean canGoOn();
}
