package multiAgent.particules.main;

import multiAgent.ConstantParams;
import multiAgent.core.Environnement;
import multiAgent.particules.sma.SMA;
import multiAgent.particules.sma.SMARandom;
import multiAgent.particules.sma.SMASequential;
import multiAgent.particules.sma.SMASequentialRandom;
import multiAgent.view.MainFrame;

public class MainGUI {

	public static void main(String[] args) throws InterruptedException {
		SMA sma = initSMA();
		Environnement env = new Environnement(ConstantParams.getGridSizeX(), ConstantParams.getGridSizeY(),
				ConstantParams.getTorus(), sma);
		sma.initAgent(env);

		new MainFrame(env);
		Thread.sleep(ConstantParams.getDelay());
		int tick = 0;
		while (true) {
			if (ConstantParams.getNumberOfTicks() != 0 && tick < ConstantParams.getNumberOfTicks()) {
				break;
			}
			executeOneTick(sma);
			tick++;
			if(tick % ConstantParams.refresh() == 0) {
				env.updateDisplay();
			}
			
		}
	}

	private static void executeOneTick(SMA sma) throws InterruptedException {
		for (int j = 0; j < ConstantParams.getNumberOfParticules(); j++) {
			sma.run();
		}
		Thread.sleep(ConstantParams.getDelay());
	}
	
	private static SMA initSMA() {
		switch (ConstantParams.getSMA()) {
		case ALLRANDOM:
			return new SMARandom();
		case SEQUENTIALRANDOM:
			return new SMASequentialRandom();
		case SEQUENTIAL:
		default:
			return new SMASequential();
		}
	}
}
