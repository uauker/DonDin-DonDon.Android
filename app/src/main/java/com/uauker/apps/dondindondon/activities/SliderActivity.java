package com.uauker.apps.dondindondon.activities;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.uauker.apps.dondindondon.R;
import com.uauker.apps.dondindondon.helpers.AudioHelper;

import java.io.IOException;
import java.util.HashMap;


public final class SliderActivity extends Activity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout slider;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        try {
            this.slider = (SliderLayout) findViewById(R.id.slider);

            this.player = AudioHelper.play(this, R.raw.chaves, true, null);

            //TODO: que coisa feia, tem q mudar isso
            HashMap<String, Integer> file_maps = new HashMap<>();
            file_maps.put("001", R.drawable.f001);
            file_maps.put("002", R.drawable.f002);
            file_maps.put("003", R.drawable.f003);

            for (String name : file_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(this);
                // initialize a SliderLayout
                textSliderView
                        .image(file_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.FitCenterCrop)
                        .setOnSliderClickListener(this);

                //add your extra information
                textSliderView.bundle(new Bundle());
//            textSliderView.getBundle().putString("extra", name);

                slider.addSlider(textSliderView);
            }

            slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
//        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        slider.setCustomAnimation(new DescriptionAnimation());
            slider.setDuration(4000);
            slider.addOnPageChangeListener(this);
        } catch (IOException e) {
            //TODO: tratar

            e.printStackTrace();
        }
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onStop() {
        if (slider != null) {
            slider.stopAutoCycle();
        }

        if (player != null) {
            player.stop();
        }

        super.onStop();
    }
}
