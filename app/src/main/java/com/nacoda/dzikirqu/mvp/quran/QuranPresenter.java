package com.nacoda.dzikirqu.mvp.quran;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.nacoda.dzikirqu.base.BasePresenter;
import com.nacoda.dzikirqu.constants.URI;
import com.nacoda.dzikirqu.model.Download;
import com.nacoda.dzikirqu.model.dzikir.Data;
import com.nacoda.dzikirqu.model.quran.QuranModel;
import com.nacoda.dzikirqu.room.database.AppDatabase;
import com.nacoda.dzikirqu.room.utils.DatabaseInitializer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Rosinante24 on 10/01/18.
 */

class QuranPresenter extends BasePresenter<QuranView> {
    QuranPresenter(QuranView view) {
        attachView(view);
    }

    void getData(Context mContext, String id, String language) {
        QuranModel data = DatabaseInitializer.getQuranByIdAndLanguage(AppDatabase.getAppDatabase(mContext), id, language);
        mvpView.getData(data.getResults(), data.getName());
    }


    void downloadAudio(String audio, String audio_name, Context context) {
        new DownloadFromURL(new Download(audio_name, audio, context)).execute();
    }

    @SuppressLint("StaticFieldLeak")
    class DownloadFromURL extends AsyncTask<Void, String, String> {

        private Download download;
        private ProgressDialog progressDialog;

        public DownloadFromURL(Download download) {
            this.download = download;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(download.getContext());
            progressDialog.setMessage("Downloading Audio...");
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            int count;
            try {
                URL url = new URL(download.getUrl());
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                int fileLength = urlConnection.getContentLength();
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);


                File f = new File(Environment.getExternalStorageDirectory(), URI.DIRECTORY);
                if (!f.exists()) {
                    f.mkdirs();
                }

                OutputStream outputStream = new FileOutputStream("/sdcard/" + URI.DIRECTORY + "/" + download.getName() + ".mp3");

                byte data[] = new byte[1024];
                long total = 0;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / fileLength));
                    outputStream.write(data, 0, count);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return "";
        }


        protected void onProgressUpdate(String... progress) {
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String file_url) {
            progressDialog.dismiss();
            mvpView.onFinishDownloading(download.getName());
        }
    }

}
