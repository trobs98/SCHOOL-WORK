
public class Pixel {
	
	//Instance variables
	private Location pLoc;
	private int pColor;
	
	//constructor
	public Pixel(Location p, int color) {
		pColor = color;
		pLoc = p;
	}
	
	public Location getLocation() {
		return pLoc;
	}
	
	public int getColor() {
		return pColor;
	}
}
