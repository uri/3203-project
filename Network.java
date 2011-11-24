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
	
	protected static int DEFAULTX = 100;
	protected static int DEFAULTY = 100;
	
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
		System.out.println("\nNew Network with Strength " + strength);
		//starting node
		sensorlist.add(new Node(DEFAULTX,DEFAULTY));
		for (int i = 1; i < n; i++)
		{
			x = rand.nextInt(i);//selects a random node to place the new node near
			temp = sensorlist.get(x);
			int max = (int)Math.sqrt(strength*strength/2);//the maximum distance in either direction from any other node
			int newX=strength, newY=strength;
			
			Node newNode = null;
			// Make sure we don't add it twice
			while(sensorlist.contains(newNode) || newNode == null) {
			
				newNode = new Node(
						temp.getX()+rand.nextInt(max),
						temp.getY()+rand.nextInt(max));
				System.out.println("Base Node: " + temp.getX() + "," + temp.getY());
				System.out.println("New Node :" + newNode.getX() + "," + newNode.getY());
			}
			sensorlist.add(newNode);
		}
	}
	
	
	/**
	 * Sets the neighbours
	 */
	protected void attachNeighbours() {
		
		// Double loop all the way accross the sky
		
		for (Node outer : sensorlist) {
			for (Node inner : sensorlist) {
				int currentDistance = outer.getWeight(inner);
				// Add a neighbour
				if (currentDistance <= strength && currentDistance > 0) {
					outer.addAllEdge(inner);
				}
			}
		}
	}
	
	public ArrayList<Node> getSensorList() {
		return sensorlist;
	}
	
}