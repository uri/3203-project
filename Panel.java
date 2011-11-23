import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.Iterator;

public class Panel extends JPanel {
	
	Network network;
	
	public Panel() {
		setLayout(null);
		network = new Network();
		JLabel inventoryLabel = new JLabel("Inventory");
		inventoryLabel.setSize(60,10);
		inventoryLabel.setLocation(10,0);
		add(inventoryLabel);
	}
	
	public void DrawNetwork(Network n){
		network = n;
		repaint();
	};
	
	public void drawDirectedNetwork(){
		
	}
	
	public void paint(Graphics graph){
		Graphics2D g = (Graphics2D)graph;
		Arc2D temp = new Arc2D.Float();
		for (Node s : network.getSensorList()){
			//g.draw(new Ellipse2D.Double(s.loc.x,s.loc.y,2*r,2*r));
			//temp.setArcByCenter(s.getX(), s.getY(), network.strength, s.getDirection(),s.getAngle(), Arc2D.PIE);
			g.draw(temp);
			g.fill(new Ellipse2D.Double(s.getX()-3,s.getY()-3,6,6));
		}
		for (Node s: network.getSensorList()){
			for (Node n: s.getAllEdges()){
				g.setColor(Color.RED);
				g.draw(new Line2D.Double(n.getX(),n.getY(),s.getX(),s.getY()));
				//g.draw(new Line2D.Double(10,10,20,20));
				System.out.println("iteration");
			}
		}
		
	};
}