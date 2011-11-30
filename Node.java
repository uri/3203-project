/*************************************************************************************
 * ===================================================================================
 *
 * The data structure used to hold a Node on a graph.
 * 
 * ===================================================================================
 *************************************************************************************/

import java.awt.Point;
import java.util.ArrayList;

public class Node {
	private Point loc;
	private double direction, angle;
	private ArrayList<Node> allEdges;
	private ArrayList<Node> mstEdges;
	
	private int distance;
	private Node pred;
	private Node match;

	/**
	 * CONSTRUCTOR
	 */
	public Node() {
		loc = new Point();
		allEdges = new ArrayList<Node>();
		mstEdges = new ArrayList<Node>();
		distance = Integer.MAX_VALUE;
		pred = null;
		match = null;
	}
	
	
	
	/**
	 * CONSTRUCTOR
	 * @param x
	 * @param y
	 */
	public Node(int x, int y) {
		this();
		loc = new Point(x,y);
		direction = 0;
		angle = 360;
	}
	
	
	/**
	 * @param n
	 */
	public void addAllEdge(Node n) {
		allEdges.add(n);
	}
	
	
	/**
	 * This removes an edge from the AllEdge list. It's rather confusingly named
	 * @param n
	 */
	public void removeAllEdge(Node n){
		allEdges.remove(n);
		
	}

	
	
	/**
	 * Gets the weight of the edge between this node and a target
	 * @param n
	 * @return
	 */
	public int getWeight(Node n) {
		// Weight will just be the hypotenous
		int x = this.loc.x - n.getX();
		int y = this.loc.y - n.getY();
		
		int hyp = (int)(Math.pow(x, 2) + Math.pow(y, 2));
		hyp = (int)(Math.pow(hyp, 0.5f));
		
		return hyp;
	}
	
	/**
	 * @param n
	 * @return
	 */
	public double getRelativeAngle(Node n){
		double currentAngle = 0;
		double x = 0;
		double deltaX = (n.getX() - getX());
		double deltaY = (n.getY() - getY());

		if (deltaX == 0 && deltaY>0){
			currentAngle = 270;
		}
		else if (deltaX == 0 && deltaY<0){
			currentAngle = 90;
		}
		else if (deltaX>0 && deltaY <= 0){//+ -
			deltaY = -deltaY;
			x = deltaY/deltaX;
			currentAngle = Math.toDegrees(Math.atan(x));
		}
		else if (deltaX<0 && deltaY >= 0){//-+
			deltaX = -deltaX;
			x = deltaY/deltaX;
			currentAngle = 180 + Math.toDegrees(Math.atan(x));
		}
		else if (deltaX<0 && deltaY<=0){//- -
			x = deltaY/deltaX;
			currentAngle = 180 - Math.toDegrees(Math.atan(x));
		}
		else if (deltaX>0 && deltaY >= 0){//+ +
			x = deltaY/deltaX;
			currentAngle = 360 - Math.toDegrees(Math.atan(x));
		}
		
		return currentAngle;
	}
	
	
	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return loc.toString();
	}
	
	
	/**
	 * Used to compare two nodes
	 * @param n
	 * @return
	 */
	boolean equals (Node n) {
		return (this.loc.x == n.loc.x && this.loc.y == n.loc.y);
	}

	
	/*******************************************************************
	 *		Getters and Setters
	 *******************************************************************/
	
	
	/**
	 * @return
	 */
	public int getX(){
		return loc.x;
	}
	
	
	/**
	 * @return
	 */
	public int getY(){
		return loc.y;
	}

	
	/**
	 * @return
	 */
	public double getDirection(){
		return direction;
	}
	

	/**
	 * @return
	 */
	public double getAngle(){
		return angle;
	}
	
	
	/**
	 * @return
	 */
	public ArrayList<Node> getAllEdges() {
		return allEdges;
	}
	
	
	
	/**
	 * @param i
	 */
	public void setAngle(double i) {
		angle = i;
	}
	
	
	/**
	 * @param i
	 */
	public void setDirection(double i){
		direction = i;
	}
	
	
	/**
	 * @return
	 */
	public ArrayList<Node> getMSTEdges() {
		return mstEdges;
	}

	
	/**
	 * @param edges
	 */
	public void setMSTEdge(ArrayList<Node> edges) {
		this.mstEdges = edges;
	}
	
	
	/**
	 * @param n
	 */
	public void addMSTEdge(Node n){
		if (!mstEdges.contains(n))
			mstEdges.add(n);
		else
			return;
	}

	
	/**
	 * @param i
	 */
	public void setDistance(int i) {
		distance = i;
	}
	
	
	/**
	 * @return
	 */
	public int getDistance() {
		return distance;
	}

	
	/**
	 * @param cur
	 */
	public void setPredecessor(Node cur) {
		pred = cur;
	}

	
	/**
	 * @return
	 */
	public Node getPredecessor() {
		return pred;
	}



	public Node getMatch() {
		return match;
	}



	public void setMatch(Node match) {
		this.match = match;
	}
}







