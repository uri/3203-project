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
	}
}