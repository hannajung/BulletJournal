package com.example.bulletjournal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bulletjournal.Adapter.ToDoAdapter;
import com.example.bulletjournal.Model.ToDoModel;
import com.example.bulletjournal.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayActivity extends AppCompatActivity implements DialogCloseListener{

    private DatabaseHandler db;

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
//    private FloatingActionButton fab;
    private TextView fab;

    private List<ToDoModel> taskList;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    CalendarView calendarView;
    TextView date;
    int check_num = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        db = new DatabaseHandler(this);
        db.openDatabase();

        Button addDateButton = (Button) findViewById(R.id.addDateButton);
        date = (TextView)findViewById(R.id.date);
        ImageView menuButton = (ImageView) findViewById(R.id.menuButton);
        TextView guide = (TextView)findViewById(R.id.guide);
//        TextView addListButton = (TextView)findViewById(R.id.addListButton);
        LinearLayout journalArea = (LinearLayout)findViewById(R.id.journalArea);
        ImageView deleteListButton = (ImageView)findViewById(R.id.deleteListButton);
        LinearLayout newList = (LinearLayout)findViewById(R.id.newList);

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // tasksRecyclerView를 Linear Layout으로 설정
        tasksAdapter = new ToDoAdapter(db,DayActivity.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        fab = findViewById(R.id.fab);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);


        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);

//        ToDoModel task = new ToDoModel();
//        task.setTask("This is a Test Task에용");
//        task.setStatus(0);
//        task.setId(1);
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//
//        tasksAdapter.setTasks(taskList);




        // addListButton is invisible until a new date is added
        fab.setVisibility(View.INVISIBLE);
//        editArea.setVisibility(View.INVISIBLE);
        // deleteListButton is invisible until edit area is clicked
//        deleteListButton.setVisibility(View.INVISIBLE);


        // Get the YEAR,MONTH, and DATE from MainActivity to add a new date
        int newYear = getIntent().getIntExtra("YEAR",0);
        int newMonth = getIntent().getIntExtra("MONTH",0);
        int newDay = getIntent().getIntExtra("DAY",0);

        String newDate = newYear + "/"+ newMonth + "/" + newDay;

        if (newYear != 0){
            date.setText(newDate);
            guide.setVisibility(View.INVISIBLE);
            fab.setVisibility(View.VISIBLE);
//            editArea.setVisibility(View.VISIBLE);
        }


        menuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DayActivity.this, IndexActivity.class));
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);

            }
        });


        addDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DayActivity.this, MainActivity.class));

            }
        });
    }

    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }
}