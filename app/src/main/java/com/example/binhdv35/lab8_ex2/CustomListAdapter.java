package com.example.binhdv35.lab8_ex2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.binhdv35.lab8_ex2.Fragment.HomeFragment;
import com.example.binhdv35.lab8_ex2.model.Items;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private final Activity context;
    private List<Items> items;
    private HomeFragment homeFragment;

    public CustomListAdapter(Context cont, ArrayList<Items> items,
                             HomeFragment homeFragment) {
        this.context = (Activity) cont;
        this.items = items;
        this.homeFragment = homeFragment;
    }
    static class ViewHolder {
        public TextView txtTitle;
        public ImageView imageViewDelete, imageViewDone;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_row, parent,
                    false);
            holder.txtTitle = (TextView)
                    convertView.findViewById(R.id.textView);
            holder.imageViewDone = (ImageView)
                    convertView.findViewById(R.id.imageView_done);
            holder.imageViewDelete = (ImageView)
                    convertView.findViewById(R.id.imageView_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Items todoItem = items.get(position);
        holder.txtTitle.setText(todoItem.getTitle());
        boolean isDone = todoItem.isDone();
        if (isDone) {
            holder.imageViewDone.setImageResource(R.drawable.baseline_double_arrow_24);
            holder.txtTitle.setPaintFlags(holder.txtTitle.getPaintFlags()|
                    Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            holder.imageViewDone.setImageResource(R.drawable.baseline_sticky_note_2_24);
            holder.txtTitle.setPaintFlags(holder.txtTitle.getPaintFlags() &
                    (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        holder.imageViewDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (todoItem.isDone)
                    todoItem.isDone = false;
                else
                    todoItem.isDone = true;
                homeFragment.updateTodoItemDetails(todoItem, position);
            }
        });
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                homeFragment.deleteTodoItem(position);
            }
        });
        return convertView;
    }
}
