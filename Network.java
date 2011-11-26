import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/*this is intended for omnidirectional networks. Use DirectedNetwork
 * for directed networks.
 */

public class Network {
	ArrayList<Node> sensorlist;
	
	List<Node> shortestPathList;
	int strength;

	protected static int DEFAULTX = 100;
	protected static int DEFAULTY = 100;

	public Network() {
		shortestPathList = null;
		strength = 50;
		sensorlist = new ArrayList<Node>();
		sensorlist.add(new Node(DEFAULTX, DEFAULTY));
	}

	public Network(int n, int s) {
		int x = 0;
		strength = s;
		Node temp = new Node();
		Random rand = new Random();
		sensorlist = new ArrayList<Node>();
		// starting node
		sensorlist.add(new Node(DEFAULTX, DEFAULTY));
		for (int i = 1; i < n; i++) {
			x = rand.nextInt(i);// selects a random node to place the new node
								// near
			temp = sensorlist.get(x);
			
			// the maximum distance in either direction from any other node
			int max = (int) Math.sqrt(strength * strength / 2);

			Node newNode = null;
			// Make sure we don't add it twice
			while (sensorlist.contains(newNode) || newNode == null) {

				newNode = new Node(temp.getX() + rand.nextInt(max), temp.getY()
						+ rand.nextInt(max));
			}
			sensorlist.add(newNode);
		}
	}
	
	
	/**
	 * Method to be called when trying to find the shortest path
	 * @param startNode
	 * @param endNode
	 * @return
	 */
	public void shortestPath(Node startNode, Node endNode) {
		List<Node> pathedList = dijkstra(sensorlist);
		
		Node end = null;
		
		// Find the node
		for (Node n : pathedList) {
			if (endNode.equals(n))
				end = n;
		}
		
		ArrayList<Node> returning = new ArrayList<Node>();
		
		do {
			returning.add(end);
			end = end.getPredecessor();
		} while (end != null);

		shortestPathList = returning;
	}

	/**
	 */
	public List<Node> dijkstra(ArrayList<Node> graph) {

		// Set all the nodes to infinity
		for (Node n : graph) {
			n.setDistance(Integer.MAX_VALUE);
		}

		// Make a list of unvisited nodes
		List<Node> unvisited = new ArrayList<Node>(graph);

		// Make a list of visited nodes
		List<Node> visited = new ArrayList<Node>();

		// Get the starting node
		Node startingNode = unvisited.get(0);
		startingNode.setDistance(0);

		visited.add(startingNode);
		unvisited.remove(startingNode);

		
		// Setup the first node
		for (Node n : startingNode.getAllEdges()) {

			int distanceTo = startingNode.getWeight(n) + startingNode.getDistance();

			if (distanceTo < n.getDistance()) {
				n.setDistance(startingNode.getWeight(n) + startingNode.getDistance());
				// No predecessor because we are starting.
			}
				
		}
		

		
		// Iterate over the unvisted nodes
		for (Node cur : unvisited) {
			// Iterate over its neighbours
			for (Node edge : cur.getAllEdges()) {

				if (!visited.contains(cur)) {
					// Get the distance from the current node to a neighbour
					// plus the distance it took to get to the current.
					int distanceTo = cur.getWeight(edge) + cur.getDistance();

					// If the distance is smaller than it's current distance we
					// update it.
					if (distanceTo < edge.getDistance()) {
						edge.setDistance(cur.getWeight(edge) + cur.getDistance());
						edge.setPredecessor(cur);
					}
				}

			}

			// Remove cur from the unvisted list
			
			// TODO: Remove from visited?
			visited.add(cur);
		}
		
		
		// TODO: Check if this actually works
		return visited;
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

	public List<Node> getShortestPathList() {
		return shortestPathList;
	}

	public void setShortestPathList(List<Node> shortestPathList) {
		this.shortestPathList = shortestPathList;
	}

}