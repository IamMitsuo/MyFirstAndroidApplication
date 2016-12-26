package kot.chatchawan.tutorialapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ArimethicApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arimethic_app);
    }

    public void addButton(View view) {
        TextView textView = (TextView) findViewById(R.id.result);
        EditText editText = (EditText) findViewById(R.id.firstNumber);
        String firstNumStr = editText.getText().toString();
        editText = (EditText) findViewById(R.id.secondNumber);
        String secondNumStr = editText.getText().toString();

        int firstNum = Integer.parseInt(firstNumStr);
        int secondNum = Integer.parseInt(secondNumStr);
        int result = firstNum+secondNum;
        textView.setText(Integer.toString(result));
    }
}
