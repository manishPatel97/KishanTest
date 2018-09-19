package com.example.dell.kishantest;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.kishantest.Common.currentValue;
import com.example.dell.kishantest.Model.MessageModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class compose_Message extends AppCompatActivity {
    private  String FIRST_NAME ;
    private int Flag =0;
    TextView msg_details;
    Button msg_sent_btn;
    FirebaseDatabase database;
    DatabaseReference messages;
    private String LAST_NAME,PHONE,OTPMessage;
    private int ordernum;
    private final static String API_KEY = "5wrmWjBZDE8-XVxE1Tapp3ofmqDz1PO1xmAq9PoOQY";
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose__message);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        msg_details = findViewById(R.id.msg_detail);
        msg_sent_btn = findViewById(R.id.msg_send_btn);
        database = FirebaseDatabase.getInstance();
        messages = database.getReference("Messages");

        if(getIntent()!=null){
            FIRST_NAME = getIntent().getStringExtra("First_Name");
            LAST_NAME = getIntent().getStringExtra("Last_name");
            PHONE = getIntent().getStringExtra("phone");


        }
        Random r = new Random();
        int numbers = 100000 + (int)(r.nextFloat() * 899900);
        System.out.println("numbers "+numbers);
        OTPMessage = "Hi your OTP is: "+numbers;
        msg_details.setText(OTPMessage);

        msg_sent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                String date = sdf.format(cal.getTime()).toString();

                ordernum = currentValue.Order;
                System.out.println(date);
                MessageModel request = new MessageModel(
                        FIRST_NAME,
                        LAST_NAME,PHONE,
                        OTPMessage,date,String.valueOf(ordernum-1));

                SendSms();

                if(Flag==1){
                    String id_number = String.valueOf(System.currentTimeMillis());
                    messages.child(id_number).setValue(request);
                    Flag=0;
                }

                Toast.makeText(compose_Message.this, "Your message is Sent.", Toast.LENGTH_SHORT).show();


            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void SendSms(){
        try {
            // Construct data
            Gson gson = new Gson();
            String apiKey = "apikey=" + API_KEY;
            String message = "&message=" + OTPMessage;
            String numbers = "&numbers=" + PHONE;
            String test = "true";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message +test ;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
                Flag =1;
            }
            rd.close();


        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            Toast.makeText(getApplicationContext(),"Error Message is "+e,Toast.LENGTH_SHORT).show();
        }
    }

}
