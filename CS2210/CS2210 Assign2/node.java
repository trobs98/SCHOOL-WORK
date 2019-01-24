//node class to be used to allow multiple TTTRecords into the same bucket incase of a collision
public class node<TTTRecord> {
	private node<TTTRecord> next, prev;
	private TTTRecord data;
	
	public node (TTTRecord configData) {
		this.next = null;
		this.prev = null;
		this.data = configData;
	}
	
	public void setNext(node<TTTRecord> theNext) {
		next = theNext;
	}
	
	public node<TTTRecord> getNext(){
		return this.next;
	}
	
	public void setPrev(node<TTTRecord> thePrev) {
		prev = thePrev;
	}
	
	public node<TTTRecord> getPrev(){
		return this.prev;
	}
	
	public TTTRecord getData() {
		return this.data;
	}
}
