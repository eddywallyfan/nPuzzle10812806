package nl.mprog.projects.nPuzzle10812806;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void hoofdmenu (View view) {
		Intent intent = new Intent (this, ImageSelection.class);
		startActivity(intent);
		}
}
