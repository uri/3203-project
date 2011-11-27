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
	 * 
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

		shortestPathList = new ArrayList<Node>();
		System.out.println("This is the weight to the last node: "
				+ pathedList.get(pathedList.size() - 1).getDistance());

		do {
			shortestPathList.add(end);
			end = end.getPredecessor();
		} while (end != null);

	}

	/**
	 */
	public List<Node> dijkstra(ArrayList<Node> graph) {

		// Set all the nodes to infinity
		for (Node n : graph) {
			n.setDistance(Integer.MAX_VALUE);
		}

		// Make a list of visited nodes
		Set<Node> visited = new HashSet<Node>();
		List<Node> unvisited = new ArrayList<Node>();

		Node startNode = graph.get(0);
		startNode.setDistance(0);
		visited.add(startNode);
		
		for (Node edge : startNode.getMSTEdges()) {
			int distanceTo = startNode.getWeight(edge);
			edge.setDistance(distanceTo);
			edge.setPredecessor(startNode);
			unvisited.add(edge);
		}
		
		while (!unvisited.isEmpty()) {
			Node cur = unvisited.get(unvisited.size() - 1);
			for (Node edge : cur.getMSTEdges()) {

				if (!visited.contains(edge)) {
					unvisited.add(edge);

					int distanceTo = cur.getWeight(edge) + cur.getDistance();

					// If the distance is smaller than it's current distance we
					// update it.
					if (distanceTo < edge.getDistance()) {
						edge.setDistance(distanceTo);
						edge.setPredecessor(cur);
					}

				}

			}
			visited.add(cur);
			unvisited.remove(cur);

		}

		return new ArrayList<Node>(visited);
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