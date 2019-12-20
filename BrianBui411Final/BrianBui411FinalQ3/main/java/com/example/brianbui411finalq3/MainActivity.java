package com.example.brianbui411finalq3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> studentList = new ArrayList<Student>();
    LinearLayout ll;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ll = findViewById(R.id.data_list);
        ctx = this;

        WsAsyncTask wsAsyncTask = new WsAsyncTask(this);
        wsAsyncTask.execute();

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.findItem(R.id.action_add).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.action_add){
            testWebService();
            updateScreen();
        }
        return super.onOptionsItemSelected(item);

    }

    public void updateScreen(){
        for (int i=0; i<studentList.size(); i++){
            TextView tv = new TextView(ctx);
            tv.setText(studentList.get(i).getCwid());
            ll.addView(tv);

        }
    }

    public void testWebService() {
        String request = "http://cs101i.fullerton.edu:400/students";
        try {
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = conn.getInputStream();
            int respCode = conn.getResponseCode();
            Log.d("Remote Application ", "Response Code: " + respCode);
            String contentType = conn.getHeaderField("Content-Type");
            Log.d("Remote Application ", contentType);

            String respString;
            if (respCode == HttpURLConnection.HTTP_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                respString = new String(out.toByteArray());

                Log.d("Remote Application", respString);

                studentList = convertFromJson2Java(respString);

            }
        } catch(Exception e) {
            Log.d("Remote Application ", e.getMessage());
        }
    }

    ArrayList<Student> convertFromJson2Java(String str) throws Exception {
        JSONObject respObj = new JSONObject(str);
        JSONArray objList = respObj.getJSONArray("Result Set");
        ArrayList<Student> sList = new ArrayList<Student>();
        for (int i=0; i<objList.length(); i++) {
            Student sObj = new Student();
            sObj.fromJSON(objList.getJSONObject(i));
            sList.add(sObj);
        }
        return sList;
    }
}


