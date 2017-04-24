package com.example.kh.vi.Module;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kh.vi.R;

import java.util.ArrayList;

/**
 * Created by kh on 4/23/2017.
 */

public class MyAdapter extends ArrayAdapter<Integer> {
    ArrayList<Integer> arrayList;
    Context context;
    int ressource;

LayoutInflater inflater;


    public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Integer> objects) {
        super(context, resource, objects);
        this.arrayList  = objects;
        this.context = context;
        this.ressource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View v  = convertView;
        ViewHolder holder= null;
    if(v==null){
        holder = new ViewHolder();
        v  = inflater.inflate(R.layout.listdata,null);
        holder.txt = (TextView) v.findViewById(R.id.txtdata);
        v.setTag(holder);
    }else {
        holder  = (ViewHolder) convertView.getTag();
        }
        holder.txt.setText(arrayList.get(position).toString());
        return v;
    }
   private class ViewHolder{
       private TextView txt;
   }
}
