package com.nacoda.dzikirqu.fragments.dzikir_detail;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.AccordionTransformer;
import com.eftimoff.viewpagertransformers.BackgroundToForegroundTransformer;
import com.eftimoff.viewpagertransformers.RotateDownTransformer;
import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.adapters.DzikirPagerAdapter;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.constants.URI;
import com.nacoda.dzikirqu.fragments.dzikir_quran.DzikirQuranFragment;
import com.nacoda.dzikirqu.model.dzikir.Data;
import com.nacoda.dzikirqu.room.database.AppDatabase;
import com.nacoda.dzikirqu.room.utils.DatabaseInitializer;
import com.nacoda.dzikirqu.ui.MvpFragment;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class DzikirDetailFragment extends MvpFragment<DzikirDetailPresenter, Fragment> implements DzikirDetailView {


    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.header)
    LinearLayout header;
    @BindView(R.id.header_content)
    RelativeLayout headerContent;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.page)
    TextView page;
    @BindView(R.id.audio)
    ImageView audio;
    @BindView(R.id.indicator)
    ScrollingPagerIndicator indicator;
    @BindView(R.id.share)
    ImageView share;

    MediaPlayer player;
    Unbinder unbinder;

    public DzikirDetailFragment() {

    }

    @BindView(R.id.footer)
    RelativeLayout footer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupTitle();
        player = new MediaPlayer();
        onPlayerStopped();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mvpPresenter.getData(getActivity(), getActivity().getIntent().getStringExtra(getString(R.string.id)), preferences.getString(Prefs.LANGUAGE, Prefs.LANGUAGE_DEFAULT));
        indicator.attachToPager(pager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerContent.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.uptodown));
            footer.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.downtoup));
        }

    }

    void onPlayerStopped() {
        player.setOnCompletionListener(mediaPlayer -> {
            player.stop();
            player.reset();
            audio.setImageResource(R.drawable.play_icon);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.reset();
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

    void setupTitle() {
        title.setText(getLocalizedString(getStringInt(getActivity(), getActivity().getIntent().getStringExtra(getString(R.string.id)))));
        title.setTypeface(getFont(getActivity(), Fonts.QUICKBOLD));
        page.setTypeface(getFont(getActivity(), Fonts.ROBOLIGHT));
    }

    @OnClick({R.id.next, R.id.back, R.id.toolbar_back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                break;
            case R.id.back:
                pager.setCurrentItem(pager.getCurrentItem() - 1);
                break;
            case R.id.toolbar_back:
                getActivity().finish();
                break;
        }
    }

    @Override
    public Fragment newInstance() {
        return new DzikirDetailFragment();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_dzikir_detail;
    }

    @Override
    protected DzikirDetailPresenter createPresenter() {
        return new DzikirDetailPresenter(this);
    }

    @Override
    public void getData(List<Data> data) {
        page.setText("1/" + String.valueOf(data.size()));
        pager.setAdapter(new DzikirPagerAdapter(getActivity(), data));
        pager.setPageTransformer(true, new BackgroundToForegroundTransformer());

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {/*unused*/}

            @Override
            public void onPageSelected(int position) {
                if (data.get(position).getAudio() != null) {
                    audio.setImageResource(R.drawable.play_icon);
                } else {
                    audio.setImageResource(R.drawable.audio_unavailable);
                }
                player.stop();
                player.reset();
                page.setText(String.valueOf(position + 1) + "/" + String.valueOf(data.size()));
                audio.setOnClickListener(view -> onAudioButtonClicked(data, position));
                share.setOnClickListener(view -> onShareButtonClicked(data.get(position).getArabic(), data.get(position).getTranslation()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {/*unused*/}
        });
    }

    void onShareButtonClicked(String arabic, String translation) {
        String title = getLocalizedString(getStringInt(getActivity(), getActivity().getIntent().getStringExtra(getString(R.string.id))));
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, title + "\n" + arabic + "\n" + translation + "\n\nShared from DzikirQu App");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, ""));
    }

    void onAudioButtonClicked(List<Data> data, int position) {

        if (data.get(position).getAudio() != null) {
            if (isNetworkAvailable(getActivity())) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        if (player.isPlaying()) {
                            player.stop();
                            player.reset();
                            audio.setImageResource(R.drawable.play_icon);
                        } else {
                            File file = new File("/sdcard/" + URI.DIRECTORY + "/" + data.get(position).getAudio_name() + ".mp3");
                            if (file.exists()) {
                                audio.setImageResource(R.drawable.stop_icon);
                                audioPlayer(data.get(position).getAudio_name() + ".mp3");

                            } else {
                                mvpPresenter.downloadAudio(data, position, getActivity());
                            }
                        }
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                    }
                } else {
                    if (player.isPlaying()) {
                        player.stop();
                        player.reset();
                        audio.setImageResource(R.drawable.play_icon);
                    } else {
                        File file = new File("/sdcard/" + URI.DIRECTORY + "/" + data.get(position).getAudio_name() + ".mp3");
                        if (file.exists()) {
                            audio.setImageResource(R.drawable.stop_icon);
                            audioPlayer(data.get(position).getAudio_name() + ".mp3");

                        } else {
                            mvpPresenter.downloadAudio(data, position, getActivity());
                        }
                    }
                }
            } else {
                if (player.isPlaying()) {
                    player.stop();
                    player.reset();
                    audio.setImageResource(R.drawable.play_icon);
                } else {
                    File file = new File("/sdcard/" + URI.DIRECTORY + "/" + data.get(position).getAudio_name() + ".mp3");
                    if (file.exists()) {
                        audio.setImageResource(R.drawable.stop_icon);
                        audioPlayer(data.get(position).getAudio_name() + ".mp3");

                    } else {
                        Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            Toast.makeText(getActivity(), "" + getLocalizedString(R.string.audio_unavailable), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFinishDownloading(String audioName) {
        audioPlayer(audioName + ".mp3");
        audio.setImageResource(R.drawable.stop_icon);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
