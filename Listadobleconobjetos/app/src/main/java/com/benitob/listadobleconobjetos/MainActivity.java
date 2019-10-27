/*
 * Copyright (c) 2018 Benito Barreiro <BenitoBarreiro89@gmail.com>
 */

package com.benitob.listadobleconobjetos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.benitob.listadobleconobjetos.adapters.AuthorAdapter;
import com.benitob.listadobleconobjetos.adapters.BookAdapter;
import com.benitob.listadobleconobjetos.model.Author;
import com.benitob.listadobleconobjetos.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Author> authors = new ArrayList<Author>();
    private List<Book> tolkienBooks = new ArrayList<Book>();
    private List<Book> georgeBooks = new ArrayList<Book>();

    private Spinner spAuthors;
    private ListView lvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spAuthors = findViewById(R.id.sp_authors);
        lvBooks = findViewById(R.id.lv_books);

        createBooks();
        createAuthors();

        AuthorAdapter authorAdapter = new AuthorAdapter(this, authors);
        final BookAdapter bookAdapter = new BookAdapter(this, new ArrayList<Book>(){});

        spAuthors.setAdapter(authorAdapter);
        spAuthors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Author a = (Author) adapterView.getSelectedItem();
                bookAdapter.LoadBooks(a.getBooks());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lvBooks.setAdapter(bookAdapter);
        lvBooks.setOnItemClickListener((adapterView, view, i, l) -> {
            Book b = (Book) adapterView.getItemAtPosition(i);
            System.out.println(b);
            Bundle bundle = new Bundle();
            bundle.putSerializable("book", b);
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    private void createAuthors(){
        authors.add(new Author(R.color.colorPrimaryDark, "Selecciona un autor", new ArrayList<Book>(){}));
        authors.add(new Author(R.mipmap.tolkien, "Tokien", tolkienBooks));
        authors.add(new Author(R.mipmap.george, "George", georgeBooks));
    }

    private void createBooks(){
        Book esdla1 = new Book( "La comunidad del anillo", "Gandalf decide confiarle" +
                " el anillo único, un anillo super poderoso y muy chungo, a un hobbit vago que" +
                " no sé yo si será muy confiable", R.mipmap.esdla1);
        tolkienBooks.add(esdla1);
        Book esdla2 = new Book("Las dos torres", "Se llama las dos torres pero " +
                "en verdad la acción se desarrolla en el camino, entre barro y sangre. Los hobbit" +
                " siguen su camino, la gente se mata... en fin, lo típico", R.mipmap.esdla2);
        tolkienBooks.add(esdla2);
        Book esdla3 = new Book("El retorno del rey", "Contra todo pronóstico, y con ayuda" +
                " de Gollum, un bicho muy raro con pintas de yonki de barrio chungo, " +
                "los hobbit hacen su trabajo y el Rey vuelve. Aunque nunca se había ido, " +
                "solo que no era rey. Pero ahora ya si. Ah, y gandalf se va y se lleva los hobbits" +
                " con él al otro lado del charco, a la tierra de los elfos.", R.mipmap.esdla3);
        tolkienBooks.add(esdla3);

        Book got1 = new Book("Juego de tronos", "Se llama juego de tronos pero que no os" +
                " engañe, no se juega con tronos. Se cuenta una historia bastante triste sobre una " +
                "gente a la que le encanta matarse y fornicar. Oh, y tiene perritos, aunque en verdad" +
                " son lobos grandes.", R.mipmap.got1);
        georgeBooks.add(got1);
        Book got2 = new Book("Choque de reyes", "Pese a lo que dice el título, los reyes" +
                " no chocan en absoluto. Son de esos que prefieren que sus súbditos hagan las cosas" +
                " por ellos mientras sus reales culos están calientes y a salvo. Hay un montón de " +
                "sangre (para no variar) y también algo de fornicio, que se ve que vende.", R.mipmap.got2);
        georgeBooks.add(got2);
        Book got3 = new Book("Tormenta de espadas", "Por fin un libro que hace honor a " +
                "su título. Aquí hay un montón de espadas, aunque no es una tormenta real, si no una " +
                "metáfora de la cantidad de soldados que van a morir por nada. También tiene" +
                " bastante protagonismo la chavalita esta rubia que le gusta a todo el mundo pero " +
                "que en el libro se ve que todos la quieren matar por algo.", R.mipmap.got3);
        georgeBooks.add(got3);
    }

}
