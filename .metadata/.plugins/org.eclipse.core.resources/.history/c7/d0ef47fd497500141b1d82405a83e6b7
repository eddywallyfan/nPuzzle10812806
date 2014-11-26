/* John Wesselingh
 * 10812806
 * ImageSelection menu
 */
package nl.mprog.projects.nPuzzle10812806;

import android.app.ListActivity;
import android.content.Intent;
//import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

 
public class ImageSelection extends ListActivity {
 
	static final String[] FOTO = new String[] { "Foto 1", "Foto 2", "Foto 3"};
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,FOTO));
 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		//listView.setOnItemClickListener(new OnItemClickListener() {
			
			
		
 
	
	
	listView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> arg0, View arg1,int pos, long arg3) {
	        Intent i= new Intent(ImageSelection.this,GamePlay.class);
	        startActivity(i);
	        finish();                       
	    }
	});}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_selection, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}