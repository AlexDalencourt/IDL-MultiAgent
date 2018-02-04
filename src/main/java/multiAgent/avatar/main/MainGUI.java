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
		MainFrame frame;
		if(!ConstantParams.showGrid()) {
			frame = new MainFrame(env);
			sma.initAgent(env, frame);
		}else {
			AvatarDebugDisplayer displayer = new AvatarDebugDisplayer(env);
			frame = new MainFrame(env, displayer);
			sma.initAgent(env, frame);
			displayer.setDisjkstraIsCalculate(true);
		}
		frame.addKeyListener(sma);
		env.updateDisplay();
		Thread.sleep(ConstantParams.getDelay());
		while(true) {
			sma.run();
			env.updateDisplay();
			Thread.sleep(ConstantParams.getDelay());
			while (sma.isEndOfGame()) {Thread.sleep(10);}
		}
	}

}
