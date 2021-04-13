package com.geekbrains.notes;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class SecondFragment extends Fragment {

    public static final String CURRENT_TITLE = "CurrentTitle";
    private Note note;


    public static SecondFragment newInstance(Note note) {
        SecondFragment fragment = new SecondFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(CURRENT_TITLE, note);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(CURRENT_TITLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        TextView textViewTitle = view.findViewById(R.id.title_note_second_activity);
        TextView textViewDescription = view.findViewById(R.id.tvDescription_from_second_fragment);
        TextView textViewID = view.findViewById(R.id.tvId);
        String title = note.getTitle();
        System.out.println(title);
        textViewTitle.setText(note.getTitle());  // Почему-то null =(
        String description = note.getDescription();
        System.out.println(description);
        textViewDescription.setText(note.getDescription());// Почему-то null =(
        int id = note.getId();
        System.out.println(id);
        textViewID.setText(note.getId());// Почему-то null =(
        return view;
    }


}