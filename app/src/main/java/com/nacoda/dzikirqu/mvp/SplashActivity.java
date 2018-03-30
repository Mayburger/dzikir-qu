package com.nacoda.dzikirqu.mvp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.libs.AppPreference;
import com.nacoda.dzikirqu.model.ListModel;
import com.nacoda.dzikirqu.model.dzikir.DzikirModel;
import com.nacoda.dzikirqu.model.quran.QuranModel;
import com.nacoda.dzikirqu.room.database.AppDatabase;
import com.nacoda.dzikirqu.room.utils.DatabaseInitializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    AppPreference preference;
    @BindView(R.id.kufic_small)
    ImageView kufic_small;
    @BindView(R.id.kufic_large)
    ImageView kuficLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        kuficLarge.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_left));
        kufic_small.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right));


        new LoadKamus().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadKamus extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            preference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {

            Boolean first_run = preference.getFirstRun();
            if (first_run) {
                List<QuranModel> qModel = new ArrayList<>();
                List<DzikirModel> dModel = new ArrayList<>();
                List<ListModel> qList = ListModel.GsonBuilder(getStringJson("quran/quran.json"));
                List<ListModel> dList = ListModel.GsonBuilder(getStringJson("dzikir/dzikir.json"));


                for (int i = 0; i < qList.size(); i++) {
                    qModel.add(QuranModel.GsonBuilder(getStringJson("quran/en/" + qList.get(i).getId() + ".json")));
                    qModel.add(QuranModel.GsonBuilder(getStringJson("quran/id/" + qList.get(i).getId() + ".json")));
                }

                for (int i = 0; i < dList.size(); i++) {
                    dModel.add(DzikirModel.GsonBuilder(getStringJson("dzikir/en/" + dList.get(i).getId() + ".json")));
                    dModel.add(DzikirModel.GsonBuilder(getStringJson("dzikir/id/" + dList.get(i).getId() + ".json")));
                }

                DatabaseInitializer.insertQuranData(AppDatabase.getAppDatabase(SplashActivity.this), qModel);
                DatabaseInitializer.insertDzikirData(AppDatabase.getAppDatabase(SplashActivity.this), dModel);

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(Void result) {
            new Handler().postDelayed(() -> {
                preference.setFirstRun(false);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }, 2000);
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
