package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{
    SensorManager SensorMng;
    Sensor sensor;
    TextView text_value,text_list,tw1;

    private Sensor accelerometer;
    private float[] mGravity;

    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    private boolean started;


    CountDownTimer timer;

    SensorManager SensorMng2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        SensorMng = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = SensorMng.getDefaultSensor(Sensor.TYPE_LIGHT);

        //duration
        accelerometer= SensorMng.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel=0.00f;
        mAccelCurrent=SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        started=false;
        tw1 = (TextView) findViewById(R.id.tw1);



        text_value =(TextView)findViewById(R.id.text_value);
        text_list = (TextView)findViewById(R.id.SensorList);
        List<Sensor> sensorList = SensorMng.getSensorList(Sensor.TYPE_ALL);
        StringBuilder strBuilder = new StringBuilder();

        for(Sensor s: sensorList){
            strBuilder.append(s.getName()+ "\n" );
        }

        //For ile tüm sensorListelerinde dönüldükten sonra; bu listelerdekini text_liste bas.

        //setVisibilty görünür yapması.
        text_list.setVisibility(View.VISIBLE);
        text_list.setText(strBuilder);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorMng.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        SensorMng.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT){
            text_value.setText(" Light Value :  "+ event.values[0]);
            if (event.values[0] < 1){
                text_list.setTextColor(Color.WHITE);
                text_list.setBackgroundColor(Color.BLACK);
            }
            if(event.values[0]>1){
                text_list.setTextColor(Color.BLACK);
                text_list.setBackgroundColor(Color.WHITE);
            }
        }

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values.clone();

            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];

            mAccelLast = mAccelCurrent;
            mAccelCurrent=(float)Math.sqrt(x*x + y*y +z*z);
            float delta = mAccelCurrent-mAccelLast;
            mAccel = mAccel * 0.9f+ delta;

            RelativeLayout rl =(RelativeLayout)  findViewById(R.id.activity_sensor);

            if(mAccel>1){
                rl.setBackgroundColor(Color.RED);
                started=false;
                tw1.setText("Move!");
            }else {
                rl.setBackgroundColor(Color.WHITE);
                int  minute = 1;
                int min = minute *1000*60;

                if(!started){
                    Counter(min);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private  void Counter(long min){
        started=true;

        if (timer!=null) {
            timer.cancel();
            timer=null;
        }

        timer = new CountDownTimer(min,10000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds= (int) (millisUntilFinished/1000)%60;
                int minutes= (int) ((millisUntilFinished/(1000*60))%60);
                int hours= (int) ((millisUntilFinished/(1000*60*60))%24);
                tw1.setText(String.format("%d:%d:%d",hours,minutes,seconds));

            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Timer Finish'",Toast.LENGTH_LONG).show();

                started=false;
                finishAffinity();
                System.exit(0);
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SensorMng.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuMenu:
                startActivity(new Intent(SensorActivity.this, MenuActivity.class));
                break;
            case R.id.registerMenu:
                startActivity(new Intent(SensorActivity.this, MainActivity.class));
                break;

            case R.id.signUpMenu:
                startActivity(new Intent(SensorActivity.this, SignUpActivity.class));
                break;
            case R.id.userListMenu:
                startActivity(new Intent(SensorActivity.this, UserListActivity.class));
                break;

            case R.id.sendSharedPref:
                startActivity(new Intent(SensorActivity.this, SharedPreferencesActivity.class));
                break;

            case R.id.sendMail:
                startActivity(new Intent(SensorActivity.this, MailActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}