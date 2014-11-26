/* John Wesselingh
 * 10812806
 * Speelscherm
 */
package nl.mprog.projects.nPuzzle10812806;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class GamePlay extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_play, menu);
		return true;
	}

	@Override
	// Deze methode zorgt ervoor dat de items in het menu goed worden doorgelinkt naar de juiste activity
	// of de juiste moeilijkheidsgraad
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
	    case R.id.makkelijk:
	        Intent intent = new Intent(this, GamePlay.class);
	        this.startActivity(intent);
	        break;
	    case R.id.middle:
	        // another startActivity, this is for item with id "menu_item2"
	        Intent intent1 = new Intent(this, GamePlay.class);
	        this.startActivity(intent1);
	    	break;
	    case R.id.moeilijk:
	        // another startActivity, this is for item with id "menu_item2"
	        Intent intent2 = new Intent(this, GamePlay.class);
	        this.startActivity(intent2);
	    	break;
	    case R.id.terug_naar_main:
	        // another startActivity, this is for item with id "menu_item2"
	        Intent intent3 = new Intent(this, ImageSelection.class);
	        this.startActivity(intent3);
	    	break;
	    default:
	        return super.onOptionsItemSelected(item);
	    }

	    return true;
	}
	// Spel
	
	// OnFinished ga naar YouWin
}
