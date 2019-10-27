/*
 * Copyright (c) 2018 Benito Barreiro <BenitoBarreiro89@gmail.com>
 */

package com.benitob.listadobleconobjetos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.benitob.listadobleconobjetos.model.Book;

public class BookDetailActivity extends AppCompatActivity {

    Button btBack;
    TextView tvTitle, tvDesc;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        btBack = findViewById(R.id.btBack);
        tvTitle = findViewById(R.id.bookdetail_title);
        tvDesc = findViewById(R.id.bookdetail_desc);
        ivImage = findViewById(R.id.bookdetail_image);

        Bundle bundle = getIntent().getExtras();
        Book book = (Book) bundle.getSerializable("book");

        tvTitle.setText(book.getTitle());
        tvDesc.setText(book.getDesc());
        ivImage.setImageResource(book.getImage());

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
