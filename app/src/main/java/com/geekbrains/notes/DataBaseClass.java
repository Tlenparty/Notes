package com.geekbrains.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseClass extends SQLiteOpenHelper {

    Context context;
    public static final String DataBaseName = "MyNotes";
    public static final int DataBaseVersion = 1;
    public static final String TableName = "mynotes";
    public static final String ColumnId = "id";
    public static final String ColumnTitle = "title";
    public static final String ColumnDescription = "description";

    public DataBaseClass(@Nullable Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TableName +
                " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnTitle + " TEXT," +
                ColumnDescription + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    void addNotes(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle, title);
        cv.put(ColumnDescription, description);
        long resultValue = db.insert(TableName, null, cv);
        // fail
        if (resultValue == -1) {
            Toast.makeText(context, "Данные не добавлены", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Данные добавлены", Toast.LENGTH_LONG).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteAllNotes() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TableName;
        db.execSQL(query);
    }

    public void updateNotes(String title, String description, String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle, title);
        cv.put(ColumnDescription, description);

        long result = database.update(TableName, cv, "id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Выполнено", Toast.LENGTH_LONG).show();
        }
    }
}
