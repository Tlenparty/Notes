package com.geekbrains.notes;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class SecondFragment extends Fragment {

    private static final String CURRENT_NOTE = "Current note";
    EditText title, description;
    Button btn_add;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        title = view.findViewById(R.id.etTitle);
        description = view.findViewById(R.id.etDescription);
        btn_add = view.findViewById(R.id.btn_addNote);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())){
                    DataBaseClass db = new DataBaseClass(getActivity());
                    db.addNotes(title.getText().toString(),description.getText().toString());
                    onBackToMainActivity();
                } else {
                    Toast.makeText(getActivity(),"Заполните поля", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void onBackToMainActivity() {
        Intent intent = new Intent();
        intent.setClass(getActivity(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /*  public static final String CURRENT_TITLE = "CurrentTitle";
    private Note note;*/


/*
      public static SecondFragment newInstance(int position) {
          SecondFragment fragment = new SecondFragment();
          Bundle arguments = new Bundle();
          arguments.putParcelable(CURRENT_NOTE, position);
          fragment.setArguments(arguments);
          return fragment;
      }
*/

/*

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
     */
/*   TextView textViewTitle = view.findViewById(R.id.title_note_second_activity);
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
        textViewID.setText(note.getId());// Почему-то null =(*//*

        return view;
    }
*/

/*    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     *//*   if (getArguments() != null) {
            note = getArguments().getParcelable(CURRENT_TITLE);
        }*//*
    }*/

}