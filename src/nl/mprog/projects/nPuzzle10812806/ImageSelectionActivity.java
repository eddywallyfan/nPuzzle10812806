/* John Wesselingh
 * 10812806
 * ImageSelection menu
 * Activity die je laat kiezen tussen de mogelijke puzzels
 */

package nl.mprog.projects.nPuzzle10812806;

import android.app.ListActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;

//import android.graphics.BitmapFactory.Options;

public class ImageSelectionActivity extends ListActivity implements OnItemClickListener {
	
	// Stel de layout van de activity in
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);
        
        CustomListAdapter adapter = new CustomListAdapter(this);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }
    
    // Ga naar gameplay, met de juiste foto als een item wordt aangeklikt
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {	
    	Intent i = new Intent(this, GamePlayActivity.class);
    	
    	TextView tv = (TextView) v;
    	Long imageId = (Long)tv.getTag();
    	
    	i.putExtra("imageId", imageId.intValue());
    	startActivity(i);
    }
}