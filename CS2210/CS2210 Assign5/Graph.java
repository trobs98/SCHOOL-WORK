
import java.util.*;

public class Graph implements GraphADT {
	
	//instance variables for the list of nodes, the size of the graph, and the adjacency matrix of edges
	private Node [] graph;
	private Edge [][] edges;
	private int n;
	
	//constructor that will create an n x n graph 
	public Graph (int n){
		
		graph = new Node [n];
		this.n = n;
		
		
		//adds 0 to n-1 empty nodes into the graph
		for (int i = 0; i < n; i++) {
			graph[i] = new Node(i);
		}
		
		edges = new Edge [n][n];
	}
	
	//insert edge will add the connection between the first Node 'u' and the second Node 'v' into the edge adjacency matrix
	public void insertEdge(Node u, Node v, String edgeType) throws GraphException {
		
		//creates variables for the names of the first and second node
		int firstNodeName = u.getName();
		int secondNodeName = v.getName();
		
		//makes sure that the first node's name and the second node's name are in the graph, if not then an exception is thrown
		if (firstNodeName < 0 || firstNodeName > n - 1 || secondNodeName < 0 || secondNodeName > n - 1) {
			throw new GraphException("GraphException: This Node does not exist");
		}
		
		//makes sure that there already isn't an edge connecting node's u and v in the edges adjacency matrix, if there already is then an exception is thrown
		if (edges[firstNodeName][secondNodeName] != null) {
			throw new GraphException("GraphException: The connection between Node 'u' and Node 'v' already exists");
		}
		
		//puts the edges into the adjacency matrix
		edges[firstNodeName][secondNodeName] = new Edge(u, v, edgeType);
		edges[secondNodeName][firstNodeName] = new Edge(v, u, edgeType);
	}

	//getNode will return the node corresponding to name 
	public Node getNode(int name) throws GraphException {
		
		//makes sure that the node corresponding to name is in the graph, if not then an exception is thrown
		if (name < 0 || name > n - 1) {
			throw new GraphException("GraphException: This node does not exist");
		}
		
		//returns the Node of the name that was found in the graph.
		return graph[name];
	}
	
	
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		
		//makes a variable for then name of node 'u'
		int nodeName = u.getName();
		
		//Checks if node 'u' exists in the graph, if it doesn't then a GraphException is thrown
		if (nodeName < 0 || nodeName > n - 1) {
			throw new GraphException("GraphException: This node does not exist");
		}
				
		//Makes sure that the nodeName exists in the graph, if not then null is returned
		if (nodeName < 0 || nodeName > n - 1) {
			return null;
		}
		
		//makes an ArrayList, that will contain all the possible edges of node u 
		List<Edge> tempEdgeList= new ArrayList<>();	
		
		//steps through each node of the adjacency list and if there is an incident edge of u, then it is added to the edgeList
		for (int i=0; i<n; i++) {
			
			if (edges[nodeName][i] != null) {
				tempEdgeList.add(edges[nodeName][i]);
			}
		}
		
		//checks to see if the tempEdgeList is empty, if it is not, then an iterator of the incident edges of u is returned
		if (tempEdgeList.isEmpty() != true) {
			return tempEdgeList.iterator();
		}
		
		//if the tempEdgeList is empty, then null is returned
		return null;
	}

	//returns the edge corresponding to the connection of nodes 'u' and 'v'
	public Edge getEdge(Node u, Node v) throws GraphException {
		
		//Creates variables for the first and second nodes' names
		int firstNodeName = u.getName();
		int secondNodeName = v.getName();
		
		//makes sure that both of the nodes exist in the graph, if one or both of them do not exist then an exception is thrown
		if (firstNodeName < 0 || firstNodeName > n - 1 || secondNodeName < 0 || secondNodeName > n - 1) {
			throw new GraphException("GraphException: Either one or both of the nodes do not exist");
		}
		
		//makes sure that there is an existing edge between nodes 'u' and 'v', if there is not, then an exception is thrown
		if (areAdjacent(u, v) != true) {
			throw new GraphException("GraphExcpetion: There is no existing edge between the two nodes");
		}
		
		//returns the edge connecting u and v
		return edges[firstNodeName][secondNodeName];
	}

	//method to check if two nodes are adjacent
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		
		//Creates and initializes the values of uName and vName.
		int firstNodeName = u.getName();
		int secondNodeName = v.getName();
		
		//makes sure that both of the nodes exist in the graph, if one or both of them do not exist then an exception is thrown
		if (firstNodeName < 0 || firstNodeName > n - 1 || secondNodeName < 0 || secondNodeName > n - 1) {
			throw new GraphException("GraphException: Either one or both of the nodes do not exist");
		}
		//Checks to see if there is an edge between the two nodes, if there is not, then it will return false
		if (edges[firstNodeName][secondNodeName] == null) {
			return false;
		}
		
		//if there is an edge between the two nodes, then it will return true
		return true;
	}

}


