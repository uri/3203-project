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
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class StatsFrame extends JFrame{
	
	public static final int STATS_HEIGHT = 440;
	public static final int STATS_WIDTH = 305;
	
	private static final int TEXT_WIDTH = 170;
	private static final int LABEL_WIDTH = 50;
	private static final int LABEL_HEIGHT = 40;
	
	StatisticsRunner stats;
	
	JLabel omniShortestPath, directedShortestPath;
	JLabel omniDiameter, directedDiameter;
	JLabel omniLength, directedLength;
	JLabel averageAngle;
	JLabel 	numNodes;
	JLabel  rtShortestPath;
	JLabel  rtDiameter;
	JLabel  rtHops;
	JLabel  angle;
	JLabel  strength;
	JLabel 	numNetworksLabel;
	JLabel 	totalNumberNodes;
	
	int numNetworks;
	
	/**
	 * 
	 */
	public StatsFrame() {
		super("Statistics");
		setLayout(null);
		
		// Size
		setSize(STATS_WIDTH, STATS_HEIGHT);
		
		// Do note destroy the frame, simply hide it
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Do not show it right away.
		setVisible(false);

		initDisplayLabels();

	}
	
	
	public void update() {
		if (stats == null) return;
		
		omniShortestPath.setText("" + (int)stats.getOmniAverageShortest());
		directedShortestPath.setText("" + (int)stats.getAverageShortest());
		
		omniDiameter.setText("" +(int)stats.getOmniAverageDiameter());
		directedDiameter.setText("" +(int)stats.getAverageDiameter());
		
		omniLength.setText("" + (int)stats.getOmniAverageLength());
		directedLength.setText("" + (int)stats.getAverageLength());
		
		averageAngle.setText("" + stats.getAverageAngle());
		
		totalNumberNodes.setText("" +stats.getNumNodes());
	}
	
	private void initDisplayLabels() {
		
		JLabel instructions = new JLabel("Omni");
		instructions.setSize(50, 50);
		instructions.setLocation(TEXT_WIDTH, 5);
		getContentPane().add(instructions);
		
		instructions = new JLabel("Directed");
		instructions.setSize(50, 50);
		instructions.setLocation(TEXT_WIDTH+55, 5);
		getContentPane().add(instructions);
		
		int position = 1;
		omniShortestPath = new JLabel("0");
		directedShortestPath = new JLabel("0");
		createField("Shortest Paths",10,LABEL_HEIGHT*position,omniShortestPath,directedShortestPath);
		position++;
		omniDiameter = new JLabel("0");
		directedDiameter = new JLabel("0");
		createField("Average Network Diameter",10, LABEL_HEIGHT*position,omniDiameter,directedDiameter );
		position++;
		omniLength = new JLabel("0");
		directedLength = new JLabel("0");
		createField("Average Route Length", 10, LABEL_HEIGHT*position,omniLength,directedLength);
		
		position++;
		averageAngle = new JLabel("360");
		instructions = new JLabel("360");
		createField("Average Angle", 10, LABEL_HEIGHT*position, instructions, averageAngle);
		
		position++;
		instructions = new JLabel("General Stats");
		addLabel(instructions, TEXT_WIDTH-30,LABEL_HEIGHT*position);
		
		position++;
		instructions = new JLabel("Total Nodes");
		addLabel(instructions,20,LABEL_HEIGHT*position);
		
		totalNumberNodes = new JLabel("0");
		totalNumberNodes.setSize(LABEL_WIDTH,LABEL_HEIGHT);
		totalNumberNodes.setLocation(20+LABEL_WIDTH*2,LABEL_HEIGHT*position);
		getContentPane().add(totalNumberNodes);
		
	}
	
	private void addLabel(JLabel label, int x, int y) {
		label.setSize(LABEL_WIDTH*2,LABEL_HEIGHT);
		label.setLocation(x + 5,y + 5);
		getContentPane().add(label);
	}
	
	public void createField(String s, int x, int y, JLabel omni, JLabel directed){
		JLabel temp = new JLabel(s);
		temp.setSize(TEXT_WIDTH,LABEL_HEIGHT);
		temp.setLocation(x,y);
		getContentPane().add(temp);
		
		omni.setSize(LABEL_WIDTH,LABEL_HEIGHT);
		omni.setLocation(x+TEXT_WIDTH+10,y);
		getContentPane().add(omni);
		
		directed.setSize(LABEL_WIDTH,LABEL_HEIGHT);
		directed.setLocation(x+TEXT_WIDTH+LABEL_WIDTH+10,y);
		getContentPane().add(directed);
	}

	public void setStatisticsRunner(StatisticsRunner stats) {
		this.stats = stats;
	}

	public int getNetworkNum() {
		return numNetworks;
	}

	public void setNetworkNum(int networkNum) {
		this.numNetworks = networkNum;
	}
	
	
}
