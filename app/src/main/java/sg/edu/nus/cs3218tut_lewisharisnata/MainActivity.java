package sg.edu.nus.cs3218tut_lewisharisnata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToCalculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    public void goToGraphing(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }

    public void goToSound(View view) {
        Intent intent = new Intent(this, SoundSamplingActivity.class);
        startActivity(intent);
    }

    public void goToCalculus(View view) {
        Intent intent = new Intent(this, CalculusActivity.class);
        startActivity(intent);
    }
}
