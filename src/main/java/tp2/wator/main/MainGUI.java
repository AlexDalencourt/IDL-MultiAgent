package tp2.wator.main;

import tp2.ConstantParams;
import tp2.core.Environnement;
import tp2.wator.sma.SMA;
import tp2.wator.sma.SMARandom;
import tp2.wator.sma.SMASequential;
import tp2.wator.sma.SMASequentialRandom;
import tp2.view.MainFrame;

public class MainGUI {

	public static void main(String[] args) throws InterruptedException {
		Environnement env = new Environnement(ConstantParams.getGridSizeX(), ConstantParams.getGridSizeY(),
				ConstantParams.getTorus());
		SMA sma = initSMA();
		sma.initAgent(env);

		new MainFrame(env);
		Thread.sleep(ConstantParams.getDelay());
		while (true) {
			if (ConstantParams.getNumberOfTicks() != 0 && env.getTick() < ConstantParams.getNumberOfTicks()) {
				break;
			}
			executeOneTick(sma);
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
