package com.watson.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NotesAdapter extends ArrayAdapter<Notes>
{
    private Context context;
    private List<Notes> notes;

    public NotesAdapter(Context context, List<Notes> list)
    {
        super(context, R.layout.rownotes_layout, list);
        this.context = context;
        this.notes = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       convertView = inflater.inflate(R.layout.rownotes_layout, parent, false);
        TextView tvtitle = convertView.findViewById(R.id.tvtitle);

        tvtitle.setText(notes.get(position).getTitle());

        return convertView;
    }
}
