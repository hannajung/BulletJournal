/*Handler 의 역할:
*
* 핸들러란, worker thread 에서 main thread 로 메시지를 전달해주는 역할을 하는 클래스이다.*/

package com.example.bulletjournal.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bulletjournal.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

// Database에 관련된 모든 functions들 공부 필요!!!!
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION =2;
    //name of database
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String CREATE_TODO_TABLE = TODO_TABLE + ID  + TASK  + STATUS ;

    private SQLiteDatabase db; // reference to the database

    public DatabaseHandler(Context context){
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop the old version of table
//        db.execSQL(TODO_TABLE);
        // create a new version of table
        onCreate(db);
    }


    public void openDatabase(){
        db = this.getWritableDatabase();
    }

    public void insertTask(ToDoModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());       // (key, value)
        cv.put(STATUS, 0);                  // initially every new task's status will be 0 (unchecked)
        db.insert(TODO_TABLE, null, cv);           // table name, insert whole row,
    }


    public List<ToDoModel> getAllTasks(){
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TODO_TABLE, null,null, null, null,null,null,null);
            if (cur != null){
                if (cur.moveToFirst()){
                    do{
                        ToDoModel task = new ToDoModel();
                        task.setId(cur.getInt(cur.getColumnIndex(ID)));
                        task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        taskList.add(task);
                    }while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            cur.close();
        }
        return taskList;
    }

    /*ContentValues란?

  이 클래스는 ContentResolver가 처리 할 수 있는 값 집합을 저장하는데 사용됩니다.
  ContentResolver는 콘텐트 프로바이더(ContentProvider)와 비즈니스 로직의 중계자 역할을 하고 있습니다.
  ContentValues는 ContentResolver가 사용하는 데이터 운송 수단이라고 생각하시면 좋을 것 같습니다.


    ContentValues 사용 순서

     1. 객체 생성
     2. put메서드를 사용해서 (항목, 값)을 DB테이블 순서에 맞게 집어 넣습니다.
     3. getContentResolver()를 통해 ContentResolver를 가져옵니다.
     4. ContentReseolver.insert()의 (URI & ContentValues)를 매개변수로 넣어줍니다.
    * */

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "=?", new String[]{String.valueOf(id)});
    }

    public void updateTask(int id, String task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv, ID + "=?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id){
        db.delete(TODO_TABLE, ID + "=?", new String[]{String.valueOf(id)});
    }

}
