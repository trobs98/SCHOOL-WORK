
public class BlockedTicTacToe {
	
	//instance variables
	private int boardSize, in_line, maxLevels;
	private char[][] gameBoard;
	
	//constructor 
	public BlockedTicTacToe (int board_size, int inline, int max_levels) {
		this.boardSize = board_size;
		this.in_line = inline;
		this.maxLevels = max_levels;
		gameBoard = new char[board_size][board_size];
		for(int i=0; i<board_size; i++) { //loops through each position of the new board, giving each location a blank space
			for(int j=0; j<board_size; j++) {
				gameBoard[i][j] = ' ';
			}
		}
	}
	
	//creates a string representation of the current gameboard
	private String createConfig() { 
		String config = "";
		for(int i=0; i<gameBoard.length; i++) { 
			for(int j=0; j<gameBoard[i].length; j++) {
				config += gameBoard[j][i]; //loops through the current gameboard starting at top left, and then moving from top to bottom and left to right
			}
		}
		return config;
	}
	
	public TTTDictionary createDictionary(){ 
		int size = 5013; //array size
		TTTDictionary dict = new TTTDictionary(size);
		return dict;
	}
	
	public int repeatedConfig(TTTDictionary configurations) {
		String config = createConfig(); //creates configuration of current board
		if(configurations.get(config) != null) { //if current configuration is equal to the one that is being looked at then return the score
			return configurations.get(config).getScore();
		}
		else { // if not return -1
			return -1;
		}
	}
	
	public void insertConfig(TTTDictionary configurations, int score, int level){
		String config = createConfig();//creates configuration of current board
		TTTRecord configEntry = new TTTRecord(config, score, level);
		try {
			configurations.put(configEntry);
		}
		catch(DuplicatedKeyException e) {
		}
	}
	
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	public boolean squareIsEmpty(int row, int col) {
		if(gameBoard[row][col] == ' ') {
			return true;
		}
		else {
			return false;
		}
	}
	
	//checks to see if the symbol has won in a horizontal manner or not
	private boolean horizontalChecker(char symbol) {
		int numOfCharachters = 0; //counts the number of characters that occur sequentially horizontally
		int y_coordinate = 0;
		boolean checker = false; //represents if a win has occurred horizontally or not
		while (y_coordinate < boardSize && checker == false) {
			
			for(int i=0; i<boardSize; i++) { //loops through the board horizontally from top to bottom
				if(symbol == gameBoard[i][y_coordinate]) {
					numOfCharachters++;
				} 
			}
			
			if(numOfCharachters == in_line) {
				checker = true;
				break;
			}
			else {
				numOfCharachters = 0;
				y_coordinate++;
			}
		}
		return checker;
	}
	
	//checks to see if the symbol has won in a vertical manner or not
	private boolean verticalChecker(char symbol) {
		int numOfCharachters = 0; //represents the number of characters that occur sequentially vertically
		int x_coordinate = 0;
		boolean checker = false; //represents if a win has occurred vertically or not
		while (x_coordinate < boardSize && checker == false) {
			
			for(int i=0; i<boardSize; i++) { //loops through the board vertically from left to right
				if(gameBoard[x_coordinate][i] == symbol) {
					numOfCharachters++;
				}
			}
	
			if(numOfCharachters == in_line) {
				checker = true;
			}
			else {
				numOfCharachters = 0;
				x_coordinate ++;
			}
		}
		return checker;
	}
	
	//checks to see if the symbol has one in a diagonal manner
	private boolean diagnolChecker(char symbol) {
		
		int numOfCharachters1 = 0;//counts the number of the symbol that occur diagonally from top left to bottom right
		int numOfCharachters2 = 0;//counts the number of the symbol that occur diagonally from bottom left to top right
		int y_coordinate2 = boardSize-1;
		boolean checker = false;
		
		for(int i=0; i<boardSize; i++) { //loops through symbols on the board only looking at the diagonal from top left to bottom right
			if(gameBoard[i][i] == symbol) {
				numOfCharachters1++;
			}
		}
			
		for(int j=0; j<boardSize; j++) {//loops through the symbols on the board only looking at the diagonal from bottom left to top right
			if(gameBoard[j][y_coordinate2] == symbol) {
				numOfCharachters2 ++;
			}
			y_coordinate2 --;
		}
		if(numOfCharachters2 == in_line || numOfCharachters1 == in_line) {
			checker = true;
		}

		return checker;
	}

	public boolean wins(char symbol) {
		boolean checker = false;
		if(horizontalChecker(symbol) == true || verticalChecker(symbol) == true || diagnolChecker(symbol) == true){ //uses all three checkers to see if a win has occurred
			checker = true;
		}
		return checker;
	}
	
	public boolean isDraw() {
		
		boolean checker = true;
		for(int i=0; i < this.in_line; i++) { //loops through every symbol on the current board to see if a vacant space occurs
			for(int j=0; j < this.in_line; j++) {
				if ( gameBoard[i][j] == ' ') {
					checker = false;
				}
			}
		}
		return checker;
	}
	
	public int evalBoard() { //checks to see if a win or draw has occured or if the game is still incomplete
		char computer = 'o';
		char human = 'x';
		
		int humanWin = 0;
		int draw = 1;
		int stillUndecided = 2;
		int compWin = 3;
		
		if(wins(computer) == true) {
			return compWin;
		}
		else if(wins(human) == true) {
			return humanWin;
		}
		else if(isDraw() == true) {
			return draw;
		}
		else {
			return stillUndecided;
		}
	}
}
