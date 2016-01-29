package com.uauker.apps.dondindondon.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.uauker.apps.dondindondon.R;
import com.uauker.apps.dondindondon.helpers.AudioHelper;

import java.io.IOException;

public final class ScrollingActivity extends AppCompatActivity {

    FloatingActionButton fab;

    Button btnDonDin;
    Button btnDonDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(ScrollingActivity.this, SliderActivity.class);
                startActivity(i);
            }
        });

        this.setButtons();
    }

    private void setButtons() {
        this.btnDonDin = (Button) findViewById(R.id.don_din);
        this.btnDonDin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(R.raw.luiz_dondin);
            }
        });

        this.btnDonDon = (Button) findViewById(R.id.don_don);
        this.btnDonDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(R.raw.luiz_dondon);
            }
        });

        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(R.raw.luiz_dondin, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        play(R.raw.luiz_dondon);
                    }
                });
            }
        });
    }


    private void play(final int rawAudio) {
        play(rawAudio, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                setButtonsEnabled(true);
            }
        });
    }

    private void play(final int rawAudio, final MediaPlayer.OnCompletionListener listener) {
        try {
            setButtonsEnabled(false);

            AudioHelper.play(this, rawAudio, listener);
        } catch (IOException e) {
            setButtonsEnabled(true);

            //TODO: Tratar o erro
            e.printStackTrace();
        }
    }

    public void setButtonsEnabled(final boolean enabled) {
        this.btnDonDin.setEnabled(enabled);
        this.btnDonDon.setEnabled(enabled);

        final int fabIcon = enabled ? android.R.drawable.ic_media_play : android.R.drawable.ic_media_pause;

        this.fab.setEnabled(enabled);
        this.fab.setImageDrawable(ContextCompat.getDrawable(this, fabIcon));
    }
}
