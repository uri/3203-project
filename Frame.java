/**
 * This is the controller
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	
	private void update(){
		network = new Network(sensors, strength);
		directedNetwork = new DirectedNetwork(network);
		view.updateNetwork(network, directedNetwork);
	}
	
	private void statsWindow(){
		//this is a stub! It should display a window and stats and stuff.
		System.out.println("Stats!");
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