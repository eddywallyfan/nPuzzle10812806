/* John Wesselingh
 * 10812806
 * ImageSelection menu
 */
package nl.mprog.projects.nPuzzle10812806;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.content.Intent;
 
public class ImageSelection extends ListActivity {
 
	static final String[] FOTO = new String[] { "Foto 1", "Foto 2", "Foto 3"};
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,FOTO));
 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
				Intent newActivity = new Intent(this, GamePlay.class);     
                startActivity(newActivity);
			}
		});
 
	}
	
}