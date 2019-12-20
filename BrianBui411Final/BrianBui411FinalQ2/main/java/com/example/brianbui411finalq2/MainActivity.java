package com.example.brianbui411finalq2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import android.database.Cursor;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ArrayList<JSONObject> convertFromJson2Java(Cursor c) throws Exception {
        String firstName = c.getString(c.getColumnIndex("FirstName"));
        String lastName = c.getString(c.getColumnIndex("LastName"));
        String cwid = c.getString(c.getColumnIndex("CWID"));

        JSONObject respObj = new JSONObject(firstName);
        JSONArray objList = respObj.getJSONArray("Result Set");
        ArrayList<JSONObject> sList = new ArrayList<JSONObject>();
        for (int i=0; i<objList.length(); i++) {
            JSONObject sObj = new JSONObject();
            sObj.fromJSON(objList.getJSONObject(i));
            sList.add(sObj);
        }

        return sList;
    }
}