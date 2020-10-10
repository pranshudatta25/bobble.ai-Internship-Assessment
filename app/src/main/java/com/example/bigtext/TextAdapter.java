package com.example.bigtext;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigtext.R;
import com.example.bigtext.MainActivity;
import com.example.bigtext.Text;

import java.util.ArrayList;

public class TextAdapter  extends RecyclerView.Adapter<TextAdapter.TextListViewHolder> {
    private ArrayList<Text> texts;
    public TextAdapter(ArrayList<Text> texts) {
        this.texts = texts;
    }
    @NonNull
    @Override
    public TextAdapter.TextListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_text, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(layoutParams);

        return new TextListViewHolder(layoutView);
    }
    @Override
    public void onBindViewHolder(@NonNull final TextAdapter.TextListViewHolder TextListViewHolder, int i) {
        TextListViewHolder.title.setText(String.valueOf(texts.get(i).getId()));
        TextListViewHolder.listLayout.setOnClickListener(view -> {
            //Call chat activity
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("textId", String.valueOf(texts.get(TextListViewHolder.getAdapterPosition().getId()));
            intent.putExtras(bundle);
            view.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return texts.size();
    }

    class TextListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        LinearLayout listLayout;
        TextListViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            listLayout = view.findViewById(R.id.textList);
        }
    }
}

