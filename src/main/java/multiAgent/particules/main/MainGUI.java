package multiAgent.particules.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
			if (ConstantParams.getNumberOfTicks() != 0 && tick >= ConstantParams.getNumberOfTicks()) {
				break;
			}
			sma.run();
			tick++;
			if(tick % ConstantParams.refresh() == 0) {
				env.updateDisplay();
			}
			Thread.sleep(ConstantParams.getDelay());
		}
		if(ConstantParams.showTrace()) {
			getOutputStream().flush();
			getOutputStream().close();
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

	private static PrintWriter logger;
	
	public static PrintWriter getOutputStream() {
		if(logger == null) {
			try {
				logger = new PrintWriter(new FileWriter(new File("Log-Particules-V1.log")));
			}catch(IOException e) {
				throw new RuntimeException("Impossible d'ouvrir un logger");
			}
		}
		return logger;
	}
}
