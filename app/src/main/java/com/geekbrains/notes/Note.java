package com.geekbrains.notes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Note implements Parcelable {

    private String title;
    private String description;
    private String id;


    public Note(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;

    }

    // Консутруктор, считывающий данные из Parcel

    protected Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        // Распаковка обьекта из Parcel
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    // Упаковываем объект в Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getTitle());
        dest.writeString(getDescription());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
