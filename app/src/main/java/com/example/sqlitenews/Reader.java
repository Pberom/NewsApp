package com.example.sqlitenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Reader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        List<item> items = new ArrayList<item>();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getdataNews();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        MyAdapter adapter;

        Admin admin = new Admin();
        //Проверка на наличие данных
        if (cursor.getCount() != 0){
            //Цикл для перебора и объединения данных
            while (cursor.moveToNext()){
                items.add(new item(cursor.getString(0), cursor.getString(1)));
            }
        }
        MyAdapter.OnStateClickListener stateClickListener = new MyAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(item items, int pos) {
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(stateClickListener, getApplicationContext(), items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnExit).setOnClickListener(view -> {
            Intent intent = new Intent(Reader.this, MainActivity.class);
            startActivity(intent);
        });
    }
}