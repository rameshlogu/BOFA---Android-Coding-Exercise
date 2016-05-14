package com.sample.googleplaces.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sample.googleplaces.R;

/**
 * This is Main Activity which will present the landing page of application. {@link SearchView}
 * widget has been initialized here to perform the Google Places search.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchView
        .OnCloseListener {

    //Search Widget
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setup tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Initializes the Search Widget
     *
     * @param menu
     *         the inflated menu
     */
    private void initializeSearchWidget(Menu menu) {
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Current activity is the searchable activity
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setOnSearchClickListener(this);
    }

    /**
     * Close the search widget programatically when required.
     */
    private void closeSearchWidget(){
        mSearchView.onActionViewCollapsed();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        initializeSearchWidget(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean handled;
        switch (id) {
            case android.R.id.home:
                //Handle home/back button
                closeSearchWidget();
                handled = true;
                break;
            case R.id.action_search:
                handled = true;
                break;
            default:
                handled = super.onOptionsItemSelected(item);

        }
        return handled;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view
     *         The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * The user is attempting to close the SearchView.
     *
     * @return true if the listener wants to override the default behavior of clearing the
     * text field and dismissing it, false otherwise.
     */
    @Override
    public boolean onClose() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        return false;
    }

    @Override
    public void onBackPressed() {
        //Handle back press appropriately
        if(!mSearchView.isIconified()){
            closeSearchWidget();
        } else {
            super.onBackPressed();
        }
    }
}
