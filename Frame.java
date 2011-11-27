/**
 * This is the controller
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

// The controller
public class Frame extends JFrame{

	public static final String FRAME_NAME = "3203 - Networking";
	Network network, directedNetwork;
	View view;
	int strength,sensors;

	public final static int WIDTH = 800;
	public final static int HEIGHT = 580;
	
	
	public Frame(String name) {
		super(name);
		strength = 50;
		sensors = 10;
		
		network = directedNetwork = null;

		// Make sure it exists properly
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the JPanel
		view = new View();
		getContentPane().add(view);
		
		//add ActionListeners
		view.getUpdateButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sensors = view.getSensors();
				strength = view.getStrength();
				update();
			}
		});
		view.getStatsButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				
				
				statsWindow();
			}
		});
		
		/*******************************************************************
		 *		Shortest Path Button 
		 *******************************************************************/
		view.getShortestPathButtong().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				updateIfNeeded(); // else we get a npe
				shortestPath();
				
			}
		});
		
		
		view.getAngleButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleAngleDisplay();
			}

			
		});

		// Size the frame.
		setSize(WIDTH, HEIGHT);
//		pack();
		
		
		
		// Show it.
		setVisible(true);
	}
	
	private void updateIfNeeded() {
		if (null == network || null == directedNetwork)
			update();
	}
	
	private void update(){
		network = new Network(sensors, strength);
		directedNetwork = new DirectedNetwork(network);
		view.updateNetwork(network, directedNetwork);
	}
	
	private void statsWindow(){
		//this is a stub! It should display a window and stats and stuff.
		System.out.println("Stats!");
		
		// For now I'm just jamming this stuff in here. I'll get it in its own window eventually :)
		int numberOfNetworks = 500;
		int numberOfNodes = 12;
		int signalStrength = 50;
		StatisticsRunner stats = new StatisticsRunner(numberOfNetworks, numberOfNodes, signalStrength);
		
		stats.shortestPaths();
		stats.diameterOfNetwork();
		stats.lengthOfRoutes();
		
	}
	
	private void shortestPath() {
//		Node start = directedNetwork.getSensorList().get(new Random().nextInt(directedNetwork.getSensorList().size()));
//		Node end = directedNetwork.getSensorList().get(new Random().nextInt(directedNetwork.getSensorList().size()));
		
		// Toggle the button
		view.directedPanel.isDisplayShorestPath = !view.directedPanel.isDisplayShorestPath;
		Node start = directedNetwork.getSensorList().get(0);
		Node end= directedNetwork.getSensorList().get(directedNetwork.getSensorList().size() - 1);
		directedNetwork.shortestPath(start, end);
		view.directedPanel.repaint();
	}
	
	
	private void toggleAngleDisplay() {
		view.directedPanel.displayAngles = !view.directedPanel.displayAngles;
		view.directedPanel.repaint();
	}
	
	public static void main(String [] args){
		new Frame(FRAME_NAME);
	}
	
}