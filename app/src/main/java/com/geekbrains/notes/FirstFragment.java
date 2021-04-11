package com.geekbrains.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FirstFragment extends Fragment {

    private static final String CURRENT_NOTE = "CurrentTitle"; // Ключ для доступа к заголовку
    private Note currentNote;
    private boolean isLandscape;
    private String description = "Описание";

/*    public static FirstFragment newInstance(Note currentTitle) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_NOTE,currentTitle);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleList(view);
        initPopupMenu(view);
    }

    private void initPopupMenu(View view) {
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
    }


    private void initTitleList(View view) {
        LinearLayout linearLayoutView = (LinearLayout) view;
        String[] titles = getResources().getStringArray(R.array.title);
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            TextView tv = new TextView(getContext()); // создаем TextView
            tv.setText(title);
            tv.setTextSize(25);
            linearLayoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(v -> {
                currentNote = new Note(fi, getResources().getStringArray(R.array.title)[fi], description);
                showNote(currentNote);
            });
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, currentNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Определение, можно ли будет расположить рядом герб в другом фрагменте
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
        }
    }

    private void showNote(Note currentNote) {
        if (isLandscape) {
            showLandNotes(currentNote);
        } else {
            showPortNotes(currentNote);
        }
    }

    private void showLandNotes(Note currentNote) {
        SecondFragment fragment = SecondFragment.newInstance(currentNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_second, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNotes(Note currentNote) {
        // Открываем вторую активити
        Intent intent = new Intent();
        intent.setClass(getActivity(), SecondActivity.class);
        intent.putExtra(SecondFragment.CURRENT_TITLE, currentNote);
        startActivity(intent);
    }
}