package tp2.wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tp2.ConstantParams;
import tp2.core.Environnement;

public class Fish extends CommonAgentBehavour{
	
	private final List<Integer> availableMovement = new ArrayList<>();
	
	public Fish(int posX, int posY, Environnement env) {
		super(posX, posY, env, ConstantParams.getFishBreedTime(), Color.GREEN);
	}

	public Fish(int posX, int posY, Environnement env, Color color) {
		super(posX, posY, env, ConstantParams.getFishBreedTime(), color);
	}
	
	@Override
	public void decide() {
		breed--;
		availableMovement.clear();
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				int calculatedX = i + posX;
				int calculatedY = j + posY;
				if(!env.checkOutOfBorders(calculatedX, calculatedY)
						&& env.getCell(calculatedX,calculatedY) == null) {
					availableMovement.add(((i + 1) << 0x4) + (j + 1));
				}
			}
		}
		if(!availableMovement.isEmpty()) {
			newCoord = availableMovement.get(ConstantParams.getRandom().nextInt(availableMovement.size()));
			env.applyTransition(this);
		} 
	}

	@Override
	public void update() {
		if(breed <= 0) {
			env.addNewAgent(new Fish(posX, posY, env, Color.YELLOW));
			breed = ConstantParams.getFishBreedTime();
		}
		posX += (newCoord >> 0x4) - 1;
		posY += (newCoord & 0b1111) - 1;
	}
	
	@Override
	public boolean canBeEat() {
		return true;
	}
	
	@Override
	public void changeGenerationAgent() {
		this.agentColor = Color.GREEN;
	}	
}
