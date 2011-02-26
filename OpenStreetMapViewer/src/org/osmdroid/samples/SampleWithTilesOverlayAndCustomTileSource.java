package org.osmdroid.samples;

import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.tileprovider.util.CloudmadeUtil;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.TilesOverlay;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 
 * @author Alex van der Linden
 * 
 */
public class SampleWithTilesOverlayAndCustomTileSource extends Activity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private MapView mOsmv;
	private TilesOverlay mTilesOverlay;
	private MapTileProviderBasic mProvider;
	private ITileSource mCustomTileSource;

	// ===========================================================
	// Constructors
	// ===========================================================
	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Setup base map
		final RelativeLayout rl = new RelativeLayout(this);

		CloudmadeUtil.retrieveCloudmadeKey(getApplicationContext());

		this.mOsmv = new MapView(this, 256);
		rl.addView(this.mOsmv, new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		this.mOsmv.setBuiltInZoomControls(true);

		// zoom to the netherlands
		this.mOsmv.getController().setZoom(7);
		this.mOsmv.getController().setCenter(new GeoPoint(51500000, 5400000));

		// Add tiles layer with custom tile source
		this.mProvider = new MapTileProviderBasic(getApplicationContext());
		this.mCustomTileSource = new XYTileSource("FietsRegionaal", null, 3, 18, 256, ".png",
				"http://overlay.openstreetmap.nl/openfietskaart-rcn/");
		this.mProvider.setTileSource(this.mCustomTileSource);
		this.mTilesOverlay = new TilesOverlay(mProvider, this.getBaseContext());
		this.mOsmv.getOverlays().add(this.mTilesOverlay);

		this.setContentView(rl);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}