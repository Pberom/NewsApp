package com.example.sqlitenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        List<item> items = new ArrayList<item>();
        EditText editText_NameNews = findViewById(R.id.editText_NameNews);
        EditText editText_TextNews = findViewById(R.id.editText_TextNews);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Cursor cursor = databaseHelper.getdataNews();
        MyAdapter adapter;

        final int[] position = new int[1];
        // определяем слушателя нажатия элемента в списке
        MyAdapter.OnStateClickListener stateClickListener = new MyAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(item items, int pos) {
                editText_NameNews.setText(items.getName());
                editText_TextNews.setText(items.getText());
                position[0] = pos;
            }
        };

        //Проверка на наличие данных
        if (cursor.getCount() != 0){
            //Цикл для перебора и объединения данных
            while (cursor.moveToNext()){
                items.add(new item(cursor.getString(0), cursor.getString(1)));
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(stateClickListener, getApplicationContext(), items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAddNews).setOnClickListener(view -> {
            if (!editText_NameNews.getText().toString().trim().equals("") && !editText_TextNews.getText().toString().trim().equals("")) {
                Boolean checkInsertData = databaseHelper.insertNews(editText_NameNews.getText().toString(), editText_TextNews.getText().toString());
                if (checkInsertData) {
                    items.add(items.size(), new item(editText_NameNews.getText().toString(), editText_TextNews.getText().toString()));
                    adapter.notifyItemInserted(items.size());
                    Toast.makeText(getApplicationContext(), "Данные успешно добавлены", Toast.LENGTH_LONG).show();
                } else Toast.makeText(getApplicationContext(), "Произошла ошибка!", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(getApplicationContext(), "Не все поля заполнены!", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btnDeleteNews).setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.deleteNews(editText_NameNews.getText().toString());
            if (checkInsertData) {
                items.remove(position[0]);
                adapter.notifyItemRemoved(position[0]);
                Toast.makeText(getApplicationContext(), "Данные успешно удалены", Toast.LENGTH_LONG).show();
            } else Toast.makeText(getApplicationContext(), "Произошла ошибка!", Toast.LENGTH_LONG).show();
        });

        findViewById(R.id.btnEditNews).setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.updateNews(editText_NameNews.getText().toString(), editText_TextNews.getText().toString());
            if (checkInsertData){
                items.set(position[0], new item(editText_NameNews.getText().toString(), editText_TextNews.getText().toString()));
                adapter.notifyItemChanged(position[0]);
                Toast.makeText(getApplicationContext(), "Данные успешно обновлены", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
        });

        findViewById(R.id.btnExit).setOnClickListener(view -> {
            Intent intent = new Intent(Admin.this, MainActivity.class);
            startActivity(intent);
        });
    }
}