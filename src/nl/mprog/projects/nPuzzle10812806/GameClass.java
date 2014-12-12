/* John Wesselingh
 * 10812806
 * Methodes in het spel
 */

package nl.mprog.projects.nPuzzle10812806;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.TextView;

public class GameClass implements OnClickListener  {
	
	private static final String GAME_SETTING = "GameSetting";
	private static final String BOARD_CONFIG = "boardConfig";
	private static final String LEVEL = "level";
	private static final String NUM_MOVES = "numMoves";
	public static final int EASY = 3;
	public static final int MEDIUM = 4;
	public static final int HARD = 5;
	
	private BordClass game;
	private SharedPreferences setting;
	private final GamePlayActivity owner;
	private boolean save;
	
	public GameClass(GamePlayActivity owner) {
		
		// Vraag de difficulty op
		setting = owner.getSharedPreferences(GAME_SETTING, Context.MODE_PRIVATE);
		int difficulty = setting.getInt(LEVEL, MEDIUM);
		
		// Laat de oplossing zien
		game = new BordClass(difficulty);
		this.owner = owner;
		save = true;
	}
	
	public void start() {
		// Zorg ervoor dat de oplossing 3 seconden blijft staan
		// en laat daarna een toast zien bij start
		int dimension = game.getDimension();
		owner.createPuzzle(game.getBoardConfig(), dimension, false);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				shuffle();
				Toast.makeText(owner, "Start!", Toast.LENGTH_SHORT).show();
			}
		}, 3000);
	}
	
	public void changeDifficulty(int difficulty) {
		game = new BordClass(difficulty);
		shuffle();
	}
	
	public void delayedShuffle() {
		shuffle();
	}
	
	public void shuffle() {
		game.shuffle();
		owner.createPuzzle(game.getBoardConfig(), game.getDimension(),true);
		saveGame();
	}
	
	public void changeImage() {
		save = false;
		owner.finish();
	}
	
	public int getNumMoves() {
		return game.getNumMoves();
	}
		
	private String configToString(int[] config) {
		
		String result = "";
		
		for(int i : config) {
			result += String.valueOf(i) + " ";
		}
		
		return result;
	}
	
	public void saveGame() {
		
		SharedPreferences setting = owner.getSharedPreferences(GAME_SETTING,Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
		
		editor.clear();
		
		if(save) {
			editor.putString(BOARD_CONFIG, configToString(game.getBoardConfig()));
			editor.putInt(NUM_MOVES, game.getNumMoves());
		}
		
		editor.putInt(LEVEL, game.getDimension());
		
		editor.commit();
	}
	
	public void onClick(View v) {
		
		int id = v.getId() - GamePlayActivity.BASE_ID;
		
		if(game.isValidMove(id)) {
			game.makeMove(id);
			
			TextView tv = (TextView) owner.findViewById(R.id.numMoves);
			tv.setText(String.valueOf(game.getNumMoves()));
			
			owner.updateLayout(v.getId());
			if(game.isWin()) {
				Intent i = new Intent(owner,YouWinActivity.class);
				i.putExtra("numMoves", game.getNumMoves());
				owner.startActivity(i);
				save = false;
				owner.finish();
			}
		}
		
	}
}