package tp2;

import tp2.sma.SMA;
import tp2.model.ConstantParams;
import tp2.model.Environnement;

public class Main {

	public static void main(String[] args) {
		Environnement env = new Environnement(ConstantParams.getGridSizeX(), ConstantParams.getGridSizeY(), ConstantParams.getTorus());
		SMA sma = ConstantParams.getSMA();
		sma.initAgent(env);
		for(int i = 0; i < ConstantParams.getNumberOfTicks();i++) {
			sma.run();
		}
	}
}
