package com.nacoda.dzikir_qu;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nacoda.dzikir_qu.libs.AppPreference;
import com.nacoda.dzikir_qu.model.BaseModel;
import com.nacoda.dzikir_qu.room.database.AppDatabase;
import com.nacoda.dzikir_qu.room.utils.DatabaseInitializer;

import java.io.IOException;
import java.io.InputStream;

public class SplashActivity extends AppCompatActivity {

    AppPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LoadKamus().execute();
    }

    private class LoadKamus extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            preference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {

            Boolean first_run = preference.getFirstRun();

            if (first_run) {
                DatabaseInitializer.insertQuranData(AppDatabase.getAppDatabase(SplashActivity.this), BaseModel.GsonBuilder(getStringJson("dzikir.json")).getResults());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(Void result) {
        startActivity(new Intent(SplashActivity.this, DetailActivity.class));
        }
    }

    private String getStringJson(String jsonName) {
        String json = null;
        try {
            InputStream is = getAssets().open("json/" + jsonName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}
