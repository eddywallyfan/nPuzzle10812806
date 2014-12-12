/* John Wesselingh
 * 10812806
 * Class om het "speelveld" te representerenS
 */
package nl.mprog.projects.nPuzzle10812806;

import java.util.Random;

public class BordClass {
    
	private int boardConfig[];
	private int dimension;
	private int numTiles;
	private int blankTilePos;
	private int numMoves;
	
	public BordClass(int dimension) {
		
		this.dimension = dimension;
		this.numTiles = dimension * dimension;
		blankTilePos = numTiles - 1;
		numMoves = 0;
		
		boardConfig = new int[numTiles];
		
		// Stel het bord (tegels) in
		for(int i = 0; i < numTiles; i++) {
			boardConfig[i] = i+1;
		}
		
		// De positie van de witte tegel
		boardConfig[blankTilePos] = 0;
	}
	
	public BordClass(int dimension, int[] config, int numMoves) {
		this(dimension);
		this.numMoves = numMoves;
		
		System.arraycopy(config, 0, boardConfig, 0, config.length);
		blankTilePos = findPosition(0);
	}
	
	public int getDimension() {
		return dimension;
	}
	
	// Check of er sprake is van een geldige move:
	// Grenst de aangeklikte tegel aan de blanco tegel
	public boolean isValidMove(int id) {
		
		int position = findPosition(id);
		
		boolean valid = false;
		
		valid = valid || ((blankTilePos - 1) == position);
		valid = valid || ((blankTilePos + 1) == position);
		valid = valid || ((blankTilePos - dimension) == position);
		valid = valid || ((blankTilePos + dimension) == position);
		
		return valid;
	}
	
	// Method die een klik op een tegel behandeld
	public void makeMove(int id) {
		
		int position = findPosition(id);
		
		swap(position,blankTilePos);
		
		blankTilePos = position;
		numMoves++;
	}
	
	// Check of de speler gewonnen heeft
	public boolean isWin() {
		
		boolean win = true;
		
		for(int i = 0; i < numTiles - 2; i++) {
			win = win && (boardConfig[i] < boardConfig[i+1]);
		}
		
		win = win && (boardConfig[numTiles - 1] == 0);
		
		return win;
	}
	
	// Vraag counter variabele op die het aantal moves heeft bijgehouden
	public int getNumMoves() {
		return numMoves;
	}
	
	// Schud het bord
	public void shuffle() {
		
		Random random = new Random();
		int position;
		
		for(int i = 0; i < 200 * dimension; i++) {
			position = random.nextInt(numTiles - 1);
			if(isValidMove(position)) {
				makeMove(position);
			}
		}
		
		numMoves = 0;
	}
	
	// Geef het bord terug als een array
	public int[] getBoardConfig(){
		
		int[] config = new int[numTiles];
		
		System.arraycopy(boardConfig, 0, config, 0, config.length);
		
		return config;
	}
	
	// Wissel de blanco tegel met de tegel die is aangeklikt
	private void swap(int x, int y) {
		int temp = boardConfig[x];
		boardConfig[x] = boardConfig[y];
		boardConfig[y] = temp;
	}
	
	// Geeft de positie van een tegel
	private int findPosition(int id) {
		
		int i = 0;
		boolean found = false;
		
		while(i < numTiles && !found) {
			if(boardConfig[i] == id) {
				found = true;
			} else {
				i++;
			}
		}
		
		return i;
	}
	
}
