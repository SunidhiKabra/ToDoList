package com.example.sunidhi.hw02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView taskTitle, taskTime, taskPriority, taskNumber, taskTotal, taskDate;
    ImageButton ImageFirst, ImagePrevious, ImageEdit, ImageDelete, ImageNext, ImageLast, ImageAdd;

    TaskValue object;
    ArrayList<TaskValue> tv = new ArrayList<>(  );
    int size;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        setTitle( "View Tasks" );

        taskTitle = findViewById( R.id.taskTitle_textView );
        taskTime = findViewById( R.id.taskTime_textView );
        taskPriority = findViewById( R.id.taskPriority_textView );
        taskNumber = findViewById( R.id.textViewTaskNumber );
        taskTotal = findViewById( R.id.textViewTaskTotal );
        ImageFirst = findViewById( R.id.image_first_button );
        ImagePrevious = findViewById( R.id.image_previous_button );
        ImageEdit = findViewById( R.id.image_edit_button );
        ImageDelete = findViewById( R.id.image_delete_button );
        ImageNext = findViewById( R.id.image_next_button );
        ImageLast = findViewById( R.id.image_last_button );
        ImageAdd = findViewById( R.id.image_add_button );
        taskDate = findViewById( R.id.taskDate_textView );

        ImageAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, CreateTaskActivity.class );
                intent.putExtra( "List", tv );
                startActivity( intent );
            }
        } );

        if (getIntent() != null && getIntent().getExtras() != null){
            tv = (ArrayList<TaskValue>) getIntent().getExtras().getSerializable("List");

            if (tv.size() > 0){
                taskTitle.setText(tv.get( 0 ).getTask());
                taskDate.setText(tv.get( 0 ).getDate());
                taskTime.setText(tv.get( 0 ).getTime());
                taskPriority.setText(tv.get( 0 ).getPriority());
                taskNumber.setText("1");
                taskTotal.setText(String.valueOf(tv.size()));
            }

            if (tv.size() > 1){
                Collections.sort( tv, new Comparator<TaskValue>() {

                    DateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");

                    @Override
                    public int compare(TaskValue taskValue, TaskValue t1) {
                        try {
                            String dateTime1 = taskValue.getDate() + " " + taskValue.getTime();
                            String dateTime2 = t1.getDate() + " " + t1.getTime();
                            Date one = f.parse(dateTime1);
                            Date two = f.parse(dateTime2);
                            return one.compareTo(two);
                        } catch (ParseException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
                } );

                ImageNext.setEnabled( true );
                ImagePrevious.setEnabled( true );
                ImageFirst.setEnabled( true );
                ImageLast.setEnabled( true );

                ImageFirst.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        taskTitle.setText(tv.get( 0 ).getTask());
                        taskDate.setText(tv.get( 0 ).getDate());
                        taskTime.setText(tv.get( 0 ).getTime());
                        taskPriority.setText(tv.get( 0 ).getPriority());
                        taskNumber.setText("1");
                        taskTotal.setText(String.valueOf(tv.size()));
                    }
                } );

                ImageLast.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        taskTitle.setText(tv.get( tv.size() - 1 ).getTask());
                        taskDate.setText(tv.get( tv.size() - 1 ).getDate());
                        taskTime.setText(tv.get( tv.size() - 1).getTime());
                        taskPriority.setText(tv.get( tv.size() - 1 ).getPriority());
                        taskNumber.setText(String.valueOf( tv.size() ));
                        taskTotal.setText(String.valueOf(tv.size()));
                    }
                } );

                ImageNext.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(count >= tv.size() - 1){
                            count = 0;
                        }
                        else{
                            count ++;
                        }

                        taskTitle.setText(tv.get( count ).getTask());
                        taskDate.setText(tv.get( count ).getDate());
                        taskTime.setText(tv.get( count).getTime());
                        taskPriority.setText(tv.get( count ).getPriority());
                        taskNumber.setText(String.valueOf( count + 1 ));
                        taskTotal.setText(String.valueOf(tv.size()));
                    }
                } );

                ImagePrevious.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (count < 0){
                            count = tv.size() - 1;
                        }
                        else{
                            count --;
                        }
                        taskTitle.setText(tv.get( count ).getTask());
                        taskDate.setText(tv.get( count ).getDate());
                        taskTime.setText(tv.get( count).getTime());
                        taskPriority.setText(tv.get( count ).getPriority());
                        taskNumber.setText(String.valueOf( count + 1 ));
                        taskTotal.setText(String.valueOf(tv.size()));
                    }
                } );


            }
        }

    }
}
