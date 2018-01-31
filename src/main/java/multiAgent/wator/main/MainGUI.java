package multiAgent.wator.main;

import java.awt.Color;

import multiAgent.ConstantParams;
import multiAgent.core.Environnement;
import multiAgent.view.MainFrame;
import multiAgent.wator.sma.SMA;
import multiAgent.wator.sma.SMARandom;
import multiAgent.wator.sma.SMASequential;
import multiAgent.wator.sma.SMASequentialRandom;

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
			if(ConstantParams.showTrace()) {
				sma.log();
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
