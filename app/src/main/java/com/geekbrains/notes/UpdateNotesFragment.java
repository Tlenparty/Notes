package com.geekbrains.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateNotesFragment extends Fragment {

    EditText title, description;
    Button btn_updateNote;
    String id;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }



  /*  private void setNoteFragment() {
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_second, fragment).commit();
    }*/

    private void initView(View view) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String titleStr = bundle.getString("title");
            String descriptionStr = bundle.getString("description");
            id = bundle.getString("id");
            title.setText(titleStr);
            description.setText(descriptionStr);
            title = view.findViewById(R.id.title_update);
            description = view.findViewById(R.id.description_update);
            btn_updateNote = view.findViewById(R.id.btn_updateNote);

            btn_updateNote.setOnClickListener(v -> {
                if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                    DataBaseClass db = new DataBaseClass(getActivity());
                    db.updateNotes(title.getText().toString(), description.getText().toString(), id);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Оба поля обязательны!", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}