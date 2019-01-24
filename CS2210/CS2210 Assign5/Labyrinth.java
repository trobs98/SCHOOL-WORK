import java.util.*;
import java.io.*;

public class Labyrinth {

	//Global Variables used to store the Labyrinth Information
	private int roomSize;
	private int numberNodes; 
	private int regularBombs; 
	private int width; 
	private int length; 
	private int acidBombs;
	
	//Variables for the number bombs that are used
	private int normalBombsUsed = 0; 
	private int acidBombsUsed = 0;
	
	//Variables for the Entrance and Exit node location
	private Node entranceNode;
	private Node exitNode;
	
	//Graph 
	private Graph graph;
	
	//A stack that will contain the path from the entrance to the exit of the labyrinth
	private Stack<Node> labyrinthSolution;
	
	//constructor
	Labyrinth(String inputFile) {
		
		BufferedReader text;
		
		//Tries to read find and store the information
		try {
			
			text = new BufferedReader(new FileReader(inputFile));
			numberNodes = 0;

			roomSize = Integer.parseInt(text.readLine());
			width = Integer.parseInt(text.readLine());
			length = Integer.parseInt(text.readLine());
			regularBombs = Integer.parseInt(text.readLine());
			acidBombs = Integer.parseInt(text.readLine());

			//Number of nodes in the Labyrinth
			numberNodes = width * length;

			graph = new Graph(numberNodes);

			int i, row = 0;
			char[][] labyrinth;
			String line;
		

			labyrinth = new char[2 * length - 1][2 * width - 1];
			
			// Read the labyrinth from the file
			for (;;) {
				
				line = text.readLine();
				if (line == null) { // End of file
					text.close();
					return;
				}

				//Loops through text file storing the data into the graph
				for (i = 0; i < line.length(); ++i) {
					
					labyrinth[row][i] = line.charAt(i);
					
					//Algorithms used to find the corresponding Nodes that are needed to create the edge
					Node horizontalNode1 = graph.getNode((row/2)*width + (i-1)/2);
					Node horizontalNode2 = graph.getNode((row/2)*width + (i + 1)/2);
					
					Node verticalNode1 =graph.getNode(((row+1)/2)*width + i/2);
					Node verticalNode2 = graph.getNode(((row-1)/2)*width + i/2);
					
					//Switch cases which will handle the type of char that corresponds to the wall
					switch (labyrinth[row][i]) {
					//Horizontal walls
					case 'h':
						graph.insertEdge(horizontalNode1, horizontalNode2, "wall");
						break;
					case 'H':
						graph.insertEdge(horizontalNode1, horizontalNode2, "thickwall");
						break;
					case 'm':
						graph.insertEdge(horizontalNode1, horizontalNode2, "metalwall");
						break;
					case '-':
						graph.insertEdge(horizontalNode1, horizontalNode2, "corridor");
						break;
					
					//Verticals walls
					case 'v':
						graph.insertEdge(verticalNode1, verticalNode2, "wall");
						break;
					case 'V':
						graph.insertEdge(verticalNode1, verticalNode2, "thickwall");
						break;
					case 'M':
						graph.insertEdge(verticalNode1, verticalNode2, "metalwall");
						break;
					case '|':
						graph.insertEdge(verticalNode1, verticalNode2, "corridor");
						break;
					
					//Nodes
					case 'b':
						//Algorithm based on the verticalNode and horizontalNode to find the current Node location
						entranceNode = graph.getNode((row / 2) * width + i / 2);
						break;
					case 'x':
						//Algorithm based on the verticalNode and horizontalNode to find the current Node location
						exitNode = graph.getNode((row / 2) * width + i / 2);
						break;
					case '+':
						break;
					case ' ':
						break;
					}
				}
				++row;
			}
		} 
		
		catch (Exception e) {
			System.out.println("Error opening file");
		}
	}
	
	//getter method to return the graph of the labyrinth
	public Graph getGraph() throws LabyrinthException {
		if(graph != null) {
			return graph;
		}
		else {
			throw new LabyrinthException("LabyrinthException thrown");
		}
	}
	
	//returns an iterator that contains the information of the solved labyrinth
	public Iterator<Node> solve() throws GraphException, LabyrinthException {
		
			labyrinthSolution = new Stack<Node>();
			
			if (findPath(entranceNode)) {
				return labyrinthSolution.iterator();
			}
			
			else {
				return null;
			}
	}
	
	//recursive algorithm that finds the path from the entrance to the exit of the labyrinth
	private boolean findPath(Node current) throws GraphException {
		
		//Initializes variables that will be used throughout the algorithm
		Node nextNode;
		Edge tempEdge;
		Iterator<Edge> nodeEdgesIterator = graph.incidentEdges(current);
		current.setMark(true);
		labyrinthSolution.push(current);
		
		//Base Case
		if (current.equals(exitNode)) {
			return true;
		}
		
		//loops through all incident edges of the node in search of an edge that is valid
		while (nodeEdgesIterator.hasNext()) {
			tempEdge = nodeEdgesIterator.next();
			nextNode = tempEdge.secondEndpoint();
			
			//Checks to see if the 2nd end point is marked
			if (tempEdge.secondEndpoint().getMark() == false) {
				
				//Checks to see if the edge type is a corridor
				if (tempEdge.getType() == "corridor") {
	
					//Recursive call with the new node
					if (findPath(nextNode)) {
						return true;
					}
				}
				
				//Checks to see if the edge type is a wall
				else if (tempEdge.getType() == "wall") {
					
					//Checks to see if there is a valid amount of bombs available to blow up the wall
					if (regularBombs != 0 && normalBombsUsed < regularBombs) {
						normalBombsUsed++;
						
						//Recursive call with the new node
						if (findPath(nextNode)) {
							return true;
						}
					}
				}
				
				//Checks to see if the edge type is thick wall
				else if (tempEdge.getType() == "thickwall") {
					
					//Checks to see if there is a valid amount of acid bombs available to blow up the wall
					if (regularBombs > 1 && regularBombs - normalBombsUsed  > regularBombs) {
						normalBombsUsed+= 2;
						
						//Recursive call with the new node
						if (findPath(nextNode)) {
							return true;
						}
					}
				}
				
				//Checks to see if the edge type is metal wall
				else if (tempEdge.getType() == "metalwall") {
					
					//Checks to see if there is a valid amount of acid bombs available to blow up the wall
					if (acidBombs != 0 && acidBombsUsed < acidBombs) {
						acidBombsUsed++;
						
						//Recursive call with the new node
						if (findPath(nextNode))
							return true;
						}
					}
				}
			
				//If there are no more incident edges then the solution path works backwards
				if (nodeEdgesIterator.hasNext() == false) {
					
					//Unmarks the node 
					labyrinthSolution.peek().setMark(false);
					
					//Initializes temporary variables that will be used in algorithm
					Node tempyNode = labyrinthSolution.pop();
					Edge checkEdge = graph.getEdge(labyrinthSolution.peek(), tempyNode);
					
					//Checks to see if the edge type required a bomb to be used, if so it restores the bombs used
					if (checkEdge.getType() == "wall") {
						normalBombsUsed --;
					}
					
					else if (checkEdge.getType() == "thickwall") {
						normalBombsUsed -= 2;
					}
					else if (checkEdge.getType() == "metalwall") {
						acidBombsUsed --;
					}
				}		
			}
		
		return false;
	}
}
