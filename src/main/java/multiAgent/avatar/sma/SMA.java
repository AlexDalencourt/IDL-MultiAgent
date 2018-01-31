package multiAgent.avatar.sma;

import multiAgent.ConstantParams;
import multiAgent.avatar.Avatar;
import multiAgent.avatar.Wall;
import multiAgent.core.Agent;
import multiAgent.core.Environnement;
import multiAgent.core.SMAInterface;
import multiAgent.view.MainFrame;

public class SMA implements SMAInterface {
	
	private Agent[] agentList;
	
	private MainFrame mainFrame;
	
	@Override
	public void initAgent(Environnement env) {
		for(int i = 0; i < ConstantParams.getGridSizeX(); i++) {
			new Wall(i, 0, env);
			new Wall(i, ConstantParams.getGridSizeY() - 1, env);
		}
		for(int i = 0; i < ConstantParams.getGridSizeY(); i++) {
			new Wall(0, i, env);
			new Wall(ConstantParams.getGridSizeX() - 1, i, env);
		}
		agentList = new Agent[1];
		agentList[0] = new Avatar(1, 1, env);
		mainFrame.addEventKeyListener((Avatar) agentList[0]);
	}
	
	public void initAgent(Environnement env, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.initAgent(env);
	}

	@Override
	public void run() {
		agentList[0].decide();
	}

	@Override
	public void addAgent(Agent agent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAgent(Agent agent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log() {
		for(int i = 0; i < agentList.length; i++) {
			System.out.println(agentList[i]); 
		}
	}
}
