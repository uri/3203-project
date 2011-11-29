/*************************************************************************************
 * ===================================================================================
 * 
 * This is the frame that pops up when the user hits the Stats button.
 * It displays useful information about the program. 
 * 
 * Uses StatisticsRunner
 * 
 * ===================================================================================
 *************************************************************************************/
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class StatsFrame extends JFrame{
	
	public static final int STATS_HEIGHT = 400;
	public static final int STATS_WIDTH = 400;
	
	private static final int LABEL_WIDTH = 100;
	private static final int LABEL_HEIGHT = 40;
	
	StatisticsRunner stats;
	
	Container contentPane;
	
	JLabel 	numNodes;
	JLabel  rtShortestPath;
	JLabel  rtDiameter;
	JLabel  rtHops;
	JLabel  angle;
	JLabel  strength;
	
	/**
	 * 
	 */
	public StatsFrame() {
		super("Statistics");
		setLayout(null);
		
		contentPane = getContentPane();

		// Size
		setSize(STATS_HEIGHT, STATS_WIDTH);
		
		// Do note destroy the frame, simply hide it
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Do not show it right away.
		setVisible(false);
		
		
		addLabel("Num Nodes", 0*LABEL_WIDTH , 1 * LABEL_HEIGHT );
		
		addLabel("Shortest Path T", 0*LABEL_WIDTH , 2 * LABEL_HEIGHT );
		addLabel("Diamter T", 0*LABEL_WIDTH , 3 * LABEL_HEIGHT );
		addLabel("Hops T", 0*LABEL_WIDTH , 4 * LABEL_HEIGHT );
		
		addLabel("Angle", 0*LABEL_WIDTH , 5 * LABEL_HEIGHT );
		addLabel("Strength", 0*LABEL_WIDTH , 6 * LABEL_HEIGHT );
		
		initDisplayLabels();

		
		// Get the statistics class
		

	}
	
	public void update() {
		if (stats == null) return;
		numNodes.setText(""+stats.getNumNodes());
		rtShortestPath.setText(""+stats.getRtShortestPath());
		rtDiameter.setText(""+stats.getRtDiameter());
		rtHops.setText(""+stats.getRtHops());
		angle.setText(""+stats.getAngle());
		strength.setText(""+stats.getStrength());
	}
	

	private void addLabel(String name, int x, int y) {
		JLabel label = new JLabel(name);
		addLabel(label, x, y);
	}
	
	private void addLabel(JLabel label, int x, int y) {
		label.setSize(LABEL_WIDTH,LABEL_HEIGHT);
		label.setLocation(x + 5,y + 5);
		contentPane.add(label);
	}
	
	private void initDisplayLabels() {
		numNodes = new JLabel("sdflasfalsdjfa"); 
		addLabel(numNodes, 1*LABEL_WIDTH , 1 * LABEL_HEIGHT );
		
		rtShortestPath  = new JLabel("0");
		addLabel(rtShortestPath, 1*LABEL_WIDTH , 2 * LABEL_HEIGHT );
		
		rtDiameter = new JLabel("0");
		addLabel(rtDiameter, 1*LABEL_WIDTH , 3 * LABEL_HEIGHT );
		
		rtHops = new JLabel("0");
		addLabel(rtHops, 1*LABEL_WIDTH , 4 * LABEL_HEIGHT );
		
		angle = new JLabel("0");
		addLabel(angle, 1*LABEL_WIDTH , 5 * LABEL_HEIGHT );
		
		strength = new JLabel("0");
		addLabel(strength, 1*LABEL_WIDTH , 6 * LABEL_HEIGHT );
	}

	public void setStatisticsRunner(StatisticsRunner stats) {
		this.stats = stats;
	}
	
	
}
