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
import com.benitob.listadobleconobjetos.model.Book;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BookAdapter extends ArrayAdapter<Book> {

    private Context context;
    private List<Book> books;

    public BookAdapter(@NonNull Context context, @NonNull List<Book> objects) {
        super(context, R.layout.item_book, objects);
        this.context = context;
        this.books = objects;
    }

    public void LoadBooks(List<Book> list){
        books.clear();
        for (Book b : list)
            books.add(b);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
            holder = new ViewHolder();
            holder.ivImage = v.findViewById(R.id.book_image);
            holder.tvTitle = v.findViewById(R.id.book_title);
            holder.tvDesc = v.findViewById(R.id.book_desc);
            v.setTag(holder);
        }else
            holder = (ViewHolder) v.getTag();
        Book b = books.get(position);
        holder.ivImage.setImageResource(b.getImage());
        holder.tvTitle.setText(b.getTitle());
        holder.tvDesc.setText(b.getDesc());

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    static class ViewHolder{
        public ImageView ivImage;
        public TextView tvTitle;
        public TextView tvDesc;
    }
}
