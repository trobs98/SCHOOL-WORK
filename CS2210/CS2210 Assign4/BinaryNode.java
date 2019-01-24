
public class BinaryNode {
	
	//instance variables
	private Pixel pValue;
	private BinaryNode nLeft;
	private BinaryNode nRight;
	private BinaryNode nParent;
	
	//constructor
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		pValue = value;
		nLeft = left;
		nRight = right;
		nParent = parent;
	}
	
	public BinaryNode() {
		pValue = null;
		nLeft = null;
		nRight = null;
		nParent = null;
	}
	
	public BinaryNode getParent() {
		return nParent;
	}
	
	public void setLeft(BinaryNode p) {
		nLeft = p;
	}
	
	public void setRight(BinaryNode p) {
		nRight = p;
	}
	
	public void setParent(BinaryNode p) {
		nParent = p;
	}
	
	public void setData(Pixel value) {
		pValue = value;
	}
	
	//if the node doesn't have a left and right node and also does not contain any data then it is a leaf node 
	public boolean isLeaf() {
		if(getRight() == null && getLeft() == null && getData() == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Pixel getData() {
		return pValue;
	}
	
	public BinaryNode getLeft() {
		return nLeft;
	}
	
	public BinaryNode getRight() {
		return nRight;
	}
}
