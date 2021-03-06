package sg.edu.nus.cs3218tut_lewisharisnata;

import android.media.AudioRecord;
import android.util.Log;

/**
 * Created by ngtk on 4/2/16.
 */

public class SoundSampler {
    private static final int  FS = 16000;     // sampling frequency
    public AudioRecord        audioRecord;
    public Boolean            recording = Boolean.valueOf(true);
    private int               audioEncoding = 2;
    private int               nChannels = 16;
    private Thread            recordingThread;

    public SoundSampler(SoundSamplingActivity mAct) throws Exception
    {
        try {
            if (audioRecord != null) {
                audioRecord.stop();
                audioRecord.release();
            }
            audioRecord = new AudioRecord(1, FS, nChannels, audioEncoding, AudioRecord.getMinBufferSize(FS, nChannels, audioEncoding));
        }
        catch (Exception e) {
            Log.d("Error in SoundSampler ", e.getMessage());
            throw new Exception();
        }

        return;

    }


    public void init() throws Exception
    {
        try {
            if (audioRecord != null) {
                audioRecord.stop();
                audioRecord.release();
            }
            audioRecord = new AudioRecord(1, FS, nChannels, audioEncoding, AudioRecord.getMinBufferSize(FS, nChannels, audioEncoding));
        }
        catch (Exception e) {
            Log.d("Error in Init() ", e.getMessage());
            throw new Exception();
        }

        SoundSamplingActivity.bufferSize = AudioRecord.getMinBufferSize(FS, nChannels, audioEncoding);
        SoundSamplingActivity.buffer = new short[SoundSamplingActivity.bufferSize];

        audioRecord.startRecording();

        recordingThread = new Thread()
        {
            public void run()
            {
                while (recording)
                {

                    audioRecord.read(SoundSamplingActivity.buffer, 0, SoundSamplingActivity.bufferSize);
                    SoundSamplingActivity.surfaceView.drawThread.setBuffer(SoundSamplingActivity.buffer);

                }
            }
        };
        recordingThread.start();

        return;
    }

    public void stopRecordingThread() throws InterruptedException {
        recording = Boolean.valueOf(false);
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
        }
        if (recordingThread != null) {
            recordingThread.join();
        }
    }

}
