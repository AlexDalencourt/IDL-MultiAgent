package multiAgent.wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import multiAgent.ConstantParams;
import multiAgent.core.Environnement;

public class Shark2 extends CommonAgentBehavour{

	private final List<Integer> fishes = new ArrayList<>();
	
	private final List<Integer> emptyCell = new ArrayList<>();
	
	private int starve;
	
	private boolean eat;
	
	public Shark2(int posX, int posY, Environnement env) {
		super(posX, posY, env, ConstantParams.getSharkBreedTime(), Color.RED);
		starve = ConstantParams.getSharkStarveTime();
	}
	
	public Shark2(int posX, int posY, Environnement env, Color color) {
		super(posX, posY, env, ConstantParams.getSharkBreedTime(), color);
		starve = ConstantParams.getSharkStarveTime();
	}

	@Override
	public void decide() {
		breed--;
		fishes.clear();
		emptyCell.clear();
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				int calculatedX = i + posX;
				int calculatedY = j + posY;
				if(!env.checkOutOfBorders(calculatedX, calculatedY)) {
					if(env.getCell(calculatedX,calculatedY) == null){
						emptyCell.add(((i + 1) << 0x4) + (j + 1));
					} else if(((CommonAgentBehavour)env.getCell(calculatedX, calculatedY)).canBeEat()){
						fishes.add(((i + 1) << 0x4) + (j + 1));
					}
				}
			}
		}
		if(!fishes.isEmpty() && (emptyCell.isEmpty() || ConstantParams.getRandom().nextInt(starve + 1) == 0)) {
			starve = ConstantParams.getSharkStarveTime();
			newCoord = fishes.get(ConstantParams.getRandom().nextInt(fishes.size()));
			eat = true;
			env.applyTransition(this);
		} else {
			starve--;
			if(starve < 0) {
				env.deleteAgent(this);
				env.getEnvironnement()[posX][posY] = null;
			} else if(!emptyCell.isEmpty()) {
				eat = false;
				newCoord = emptyCell.get(ConstantParams.getRandom().nextInt(emptyCell.size()));
				env.applyTransition(this);
			}
		}
	}

	@Override
	public void update() {
		if(breed < 0) {
			env.addNewAgent(new Shark2(posX, posY, env, Color.PINK));
			breed = ConstantParams.getSharkBreedTime();
		}
		posX = env.getTorus() ? env.calculateTorus(posX + (newCoord >> 0x4) - 1, env.getEnvironnement().length) : posX + (newCoord >> 0x4) - 1;
		posY = env.getTorus() ? env.calculateTorus(posY + (newCoord & 0b1111) - 1, env.getEnvironnement()[0].length) : posY + (newCoord & 0b1111) - 1;
		if(eat) {
			Fish agent = (Fish) env.getCell(posX, posY);
			agent.setIsEat();
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
	
	@Override
	public String toString() {
		return "Shark;"+ starve + ";" + super.toString();
	}
}
