package multiAgent.avatar.main;

import multiAgent.ConstantParams;
import multiAgent.avatar.sma.SMA;
import multiAgent.core.Environnement;
import multiAgent.view.MainFrame;

public class MainGUI {

	public static void main(String[] args) throws InterruptedException {
		SMA sma = new SMA();
		Environnement env = new Environnement(ConstantParams.getGridSizeX(), ConstantParams.getGridSizeY(),
			ConstantParams.getTorus(),sma);
		sma.initAgent(env, new MainFrame(env));

		Thread.sleep(ConstantParams.getDelay());
		int tickNumber = 0;
		while (true) {
			sma.run();
			sma.log();
			tickNumber++;
			env.updateDisplay();
			Thread.sleep(ConstantParams.getDelay());
		}
	}
}
