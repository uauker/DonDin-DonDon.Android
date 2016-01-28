package com.uauker.apps.dondindondon.helpers;


import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class AudioHelper {

    public static FileInputStream prepareAudioFile(final Context context, final int rawAudio) throws IOException {
        final InputStream is = context.getResources().openRawResource(rawAudio);

        // create file to store audio
        final File mediaFile = new File(context.getCacheDir(), "tmp");
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
    }
}
