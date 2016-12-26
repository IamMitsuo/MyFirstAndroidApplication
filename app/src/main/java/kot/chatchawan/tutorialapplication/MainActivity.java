package kot.chatchawan.tutorialapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.tutorialapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSendMessageApp(View view) {
        Intent intent = new Intent(this, SendMessageApp.class);
        startActivity(intent);
    }

    public void startArimethicApp(View view) {
        Intent intent = new Intent(this, ArimethicApp.class);
        startActivity(intent);
    }

    public void startCalculatorApp(View view) {
        Intent intent = new Intent(this, CalculatorApp.class);
        startActivity(intent);
    }
}
