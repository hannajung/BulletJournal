/*Adapter가 하는 일:

* Adapter는 하나의 Object(객체)로서,
* 보여지는 View와 그 View에 올릴 Data를 연결하는 일종의 Bridge다.
* 즉, 데이터의 원본을 받아 관리하고,
* 어댑터뷰가 출력할 수 있는 형태로 데이터를 제공하는 중간 객체 역할을 한다.*/




package com.example.bulletjournal.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bulletjournal.AddNewTask;
import com.example.bulletjournal.DayActivity;
import com.example.bulletjournal.MainActivity;
import com.example.bulletjournal.Model.ToDoModel;
import com.example.bulletjournal.R;
import com.example.bulletjournal.Utils.DatabaseHandler;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoModel> todoList;
    private DayActivity activity;
    private DatabaseHandler db;

    public ToDoAdapter(DatabaseHandler db, DayActivity activity){
        this.db = db;
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        db.openDatabase();
        ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask()); // just for showing on the screen
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(item.getId(), 1);
                }else{
                    db.updateStatus(item.getId(),0);
                }
            }
        });
    }

    private boolean toBoolean(int n){
        // 0이 아니면 true. otherwise false
        return n!=0;
    }


    public int getItemCount(){
        // recycler view knows how many items needs to be print
        return todoList.size();
    }


    public Context getContext(){
        return activity;
    }

    public void setTasks(List<ToDoModel> todoList){
        this.todoList = todoList;
        notifyDataSetChanged(); // for the update
    }

    public void deleteItem(int position) {
        ToDoModel item = todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position){
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}
