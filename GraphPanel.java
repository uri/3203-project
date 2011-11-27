import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	
	Network network;
	public boolean isDisplayShorestPath;
	public boolean displayAngles;
	
	public GraphPanel() {
		setLayout(null);
		network = new Network();
		JLabel inventoryLabel = new JLabel("Inventory");
		inventoryLabel.setSize(60,10);
		inventoryLabel.setLocation(10,0);
		add(inventoryLabel);
		isDisplayShorestPath = false;
		displayAngles = true;
	}
	
	public void DrawNetwork(Network n){
		network = n;
		repaint();
	};
	
	public void drawDirectedNetwork(){
		
	}
	
	public void paint(Graphics graph){
		super.paint(graph);
		Graphics2D g = (Graphics2D)graph;
		Arc2D temp = new Arc2D.Float();
		
		
		// Display the angles/arcs
		if (displayAngles) {
			for (Node s : network.getSensorList()){
				temp.setArcByCenter(s.getX(), s.getY(), network.strength, s.getDirection(),s.getAngle(), Arc2D.PIE);
				g.draw(temp);
			}
				
		}
		
		// Display the nodes
		for (Node s : network.getSensorList()){
			g.fill(new Ellipse2D.Double(s.getX()-3,s.getY()-3,6,6));
		}
		
		
		
		//System.out.println("\nNew Batch");
		for (Node s: network.getSensorList()){
			for (Node n: s.getAllEdges()){
//				g.setColor(Color.BLUE);
//				g.draw(new Line2D.Double(n.getX(),n.getY(),s.getX(),s.getY()));
		
				
				/*		System.out.println("Origin " + n.getX() + "," + n.getY() +
						". Destination " + s.getX()+ "," + s.getY());*/
			}
			for(Node n: s.getMSTEdges()){
				g.setColor(Color.RED);
				g.draw(new Line2D.Double(n.getX(),n.getY(),s.getX(),s.getY()));
			}
		}
		
		
		
		// Draw the shortest path
		
		if (network.getShortestPathList() != null && isDisplayShorestPath) {
			g.setColor(Color.GREEN);
			for (int i = 0; i < network.getShortestPathList().size() - 1; i++) {
				Node n1 = network.getShortestPathList().get(i);
				Node n2 = network.getShortestPathList().get(i + 1);
				
				g.draw(new Line2D.Double(n1.getX(),n1.getY(),n2.getX(),n2.getY()));
			}
				
		}
		
		
	}
}