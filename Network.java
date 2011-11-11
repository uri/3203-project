import java.util.*;

public class Network{
	ArrayList<Node> nodelist;
	ArrayList<Node> sensorlist;
	int r;
	
	public Network() {//this constructor may be irrelevant
		sensorlist = new ArrayList<Node>();
	}
	public Network(int n, int radius){
		int x = 0;
		r = radius;
		Node temp = new Node();
		Random rand = new Random();
		sensorlist = new ArrayList<Node>();
		sensorlist.add(new Node(10,10,r));
		//currently this algorithm doesn't protect from multile sensors at the same location
		for (int i = 1; i < n; i++)
		{
			x = rand.nextInt(i);//selects a random node to place the new node near
			temp = sensorlist.get(x);
			sensorlist.add(new Node(
					temp.loc.x+rand.nextInt(temp.strength-10),
					temp.loc.x+rand.nextInt(temp.strength-10),r));
		}
	}
	
	public void addNode(Node n) {
		nodelist.add(n);
	}
	
	ArrayList<Node> getSensorList() {
		return sensorlist;
	}
	
}