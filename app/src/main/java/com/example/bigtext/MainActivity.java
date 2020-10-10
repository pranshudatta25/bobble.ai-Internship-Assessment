package com.example.bigtext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private static final String TAG = "MainActivity";
    private RecyclerView textList;
    private RecyclerView.Adapter textAdapter;
    private RecyclerView.LayoutManager textLayoutManager;

    ArrayList<Text> texts = new ArrayList<>();
    String textId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textId = getIntent().getExtras().getString("textId");
        Button display = findViewById(R.id.display);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.displayTexts();
            }
        });
        initializeRecyclerView();
    }
    private void displayTexts() {
        EditText text = findViewById(R.id.Text);
        if (!text.getText().toString().isEmpty()) {
            Text textObject = new Text(text.getText().toString(), 0);
            Log.d(TAG, "displayTexts: " + textObject.getText());
            try {
                Text chat = textRepository.get(Long.parseLong(textId));
                //Add message to DB
                text.addText(textObject);
                textRepository.update(text);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        text.setText(null);
    }

    private void initializeRecyclerView() {
        textList = findViewById(R.id.textList);
        textList.setNestedScrollingEnabled(false); //Disable scrolling
        textList.setHasFixedSize(false);
        textLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        textList.setLayoutManager(textLayoutManager);
        textAdapter = new TextAdapter(texts);
        textList.setAdapter(textAdapter);
    }
}