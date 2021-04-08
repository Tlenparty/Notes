package com.geekbrains.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    /*
    1. Создайте класс данных со структурой заметок: название заметки, описание заметки, дата создания
    и т. п.
    2. Создайте фрагмент для вывода этих данных. +
    3. Встройте этот фрагмент в активити. У вас должен получиться экран с заметками, который мы будем
    улучшать с каждым новым уроком. +
    4. Добавьте фрагмент, в котором открывается заметка. По аналогии с примером из урока: если нажать на
    элемент списка в портретной ориентации — открывается новое окно, если нажать в ландшафтной —
    окно открывается рядом.
    5. * Разберитесь, как можно сделать, и сделайте корректировку даты создания при помощи DatePicker.

     */

    FloatingActionButton fab;
    TextView tvTitle1, tvDescription1;

    Publisher publisher = new Publisher();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        onSetListeners();
       // Убедимся что у нас портретный режим
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
          // finish(); // Закрываем активити
            setFragmentSecond();

        }

    }


    private void initViews() {
        fab = findViewById(R.id.fab);
        tvTitle1 = findViewById(R.id.tvTitle1);
        tvDescription1 = findViewById(R.id.tvDescription1);
    }

    private void setFragmentSecond() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new NoteFragment())
                .commit();
    }

    private void onSetListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}