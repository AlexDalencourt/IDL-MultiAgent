package tp2.wator;

import java.awt.Color;

import tp2.ConstantParams;
import tp2.core.Environnement;

public class Shark extends CommonAgentBehavour{

	public Shark(int id, int posX, int posY, Environnement env) {
		super(id, posX, posY, env, ConstantParams.getSharkBreedTime(), Color.RED);
	}

	@Override
	public void decide() {
		int x,y;
		do {
			x = ConstantParams.getRandom().nextInt(3);
			y = ConstantParams.getRandom().nextInt(3);
		}while(x == 0 && y == 0);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getNewPosX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNewPosY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
