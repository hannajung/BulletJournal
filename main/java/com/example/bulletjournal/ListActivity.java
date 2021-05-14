package com.example.bulletjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        EditText editArea = (EditText)findViewById(R.id.editArea111);
//        ImageView deleteListButton = (ImageView)findViewById(R.id.deleteListButton111);
//        deleteListButton.setVisibility(View.INVISIBLE);
//
//        editArea.setText("change this");
//
//
//        editArea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus){
//                    deleteListButton.setVisibility(View.VISIBLE);
//                }else{
//                    deleteListButton.setVisibility(View.INVISIBLE);
//                }
//            }
//
//        });
    }


}