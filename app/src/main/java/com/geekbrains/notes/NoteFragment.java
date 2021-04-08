package com.geekbrains.notes;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

public class NoteFragment extends Fragment {
    // При пересоздании фрагмента, чтобы мы не смогли передавать переременные(аргументы)
    public NoteFragment(){
    }

   // EditText edTitleFragment, edDescriptionFragment;
    private static final String KEY = "key";
    private Note currentNote;
    private boolean isLandscape;

    // Метод для передачи данных во фрагмент перед его созданием. Тут можно передавать параметры
    // Так же можно через фрагментменеджер
    // Если аргументы не передаем то через фрагмент менедежры
    public static NoteFragment newInstance(){
        NoteFragment fragment = new NoteFragment(); // Создаем фрагмент
        Bundle args = new Bundle(); // Bundle - класс. Хранит все типы переменных.
        // Передаем аргументы args.putString (ключ, значение); args.putInt(ключ,значение)
        fragment.setArguments(args); // Передаем bundle во фрагмент
        return fragment;
    }


    /*
    // Просто чтобы понимать что активити создано и фрагмент
    // Получаем Bundle
    // А так же в onSaveinstatcestate
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        if(getArguments() != null ){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM1);
            index = getArguments().getInt(ключ)
        }
    }*/

    // Тут создаем fxml. Inflater - надувает layout из fxml. Анлаог setContentView.
    // Тут создаем фрагмент
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes,container,false);
    }

    // Когда наш layout готов и надут, то мы можем с ним рабоать. Искать кликлистнеры, кнопки
    // Находить текствью и сетит текст например
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TextView tv = view.findViewById(R.id.textFragment) например
        initList(view); // Передаем вью
    }

    private void initList(View view) {
        LinearLayout layout = (LinearLayout) view; // кастим вью в LinearLayout
        String[] notes = getResources().getStringArray(R.array.notes); // Получаем ресурсы строк

        // В цикле создаем элементы
        for (String note : notes) {
            Context context = getContext();
            if (context != null) {
                //String note = notes[i];
                TextView tv = new TextView(context); // Чтобы создать текствью нужен контекст
                // getContext возвращает контекст активити где живет фрагмент. Может вернуть null
                tv.setText(note);
                tv.setTextSize(25);
                layout.addView(tv);
                //final int fi = i;
 /*               //tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentNote = new Note(getResources().getStringArray(R.array.notes));
                        //showNote(); // Ничего не передаем. Просто открываем 2е активити
                    }
                });*/
            }

        }
        //  for (int i = 0; i < notes.length; i++) {
        }




    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(KEY,currentNote);
        super.onSaveInstanceState(outState);
    }

    private void showNote() {
      /*  if (isLandscape){
            showLandNote();
        }else{
            showPortNote();
        }*/
        // Открытие второй активити
        // Проверка на null активти
        Context context = getContext();
        if(context != null){
            Intent intent = new Intent(getActivity(), SecondActivity.class); //getActivity может вернуть null
            intent.putExtra(NoteFragment.KEY,currentNote);
            startActivity(intent); // запускаем активти передаем intent
        }
    }


    // Показ записки в ландшафтной ориентации
    private void showLandNote() {
       FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       fragmentTransaction.replace(R.id.fragmentContainer, new NoteFragment());

    }

}