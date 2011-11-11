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
	
	public Node(int x, int y,int r) {
		loc = new Point(x,y);
		strength = r;
		dir = -1f;
	}
	
	boolean equals (Node n) {
		return (this.loc.x == n.loc.x && this.loc.y == n.loc.y);
	}
}