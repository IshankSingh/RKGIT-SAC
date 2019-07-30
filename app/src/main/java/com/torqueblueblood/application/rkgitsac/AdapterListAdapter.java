package com.torqueblueblood.application.rkgitsac;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterListAdapter extends ArrayAdapter<Adapter> {
    private Context mcontext;
    int mResource;

    public AdapterListAdapter(@NonNull Context context, int resource, @NonNull List<Adapter> objects) {
        super(context, resource, objects);
        mcontext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name=getItem(position).getNames();
        String event=getItem(position).getEventdetails();

        Adapter adapter=new Adapter(name,event);

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView=inflater.inflate(mResource,parent,false);
        TextView namesview=convertView.findViewById(R.id.nameslistview);
        TextView eventview=convertView.findViewById(R.id.detailstextview);

        namesview.setText(name);
        eventview.setText(event);

        return convertView;
    }
}
