package com.sample.todoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sample.todoapp.R;

import com.sample.todoapp.ToDoApplication;
import com.sample.todoapp.data.Constants;
import com.sample.todoapp.data.ToDoAdapter;

public class ToDoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        View.OnClickListener{

    ToDoAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        initViews();
    }

    private void initViews(){
        findViewById(R.id.btn_add).setOnClickListener(this);
        ListView listView = (ListView) findViewById(R.id.list_todo);
        mAdapter = new ToDoAdapter(((ToDoApplication)getApplication()).getToDoList());
        listView.setAdapter(mAdapter);
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent
     *         The AdapterView where the click happened.
     * @param view
     *         The view within the AdapterView that was clicked (this
     *         will be a view provided by the adapter)
     * @param position
     *         The position of the view in the adapter.
     * @param id
     *         The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        launchDetails(ToDoDetailActivity.LAUNCH_MODE.VIEW,position);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v
     *         The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        launchDetails(ToDoDetailActivity.LAUNCH_MODE.ADD,0);
    }

    /**
     * Launch details activity with appropriate mode
     * @param mode the launch mode
     */
    private void launchDetails(ToDoDetailActivity.LAUNCH_MODE mode,int position){
        Intent intent = new Intent(this,ToDoDetailActivity.class);
        intent.putExtra(Constants.BUNDLE_KEY_LAUNCH,mode.ordinal());
        intent.putExtra(Constants.BUNDLE_KEY_POSITION,position);
        startActivityForResult(intent,Constants.ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Handle the result
        if(requestCode == Constants.ACTIVITY_REQUEST_CODE){
            switch (resultCode){
                case Constants.ACTIVITY_RESULT_CODE:
                    mAdapter.setList(((ToDoApplication)getApplication()).getToDoList());
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
//        super.onActivityResult(requestCode, resultCode, data);
    }
}
