
public class BinarySearchTree implements BinarySearchTreeADT {
	
	//instance variable
	private BinaryNode root;
	
	//constructor
	public BinarySearchTree() {
		root = new BinaryNode();
	}
	
	//will find the BinaryNode that contains the key Location in the BinarySearchTree given r using the findNode
	//private method. If the key is not found in the tree then it will return null
	public Pixel get (BinaryNode r, Location key){
		BinaryNode node = findNode(r, key);
		
		if (node.getData() != null) {
			return node.getData();
		}
		else {
			return null;
		}
	}
	
	//private method that returns a BinaryNode given a key 
	private BinaryNode findNode (BinaryNode r, Location key){
		int check;//variable that contains the result of comparing two locations
		BinaryNode current = r; //starts at the root and then is updated once compared to the key
		
		//iterates through the tree until current contains the key or current is a leaf node
		while (! current.isLeaf())
		{
			check = current.getData().getLocation().compareTo(key);//compares the searching location
			if (check == 0) {
				break;
			}
			//iterates through the BST depending on the value of compareTo method.
			else if (check == 1) {
				current = current.getLeft();
			}
			else if (check == -1) {
				current = current.getRight();
			}
		}
		return current;
	}
	
	//adds a pixel to the BinarySearchTree given r using the findNode private method
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException{
		BinaryNode current = findNode(r, data.getLocation()); //finds the node where the pixel will be inserted

		//if the pixel already exists then throw an exception
		if(current.getData() != null) {
			throw new DuplicatedKeyException();
		}
		else {
			//sets the data to the BinaryNode and has parent/left/right nodes point to the right nodes
			current.setData(data);
			current.setLeft(new BinaryNode());
			current.setRight(new BinaryNode());
			current.getLeft().setParent(current);
			current.getRight().setParent(current);
		}
	}

	//removes a node containing the location key from the BinarySearchTree
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		
		//if the key is not found in the BinarySearchTree then throw an exception
		if (get(r, key) == null) {
			throw new InexistentKeyException();
		}
		
		//
		else
		{
			BinaryNode node=findNode(r, key);
			BinaryNode nodeRight=node.getRight(); 
			BinaryNode nodeLeft=node.getLeft();
			
			//case 1: no children
			if (nodeRight.getData()==null && nodeLeft.getData()==null)
			{
				//sets the right, left, and the right/left parent nodes to null, and
				//then sets the data value to null;
				node.setLeft(null);
				node.setRight(null);
				node.setData(null);
				nodeRight.setParent(null);
				nodeLeft.setParent(null);
			}
			//case 2: 1 child (right child)
			else if (nodeRight.getData()!=null && nodeLeft.getData()==null)
			{
				if(node == root) { //checks to see if the node is the root
					root = nodeRight;
					nodeRight.setParent(null);
				}
				else {
					if(node.getParent().getData().getLocation().compareTo(nodeRight.getData().getLocation()) == 1){
						node.getParent().setLeft(nodeRight);
					}
					else {
						node.getParent().setRight(nodeRight);;
					}
				
					nodeRight.setParent(node.getParent());
					node.setParent(null);
					node.setRight(null);
					node.setLeft(null);
					node.setData(null);
				}
					
			}
			//case 3: 1 child (left child)
			else if (nodeRight.getData()==null && nodeLeft.getData()!=null)
			{
				if(node == root) {//checks to see if the node is a root
					root = nodeLeft;
					nodeLeft.setParent(null);
				}
				else {
					if(node.getParent().getData().getLocation().compareTo(nodeLeft.getData().getLocation()) == 1) {
						node.getParent().setLeft(nodeLeft);
					}
					else {
						node.getParent().setRight(nodeLeft);
					}
					
					nodeLeft.setParent(node.getParent());
					node.setParent(null);
					node.setRight(null);
					node.setLeft(null);
					node.setData(null);
				}
			}
			//case 3: 2 children
			else{
				BinaryNode smallest = smallestNode(nodeRight);
				node.setData(smallest.getData());
				remove(smallest, smallest.getData().getLocation());
			}	
		}
	}
	
	//traverses the BinarySearchTree and looks for the node containing a key that is smallest in the tree but
	//larger than the key
	public Pixel successor(BinaryNode r, Location key) {
		BinaryNode rParent = new BinaryNode();
		Pixel theData = r.getData();
		int check;
		
		//traverses through the tree comparing the locations 
		while(theData != null) {
			check = r.getData().getLocation().compareTo(key);
			
			if(check == 1) {
				rParent = r;
				r = r.getLeft();
			}
			//check == -1
			else {
				r=r.getRight();
			}
			theData = r.getData();
		}
		return rParent.getData();
	}
	
	//traverses the BinarySearchTree and looks for the node containing a key that is largest in the tree but
	//smaller than the key
	public Pixel predecessor(BinaryNode r, Location key) {
		BinaryNode rParent = new BinaryNode(); //the variable that will contain the predecessor
		Pixel theData = r.getData();
		int check;
		
		//traverses through the tree comparing the locations
		while(theData != null) {
			check = r.getData().getLocation().compareTo(key);
			
			if(check == -1) {
				rParent = r;
				r = r.getRight();
			}
			//check == 1
			else {
				r = r.getLeft();
			}
			theData = r.getData();
		}
		return rParent.getData();
	}
	
	//traverses each left node in the tree until the last node (that isn't a leaf node) and then returns it
	private BinaryNode smallestNode(BinaryNode r) {
		BinaryNode node = r;
		if (node.getData()!=null) {
			
			while (node.getLeft().getData()!=null) {
				node=node.getLeft();
			}
		}
		return node;
	}
	
	//finds the node with the smallest location in the BinarySearchTree using the smallestNode private method
	public Pixel smallest(BinaryNode r) throws EmptyTreeException{
		BinaryNode node = smallestNode(r);
		
		if(node.getData() != null) {
			return node.getData();
		}
		else {
			throw new EmptyTreeException(); //if the tree is empty it will throw an exception
		}
	}
	
	//traverses each right node in the tree until the last node (that isn't a leaf node) and then returns it
	private BinaryNode largestNode(BinaryNode r) {
		BinaryNode node = r;
		
		if(node.getData() != null) {
			
			while(node.getRight().getData() != null) {
				node = node.getRight();
			}
		}
		return node;
	}
	
	//finds the node with the largest location in the BinarySearchTree using the largestNode private method
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		BinaryNode node = largestNode(r);
		
		if(node.getData() != null) {
			return node.getData();
		}
		else {
			throw new EmptyTreeException();//if the tree is empty it will return an error
		}
	
	}
	
	//returns the root node
	public BinaryNode getRoot() {
		return root;
	}
}

