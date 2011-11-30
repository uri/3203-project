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
		
		// Network numbers
		

		// Size
		setSize(STATS_WIDTH, STATS_HEIGHT);
		
		// Do note destroy the frame, simply hide it
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Do not show it right away.
		setVisible(false);
		

		
	/*	
		addLabel("Num Nodes", 0*LABEL_WIDTH , 1 * LABEL_HEIGHT );
		
		addLabel("Shortest Path T", 0*LABEL_WIDTH , 2 * LABEL_HEIGHT );
		addLabel("Diamter T", 0*LABEL_WIDTH , 3 * LABEL_HEIGHT );
		addLabel("Hops T", 0*LABEL_WIDTH , 4 * LABEL_HEIGHT );
		
		addLabel("Strength", 0*LABEL_WIDTH , 5 * LABEL_HEIGHT );
		addLabel("Num Networks", 0*LABEL_WIDTH , 6 * LABEL_HEIGHT );
		
		addLabel("Strength", 0*LABEL_WIDTH , 5 * LABEL_HEIGHT );
		addLabel("Total Nodes", 0*LABEL_WIDTH , 7 * LABEL_HEIGHT );
		*/
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
		
		totalNumberNodes.setText("" +stats.getNumNodes());
		/*
		numNodes.setText(""+(int)stats.getNumNodes());
		rtShortestPath.setText(""+(int)stats.getRtShortestPath());
		rtDiameter.setText(""+(int)stats.getRtDiameter());
		rtHops.setText(""+(int)stats.getRtHops());
		strength.setText(""+(int)stats.getStrength());
		numNetworksLabel.setText(""+(int)stats.getNumNetworks());
		totalNumberNodes.setText(""+ (int)(stats.getNumNodes() * stats.getNumNetworks()));
		*/
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
	
	public JLabel createField(String s, int x, int y, JLabel omni, JLabel directed){
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
		
		return temp;
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
