package nl.mprog.projects.nPuzzle10812806;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Difficulty extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.menu.difficulty);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.difficulty, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Bericht als menuknop wordt ingedrukt, maak hier difficulty van en sla deze op
		String message = "";
		if (item.getItemId() == R.id.makkelijk){
			message = "De volgende puzzel is makkelijk";
		}
		else if (item.getItemId() == R.id.gemiddeld){
			message = "De volgende puzzel is normaal";
		}
		else if (item.getItemId() == R.id.moeilijk){
			message = "De volgende puzzel is moeilijk";
		}
		
		Toast toast = Toast.makeText (this, message, Toast.LENGTH_LONG);
		toast.show();
		return true;
	}
}
