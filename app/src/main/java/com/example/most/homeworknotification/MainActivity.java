package com.example.most.homeworknotification;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements

        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
        {

            final String[] sub = {""};
            Button Create;
            int day , month , year , hour , minute;
            int dayFinal , mountFinal , yearFinal , hourFinal, minuteFinal;
            AlarmManager alarmManager;
            PendingIntent alarmIntent;
            private ListView lv1;
            private ArrayAdapter<String> addAdapter;
            private ArrayList<String> addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Create = (Button) findViewById(R.id.button);


        Create.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                AlertDialog.Builder Builder = new AlertDialog.Builder(MainActivity.this);
                Builder.setTitle("Subject Name");
                Builder.setMessage("Enter Subject Name");
                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                Builder.setView(input);

                Builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sub[0] = input.getText().toString();
                        Toast.makeText(getApplicationContext(),
                                sub[0], Toast.LENGTH_SHORT).show();

                        Calendar calendar = Calendar.getInstance();
                        year = calendar.get(Calendar.YEAR);
                        month = calendar.get(Calendar.MONTH);
                        day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, MainActivity.this,
                                year,month,day);



                        datePickerDialog.show();

                    }
                });

                Builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                Builder.show();








            }

        });


    }
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                yearFinal = i;
                mountFinal = i1;
                dayFinal = i2;

                Calendar calendar = Calendar.getInstance();
                        hour = calendar.get(calendar.HOUR_OF_DAY);
                        minute = calendar.get(calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, MainActivity.this,
                        hour,minute, DateFormat.is24HourFormat(this));
                timePickerDialog.show();

            }

            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hourFinal = i;
                minuteFinal = i1;


            setAlarm(yearFinal,mountFinal,dayFinal,hourFinal,minuteFinal);

                String s = dayFinal +"/"+mountFinal+"/"+ yearFinal + "\n" +
                        hourFinal+ ":" + minuteFinal + "\n"+
                        "วิชาที่ต้องส่ง" + " : " + sub[0];
                init();
                addItem.add(s);
                addAdapter.notifyDataSetChanged();



            }



            private void setAlarm(int y, int mo ,int d ,int h, int mi){
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                //Toast.makeText(MainActivity.this,h + ":" +m, Toast.LENGTH_SHORT).show();
                calendar.set(Calendar.HOUR_OF_DAY, h);
                calendar.set(Calendar.MINUTE, mi);
                calendar.set(Calendar.YEAR, y);
                calendar.set(Calendar.DAY_OF_MONTH, d);
                calendar.set(Calendar.MONTH, mo);


                Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), alarmIntent);


            }
            private void init(){

                lv1 = (ListView) findViewById(R.id.list1);
                addItem = new ArrayList<String>();
                addAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addItem);
                lv1.setAdapter(addAdapter);

            }



        }
