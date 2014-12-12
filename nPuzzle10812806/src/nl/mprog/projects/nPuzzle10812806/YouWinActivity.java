/* John Wesselingh
 * 10812806
 * YouWin Activity, om te feliciteren met winst
 */
package nl.mprog.projects.nPuzzle10812806;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class YouWinActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winpage);
		}
	
	// Methode voor het knopje
	public void hoofdmenu (View view) {
		Intent intent = new Intent (this, ImageSelectionActivity.class);
		startActivity(intent);
		}
}
