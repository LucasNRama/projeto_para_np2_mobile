package com.example.diariodehumor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etDate, etDescription;
    private Spinner spMood;
    private Button btnSave, btnBack; // Botão de Voltar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etDate = findViewById(R.id.editTextDate);
        etDescription = findViewById(R.id.editTextDescription);
        spMood = findViewById(R.id.spinnerMood);
        btnSave = findViewById(R.id.btnSaveNote);
        btnBack = findViewById(R.id.btnBack);  // Referência ao botão de voltar

        // Comportamento do botão "Voltar"
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Finaliza a AddNoteActivity
        });

        btnSave.setOnClickListener(v -> {
            String date = etDate.getText().toString();
            String description = etDescription.getText().toString();
            String mood = spMood.getSelectedItem().toString();


            if (date.isEmpty() || description.isEmpty() || mood.isEmpty()) {
                Toast.makeText(AddNoteActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {

                Note newNote = new Note(date, description, mood);


                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    try {

                        NoteDatabase.getInstance(AddNoteActivity.this).noteDao().insert(newNote);


                        runOnUiThread(() -> {
                            Toast.makeText(AddNoteActivity.this, "Nota salva com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        });
                    } catch (Exception e) {

                        runOnUiThread(() -> {
                            Toast.makeText(AddNoteActivity.this, "Erro ao salvar a nota. Verifique os dados.", Toast.LENGTH_SHORT).show();
                        });
                        e.printStackTrace();
                    }
                });
            }
        });



    }
}
