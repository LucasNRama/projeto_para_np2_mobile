package com.example.diariodehumor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAllNotesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private Button btnBack;  // Botão de Voltar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_notes);

        recyclerView = findViewById(R.id.recyclerViewNotes);
        btnBack = findViewById(R.id.btnBack);  // Referência ao botão de voltar

        // Definindo o LayoutManager para o RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Comportamento do botão "Voltar"
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ViewAllNotesActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Finaliza a ViewAllNotesActivity
        });

        // Carregar as notas
        loadNotes();
    }

    private void loadNotes() {
        // Criação da instância do banco de dados
        NoteDatabase db = NoteDatabase.getInstance(this);

        // Executando a consulta de forma assíncrona
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Note> notes = db.noteDao().getAllNotes();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Verificar se há notas
                            if (notes.isEmpty()) {
                                Toast.makeText(ViewAllNotesActivity.this, "Nenhuma nota encontrada.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Configurando o RecyclerView com o Adapter
                                noteAdapter = new NoteAdapter(notes);
                                recyclerView.setAdapter(noteAdapter);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
