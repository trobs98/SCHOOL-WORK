
public class TTTDictionary implements TTTDictionaryADT{
	
	//instance variables
	private node<TTTRecord> [] dict;
	private int count, size;
	
	//constructor for creating a TTTDictionary with the size size
	public TTTDictionary(int size) {
		this.size = size;
		dict = (node<TTTRecord>[])new node[size];
	}
	
	//hash function + compression function
	private int hash(String configs) {
		
		int sum = 0;
		String str = configs;
		for (int i = 0; i < str.length(); i++){
		    sum = Math.abs(31*sum) + (int)(str.charAt(i));
		}
		int compression = sum % this.size;
		return (compression);
	}
	
	
	public int put (TTTRecord record) throws DuplicatedKeyException{
		
		int returnValue = 0;//represents whether or not the putting of the TTTRecord into the dictionary was successful or not
		int hashValue = hash(record.getConfiguration());
		node<TTTRecord> configNode = dict[hashValue]; //the node that is originally at the hash value of the input record
		
		if(configNode == null) { //checks to see if original node is null
			node<TTTRecord> recordNode = new node<TTTRecord>(record);//
			this.count ++;//increase the number of elements in dictionary
			dict[hashValue] = recordNode; // puts the input record into the dictionary as a node
			return 0;//means putting was successful
		}
		
		while(configNode != null) {// occurs when the original node IS NOT null and loops till the end of current bucket to add new node
			
			if(configNode.getData().getConfiguration().equals(record.getConfiguration())) { //if the original configuration string is the same as the inputted one then it will throw an exception
				throw new DuplicatedKeyException();
				}
			
			else if(configNode.getNext() == null){// when the original node is not empty and isnt the same as the inputted one then a collision has occured
				
				node<TTTRecord> recordNode = new node<TTTRecord> (record); //creates the inputted TTTRecord as a node
				configNode.setNext(recordNode);
				configNode.getNext().setPrev(configNode);
				this.count ++;
				
				while (configNode.getPrev() != null){ //links all the nodes correctly 
					
					if (configNode.getPrev().getPrev() == null){
						dict[hashValue] = configNode.getPrev();
					}
					configNode = configNode.getPrev();
				}
				returnValue = 1;//means a collision has occured
				break;
			}
			else{
				configNode = configNode.getNext();
			}
		}
	return returnValue;
				
	}
			
	public void remove(String config) throws InexistentKeyException {
		
		boolean found = false;//represents whether the config was found or not
		int hashValue = hash(config);
		node<TTTRecord> configNode = dict[hashValue];//node with the given config
		
		if(configNode == null) { // makes sure that node exists
			throw new InexistentKeyException();
		}
		else {
			while (configNode != null){ // when the node does exist 
				if (configNode.getData().getConfiguration().equals(config)){
					found = true;
					if (configNode.getPrev() == null && configNode.getNext() == null){
						dict[hashValue] = null;
						this.count --;
						break;
					}
					else if(configNode.getPrev() == null && configNode.getNext() != null){
						dict[hashValue] = configNode.getNext();
						configNode.getNext().setPrev(null);
						this.count --;
						break;
					}
					else{
						configNode.getPrev().setNext(configNode.getNext());
						configNode.getNext().setPrev(configNode.getPrev());
						while (configNode.getPrev() != null){
							if (configNode.getPrev().getPrev() == null){
								dict[hashValue] = configNode.getPrev();
								this.count --;
								break;
							}
							configNode = configNode.getPrev();
						}
					}
				}
				else {
					configNode = configNode.getPrev();
				}
			}
			if (found==false){
				throw new InexistentKeyException();
			}
		}	
	}
	
	public TTTRecord get(String config) {
		
		boolean found = false;// represents if we found it or not
		int hashvalue = hash(config);
		TTTRecord returnRecord = null;//represents the TTTRecord that we are trying to return corresponding to the config
		node<TTTRecord> configNode = dict[hashvalue];//represents node originally in hash value
		
		if (configNode != null){ 
			while (configNode != null){//loops through all the nodes in the bucket 
				if (configNode.getData().getConfiguration().equals(config)){
					found = true;
					returnRecord = configNode.getData();
					break;
				}
				else{
					configNode = configNode.getNext();
				}
			}
		}
		if (found == false){
			returnRecord = null;
		}
		return returnRecord;
	}
	
	public int numElements() { // returns the number of elements that are currently in the dictionary
		return this.count;
	}
	
}

	


