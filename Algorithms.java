import java.util.ArrayList;
import java.util.Random;

public class Algorithms {
	
	/**	Prim's MST algorithm
	 */
	public static void primMST(Network net) {
		
		// Create a list to search
		ArrayList<Node> searchList = new ArrayList<Node>();
		
		// Get a random node
		Node startNode = net.getSensorList().get(new Random().nextInt(net.getSensorList().size()));
		
		// Add it to the search list
		searchList.add(startNode);

		
		// Find the next node that is connected to a node in the search list that has the smallest weight
		
		
		
		
		
		
		while (true) {
			Node smallest = null;
			Node smallestParent = null;
			for (Node n : searchList) {
				
				// Find the node with the smallest distance
				for (Node neigh : n.getAllEdges()) {
					
					int distance = n.getWeight(neigh);
					
					// KLUDGE!!!!
					if (smallest == null) smallest = neigh;
					
					else if (distance > 0 && n.getWeight(neigh) < n.getWeight(smallest) && !searchList.contains(smallest) ) {
						smallest = neigh;
						smallestParent = n;
					}
				}

				// Add the smallest node to the list
				
			}
			
			if (smallest == null) break;
			
			searchList.add(smallest);
			smallestParent.addMSTEdge(smallestParent);
		} 
		

		
		// At this point we have the smallest
	}
}