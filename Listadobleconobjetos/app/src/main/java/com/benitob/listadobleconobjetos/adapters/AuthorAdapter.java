/*
 * Copyright (c) 2018 Benito Barreiro <BenitoBarreiro89@gmail.com>
 */

package com.benitob.listadobleconobjetos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.benitob.listadobleconobjetos.R;
import com.benitob.listadobleconobjetos.model.Author;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AuthorAdapter extends ArrayAdapter<Author> {

    private Context context;
    private final List<Author> authors;

    public AuthorAdapter(@NonNull Context context, @NonNull List<Author> objects) {
        super(context, R.layout.item_author, objects);
        this.context = context;
        this.authors = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.item_author, parent, false);
            holder = new ViewHolder();
            holder.ivImage = v.findViewById(R.id.author_image);
            holder.tvName = v.findViewById(R.id.author_name);
            holder.tvBooksWritten = v.findViewById(R.id.author_written);
            v.setTag(holder);
        }else
            holder = (ViewHolder) v.getTag();
        Author a = authors.get(position);
        holder.ivImage.setImageResource(a.getImage());
        holder.tvName.setText(a.getName());
        holder.tvBooksWritten.setText(context.getString(R.string.str_books_written, a.booksWritten()));

        return v;
    }

    @Override
    public boolean isEnabled(int position) {
        return position != 0;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    static class ViewHolder{
        public ImageView ivImage;
        public TextView tvName;
        public TextView tvBooksWritten;
    }
}
