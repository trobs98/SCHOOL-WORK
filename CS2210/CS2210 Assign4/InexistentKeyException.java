//class for when a searched key does not exist
public class InexistentKeyException extends Exception {
	
	public InexistentKeyException() {
		System.out.println("InexistentKeyException: This key does not exist.");
	}
}
