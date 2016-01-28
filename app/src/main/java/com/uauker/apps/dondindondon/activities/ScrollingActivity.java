package com.uauker.apps.dondindondon.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.uauker.apps.dondindondon.R;
import com.uauker.apps.dondindondon.helpers.AudioHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import timber.log.Timber;

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

        this.setButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setButtons() {
        this.btnDonDin = (Button) findViewById(R.id.don_din);
        this.btnDonDin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(R.raw.dondin);
            }
        });

        this.btnDonDon = (Button) findViewById(R.id.don_don);
        this.btnDonDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(R.raw.dondon);
            }
        });

        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(R.raw.dondin, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        play(R.raw.dondon);
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

            final FileInputStream file = AudioHelper.prepareAudioFile(this, rawAudio);

            final MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(listener);
            mediaPlayer.setDataSource(file.getFD());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            setButtonsEnabled(true);

            //TODO: Tratar o erro
            e.printStackTrace();
        }
    }

    public void setButtonsEnabled(final boolean enabled) {
        this.btnDonDin.setEnabled(enabled);
        this.btnDonDon.setEnabled(enabled);

        this.fab.setEnabled(enabled);
    }
}
