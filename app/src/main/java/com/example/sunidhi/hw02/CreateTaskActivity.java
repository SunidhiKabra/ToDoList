package com.example.sunidhi.hw02;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CreateTaskActivity extends AppCompatActivity {

    EditText editTextTitle, editTextDate, editTextTime;
    RadioGroup radioGroup;
    Button buttonSave;

    static String KEY = "key_value";
    private DatePickerDialog.OnDateSetListener mDateSetListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_task );

        setTitle( "Create Task" );

        editTextTitle = findViewById( R.id.editTextTitle);
        editTextDate = findViewById( R.id.editTextDate );
        editTextTime = findViewById( R.id.editTextTime );
        radioGroup = findViewById( R.id.radioGroup);
        buttonSave = findViewById( R.id.buttonSave );


        editTextDate.setKeyListener( null );
        editTextTime.setKeyListener( null );
        editTextDate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dailog = new DatePickerDialog(
                        CreateTaskActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListner, year, month, day);
                dailog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
                dailog.show();
            }
        } );

        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month =  month +1;
                String date = month + "/" + day + "/" + year;
                editTextDate.setText(date);
            }
        };

        editTextTime.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get( Calendar.HOUR_OF_DAY );
                int minute = mcurrentTime.get( Calendar.MINUTE );
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog( CreateTaskActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String input = selectedHour + ":" + selectedMinute;
                        TimeZone utc = TimeZone.getTimeZone( "etc/UTC" );
                        DateFormat inputFormat = new SimpleDateFormat( "HH:mm",
                                Locale.US );
                        inputFormat.setTimeZone( utc );
                        DateFormat outputFormat = new SimpleDateFormat( "hh:mm aa",
                                Locale.US );
                        outputFormat.setTimeZone( utc );
                        Date date = null;
                        try {
                            date = inputFormat.parse( input );
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String output = outputFormat.format( date );
                        editTextTime.setText( output );
                    }
                }, hour, minute, false );//Yes 24 hour time
                mTimePicker.setTitle( "Select Time" );
                if (!mTimePicker.isShowing())
                    mTimePicker.show();
            }
        } );

        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskTitle = editTextTitle.getText().toString();
                String date = editTextDate.getText().toString();
                String time = editTextTime.getText().toString();
                int selected = radioGroup.getCheckedRadioButtonId();

                RadioButton rb = findViewById( selected );
                String priority = (String) rb.getText();


                TaskValue tv = new TaskValue( taskTitle, date, time, priority );
                ArrayList<TaskValue> list = (ArrayList<TaskValue>) getIntent().getSerializableExtra( "List" );
                list.add( tv );

                Intent intent = new Intent( CreateTaskActivity.this, MainActivity.class );
                intent.putExtra( "List", list );
                startActivity( intent );
            }
        } );

    }
}
