
public class Edge {
	
	//instance variables
	private Node firstEndPoint;
	private Node secondEndPoint;
	private String type;
	
	//constructor
	public Edge(Node u, Node v, String type) {
		
		firstEndPoint = u;
		secondEndPoint = v;
		this.type = type;
	}
	
	//getter method for the first end point of the edge
	public Node firstEndpoint() {
		return firstEndPoint;
	}
	
	//getter method for the second end point of the edge
	public Node secondEndpoint() {
		return secondEndPoint;
	}
	
	//getter method for the edge type
	public String getType() {
		return type;
	}
	
	//setter method for the edge type
	public void setType(String type) {
		this.type = type;
	}
}

