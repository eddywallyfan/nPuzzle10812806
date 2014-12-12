/* John Wesselingh
 * 10812806
 * Activity waar het spel in plaats vind
 */
package nl.mprog.projects.nPuzzle10812806;

import android.app.Activity;
import android.view.Menu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.os.Bundle;


public class GamePlayActivity extends Activity {
	
	protected static final int BASE_ID = 10;
	private final int ERROR = -1;
	private final int SCREEN_PADDING = 20;
	
	private GameClass gamecontroller;
	private int screenWidth;
	private int screenHeight;
	private int dimension;
	private Bitmap scaledImg;
	private int tileWidth;
	private int tileHeight;
	private boolean clickable = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_gameplay);
		
		// Make een game controller die de methodes uit game implementeert
		gamecontroller = new GameClass(this);
				
		// Vraag het schermformaat op
		screenWidth = this.getResources().getDisplayMetrics().widthPixels;
		screenHeight = this.getResources().getDisplayMetrics().heightPixels;
		
		// Zorg dat het plaatje wordt aangepast aan het scherm
		Intent i = this.getIntent();
		int imageId = i.getIntExtra("imageId", ERROR);
		scaledImg = scaleBitmap(imageId);
		
		// Start het spel
		gamecontroller.start();
	
	}
	
	@Override
	protected void onPause() {
		
		gamecontroller.saveGame();
		
		super.onPause();
	}
	
	@Override
	public void onBackPressed() {
		gamecontroller.changeImage();
	}
	
	// Maak de puzzel
	public void createPuzzle(int[] config, int dimension, boolean clickable) {
				
		RelativeLayout root = (RelativeLayout) findViewById(R.id.root_layout);
		
		// Haal de oude views weg
		root.removeAllViews();
		
		this.dimension = dimension;
		this.clickable = clickable;
		
		// Zorg ervoor dat de grootte van de tegels klopt met het plaatje en de moeilijkheid
		tileWidth = scaledImg.getWidth() / dimension;
		tileHeight = scaledImg.getHeight() / dimension;
		
		// Voeg rijen toe aan de layout
		for(int i = 0; i < dimension; i++) {
			root.addView(createRow(config,i));
		}
		
		root.addView(createScoreBoard(root));
				
	}
	
	// Maak rijen en maak er aparte linearlayouts van
	private LinearLayout createRow(int[] config, int row) {
		
		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout newRow = (LinearLayout) inflater.inflate(R.layout.puzzle_row, null);
		
		newRow.setId(BASE_ID - 1 - row);
		
		if(row != 0) {
			final LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			p.addRule(RelativeLayout.BELOW, BASE_ID - row);
			p.addRule(RelativeLayout.ALIGN_LEFT, BASE_ID - row);
			
			newRow.setLayoutParams(p);
		}
		
		int id, index;
		
		// Maak tegels en voeg ze toe aan de rij
		for(int i = 0; i < dimension; i++) {
			index = row * dimension + i;
			id = config[index];
			newRow.addView(createTile(id));
		}
		
		return newRow;
	}
	
	// Zorg dat de delen van de foto op een tegel passen
	private ImageView createTile(int id) {
		
		LayoutInflater inflater = LayoutInflater.from(this);
		ImageView imgView = (ImageView) inflater.inflate(R.layout.puzzle_tile, null);
		
		if(id == 0) {
			setBlankTile(imgView);
		} else {
			setNormalTile(imgView,id);
		}
			
		return imgView;
	}
	
	// Voeg de foto er aan toe
	private ImageView createTile(int id, Drawable drawable) {
		
		LayoutInflater inflater = LayoutInflater.from(this);
		ImageView imgView = (ImageView) inflater.inflate(R.layout.puzzle_tile, null);
		
		imgView.setId(id);
		imgView.setImageDrawable(drawable);
		
		if(id != BASE_ID) {
			imgView.setOnClickListener(gamecontroller);
		}
		
		return imgView;
	}
	
	private void setNormalTile(ImageView imgView, int id) {

		Bitmap content = null;
		
		// Kent elke tegel zijn eigen ID toe
		imgView.setId(id + BASE_ID);
		
		// Trek er 1 vanaf omdat de array bij 0 begint
		id--;
		
		int row = id / dimension;
		int col = id - row * dimension;
		
		// Hak de foto in stukjes en plak hem aan de view
		try {
			content = Bitmap.createBitmap(scaledImg, col * tileWidth, row * tileHeight, tileWidth, tileHeight);
			
		} catch (OutOfMemoryError e) {
			Log.e("Npuzzle","GamePlay:OutOfMemory:setNormalTile");
		}
		
		imgView.setImageBitmap(content);
		
		if(clickable) {
			imgView.setOnClickListener(gamecontroller);
		}
	}
	
	// Zorgt ervoor dat de blanco tegel op zijn plek komt
	private void setBlankTile(ImageView imgView) {
		
		Bitmap content = null;
		
		imgView.setId(BASE_ID);
		
		try {
			Bitmap origin = BitmapFactory.decodeResource(this.getResources(), R.drawable.blanktile);
			content = Bitmap.createScaledBitmap(origin, tileWidth, tileHeight, false);
			origin.recycle();
			
		} catch (OutOfMemoryError e) {
			Log.e("Npuzzle","GamePlay:OutOfMemory:setBlankTile");
		}
		
		// Zorg ervoor dat er niet op geklikt kan worden
		imgView.setImageBitmap(content);
		imgView.setClickable(false);
	}
	
	// Maak het scorebord
	private LinearLayout createScoreBoard(RelativeLayout root) {
		
		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout scoreView = (LinearLayout) inflater.inflate(R.layout.scoreview, null);
		
		final LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		if(screenWidth > screenHeight) {
			p.addRule(RelativeLayout.ALIGN_TOP, BASE_ID - 1);
			p.addRule(RelativeLayout.RIGHT_OF, BASE_ID - 1);
		} else {
			p.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			p.addRule(RelativeLayout.ALIGN_RIGHT, BASE_ID - 1);
			root.setIgnoreGravity(R.id.scoreView);
		}
		
		scoreView.setLayoutParams(p);
		
		return scoreView;
	}
	
	private double getMin(double x, double y) {
		
		double result = x;
		
		if(Double.compare(y, x) <= 0) {
			result = y;
		}
		
		return result;
	}
	
	// Kijk of de gemaakte bitmap niet teveel geheugen vraagt en maak hem op schaal
	private Bitmap createScaledBitmap(Bitmap origin) throws OutOfMemoryError {
		
		Bitmap scaledImg = null;
		
		// Kijk hoe groot het originele plaatje is
		int imageWidth = origin.getWidth();
		int imageHeight = origin.getHeight();
		
		// Bereken de verhouding van het plaatje tot het scherm
		double ratio1 = (double)screenWidth / imageWidth;
		double ratio2 = (double)screenHeight / imageHeight;
		double ratio = getMin(ratio1,ratio2);
				
		// Een beetje padding zodat het iets netter staat
		int scaledWidth = (int) (imageWidth * ratio) - SCREEN_PADDING;
		int scaledHeight = (int) ((imageHeight - SCREEN_PADDING) * ratio);
		
		// Recycle de oude bitmap met deze ivm geheugenbesparing
		scaledImg = Bitmap.createScaledBitmap(origin, scaledWidth, scaledHeight, false);
		origin.recycle();
			
		return scaledImg;
	}

	
	// Simpele 'maak bitmap op schaal' methode met geheugen check
	// Maakt gebruik van de vorige method
	private Bitmap scaleBitmap(int id) {
			
		Bitmap scaledImg = null;
			
		try {
			//get the image from it's id that got passed in
			Bitmap origin = BitmapFactory.decodeResource(this.getResources(), id);
			scaledImg = createScaledBitmap(origin);
				
		} catch (OutOfMemoryError e) {
			Log.e("Npuzzle","GamePlay:OutOfMemory:scaledBitmap " + "image id is " + id);
		}
			
		return scaledImg;
	}
	
		
	// Update de layout als een tegel wordt aangetikt
	public void updateLayout(int id) {
		
		ImageView blank = (ImageView) findViewById(BASE_ID);
		ImageView tile = (ImageView) findViewById(id);
		
		LinearLayout blankParent = (LinearLayout)blank.getParent();
		LinearLayout tileParent = (LinearLayout)tile.getParent();
		
		int blankPosition = blankParent.indexOfChild(blank);
		int tilePosition = tileParent.indexOfChild(tile);
		
		Drawable blankDrawable = blank.getDrawable();
		Drawable tileDrawable = tile.getDrawable();
		
		blankParent.removeViewAt(blankPosition);
		blankParent.addView(createTile(id,tileDrawable),blankPosition);
		
		tileParent.removeViewAt(tilePosition);
		tileParent.addView(createTile(BASE_ID,blankDrawable),tilePosition);
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.hoofdmenu, menu);
		return true;
	}
	// Menu opties
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
			case R.id.easy: gamecontroller.changeDifficulty(GameClass.EASY); break;
			case R.id.medium: gamecontroller.changeDifficulty(GameClass.MEDIUM); break;
			case R.id.hard: gamecontroller.changeDifficulty(GameClass.HARD); break;
			case R.id.back: gamecontroller.changeImage(); break;
			default:
		}
		
		return true;
	}
}
