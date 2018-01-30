package tp2.wator.main;

import java.awt.Color;

import tp2.ConstantParams;
import tp2.core.Environnement;
import tp2.wator.sma.SMA;
import tp2.wator.sma.SMARandom;
import tp2.wator.sma.SMASequential;
import tp2.wator.sma.SMASequentialRandom;
import tp2.view.MainFrame;

public class MainGUI {

	public static void main(String[] args) throws InterruptedException {
		SMA sma = initSMA();
		Environnement env = new Environnement(ConstantParams.getGridSizeX(), ConstantParams.getGridSizeY(),
			ConstantParams.getTorus(),sma, Color.CYAN);
		sma.initAgent(env);

		new MainFrame(env);
		Thread.sleep(ConstantParams.getDelay());
		int tickNumber = 0;
		while (true) {
			if (ConstantParams.getNumberOfTicks() != 0 && tickNumber < ConstantParams.getNumberOfTicks()) {
				break;
			}
			sma.run();
			tickNumber++;
			if(tickNumber % ConstantParams.refresh() == 0) {
				env.updateDisplay();
			}
			Thread.sleep(ConstantParams.getDelay());
			sma.addNewGeneration();
		}
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
