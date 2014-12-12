/* John Wesselingh
 * 10812806
 * CustomListAdapter
 * Een class die ervoor zorgt dat de listview in ImageSelection een plaatje en tekst heeft
 */
package nl.mprog.projects.nPuzzle10812806;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.BaseAdapter;

public class CustomListAdapter extends BaseAdapter {
	
	private final ThumbnailManager thumbman;
	private final int WIDTH = 120;
	private final int HEIGHT = 120;
	private Context context;
	
	public CustomListAdapter(Context context) {
		
		this.context = context;
		thumbman = new ThumbnailManager(context,WIDTH,HEIGHT);
	}
	
	@Override
	public View getView(int position, View v, ViewGroup parent) {

		if(v == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			v = inflater.inflate(R.layout.list_item, null);
		}
		
		TextView tv = (TextView)v.findViewById(R.id.adapterTextView);
		tv.setText(thumbman.getName(position));
		tv.setCompoundDrawablesWithIntrinsicBounds(thumbman.getThumbnail(position),null,null,null);
		tv.setTag(thumbman.getItemId(position));
		
		return v;
	}
	
	@Override
	public int getCount() {
		return thumbman.getCount();
	}
	
	@Override
	public Object getItem(int position) {
		return null;
	}
	
	@Override
	public long getItemId(int position) {
		return thumbman.getItemId(position);
	}
}
