package com.example.brianbui411finalq3;

import android.content.Context;
import android.os.AsyncTask;

public class WsAsyncTask extends AsyncTask<Void, Void, Void> {

    protected MainActivity activity;

    public WsAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        activity.testWebService();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Update the screen
        activity.updateScreen();
    }
}