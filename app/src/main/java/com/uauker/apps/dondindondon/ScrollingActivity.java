package com.uauker.apps.dondindondon;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ScrollingActivity extends AppCompatActivity {

    Button btnDonDin;
    Button btnDonDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
        this.btnDonDon = (Button) findViewById(R.id.don_don);

        this.btnDonDin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream file = playAudio(R.raw.dondin);

                    final MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(file.getFD());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    //TODO: Tratar o erro
                    e.printStackTrace();
                }
            }
        });
    }

    private FileInputStream playAudio(final int rawAudio) {
        try {
            final InputStream is = getResources().openRawResource(rawAudio);

            // create file to store audio
            final File mediaFile = new File(this.getCacheDir(), "tmp");
            final FileOutputStream fos = new FileOutputStream(mediaFile);
            final byte buf[] = new byte[16 * 1024];

            // write to file until complete
            do {
                final int numread = is.read(buf);

                if (numread <= 0) {
                    break;
                }

                fos.write(buf, 0, numread);
            } while (true);

            fos.flush();
            fos.close();

            return new FileInputStream(mediaFile);
        } catch (Exception e) {
            //TODO:tratar erro
            e.printStackTrace();
        }
        //TODO:null eh feio... exception por favor
        return null;
    }
}
