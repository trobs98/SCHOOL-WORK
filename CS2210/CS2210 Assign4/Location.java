public class Location {

	//Instance variables
	private int xCoord,yCoord;
	
	//constructor
	public Location(int x, int y) {
		xCoord = x;
		yCoord = y;
	}

	public int xCoord() {
		return xCoord;
	}
	
	public int yCoord() {
		return yCoord;
	}
	
	//compares the location of this object v.s. the location of the location object p
	//returns 1 if this location > p location
	//returns -1 if this location < p location
	//returns 0 if this location = p location
	public int compareTo(Location P) {
		
		if(xCoord < P.xCoord()) {
			return -1;
		}
		else if(xCoord > P.xCoord()) {
			return 1;
		}
		else{
			
			if(yCoord < P.yCoord()) {
				return -1;
			}
			else if(yCoord > P.yCoord()) {
				return 1; 
			}
			else {
				return 0;
			}
		}
	}
}
