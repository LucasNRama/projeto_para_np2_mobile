package com.example.diariodehumor;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note currentNote = noteList.get(position);
        holder.tvDate.setText(currentNote.getDate());
        holder.tvDescription.setText(currentNote.getDescription());
        holder.tvMood.setText(currentNote.getMood());

        holder.btnDelete.setOnClickListener(v -> {
            NoteDatabase db = NoteDatabase.getInstance(v.getContext());
            new Thread(() -> {
                db.noteDao().delete(currentNote);
                noteList.remove(position);


                new Handler(Looper.getMainLooper()).post(() -> notifyItemRemoved(position));  // Atualizar o RecyclerView
            }).start();
        });
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

    // ViewHolder para cada item da lista
    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDate, tvDescription, tvMood;
        public Button btnDelete;  // Botão de excluir

        public NoteViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvMood = itemView.findViewById(R.id.tvMood);
            btnDelete = itemView.findViewById(R.id.btnDelete);  // Inicializando o botão
        }
    }
}
