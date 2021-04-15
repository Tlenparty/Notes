package com.geekbrains.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

/*    private static final String CURRENT_NOTE = "CurrentTitle"; // Ключ для доступа к заголовку
    private Note currentNote;
    private boolean isLandscape;
    private String description = "Описание";*/

    RecyclerView recyclerView;
    FloatingActionButton fab;
    MyAdapter myAdapter;
    List<Note> noteList;
    DataBaseClass dataBaseClass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initPopupMenu(view);
        initView(view);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), SecondActivity.class);
            startActivity(intent);
        });

        noteList = new ArrayList<>();
        dataBaseClass = new DataBaseClass(getActivity());
        fetchAllNotesFromDatabase();
        // Отображение списка ч/з layoutManager. getActivity = context.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(getContext(), noteList); // Уточнить про активити
        recyclerView.setAdapter(myAdapter);
    }

    private void fetchAllNotesFromDatabase() {
        Cursor cursor = dataBaseClass.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "Нет данных чтобы показать", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                noteList.add(new Note(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }
    // Удаление всех записок


    /*private void initPopupMenu(View view) {
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setOnClickListener(v -> {
            Activity activity = requireActivity();
            PopupMenu popupMenu = new PopupMenu(activity, v);
            activity.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            Menu menu = popupMenu.getMenu();
            menu.findItem(R.id.tvDescription).setVisible(false);
            menu.add(0, 123, 12, "kek"); // Добавление програмно
            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                switch (id) {
                    case R.id.resend:
                        Toast.makeText(getContext(), "Вы переслали заметку", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.delete:
                        Toast.makeText(getContext(), "Вы удалили заметку", Toast.LENGTH_LONG).show();
                        return true;
                    case 123:
                        Toast.makeText(getContext(), "kek", Toast.LENGTH_LONG).show();
                        return true;
                }
                return false;
            });
            popupMenu.show();
        });
    }*/


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // outState.putParcelable(CURRENT_NOTE, currentNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*// Определение, можно ли будет расположить рядом герб в другом фрагменте
        // Проверка на ориентацию
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        // Если это не первое создание, то восстановим текущую позицию
        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            // Если перевернули восстанавливаем текущее значение
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            // Если восстановить не удалось, то сделаем объект с первым индексом
            currentNote = new Note(0, getResources().getStringArray(R.array.title)[0], description);
        }

        if (isLandscape) {
            showLandNotes(currentNote);
        }*/
    }

/*    private void showNote(Note currentNote) {
        if (isLandscape) {
            showLandNotes(currentNote);
        } else {
            showPortNotes(currentNote);
        }
    }*/
/*
    private void showLandNotes(Note currentNote) {
      *//*  SecondFragment fragment = SecondFragment.newInstance(currentNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_second, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();*//*
    }

    private void showPortNotes(Note currentNote) {
        // Открываем вторую активити
        Intent intent = new Intent();
        intent.setClass(getActivity(), SecondActivity.class);
        intent.putExtra(SecondFragment.CURRENT_TITLE, currentNote);
        startActivity(intent);
    }*/
}