package com.ihealth.androidbg.audio;

import android.media.AudioTrack;
import android.os.Build;
import android.os.Build.VERSION;
import com.ihealth.communication.utils.Log;

public class AudioTrackManager {
    private static final String f231a = AudioTrackManager.class.getSimpleName();
    public static boolean inCommunication = false;
    public static boolean isR2017 = false;
    private int f232b = 4;
    private AudioTrack f233c = null;
    private short[] f234d = null;
    private boolean f235e = false;
    private AudioTrack f236f = null;

    private void m207a() {
        try {
            int minBufferSize = AudioTrack.getMinBufferSize(44100, 4, 2);
            Log.v(f231a, "AudioTrack min_buffer_size ---> " + minBufferSize);
            Log.v(f231a, "AudioTrack.getMaxVolume() ---> " + AudioTrack.getMaxVolume());
            this.f236f = new AudioTrack(3, 44100, 4, 2, minBufferSize * 2, 1);
            if (VERSION.SDK_INT >= 21) {
                this.f236f.setVolume(AudioTrack.getMaxVolume());
            } else {
                this.f236f.setStereoVolume(AudioTrack.getMaxVolume(), AudioTrack.getMaxVolume());
            }
        } catch (Exception e) {
            Log.w(f231a, "initAudioTrack Exception ---> " + e);
        }
    }

    public static AudioTrackManager getInstance() {
        return C2021a.f261a;
    }

    public void initManager() {
        try {
            if (Build.MODEL.toUpperCase().contains("GT-S7390") || Build.MODEL.toUpperCase().contains("GT-S7562")) {
                this.f235e = true;
            } else if (Build.MODEL.equalsIgnoreCase("R2017")) {
                this.f235e = true;
                isR2017 = true;
            }
            if (Build.MANUFACTURER.equalsIgnoreCase("samsung") || Build.BRAND.equalsIgnoreCase("samsung")) {
                this.f232b = 8;
            }
            int minBufferSize = AudioTrack.getMinBufferSize(44100, 4, 2);
            Log.v(f231a, "AudioTrack min_buffer_size ---> " + minBufferSize);
            Log.v(f231a, "AudioTrack.getMaxVolume() ---> " + AudioTrack.getMaxVolume());
            this.f233c = new AudioTrack(3, 44100, 4, 2, minBufferSize * 2, 1);
            if (VERSION.SDK_INT >= 21) {
                this.f233c.setVolume(AudioTrack.getMaxVolume());
            } else {
                this.f233c.setStereoVolume(AudioTrack.getMaxVolume(), AudioTrack.getMaxVolume());
            }
        } catch (Exception e) {
            Log.w(f231a, "initAudioTrack Exception ---> " + e);
        }
    }

    public void play(int[] rates) {
        int i;
        int i2 = 0;
        Log.v(f231a, this.f232b + " rates.length = " + rates.length);
        this.f234d = new short[15360];
        for (i = 0; i < this.f232b * 256; i++) {
            this.f234d[i] = (short) ((int) (Math.sin(((double) i) * 0.14247585730565954d) * 32767.0d));
        }
        for (i = 0; i < rates.length; i++) {
            double d = (6.283185307179586d * ((double) rates[i])) / 44100.0d;
            for (int i3 = 0; i3 < 256; i3++) {
                this.f234d[((this.f232b + i) * 256) + i3] = (short) ((int) (Math.sin(((double) i3) * d) * 32767.0d));
            }
        }
        while (i2 < 512) {
            this.f234d[((this.f232b + rates.length) * 256) + i2] = (short) ((int) (Math.sin(((double) i2) * 0.14247585730565954d) * 32767.0d));
            i2++;
        }
        if (this.f233c == null) {
            initManager();
        }
        if (this.f233c == null || this.f233c.getState() == 0) {
            Log.w(f231a, "audioTrack == null cannot send wave to BG1");
            return;
        }
        try {
            this.f233c.play();
            this.f233c.write(this.f234d, 0, this.f234d.length);
            if (this.f235e) {
                this.f233c.stop();
            }
            this.f233c.flush();
        } catch (Exception e) {
            Log.w(f231a, "audioTrack Exception ---> " + e);
        }
    }

    public void playMI2S() {
        Log.v(f231a, "1 rates.length = MI2S");
        short[] sArr = new short[15360];
        for (int i = 0; i < 256; i++) {
            sArr[i] = (short) ((int) (32767.0d * Math.sin(((double) i) * 0.14247585730565954d)));
        }
        if (this.f236f == null) {
            m207a();
        }
        if (this.f236f == null || this.f236f.getState() == 0) {
            Log.w(f231a, "audioTrackMI2S == null cannot send wave to BG1");
            return;
        }
        try {
            this.f236f.play();
            this.f236f.write(sArr, 0, sArr.length);
            this.f236f.stop();
            this.f236f.flush();
        } catch (Exception e) {
            Log.w(f231a, "audioTrackMI2S Exception ---> " + e);
        }
    }

    public void stop() {
        if (this.f233c != null) {
            try {
                this.f233c.release();
                this.f233c = null;
            } catch (IllegalStateException e) {
                Log.w(f231a, "IllegalStateException ----> " + e);
            } finally {
                this.f233c = null;
            }
        }
    }
}
