package com.nacoda.dzikirqu.mvp.quran;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.constants.URI;
import com.nacoda.dzikirqu.fragments.dzikir_quran.DzikirQuranFragment;
import com.nacoda.dzikirqu.model.quran.Data;
import com.nacoda.dzikirqu.ui.MvpActivity;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuranActivity extends MvpActivity<QuranPresenter> implements QuranView {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.audio)
    ImageView audio;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);
        ButterKnife.bind(this);
        player = new MediaPlayer();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        mvpPresenter.getData(this, getIntent().getStringExtra(getString(R.string.id)), preferences.getString(Prefs.LANGUAGE, Prefs.LANGUAGE_DEFAULT));
    }

    @Override
    protected QuranPresenter createPresenter() {
        return new QuranPresenter(this);
    }

    void onAudioButtonClicked() {
        String audio_url = getIntent().getStringExtra(getString(R.string.audio_url));
        String audio_name = getIntent().getStringExtra(getString(R.string.audio_name));

        if (audio_url != null) {
            if (isNetworkAvailable(this)) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        if (player.isPlaying()) {
                            player.stop();
                            player.reset();
                            audio.setImageResource(R.drawable.play_dark);
                        } else {
                            File file = new File("/sdcard/" + URI.DIRECTORY + "/" + audio_name + ".mp3");
                            if (file.exists()) {
                                audio.setImageResource(R.drawable.stop_light);
                                audioPlayer(audio_name + ".mp3");

                            } else {
                                mvpPresenter.downloadAudio(audio_url, audio_name, this);
                            }
                        }
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                    }
                } else {
                    if (player.isPlaying()) {
                        player.stop();
                        player.reset();
                        audio.setImageResource(R.drawable.play_dark);
                    } else {
                        File file = new File("/sdcard/" + URI.DIRECTORY + "/" + audio_name + ".mp3");
                        if (file.exists()) {
                            audio.setImageResource(R.drawable.stop_light);
                            audioPlayer(audio_name + ".mp3");

                        } else {
                            mvpPresenter.downloadAudio(audio_url, audio_name, this);
                        }
                    }
                }
            } else {
                if (player.isPlaying()) {
                    player.stop();
                    player.reset();
                    audio.setImageResource(R.drawable.play_dark);
                } else {
                    File file = new File("/sdcard/" + URI.DIRECTORY + "/" + audio_name + ".mp3");
                    if (file.exists()) {
                        audio.setImageResource(R.drawable.stop_light);
                        audioPlayer(audio_name + ".mp3");

                    } else {
                        Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            Toast.makeText(this, "" + getLocalizedString(R.string.audio_unavailable), Toast.LENGTH_SHORT).show();
        }

    }

    public void audioPlayer(String fileName) {
        try {
            player.setDataSource(URI.AUDIO_PATH + File.separator + fileName);
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    public void getData(List<Data> response, String surahName) {
        title.setTypeface(getFont(this, Fonts.QUICKBOLD));
        title.setText(surahName);
        addFragmentOnTop(this, new DzikirQuranFragment(response), R.id.frame_quran);
    }

    @Override
    public void onFinishDownloading(String audioName) {
        audioPlayer(audioName + ".mp3");
        audio.setImageResource(R.drawable.stop_light);
    }

    @OnClick({R.id.toolbar_back, R.id.audio})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.audio:
                onAudioButtonClicked();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        player.reset();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
        player.reset();
    }
}
