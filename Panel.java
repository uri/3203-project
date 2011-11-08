import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.Iterator;

public class Panel extends JPanel {
	
	public final static int WIDTH = 480;
	public final static int HEIGHT = 480;
	
	Network network;
	
	public Panel() {
		network = new Network();
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