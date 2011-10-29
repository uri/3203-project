import java.awt.Point;

public class Node {
	Point loc;
	int strength;
	float dir;
	
	public Node() {
		loc = new Point();
		strength = -1;
		dir = -1f;
	}
}