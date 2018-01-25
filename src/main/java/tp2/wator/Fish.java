package tp2.wator;

import java.awt.Color;

import tp2.ConstantParams;
import tp2.core.Environnement;

public class Fish extends CommonAgentBehavour{
	
	public Fish(int id, int posX, int posY, Environnement env) {
		super(id, posX, posY, env, ConstantParams.getFishBreedTime(), Color.GREEN);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decide() {
		availableMovement.clear();
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(env.getCell(i,j) == null) {
					availableMovement.add((i << 0x4) + j);
				}
			}
		}
		int coord = availableMovement.get(ConstantParams.getRandom().nextInt(availableMovement.size()));
		int x = coord >> 0x4;
		int y = coord & 0b1111;			
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
	
	public static void main(String[] args) {
		int test = (1 << 0x4) + 1;
		System.out.println(test + " : " + Integer.toBinaryString(test) + " : " + (test >> 0x4) + " : " + (test & 0b1111));
	}
	
}
