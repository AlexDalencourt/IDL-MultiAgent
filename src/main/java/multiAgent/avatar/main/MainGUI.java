package multiAgent.avatar.main;

import multiAgent.ConstantParams;
import multiAgent.avatar.sma.SMA;
import multiAgent.core.Environnement;
import multiAgent.view.AvatarDebugDisplayer;
import multiAgent.view.MainFrame;

public class MainGUI {

	public static void main(String[] args) throws InterruptedException {
		SMA sma = new SMA();
		Environnement env = new Environnement(ConstantParams.getGridSizeX(), ConstantParams.getGridSizeY(),
			ConstantParams.getTorus(),sma);
		if(!ConstantParams.showGrid()) {
			sma.initAgent(env, new MainFrame(env));
		}else {
			AvatarDebugDisplayer displayer = new AvatarDebugDisplayer(env);
			sma.initAgent(env, new MainFrame(env, displayer));
			displayer.setDisjkstraIsCalculate(true);
		}
		Thread.sleep(ConstantParams.getDelay());
		int tickNumber = 0;
		while (true) {
			sma.run();
			tickNumber++;
			env.updateDisplay();
			Thread.sleep(ConstantParams.getDelay());
		}
	}
}
