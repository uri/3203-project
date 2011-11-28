/*************************************************************************************
 * ===================================================================================
 *
 * Acts as the CONTROLLER for the program. This is the main JFrame.
 * 
 * It houses two panels: 
 * 
 * ===================================================================================
 *************************************************************************************/

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

// The controller
public class Frame extends JFrame{

	public static final String FRAME_NAME = "3203 - Networking";
	
	Network network;
	Network directedNetwork;
	
	View view;
	
	
	int strength;
	int sensors;
	
	// Statistics frame
	StatsPanel statsFrame;
	

	
	
	public final static int WIDTH = 800;
	public final static int HEIGHT = 580;
	
	
	
	/**
	 * CONSTRUCTOR
	 * @param name - name of the program
	 */
	public Frame(String name) {
		super(name);
		strength = 50;
		sensors = 10;
		
		statsFrame = new StatsPanel();
		
		network = null;
		directedNetwork = null;
		
		// Add the JPanel
		view = new View();
		getContentPane().add(view);
		
		// Add ActionListeners
		
		/*******************************************************************
		 *		"GO" Button
		 *******************************************************************/
		view.getUpdateButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sensors = view.getSensors();
				strength = view.getStrength();
				update();
			}
		});
		
		
		/*******************************************************************
		 *		Stats Button
		 *******************************************************************/
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
		
		/*******************************************************************
		 *		Angle Toggle Button 
		 *******************************************************************/
		view.getAngleButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleAngleDisplay();
			}

			
		});

		// Make sure it exists properly
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Size the frame.
		setSize(WIDTH, HEIGHT);
		
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
		
		// Shortest path
		Node start = directedNetwork.getSensorList().get(0);
		Node end= directedNetwork.getSensorList().get(directedNetwork.getSensorList().size() - 1);
		directedNetwork.shortestPath(start, end);
		
		// Update the stats pane
		statsFrame.update();	
		
		
	}
	
	private void statsWindow(){
		//this is a stub! It should display a window and stats and stuff.
		System.out.println("Stats!");
		
		// For now I'm just jamming this stuff in here. I'll get it in its own window eventually :)
		int numberOfNetworks = 50;
		int numberOfNodes = 12;
		int signalStrength = 50;
		StatisticsRunner stats = new StatisticsRunner(numberOfNetworks, numberOfNodes, signalStrength);
		
//		stats.shortestPaths();
//		stats.diameterOfNetwork();
//		stats.lengthOfRoutes();
		
		statsFrame.setStatisticsRunner(stats);
		
		
		// Toggle the frame
		if (statsFrame.isVisible()) {
			statsFrame.setVisible(false);
		} else {
			statsFrame.setVisible(true);
		}
		
		
	}
	
	
	/**
	 * The action for the ActionListener that is called when the "SP" button is pressed.
	 */
	private void shortestPath() {
		// Toggle the button
		view.directedPanel.isDisplayShorestPath = !view.directedPanel.isDisplayShorestPath;
		
		// Toggle the buttons color.
		if (view.directedPanel.isDisplayShorestPath)
			view.getShortestPathButtong().setBackground(Color.green);
		else 
			view.getShortestPathButtong().setBackground(Color.GRAY);

		// Make the view draw the changes
		view.directedPanel.repaint();
	}
	
	
	
	/**
	 * The action for the ActionListener that is called when the "Angle" button is pressed.
	 * The button color is toggled to Color.GREEN and then back to white.
	 * 
	 * This turns on and off the "arcs" for the sensors
	 */
	private void toggleAngleDisplay() {
		
		if (view.directedPanel.displayAngles)
			view.getAngleButton().setBackground(Color.green);
		else 
			view.getAngleButton().setBackground(Color.white);
		
		view.directedPanel.displayAngles = !view.directedPanel.displayAngles;
		view.directedPanel.repaint();
	}
	
	public static void main(String [] args){
		new Frame(FRAME_NAME);
	}
	
}