package com.sample.todoapp.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sample.todoapp.R;

import java.util.List;

/**
 * This is a data adapter for to do list
 * Created by rameshloganathan on 23/05/16.
 */
public class ToDoAdapter extends BaseAdapter{
    private List<ToDo> list;

    public ToDoAdapter(List<ToDo> items){
        list = items;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position
     *         Position of the item whose data we want within the adapter's
     *         data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position
     *         The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position
     *         The position of the item within the adapter's data set of the item whose view
     *         we want.
     * @param convertView
     *         The old view to reuse, if possible. Note: You should check that this view
     *         is non-null and of an appropriate type before using. If it is not possible to convert
     *         this view to display the correct data, this method can create a new view.
     *         Heterogeneous lists can specify their number of view types, so that this View is
     *         always of the right type (see {@link #getViewTypeCount()} and
     *         {@link #getItemViewType(int)}).
     * @param parent
     *         The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.todo_list_item,null);
        }
        //Get data
        ToDo data = list.get(position);
        //Populate views
        ((TextView)convertView.findViewById(R.id.to_do_title)).setText(data.getName());
        ((TextView)convertView.findViewById(R.id.desc)).setText(data.getDescription());
        return convertView;
    }

    public List<ToDo> getList() {
        return list;
    }

    public void setList(List<ToDo> list) {
        this.list = list;
    }
}
