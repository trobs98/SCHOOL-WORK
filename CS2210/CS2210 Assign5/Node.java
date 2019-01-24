
public class Node {
	
	//instance variables 
	private int name;
	private boolean mark;
	
	//constructor
	public Node(int name) {
		this.name = name;	
	}
	
	//setter method for the node's mark
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	//getter method for the node's mark
	public boolean getMark() {
		return mark;
	}
	
	//getter method for the node's name
	public int getName() {
		return name;
	}
	
}
