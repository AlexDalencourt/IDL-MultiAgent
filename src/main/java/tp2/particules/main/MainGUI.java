package tp2.particules.main;

import tp2.ConstantParams;
import tp2.core.Environnement;
import tp2.particules.sma.SMA;
import tp2.particules.sma.SMARandom;
import tp2.particules.sma.SMASequential;
import tp2.particules.sma.SMASequentialRandom;
import tp2.view.MainFrame;

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
