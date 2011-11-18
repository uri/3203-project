import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/*this is intended for omnidirectional networks. Use DirectedNetwork
 * for directed networks.
*/

public class Network{
	ArrayList<Node> sensorlist;
	int strength;
	
	private static int DEFAULTX = 100;
	private static int DEFAULTY = 100;
	
	public Network() {
		strength = 50;
		sensorlist = new ArrayList<Node>();
		sensorlist.add(new Node(DEFAULTX,DEFAULTY));
	}
	public Network(int n, int s){
		int x = 0;
		strength = s;
		Node temp = new Node();
		Random rand = new Random();
		sensorlist = new ArrayList<Node>();
		//starting node
		sensorlist.add(new Node(DEFAULTX,DEFAULTY));
		for (int i = 1; i < n; i++)
		{
			x = rand.nextInt(i);//selects a random node to place the new node near
			temp = sensorlist.get(x);
			
			
			Node newNode = null;
			
			// Make sure we don't add it twice
			while(sensorlist.contains(newNode) || newNode == null) {
				newNode = new Node(
						temp.getX()+rand.nextInt(strength-10),
						temp.getY()+rand.nextInt(strength-10));
			}
			
			sensorlist.add(newNode);
		}
		
		
		// Make sure the neighbours are set.
		attachNeighbours();
	}
	
	
	/**
	 * Sets the neighbours
	 */
	private void attachNeighbours() {
		
		
		// Double loop all the way accross the sky
		
		for (Node outter : sensorlist) {
			for (Node inner : sensorlist) {
				
				// Check if they are in a radius 
				if (outter.getX() >= inner.getX() - strength && outter.getX() <= inner.getX() + strength &&	
					outter.getY() >= inner.getY() - strength && outter.getY() <= inner.getY() + strength) {
					
					// Check if already neightbour
					if (!outter.getNeighbours().contains(inner)) {
						outter.addNeighbour(inner);
					}
					
					// Add in the other direction
					if (!inner.getNeighbours().contains(outter)) {
						inner.addNeighbour(outter);
					}
				}
			}
		}
	}
	
	public ArrayList<Node> getSensorList() {
		return sensorlist;
	}
	
}