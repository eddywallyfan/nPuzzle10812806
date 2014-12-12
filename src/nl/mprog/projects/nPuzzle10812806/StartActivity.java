/* John Wesselingh
 * 10812806
 * Beginscherm
 */
package nl.mprog.projects.nPuzzle10812806;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startscherm);
	}

	public void hoofdmenu (View view) {
		Intent intent = new Intent (this, ImageSelectionActivity.class);
		startActivity(intent);
		}
}