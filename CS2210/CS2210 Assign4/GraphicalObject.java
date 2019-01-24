
public class GraphicalObject implements GraphicalObjectADT {
	
	//Instance variables
	private int id, width, height;
	private String type;
	private Location pos;
	private BinarySearchTree bst = new BinarySearchTree();
	
	//constructor
	public GraphicalObject(int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Location getOffset() {
		return this.pos;
	}
	
	public void setOffset(Location value) {
		this.pos = value;
	}
	
	//adds a pixel to the BinarySearchTree
	//if the pixel already exists then it will throw a DuplicatedKeyException
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		if(bst.get(bst.getRoot(), pix.getLocation()) != null) {
			throw new DuplicatedKeyException();
		}
		else {
			bst.put(bst.getRoot(), pix);
		}
	}
	
	//determines if two graphical objects intersect 
	public boolean intersects(GraphicalObject gobj){
		
		try {
			//sets the BinarySearchTree for this Graphical Object and for the other 
			//Graphical object were looking at
			BinarySearchTree thisTREE = this.bst;
			BinarySearchTree otherTREE = gobj.bst;
			
			//saves the smallest locations for both trees which will be updated from smallest location to largest
			//as we compare locations
			Location thisSmallestLoc = thisTREE.smallest(thisTREE.getRoot()).getLocation();
			Location otherSmallestLoc = otherTREE.smallest(otherTREE.getRoot()).getLocation();
			
			//saves the largest locations of both trees
			Location thisLargestLoc = thisTREE.largest(thisTREE.getRoot()).getLocation();
			Location otherLargestLoc = otherTREE.largest(otherTREE.getRoot()).getLocation();
			
			//variables so we can save and check the locations in both trees(continually changed)
			int x,y;
			
			//check variable that saves the result of comparing two locations (continually updated)
			int check;
			
			while(true) {
				
				//saves the location of the node were looking at in this Graphical Object's BinarySearchTree
				Location this_Offset = this.getOffset();
				x = thisSmallestLoc.xCoord() + this_Offset.xCoord();
				y = thisSmallestLoc.yCoord() + this_Offset.yCoord();
				Location thisLocation = new Location(x,y);
				
				//saves the location of the node were looking at in the other Graphical Object's BinarySearchTree
				Location other_Offset = gobj.getOffset();
				x = otherSmallestLoc.xCoord() + other_Offset.xCoord();
				y = otherSmallestLoc.yCoord() + other_Offset.yCoord();
				Location otherLocation = new Location(x,y);
				
				//compares the current locations of both trees
				check = thisLocation.compareTo(otherLocation);
				
				//when this location > other location (DO NOT INTERSECT)
				if(check == 1) {
					//Checks to see if we have reached the last location in this BinarySearchTree.
					//If so, then that means the two graphical objects did not intersect.
					//If not, then we update the current location of this BinarySearchTree to the successor. 
					if(otherSmallestLoc.compareTo(otherLargestLoc) == 0) {
						return false;
					}
					else {
						otherSmallestLoc = otherTREE.successor(otherTREE.getRoot(), otherSmallestLoc).getLocation();
					}
				}
				//when this location < other location (DO NOT INTERSECT)
				else if(check == -1) {
					//Checks to see if we have reached the last location in the other BinarySearchTree.
					//If so, then that means the two graphical objects did not intersect
					//if not, then we update the current location of the other BinarySearchTree to the successor.
					if(thisSmallestLoc.compareTo(thisLargestLoc) == 0) {
						return false;
					}
					else {
						thisSmallestLoc = thisTREE.successor(thisTREE.getRoot(), thisSmallestLoc).getLocation();
					}
				}
				//when this location = other location (DO INTERSECT)
				else {
					return true;
				}
			}
		}
		//throw this exception when there is no tree for the graphical object (ex.boundaries of graphic window)
		catch(EmptyTreeException e) {
			return false;
		}
	}
}