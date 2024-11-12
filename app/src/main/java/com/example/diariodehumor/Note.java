package com.example.diariodehumor;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note") // Nome da tabela
public class Note {

    @PrimaryKey(autoGenerate = true) // Se você quer que o ID seja gerado automaticamente
    private int id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "mood")
    private String mood;

    public Note(String date, String description, String mood) {
        this.date = date;
        this.description = description;
        this.mood = mood;
    }

    // Getters e Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
