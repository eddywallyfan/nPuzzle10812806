/* John Wesselingh
 * 10812806
 * ImageSelection menu
 */
package nl.mprog.projects.nPuzzle10812806;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import android.content.Intent;

 
public class ImageSelection extends ListActivity {
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] FOTO = this.getResources().getStringArray(R.array.FOTO);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,FOTO));
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int pos, long arg3) {
	        Intent i= new Intent(ImageSelection.this,GamePlay.class);
	        startActivity(i);
	        finish();                       
			}
		});
	}
	public class MobileArrayAdapter extends ArrayAdapter<String> {
		private final Context context;
		private final String[] values;
	 
		public MobileArrayAdapter(Context context, String[] values) {
			super(context, R.layout.list_item, values);
			this.context = context;
			this.values = values;
		}
	 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
			View rowView = inflater.inflate(R.layout.list_item, parent, false);
			TextView textView = (TextView) rowView.findViewById(R.array.FOTO);
			ImageView imageView = (ImageView) rowView.findViewById(R.drawable.hoogwerker);
			textView.setText(values[position]);
	 
			// Change icon based on name
			String s = values[position];
	 
			System.out.println(s);
	 
			if (s.equals("hoogwerker")) {
				imageView.setImageResource(R.drawable.hoogwerker);
			} else if (s.equals("knipper")) {
				imageView.setImageResource(R.drawable.knipper);
			} else if (s.equals("zonnigdak")) {
				imageView.setImageResource(R.drawable.zonnigdak);
			} else {
				imageView.setImageResource(R.drawable.ic_launcher);
			}
	 
			return rowView;
		}
	
	}
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