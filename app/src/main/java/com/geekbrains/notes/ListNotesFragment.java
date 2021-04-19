package com.geekbrains.notes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;

public class ListNotesFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    NotesAdapter myAdapter;
    List<Note> noteList;
    DataBaseClass dataBaseClass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        initRecyclerView(view);
        initFabView(view);

    }

    private void initFabView(View view) {
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            AddNoteFragment fragment = new AddNoteFragment();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.add(R.id.fragment_container, fragment, "ADD").commit();
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        noteList = new ArrayList<>();
        dataBaseClass = new DataBaseClass(getActivity());
        fetchAllNotesFromDatabase();
        // Отображение списка ч/з layoutManager. getActivity = context. Будем работать со встроенным менеджером
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        // initRecyclerView
        recyclerView.setLayoutManager(linearLayoutManager);
        // Установим адаптер
        myAdapter = new NotesAdapter(getContext(), noteList); // Уточнить про активити
        recyclerView.setAdapter(myAdapter);
        // Добавим разделитель карточек
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),  LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);
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

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.card_menu,menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_update:
            Toast.makeText(getContext(),"обновить",Toast.LENGTH_LONG).show();
            return true;
            case R.id.action_delete:
            Toast.makeText(getContext(),"удалить",Toast.LENGTH_LONG).show();
            return  true;
        }
        return super.onContextItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    private void initPopupMenu(View view) {
        LinearLayout layout = view.findViewById(R.id.note_layout);  // Почему null ?((
        layout.setOnClickListener(v -> {
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
    }
}