package com.geekbrains.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    /*
    1. Создайте класс данных со структурой заметок: название заметки, описание заметки, дата создания
    и т. п.
    2. Создайте фрагмент для вывода этих данных.
    3. Встройте этот фрагмент в активити. У вас должен получиться экран с заметками, который мы будем
    улучшать с каждым новым уроком.
    4. Добавьте фрагмент, в котором открывается заметка. По аналогии с примером из урока: если нажать на
    элемент списка в портретной ориентации — открывается новое окно, если нажать в ландшафтной —
    окно открывается рядом.
    5. * Разберитесь, как можно сделать, и сделайте корректировку даты создания при помощи DatePicker.

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}