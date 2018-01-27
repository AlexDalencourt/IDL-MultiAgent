package tp2.wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tp2.ConstantParams;
import tp2.core.Environnement;

public class Shark extends CommonAgentBehavour{

	private final List<Integer> fishes = new ArrayList<>();
	
	private final List<Integer> emptyCell = new ArrayList<>();
	
	private int starve;
	
	private boolean eat;
	
	public Shark(int posX, int posY, Environnement env) {
		super(posX, posY, env, ConstantParams.getSharkBreedTime(), Color.RED);
		starve = ConstantParams.getSharkStarveTime();
	}
	
	public Shark(int posX, int posY, Environnement env, Color color) {
		super(posX, posY, env, ConstantParams.getSharkBreedTime(), color);
		starve = ConstantParams.getSharkStarveTime();
	}

	@Override
	public void decide() {
		breed--;
		fishes.clear();
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				int calculatedX = i + posX;
				int calculatedY = j + posY;
				if(!env.checkOutOfBorders(calculatedX, calculatedY)
						&& env.getCell(calculatedX,calculatedY) != null) {
					if(((CommonAgentBehavour)env.getCell(calculatedX, calculatedY)).canBeEat()){
						fishes.add(((i + 1) << 0x4) + (j + 1));
					} else {
						emptyCell.add(((i + 1) << 0x4) + (j + 1));
					}
				}
			}
		}
		if(!fishes.isEmpty()) {
			starve = ConstantParams.getSharkStarveTime();
			newCoord = fishes.get(ConstantParams.getRandom().nextInt(fishes.size()));
			eat = true;
			env.applyTransition(this);
		} else {
			starve--;
			if(starve <= 0) {
				env.deleteAgent(this);
				env.getEnvironnement()[posX][posY] = null;
			} else if(!emptyCell.isEmpty()) {
				eat = false;
				newCoord = fishes.get(ConstantParams.getRandom().nextInt(fishes.size()));
				env.applyTransition(this);
			}
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
		if(eat) {
			env.deleteAgent(posX,posY);
		}
	}
	
	@Override
	public boolean canBeEat() {
		return false;
	}

	@Override
	public void changeGenerationAgent() {
		this.agentColor = Color.RED;
	}
}
