package com.uauker.apps.dondindondon.activities;

import android.app.Activity;
import android.os.Bundle;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.uauker.apps.dondindondon.R;

import java.util.HashMap;


public final class SliderActivity extends Activity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        this.slider = (SliderLayout) findViewById(R.id.slider);

        HashMap<String, Integer> file_maps = new HashMap<>();
        file_maps.put("001", android.R.drawable.alert_dark_frame);
        file_maps.put("002", android.R.drawable.ic_notification_clear_all);

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
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        slider.stopAutoCycle();
        super.onStop();
    }
}
