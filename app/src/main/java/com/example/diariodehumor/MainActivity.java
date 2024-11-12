package com.example.diariodehumor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private Button btnAddNote, btnViewAllNotes;  // Declare o botão aqui

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando o RecyclerView e os Botões
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        btnAddNote = findViewById(R.id.btnAddNote);
        btnViewAllNotes = findViewById(R.id.btnViewAllNotes);  // Inicializando o botão

        // Configurando o clique do botão para visualizar todas as notas
        btnViewAllNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação para visualizar todas as notas
                Intent intent = new Intent(MainActivity.this, ViewAllNotesActivity.class);
                startActivity(intent);
            }
        });

        // Configurando o clique do botão para adicionar uma nova nota
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação para adicionar uma nova nota
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
