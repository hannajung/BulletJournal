package com.example.bulletjournal;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class IndexActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        LinearLayout dayButton = (LinearLayout)findViewById(R.id.dayButton);
        LinearLayout monthButton = (LinearLayout)findViewById(R.id.monthButton);
        LinearLayout keyButton = (LinearLayout)findViewById(R.id.keyButton);
        LinearLayout goalButton = (LinearLayout)findViewById(R.id.goalButton);
        LinearLayout noteButton = (LinearLayout)findViewById(R.id.noteButton);

        dayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndexActivity.this, DayActivity.class));

            }
        });

        monthButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        keyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        goalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        noteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });


    }
}
