//Throws this exception when trying to add a key into a BinarySearchTree that already exists
public class DuplicatedKeyException extends Exception {
	
	public DuplicatedKeyException() {
		System.out.println("DuplicatedKeyException: key already exists.");
	}
}
