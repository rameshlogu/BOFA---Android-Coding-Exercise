package com.sample.googleplaces.activity;

import android.app.Dialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.sample.googleplaces.R;
import com.sample.googleplaces.data.loader.PlaceDetailsLoader;
import com.sample.googleplaces.data.model.PlaceDetails;
import com.sample.googleplaces.util.Constants;
import com.sample.googleplaces.util.URLStore;

import java.util.Locale;

/**
 * This is Main Activity which will present the Google Place details. Here, place details will be
 * fetched and displayed.
 * <p/>
 * Created by rameshloganathan on 14/05/16.
 */
public class PlaceDetailsActivity extends AppCompatActivity implements LoaderManager
        .LoaderCallbacks<PlaceDetails>, View.OnClickListener {

    private static final int LOADER_ID = 1;
    private Dialog mLoadingDialog;
    private PlaceDetails mPlaceDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.place_detail_title));
        initialize();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            getLoaderManager().initLoader(LOADER_ID, bundle, this);
        }
    }

    /**
     * Initialize views
     */
    private void initialize() {
        // Create loading dialog
        mLoadingDialog = new Dialog(this,
                R.style.Dialog_Load);
        mLoadingDialog.setContentView(R.layout.progress_spinner);
        mLoadingDialog.setCanceledOnTouchOutside(false);

        findViewById(R.id.open_map_btn).setOnClickListener(this);
    }

    /**
     * Place the address in Google Maps application
     */
    private void openMaps() {
        String uri = URLStore.URL_GOOGLE_MAP+mPlaceDetails.getFormattedAddress();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }


    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id
     *         The ID whose loader is to be created.
     * @param args
     *         Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<PlaceDetails> onCreateLoader(int id, Bundle args) {
        mLoadingDialog.show();
        return new PlaceDetailsLoader(this, args.getString(Constants.KEY_ID));
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     * <p/>
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     * <p/>
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context,
     * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     *
     * @param loader
     *         The Loader that has finished.
     * @param data
     *         The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(Loader<PlaceDetails> loader, PlaceDetails data) {
        mLoadingDialog.dismiss();
        TextView address = (TextView) findViewById(R.id.place_address);
        if (data != null) {
            if (data.isSuccess()) {
                mPlaceDetails = data;
                address.setText(mPlaceDetails.getFormattedAddress());
                findViewById(R.id.open_map_btn).setVisibility(View.VISIBLE);
            } else {
                address.setText(data.getError().getMessage());
            }
        }
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * @param loader
     *         The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<PlaceDetails> loader) {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v
     *         The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        openMaps();
    }
}
