package sg.edu.nus.cs3218tut_lewisharisnata;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SoundSamplingActivity extends Activity {


    public  static          CSurfaceViewSoundSampling   	surfaceView;
    private SoundSampler   	soundSampler;
    public  static short[]  buffer;
    public  static int      bufferSize;     // in bytes

    public void goToMainActivity(View view){
        try
        {
            surfaceView.drawFlag = Boolean.valueOf(false);
            surfaceView.drawThread.join();
            soundSampler.stopRecordingThread();
        }
        catch (InterruptedException localInterruptedException) {
            Log.d("Error closing threads", localInterruptedException.getMessage());
        }
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_sampling);

        try {
            soundSampler = new SoundSampler(this);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot instantiate SoundSampler", Toast.LENGTH_LONG).show();
        }

        try {
            soundSampler.init();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Cannot initialize SoundSampler.", Toast.LENGTH_LONG).show();
        }

        surfaceView = (CSurfaceViewSoundSampling)findViewById(R.id.surfaceView);
        surfaceView.drawThread.setBuffer(buffer);
    }


    public void captureSound(View v) {
        if (surfaceView.drawThread.soundCapture) {
            surfaceView.drawThread.soundCapture = Boolean.valueOf(false);
            surfaceView.drawThread.segmentIndex = -1;
        }
        else {
            surfaceView.drawThread.soundCapture = Boolean.valueOf(true);
        }
    }



}
