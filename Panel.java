import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	public void paint(Graphics graph){
		Graphics2D g = (Graphics2D)graph;
		int r;
		for (Node s : network.getSensorList()){
			r = s.strength;
			g.draw(new Ellipse2D.Double(s.loc.x,s.loc.y,2*r,2*r));
			g.fill(new Ellipse2D.Double(s.loc.x-3+r,s.loc.y-3+r,6,6));
		}
		
	};
}