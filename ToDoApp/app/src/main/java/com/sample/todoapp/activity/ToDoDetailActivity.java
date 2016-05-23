package com.sample.todoapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sample.todoapp.R;
import com.sample.todoapp.ToDoApplication;
import com.sample.todoapp.data.Constants;
import com.sample.todoapp.data.ToDo;

/**
 * Created by rameshloganathan on 23/05/16.
 */
public class ToDoDetailActivity extends AppCompatActivity implements View.OnClickListener{
    public enum LAUNCH_MODE {ADD,VIEW};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_to_do_detail);
        initViews();
    }

    private void initViews(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int launchModePos = bundle.getInt(Constants
                            .BUNDLE_KEY_LAUNCH);
            LAUNCH_MODE launchMode = LAUNCH_MODE.values()[launchModePos];
            switch (launchMode){
                case ADD:
                    View addBtn = findViewById(R.id.btn_add_detail);
                    addBtn.setOnClickListener(this);
                    addBtn.setVisibility(View.VISIBLE);
                    break;
                case VIEW:
                    int dataPosition = bundle.getInt(Constants.BUNDLE_KEY_POSITION);
                    ToDo toDo = ((ToDoApplication)getApplication()).getToDoList().get(dataPosition);
                    populateView(toDo);
                    break;
            }
        }
    }

    private void populateView(ToDo toDo){
        if(toDo != null){
            //Title
            TextView textView = ((TextView)findViewById(R.id.txt_name));
            textView.setText(toDo.getName());
            textView.setEnabled(false);

            //Detail
            textView = ((TextView)findViewById(R.id.txt_detail));
            textView.setText(toDo.getDescription());
            textView.setEnabled(false);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view
     *         The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        String name = ((TextView)findViewById(R.id.txt_name)).getText().toString();
        String desc = ((TextView)findViewById(R.id.txt_detail)).getText().toString();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(desc)){
            ToDo toDo = new ToDo();
            toDo.setName(name);
            toDo.setName(desc);
            ((ToDoApplication)getApplication()).getToDoList().add(toDo);
        }

        setResult(Constants.ACTIVITY_RESULT_CODE);
        finish();
    }

}
